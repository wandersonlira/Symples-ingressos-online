package br.com.symples.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import br.com.symples.DbException;

public class DbConexao {
	
	private static Connection conection = null;
	

	private static Properties loadPropriedades() {
		
		try (FileInputStream carregaArquivo = new FileInputStream("db.propriedades")) {
			
			Properties pegaArquivo = new Properties();
			pegaArquivo.load(carregaArquivo);
			
			return pegaArquivo;
			
		} catch (IOException e) {
				throw new DbException(e.getMessage());
			}
	
 	}

	
	public static Connection getConexao() {
		
		if (conection == null) {
			
			Properties carregaArquivo = loadPropriedades();
			String url = carregaArquivo.getProperty("dburl");
			
			try {
				conection = DriverManager.getConnection(url, carregaArquivo);
				
			} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
		}
		return conection;
		
	}
	
	
	public static void closeConexao() {
		
		if (conection != null) {
			try {
				conection.close();
				
			} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
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

	
}




