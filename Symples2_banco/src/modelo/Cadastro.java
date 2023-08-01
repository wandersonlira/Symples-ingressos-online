package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Scanner;

import service.DbConexao;

public class Cadastro {
	
//	private String nomePessoa;
//	private String cpfPessoa;
//	private String email;
	private LocalDateTime timeLocalIngresso;

	
	public void cadastrar() {
		
		Connection conexao = null;
		PreparedStatement st = null;
		
		try {
			
			conexao = DbConexao.getConexao();
			
			st = conexao.prepareStatement(
					"INSERT INTO participantes" 
					+ "(Nome_participante, Cpf, Email)"
					+ "VALUES"
					+ "(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			
			System.out.print("Nome: ");
			st.setString(1, input.nextLine());
			
			System.out.print("CPF: ");
			st.setString(2, input.nextLine());
			
			System.out.print("Email: ");
			st.setString(3, input.nextLine());
			
			this.timeLocalIngresso = LocalDateTime.now();
			
			int linhasAlteradas = st.executeUpdate();
			
			if (linhasAlteradas > 0) {
				
				ResultSet rs = st.getGeneratedKeys();
				
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! ID = " + id);
				}
				
			} else {
					System.out.println("No rows affected!");
				}
			
		} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DbConexao.closeResultSet(null);
				DbConexao.closeStatement(st);
				DbConexao.closeConexao();
			}
		
	}
	
	
//	public String getNomePessoa() {
//		return nomePessoa;
//	}
//	public void setNomePessoa(String nomePessoa) {
//		this.nomePessoa = nomePessoa;
//	}
//	public String getCpfPessoa() {
//		return cpfPessoa;
//	}
//	public void setCpfPessoa(String cpfPessoa) {
//		this.cpfPessoa = cpfPessoa;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}

	public LocalDateTime getTimeLocalIngresso() {
		return timeLocalIngresso;
	}

	public void setTimeLocalIngresso(LocalDateTime timeLocalIngresso) {
		this.timeLocalIngresso = timeLocalIngresso;
	}

	
}
