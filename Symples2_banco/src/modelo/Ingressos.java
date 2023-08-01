package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import service.DbConexao;

public class Ingressos{	
	
	private Cadastro participante;

	
	public void cadastrarIngresso() {
		setParticipante(participante = new Cadastro());
		getParticipante().cadastrar();
	}	
	
	
	public LocalDateTime registroCadastro() {
		return getParticipante().getTimeLocalIngresso();
	}
	
	
	public void pegaParticipante() {
		Connection conexao = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		conexao = DbConexao.getConexao();
		
		try {
			statement = conexao.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM participantes");
			
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("Id_participante") + ", " + resultSet.getString("Nome_participante"));
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
			
		} finally {
				DbConexao.closeResultSet(resultSet);
				DbConexao.closeStatement(statement);
				DbConexao.closeConexao();
			}
			
		
	}


	public Cadastro getParticipante() {
		return participante;
	}

	public void setParticipante(Cadastro participante) {
		this.participante = participante;
	}
	
	public static void main(String[] args) {
		
		Ingressos x= new Ingressos();
		x.pegaParticipante();
	}
	
}
