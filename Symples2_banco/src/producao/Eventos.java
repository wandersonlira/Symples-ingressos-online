package producao;

import modelo.Endereco;
import modelo.Ingressos;
import service.DbConexao;
import service.ViacepService;

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


public class Eventos {
	
	private String nomeEvento;
	private LocalDateTime dataEvento;
	private int quantidadeIngresso;
	private Endereco enderecoEvento;
	private Ingressos[] ingresso;
	private boolean boleano = false;
	private int cont = 0;
	
	public Eventos() {}
	

	public Eventos(String nomeEvento, LocalDateTime dataEvento, int quantiInteger, Endereco enderecoEvento) {
		this.setNomeEvento(nomeEvento);
		this.setDataEvento(dataEvento);
		this.setQuantidadeIngresso(quantiInteger);
		this.setEnderecoEvento(enderecoEvento);
		this.ingresso = new Ingressos[this.quantidadeIngresso];
	}
	
	
	
	public void criarEvento() {
		
		cadastraEvento();
	}
	
	
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
			
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			
			System.out.print("Nome: ");
			consultaDataBase.setString(1, input.nextLine());
			
			System.out.print("Data: ");
			consultaDataBase.setDate(2, new java.sql.Date(formatoDate.parse(input.nextLine()).getTime()));
			
			System.out.print("Hora: ");
			consultaDataBase.setString(3, input.nextLine());
			
			System.out.print("Quantidade de ingressos: ");
			consultaDataBase.setInt(4, input.nextInt());	
			
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

		try (Scanner input = new Scanner(System.in)) {
			System.out.print("CEP: ");
			String cepCadastro = input.nextLine();
			
			try {
				ViacepService apiCep = new ViacepService();
				this.enderecoEvento = apiCep.getEndereco(cepCadastro);
				this.enderecoEvento.insertEndereco();
				idEndereco = this.enderecoEvento.getIdEndereco();
				
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				
			} catch (IOException e) {
				e.printStackTrace();
				
			} finally {
				input.close();
			}
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
					+ "SET codigo_id_endereco = ? "
					+	"WHERE Id_evento = ?");
			
			consultaDatabase.setInt(1, idEndereco);
			consultaDatabase.setInt(2, idEvento);
			
			Integer linhaAlterada = consultaDatabase.executeUpdate();
			
			System.out.println("Linha Alterada: " + linhaAlterada);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DbConexao.closeStatement(consultaDatabase);
			DbConexao.closeConexao();
		}
		
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
		
		if (this.boleano == false) {
			System.out.println("Não há ingressos comprados");
		}
		
		else if (this.boleano == true) {
	
			for (int i=0; i<ingresso.length; i++) {
				if (ingresso[i] != null) {
				System.out.println("Nome Evento: " + this.getNomeEvento());
				System.out.println("Local Evento: " + this.converteDateTime(getDataEvento()));
				System.out.println("Endereço: " + this.getEnderecoEvento().getLogradouro() + ", " + this.getEnderecoEvento().getBairro()
						+ ", " + this.getEnderecoEvento().getLocalidade() + ", " + this.getEnderecoEvento().getUf());
				this.ingresso[i].selectParticipante();
				System.out.println("Comprado dia: " + converteDateTime(this.ingresso[i].registroCadastro()));
				System.out.println("");
				} else {
					System.out.println("Ingresso nº" + i + " - Disponível");
					}
			}
		}
		
	}
	
	
	
	public void comprarIngresso() {
		
		exibirEventos();

		Scanner input = new Scanner(System.in); // apenas para teste
		
		System.out.print("Cod. Evento: ");
		Integer codEvento = input.nextInt();
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

	
	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
	

	public LocalDateTime getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(LocalDateTime dataEvento) {
		this.dataEvento = dataEvento;
	}


	public int getQuantidadeIngresso() {
		return quantidadeIngresso;
	}

	public void setQuantidadeIngresso(int quantidadeIngresso) {
		this.quantidadeIngresso = quantidadeIngresso;
	}
	
	public Endereco getEnderecoEvento() {
		return enderecoEvento;
	}

	public void setEnderecoEvento(Endereco enderecoEvento) {
		this.enderecoEvento = enderecoEvento;
	}

	
	public Ingressos[] getIngresso() {
		return ingresso;
	}

	public void setIngresso(Ingressos[] ingresso) {
		this.ingresso = ingresso;
	}
	
	
	public static void main(String[] args) {
		Eventos evento = new Eventos();
		evento.comprarIngresso();
		
	}
	
}
