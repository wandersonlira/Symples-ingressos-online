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

import org.apache.http.client.ClientProtocolException;

import Dao.CRUD;
import br.com.symples.modelo.Endereco;
import br.com.symples.modelo.Ingressos;
import br.com.symples.modelo.LeitorTeclado;
import br.com.symples.service.DbConexao;
import br.com.symples.service.ViacepService;


public class Eventos {
	
	private static Endereco enderecoEvento;


	private static Integer insertEvento() {
		
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
	
	
	
	private static Integer insereEndereco() {
		
		Integer idEndereco = null;
		
		System.out.print("Local: ");
		LeitorTeclado.clearInput();
		String nomeLocal = LeitorTeclado.getInputLine();

		System.out.print("CEP: ");
		String cepCadastro = LeitorTeclado.getInputLine();
		
		try {
			ViacepService apiCep = new ViacepService();
//			this.enderecoEvento = apiCep.getEndereco(cepCadastro);
			enderecoEvento = apiCep.getEndereco(cepCadastro);
//			this.enderecoEvento.insertEndereco(nomeLocal);
			enderecoEvento.insertEndereco(nomeLocal);
//			idEndereco = this.enderecoEvento.getIdEndereco();
			idEndereco = enderecoEvento.getIdEndereco();
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		return idEndereco;	
	}
	
	
	private static void cadastraEvento() {
		
		
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
		
	}
	
	
	public static void criarEvento() {
		
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
	
	
	
	public static void exibirEventos() {
		
		ResultSet resultadoConsulta = null;		
		
		try {
			
			CRUD crud = new CRUD();
			resultadoConsulta = crud.getSelect("eventos", "endereco");
			
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
		
	}

	
	
	public void exibirIngresso() {
		
		ResultSet resultadoConsulta = null;
			
			try {
				
				CRUD crud = new CRUD();
				resultadoConsulta = crud.getSelect("eventos");
				
				resultadoConsulta.absolute(1); // este número é para teste
				
				if (resultadoConsulta.getInt("Ingresso_comprado") > 0) {
					
					 System.out.println("Ingressos: " + resultadoConsulta.getInt("Qtd_ingresso"));
					 System.out.println("Ingressos Comprados: " + resultadoConsulta.getInt("Ingresso_comprado"));
					
				} else {
					System.out.println(" --- NÃO HÁ INGRESSOS COMPRADOS! --- ");	
				}
				
			} catch (SQLException e) {

				e.printStackTrace();
			} 
			
				
				
		
	}
	
	
	
	public static void comprarIngresso() {
		
		System.out.print("Cod. Evento: ");
		Integer codEvento = LeitorTeclado.getInputInteger();
		LeitorTeclado.clearInput();
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
					"SELECT Qtd_ingresso, Ingresso_comprado "
					+	"FROM eventos");
		
//		CRUD crud = new CRUD();
//		resultadoConsulta = crud.getSelect("eventos");
			
//			try {
				
				resultadoConsulta.absolute(1);
				
				if (resultadoConsulta.getInt("Ingresso_comprado") 
						< resultadoConsulta.getInt("Qtd_ingresso")) {
					
					System.out.println("Qtd: " + resultadoConsulta.getInt("Qtd_ingresso"));
					System.out.println("Ingre: " + resultadoConsulta.getInt("Ingresso_comprado"));
					
				Ingressos ingresso = new Ingressos();
				ingresso.cadastrarIngresso(codEvento);
				
				 int ingressoComprado = resultadoConsulta.getInt("Ingresso_comprado") + 1;
				 
				 System.out.println("Cont: " + ingressoComprado);
				
				updateDatabase = conexaoDataBase.prepareStatement("UPDATE `db_symples`.`eventos` SET `Ingresso_comprado` = ? WHERE (`Id_evento` = ?)");
				
				updateDatabase.setInt(1, ingressoComprado);
				updateDatabase.setInt(2, codEvento);
				
				updateDatabase.executeUpdate();
					
				} else {
					System.out.println(" --- INGRESSO ESGOTADO! --- ");	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

	}
	
}
