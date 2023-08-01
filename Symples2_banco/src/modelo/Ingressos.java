package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Scanner;

import service.DbConexao;

public class Ingressos{	
	
	private Cadastro participante;
	private LocalDateTime timeLocalIngresso;

	
	public void cadastrarIngresso() {
//		setParticipante(participante = new Cadastro());
//		getParticipante().cadastraParticipante();
		cadastraParticipante();
		
	}	
	
	
	
	public LocalDateTime registroCadastro() {
//		return getParticipante().getTimeLocalIngresso();
		return null;
	}

	
	
	private void cadastraParticipante() {
		
		Connection conexaoDataBase = null;
		PreparedStatement consultaDataBase = null;
		ResultSet resultadoConsulta = null;
		
		try {
			
			conexaoDataBase = DbConexao.getConexao();
			
			consultaDataBase = conexaoDataBase.prepareStatement(
					"INSERT INTO participantes" 
					+ "(Nome_participante, Cpf, Email)"
					+ "VALUES"
					+ "(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			
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
					int id = resultadoConsulta.getInt(1);
					System.out.println("Done! ID = " + id);
				}
				
			} else {
					System.out.println("No rows affected!");
				}
			
		} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				DbConexao.closeResultSet(resultadoConsulta);
				DbConexao.closeStatement(consultaDataBase);
				DbConexao.closeConexao();
			}
		
	}
	
	
	
	public void pegaParticipante() {
		Connection conexaoDataBase = null;
		Statement consultaDataBase = null;
		ResultSet resultadoConsulta = null;
		
		conexaoDataBase = DbConexao.getConexao();
		
		try {
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
	
	
}
