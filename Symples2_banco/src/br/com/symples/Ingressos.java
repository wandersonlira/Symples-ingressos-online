package br.com.symples;

//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.time.LocalDateTime;

import Dao.CRUD;
//import br.com.symples.service.DbConexao;

public class Ingressos{	
	
	private LocalDateTime timeLocalIngresso;

	
	private Integer insertParticipante() {
			
			Integer idParticipante = null;
			
			PreparedStatement consultaDataBase = null;
			ResultSet resultadoConsulta = null;
		
			try {
				
				CRUD crud = new CRUD();
				consultaDataBase = crud.getInsert("participantes");
				
				System.out.print("Nome: ");
				consultaDataBase.setString(1, LeitorTeclado.getInputLine());
				
				System.out.print("CPF: ");
				consultaDataBase.setString(2, LeitorTeclado.getInputLine());
				
				System.out.print("Email: ");
				consultaDataBase.setString(3, LeitorTeclado.getInputLine());
				
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
			
			return idParticipante;
			
		}
	


	private void insertParticipanteEvento(Integer idParticipante, Integer codEvento) {
			
		PreparedStatement consultaDatabase = null;
		ResultSet resultadoConsulta = null;
		try {
			
			CRUD crud = new CRUD();
			consultaDatabase = crud.InsertTableColum("participante_evento", "Codigo_id_participante", "Codigo_id_evento");
				
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


	public void cadastrarIngresso(Integer codEvento) {
		
		Integer idParticipante = insertParticipante();
		System.out.println("-------------");
		
		insertParticipanteEvento(idParticipante, codEvento);
		System.out.println("-------------");
		
		ingressoComprado(idParticipante, codEvento);
		
	}	
	
	
	public void ingressoComprado(Integer idParticipante, Integer codEvento) {
		
		ResultSet resultadoConsulta = null;
		
		try {
			
			CRUD crud = new CRUD();
			resultadoConsulta = crud.getSelect("eventos", "endereco", "participantes", "participante_evento");
			

			while (resultadoConsulta.next()) {
				if (resultadoConsulta.getInt("Id_participante") == idParticipante 
						&& resultadoConsulta.getInt("Id_evento") == codEvento) {
				System.out.println(
						"Evento: " + resultadoConsulta.getString("Nome_evento")
					+	"\nRua: " + resultadoConsulta.getString("Logradouro")
					+	", " + resultadoConsulta.getString("NumCasa")
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
		
		ResultSet resultadoConsulta = null;
		
		try {
			
			CRUD crud = new CRUD();
			resultadoConsulta = crud.getSelect("participantes");
			
			while (resultadoConsulta.next()) {
				System.out.println(resultadoConsulta.getInt("Id_participante") + ", " + resultadoConsulta.getString("Nome_participante"));
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
			
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


	
}
