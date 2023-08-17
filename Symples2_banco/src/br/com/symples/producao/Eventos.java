package br.com.symples.producao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.apache.http.client.ClientProtocolException;

import br.com.symples.modelo.Endereco;
import br.com.symples.modelo.Ingressos;
import br.com.symples.modelo.LeitorTeclado;
import br.com.symples.service.DbConexao;
import br.com.symples.service.ViacepService;


public class Eventos {
	
	private Endereco enderecoEvento;


	private Integer insertEvento() {
		
		Integer idEvento = null;
		
		SimpleDateFormat formatoDate = new SimpleDateFormat("dd/MM/yyyy");
		Connection conexaoDataBase = null;
		PreparedStatement consultaDataBase = null;
		ResultSet resultadoConsulta = null;
		
		try {
			conexaoDataBase = DbConexao.getConexao();
			
			consultaDataBase = conexaoDataBase.prepareStatement(
					"INSERT INTO eventos "
					+ "(Nome_evento, Data_evento, Hora_evento, Qtd_ingresso) "
					+ 	"VALUES "
					+ 		"(?, ?, ? ,?)", Statement.RETURN_GENERATED_KEYS);
			
			
			System.out.print("Nome: ");
			consultaDataBase.setString(1, LeitorTeclado.getInputLine());
			
			System.out.print("Data: ");
			consultaDataBase.setDate(2, new java.sql.Date(formatoDate.parse(LeitorTeclado.getInputLine().replace(" ", "")).getTime()));
			
			System.out.print("Hora: ");
			consultaDataBase.setString(3, LeitorTeclado.getInputLine().replace(" ", ""));
			
			System.out.print("Quantidade de ingressos: ");
			consultaDataBase.setInt(4, LeitorTeclado.getInputInteger());	
			
			int linhaModificada = consultaDataBase.executeUpdate();
			
			if (linhaModificada > 0) {
				
				resultadoConsulta = consultaDataBase.getGeneratedKeys();
				
				while (resultadoConsulta.next()) {
					idEvento = resultadoConsulta.getInt(1);
					System.out.println("Done! ID = " + idEvento);
					
				}
				
			} else {
					System.out.println("No rown affected!");
				}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (java.text.ParseException e) { // referente ao tipo Date usado em Data
			e.printStackTrace();
		} 
		
		return idEvento;
		
	}
	
	
	
	private Integer insereEndereco() {
		
		Integer idEndereco = null;
		
		System.out.print("CEP: ");
		String cepCadastro = LeitorTeclado.getInputLine();
		
		try {
			ViacepService apiCep = new ViacepService();
			this.enderecoEvento = apiCep.getEndereco(cepCadastro);
			this.enderecoEvento.insertEndereco();
			idEndereco = this.enderecoEvento.getIdEndereco();
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		return idEndereco;	
	}
	
	
	private void cadastraEvento() {
		
		
		Integer idEvento = insertEvento();
		Integer idEndereco = insereEndereco();
		
		Connection conexaoDataBase = null;
		PreparedStatement consultaDatabase = null;
		
		try {
			conexaoDataBase = DbConexao.getConexao();
			consultaDatabase = conexaoDataBase.prepareStatement(
					"UPDATE eventos "
					+ "SET Codigo_id_endereco = ? "
					+	"WHERE Id_evento = ?");
			
			consultaDatabase.setInt(1, idEndereco);
			consultaDatabase.setInt(2, idEvento);
			
			Integer linhaAlterada = consultaDatabase.executeUpdate();
			
			System.out.println("Linha Alterada: " + linhaAlterada);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} 
		
//		finally {
//			DbConexao.closeStatement(consultaDatabase);
//			DbConexao.closeConexao();
//		}
		
	}
	
	
	public void criarEvento() {
		
		cadastraEvento();
	}


	public String exibirDataHoraLocal(Instant dataHora) {
		LocalDateTime dataHoraMundial = LocalDateTime.ofInstant(dataHora, ZoneId.systemDefault());
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
		return formatoDataHora.format(dataHoraMundial);
	}
	
	
	public String converteDateTime(LocalDateTime dataHora) {
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return dataHora.format(formatoDataHora);
	}
	
	
	
	public void exibirEventos() {
		
		Connection conexaoDatabase = null;
		Statement consultaDataBase = null;
		ResultSet resultadoConsulta = null;		
		
		try {
			conexaoDatabase = DbConexao.getConexao();
			consultaDataBase = conexaoDatabase.createStatement();
			
			resultadoConsulta = consultaDataBase.executeQuery(
					"SELECT Id_evento, Nome_evento, Data_evento, Hora_evento, Localidade, Uf "
					+ "FROM eventos, endereco "
					+ 	"WHERE eventos.Data_evento >= curdate() "
//					+ 		"AND eventos.Hora_evento >= curtime() "
					+		"AND eventos.codigo_Id_endereco = endereco.Id_endereco ");
			
			while (resultadoConsulta.next()) {
				System.out.println(
				"Nº evento: " + resultadoConsulta.getInt("Id_evento")
				+ "\nNome: " + resultadoConsulta.getString("Nome_evento")
				+ "\nData: " + resultadoConsulta.getString("Data_evento")
				+ "\nHora: " + resultadoConsulta.getString("Hora_evento")
				+ "\nLocal: " + resultadoConsulta.getString("Localidade") + "/" + resultadoConsulta.getString("Uf"));
				System.out.println("-----------");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} 
//		finally {
//			DbConexao.closeResultSet(resultadoConsulta);
//			DbConexao.closeStatement(consultaDataBase);
//			DbConexao.closeConexao();
//		}
	}

	
	
	public void exibirIngresso() {
		
		Connection conexaoDataBase = null;
		Statement consultaDataBase = null;
		ResultSet resultadoConsulta = null;
		
		try {
			conexaoDataBase = DbConexao.getConexao();
			consultaDataBase = conexaoDataBase.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			resultadoConsulta = consultaDataBase.executeQuery(
					"SELECT Qtd_ingresso, Ingressos_comprados "
					+	"FROM eventos");
			
			resultadoConsulta.absolute(1);
			
			if (resultadoConsulta.getInt("Ingressos_comprados") > 0) {
				
				 System.out.println("Ingressos: " + resultadoConsulta.getInt("Qtd_ingresso"));
				 System.out.println("Ingressos Comprados: " + resultadoConsulta.getInt("Ingressos_comprados"));
				
			} else {
				System.out.println(" --- NÃO HÁ INGRESSOS COMPRADOS! --- ");	
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
				
		
	}
	
	
	
	public void comprarIngresso() {
		
		exibirEventos();
		
		System.out.print("Cod. Evento: ");
		Integer codEvento = LeitorTeclado.getInputInteger();
		System.out.println("-------------");
		
		Connection conexaoDataBase = null;
		Statement consultaDataBase = null;
		PreparedStatement updateDatabase = null;
		ResultSet resultadoConsulta = null;
		
		try {
			conexaoDataBase = DbConexao.getConexao();
			consultaDataBase = conexaoDataBase.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			resultadoConsulta = consultaDataBase.executeQuery(
					"SELECT Qtd_ingresso, Ingressos_comprados "
					+	"FROM eventos");
			
			resultadoConsulta.absolute(codEvento);
			
			if (resultadoConsulta.getInt("Ingressos_comprados") 
					< resultadoConsulta.getInt("Qtd_ingresso")) {
				
				Ingressos ingresso = new Ingressos();
				ingresso.cadastrarIngresso(codEvento);
				
				 int ingressoComprado = resultadoConsulta.getInt("Ingressos_comprados") + 1;
				 
				 System.out.println("Cont: " + ingressoComprado);
				
				updateDatabase = conexaoDataBase.prepareStatement("UPDATE `db_symples`.`eventos` SET `Ingressos_comprados` = ? WHERE (`Id_evento` = ?)");
				
				updateDatabase.setInt(1, ingressoComprado);
				updateDatabase.setInt(2, codEvento);
				
				updateDatabase.executeUpdate();
				
			} else {
				System.out.println(" --- INGRESSO ESGOTADO! --- ");	
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		Eventos evento = new Eventos();
		evento.insereEndereco();
		Eventos evento2 = new Eventos();
		evento2.insereEndereco();
		LeitorTeclado.closeInput();
	}
	
}
