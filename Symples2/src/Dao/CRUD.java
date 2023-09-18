package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.symples.LeitorTeclado;
import br.com.symples.service.DbConexao;



public class CRUD {
	


	public ResultSet getSelect(String TabEventos) {
		
		Connection conexaoDataBase = null;
		Statement consultaDataBase = null;
		ResultSet resultadoConsulta = null;
		
		try {
			conexaoDataBase = DbConexao.getConexao();
			consultaDataBase = conexaoDataBase.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			resultadoConsulta = consultaDataBase.executeQuery(
					"SELECT * " // alterar a solicitação
					+	"FROM " + TabEventos);
			
		} catch (SQLException e) {
			System.out.println("Tabela " + "'"+TabEventos+"'" + " não encontrada!");
			e.printStackTrace();
		}
		
		return resultadoConsulta;
		
	}



	public ResultSet getSelect(String TabEventos, String TabEndereco){
		
		Connection conexaoDataBase = null;
		Statement consultaDatabase = null;
		ResultSet resultadoConsulta = null;
		
		try {
			conexaoDataBase = DbConexao.getConexao();
			
			consultaDatabase = conexaoDataBase.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_UPDATABLE);
			
			resultadoConsulta = consultaDatabase.executeQuery(
					"SELECT Id_evento, Nome_evento, Data_evento, Hora_evento, Localidade, Uf "
					+ "FROM " + TabEventos + ", " + TabEndereco
					+ 	" WHERE eventos.Data_evento >= curdate() "
				//	+ 		"AND eventos.Hora_evento >= curtime() "
					+		"AND eventos.Codigo_Id_endereco = endereco.Id_endereco ");

		} catch (SQLException e) {

			System.out.println("Tabela " + "'"+TabEventos+"'" + " ou " + "'"+TabEndereco+"'" + " não encontrada!");
			e.printStackTrace();

			}
		
		return resultadoConsulta;
		
	}
	
	
		
	public ResultSet getSelect(String TabEventos, String TabEndereco, String TabParticipantes, String TabParticipante_evento) {
		
		Connection conexaoDatabase = null;
		Statement consultaDatabase = null;
		ResultSet resultadoConsulta = null;
		
		try {
			conexaoDatabase = DbConexao.getConexao();
			consultaDatabase = conexaoDatabase.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			resultadoConsulta = consultaDatabase.executeQuery(
					"SELECT Id_evento, Nome_evento, Logradouro, NumCasa, Bairro, Localidade, Uf, Id_participante, Nome_participante "
					+ "FROM " 
						+ TabParticipantes + " as P, " 
						+ TabEventos + " as EV, " 
						+ TabParticipante_evento + " as PE, " 
						+ TabEndereco + " as EN "
					+ 	"WHERE PE.Codigo_id_evento = EV.Id_evento "
					+		"AND PE.Codigo_id_participante = P.Id_participante "
					+ 		"AND EN.Id_endereco = EV.Codigo_Id_endereco "
					);
			
		} catch (SQLException e) {
			System.out.println("Tabela " + "'"+TabEventos+"'" + " ou " + "'"+TabEndereco+"'" + " ou " + "'"+TabParticipantes+"'" + " ou " + "'"+TabParticipante_evento+"'" + " não encontrada!");
			e.printStackTrace();
			}
		
		return resultadoConsulta;
		
	}

	
	
	public PreparedStatement getInsert(String TabParticipantes) {
		
			Connection conexaoDataBase = null;
			PreparedStatement consultaDataBase = null;
			
			try {
				
				conexaoDataBase = DbConexao.getConexao();
				
				consultaDataBase = conexaoDataBase.prepareStatement(
						"INSERT INTO participantes " 
						+ "(Nome_participante, Cpf, Email) "
						+ 	"VALUES "
						+ 		"(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			
			return consultaDataBase;
		
	}

	
	public PreparedStatement InsertTableColum(String Tabela, String Coluna1, String Coluna2) {
		
		Connection conexaoDatabase = null;
		PreparedStatement consultaDatabase = null;
		
		try {
			conexaoDatabase = DbConexao.getConexao();
			consultaDatabase = conexaoDatabase.prepareStatement(
					"INSERT INTO " + Tabela
					+ "( "+ Coluna1 + ", " + Coluna2 + ") "
					+ 	"VALUES (?, ?) ", Statement.RETURN_GENERATED_KEYS);
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		
		return consultaDatabase;
	}
	
}
