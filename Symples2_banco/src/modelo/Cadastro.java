package modelo;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.time.LocalDateTime;
//import java.util.Scanner;
//
//import service.DbConexao;

abstract interface Cadastro {
	
	public abstract void escreveAgora();
	
//	private LocalDateTime timeLocalIngresso;
//
//	
//	public void cadastraParticipante() {
//		
//		Connection conexaoDataBase = null;
//		PreparedStatement consultaDataBase = null;
//		ResultSet resultadoConsulta = null;
//		
//		try {
//			
//			conexaoDataBase = DbConexao.getConexao();
//			
//			consultaDataBase = conexaoDataBase.prepareStatement(
//					"INSERT INTO participantes" 
//					+ "(Nome_participante, Cpf, Email)"
//					+ "VALUES"
//					+ "(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
//			
//			
//			@SuppressWarnings("resource")
//			Scanner input = new Scanner(System.in);
//			
//			System.out.print("Nome: ");
//			consultaDataBase.setString(1, input.nextLine());
//			
//			System.out.print("CPF: ");
//			consultaDataBase.setString(2, input.nextLine());
//			
//			System.out.print("Email: ");
//			consultaDataBase.setString(3, input.nextLine());
//			
//			this.timeLocalIngresso = LocalDateTime.now();
//			
//			int linhasAlteradas = consultaDataBase.executeUpdate();
//			
//			if (linhasAlteradas > 0) {
//				
//				resultadoConsulta = consultaDataBase.getGeneratedKeys();
//				
//				while (resultadoConsulta.next()) {
//					int id = resultadoConsulta.getInt(1);
//					System.out.println("Done! ID = " + id);
//				}
//				
//			} else {
//					System.out.println("No rows affected!");
//				}
//			
//		} catch (SQLException e) {
//				e.printStackTrace();
//				
//			} finally {
//				DbConexao.closeResultSet(resultadoConsulta);
//				DbConexao.closeStatement(consultaDataBase);
//				DbConexao.closeConexao();
//			}
//		
//	}
//	
//	
//	public LocalDateTime getTimeLocalIngresso() {
//		return timeLocalIngresso;
//	}
//
//	public void setTimeLocalIngresso(LocalDateTime timeLocalIngresso) {
//		this.timeLocalIngresso = timeLocalIngresso;
//	}

	
}
