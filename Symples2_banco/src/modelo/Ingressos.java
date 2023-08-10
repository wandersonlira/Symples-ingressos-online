package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Scanner;

import producao.Eventos;
import service.DbConexao;

public class Ingressos{	
	
	private Cadastro participante;
	private LocalDateTime timeLocalIngresso;

	
	private Integer insertParticipante() {
			
			Integer idParticipante = null;
			
			Connection conexaoDataBase = null;
			PreparedStatement consultaDataBase = null;
			ResultSet resultadoConsulta = null;
			
			try {
				
				conexaoDataBase = DbConexao.getConexao();
				
				consultaDataBase = conexaoDataBase.prepareStatement(
						"INSERT INTO participantes " 
						+ "(Nome_participante, Cpf, Email) "
						+ 	"VALUES "
						+ 		"(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				
				
				@SuppressWarnings("resource")
				Scanner input = new Scanner(System.in);
				
				System.out.print("Nome: ");
				consultaDataBase.setString(1, input.nextLine());
				
				System.out.print("CPF: ");
				consultaDataBase.setString(2, input.nextLine());
				
				System.out.print("Email: ");
				consultaDataBase.setString(3, input.nextLine());
				
				this.timeLocalIngresso = LocalDateTime.now();
				
				int linhasAlteradas = consultaDataBase.executeUpdate();
				
				if (linhasAlteradas > 0) {
					
					resultadoConsulta = consultaDataBase.getGeneratedKeys();
					
					while (resultadoConsulta.next()) {
						idParticipante = resultadoConsulta.getInt(1);
						System.out.println("Done! ID = " + idParticipante);
					}
					
				} else {
						System.out.println("No rows affected!");
					}
				
			} catch (SQLException e) {
					e.printStackTrace();
					
				} 
	//		finally {
	//				DbConexao.closeResultSet(resultadoConsulta);
	//				DbConexao.closeStatement(consultaDataBase);
	//				DbConexao.closeConexao();
	//			}
			return idParticipante;
			
		}
	


	private void insertParticipanteEvento(Integer idParticipante, Integer codEvento) {
			
		Connection conexaoDatabase = null;
		PreparedStatement consultaDatabase = null;
		ResultSet resultadoConsulta = null;
		
		try {
			conexaoDatabase = DbConexao.getConexao();
			consultaDatabase = conexaoDatabase.prepareStatement(
					"INSERT INTO participante_evento "
					+ "(codigo_id_participante, codigo_id_evento) "
					+ 	"VALUES (?, ?) ", Statement.RETURN_GENERATED_KEYS);
				
				consultaDatabase.setInt(1, idParticipante);
				consultaDatabase.setInt(2, codEvento);
			
			int linhaAlterada = consultaDatabase.executeUpdate();
			
			if (linhaAlterada > 0) {
				
				resultadoConsulta = consultaDatabase.getGeneratedKeys();
				
				while (resultadoConsulta.next()) {
					int id = resultadoConsulta.getInt(1);
					System.out.println("DONE! affected: " + id);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	public void cadastrarIngresso() {
		
		Eventos evento = new Eventos();
		evento.exibirEventos();

		Scanner input = new Scanner(System.in); // apenas para teste
		System.out.print("Cod. Evento: ");
		Integer codEvento = input.nextInt();
		System.out.println("-------------");
		
		Integer idParticipante = insertParticipante();
		System.out.println("-------------");
		
		insertParticipanteEvento(idParticipante, codEvento);
		System.out.println("-------------");
		
		ingressoComprado(idParticipante, codEvento);
		
	}	
	
	
	public void ingressoComprado(Integer idParticipante, Integer codEvento) {
		
		Connection conexaoDatabase = null;
		Statement consultaDatabase = null;
		ResultSet resultadoConsulta = null;
		
		try {
			conexaoDatabase = DbConexao.getConexao();
			consultaDatabase = conexaoDatabase.createStatement();
			resultadoConsulta = consultaDatabase.executeQuery(
					"SELECT Id_evento, Nome_evento, Logradouro, Num_casa, Bairro, Localidade, Uf, Id_participante, Nome_participante "
					+ "FROM participantes as P, eventos as EV, participante_evento as PE, endereco as EN "
					+ 	"WHERE PE.codigo_id_evento = EV.Id_evento "
					+		"AND PE.codigo_id_participante = P.Id_participante "
					+ 		"AND EN.Id_endereco = EV.codigo_Id_endereco "
					);

			while (resultadoConsulta.next()) {
				if (resultadoConsulta.getInt("Id_participante") == idParticipante 
						&& resultadoConsulta.getInt("Id_evento") == codEvento) {
				System.out.println(
						"Evento: " + resultadoConsulta.getString("Nome_evento")
					+	"\nRua: " + resultadoConsulta.getString("Logradouro")
					+	", " + resultadoConsulta.getString("Num_casa")
					+	"\nBairro: " + resultadoConsulta.getString("Bairro")
					+	"\nCidade: " + resultadoConsulta.getString("Localidade")
					+	"/" + resultadoConsulta.getString("Uf")
					+	"\nParticipante: " + resultadoConsulta.getString("Nome_participante"));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void selectParticipante() {
		Connection conexaoDataBase = null;
		Statement consultaDataBase = null;
		ResultSet resultadoConsulta = null;
		
		try {
			conexaoDataBase = DbConexao.getConexao();
			
			consultaDataBase = conexaoDataBase.createStatement();
			
			resultadoConsulta = consultaDataBase.executeQuery("SELECT * FROM participantes");
			
			while (resultadoConsulta.next()) {
				System.out.println(resultadoConsulta.getInt("Id_participante") + ", " + resultadoConsulta.getString("Nome_participante"));
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
			
		} finally {
				DbConexao.closeResultSet(resultadoConsulta);
				DbConexao.closeStatement(consultaDataBase);
				DbConexao.closeConexao();
			}
				
	}


	public LocalDateTime registroCadastro() {
//		return getParticipante().getTimeLocalIngresso();
		return null;
	}

	
	
	public LocalDateTime getTimeLocalIngresso() {
		return timeLocalIngresso;
	}

	public void setTimeLocalIngresso(LocalDateTime timeLocalIngresso) {
		this.timeLocalIngresso = timeLocalIngresso;
	}


	public Cadastro getParticipante() {
		return participante;
	}

	public void setParticipante(Cadastro participante) {
		this.participante = participante;
	}
	
	
	public static void main(String[] args) {
		Ingressos in = new Ingressos();
		in.cadastrarIngresso();
	}

	
}
