package br.com.symples.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.symples.service.DbConexao;

public class Endereco {
	
	  private String cep;
	  private String logradouro;
	  private String complemento;
	  private String bairro;
	  private String localidade;
	  private String uf;
	  private String ibge;
	  private String gia;
	  private String ddd;
	  private String siafi;
	  
	  private Integer idEndereco = null;
	  
	  
	  public void insertEndereco(String nomeLocal) {  	  
		  
		  Connection conexaoDataBase = null;
		  PreparedStatement consultaDataBases = null;
		  ResultSet resultadoConsulta = null;
		  
		  try {
			  
			conexaoDataBase = DbConexao.getConexao();
			
			consultaDataBases = conexaoDataBase.prepareStatement(
					  "INSERT INTO endereco "
					  +"(Logradouro, NomeLocal, NumCasa, Complemento, Bairro, Localidade, Uf, Cep, Ibge, Gia, Ddd, Siafi)"
					  + "VALUES"
					  + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			consultaDataBases.setString(1, this.logradouro);
			consultaDataBases.setString(2, nomeLocal);
			
			System.out.print("Nº: ");
			String numCasa = LeitorTeclado.getInputLine();
			consultaDataBases.setString(3, numCasa);
			
			consultaDataBases.setString(4, this.complemento);
			consultaDataBases.setString(5, this.bairro);
			consultaDataBases.setString(6, this.localidade);
			consultaDataBases.setString(7, this.uf);
			consultaDataBases.setString(8, this.cep);
			consultaDataBases.setString(9, this.ibge);
			consultaDataBases.setString(10, this.gia);
			consultaDataBases.setString(11, this.ddd);
			consultaDataBases.setString(12, this.siafi);
			
			int linhasAlteradas = consultaDataBases.executeUpdate();
			
			if (linhasAlteradas > 0) {
				resultadoConsulta = consultaDataBases.getGeneratedKeys();
				
				while (resultadoConsulta.next()) {
					this.idEndereco = resultadoConsulta.getInt(1);
					System.out.println("Done! ID = " + idEndereco);
				}
			} else {
				System.out.println("No rows affected!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		  finally {
			DbConexao.closeResultSet(resultadoConsulta);
//				DbConexao.closeStatement(consultaDataBases);
//				DbConexao.closeConexao();
			}	  
	  }
	  	  
	  
	public Integer getIdEndereco() {
		return this.idEndereco;
	}
	
	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}
	 
	  
}
