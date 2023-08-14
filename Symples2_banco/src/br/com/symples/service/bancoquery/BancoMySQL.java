package br.com.symples.service.bancoquery;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import br.com.symples.modelo.DbException;

public class BancoMySQL implements SqlDML{
	
	private static Connection conection2 = null;
	
	public static Connection abrirConexao() {
		
		if (conection2 == null) {
			
			Properties carregaArquivo2 = loadPropiedades();
			String url = carregaArquivo2.getProperty("dburl");
			
			try {
				conection2 = DriverManager.getConnection(url, carregaArquivo2);
				
			} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
		}
		
		return conection2;
		
	}
	
	
	public static void fecharConexao() {
		
		if (conection2 != null) {
			try {
				conection2.close();
				
			} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
		}
		
	}
	
	
	private static Properties loadPropiedades() {
		
		try (FileInputStream carregaArquivo2 = new FileInputStream("db.propriedades")) {
			
			Properties pegaArquivo2 = new Properties();
			pegaArquivo2.load(carregaArquivo2);
			
			return pegaArquivo2;
			
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
		
	}
	
	
	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
				
			} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
		}
		
	}
	
	
	public static void closeResultSet(ResultSet resultset) {
		if (resultset != null) {
			try {
				resultset.close();
				
			} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
		}
		
	}


	@Override
	public void select(String query) {
		
		Connection conexaoDataBase2 = null;
		Statement consultaDataBase2 = null;
		ResultSet resultadoConsulta2 = null;
		
		try {
			
			conexaoDataBase2 = abrirConexao();
			consultaDataBase2 = conexaoDataBase2.createStatement();
			resultadoConsulta2 = consultaDataBase2.executeQuery("SELECT * FROM "
					+ query);
			
			while (resultadoConsulta2.next()) {
				System.out.println(resultadoConsulta2.getInt(1) + ", "
						+ resultadoConsulta2.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
	}
		
	}


	@Override
	public void insert(String query) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(String query) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(String query) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		
		BancoMySQL d = new BancoMySQL();
		d.select("endereco");
	}


}
