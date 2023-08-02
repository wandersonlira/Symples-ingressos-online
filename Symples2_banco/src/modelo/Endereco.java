package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import service.DbConexao;

public class Endereco{
	
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
	  
	
	  
	  public void insertEndereco() {
		  
		  try (Scanner input = new Scanner(System.in)) {
			  Connection conexaoDataBase = null;
			  PreparedStatement consultaDataBases = null;
			  ResultSet resultadoConsulta = null;
			  
			  try {
				  
				conexaoDataBase = DbConexao.getConexao();
				
				consultaDataBases = conexaoDataBase.prepareStatement(
						  "INSERT INTO endereco"
						  +"(Logradouro, Num_casa, Complemento, Bairro, Localidade, Uf, Cep, Ibge, Gia, Ddd, Siafi)"
						  + "VALUES"
						  + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				
				consultaDataBases.setString(1, this.logradouro);
				System.out.println("Num_Casa: ");
				consultaDataBases.setString(2, input.nextLine());
				consultaDataBases.setString(3, this.complemento);
				consultaDataBases.setString(4, this.bairro);
				consultaDataBases.setString(5, this.localidade);
				consultaDataBases.setString(6, this.uf);
				consultaDataBases.setString(7, this.cep);
				consultaDataBases.setString(8, this.ibge);
				consultaDataBases.setString(9, this.gia);
				consultaDataBases.setString(10, this.ddd);
				consultaDataBases.setString(11, this.siafi);
				
				int linhasAlteradas = consultaDataBases.executeUpdate();
				
				if (linhasAlteradas > 0) {
					resultadoConsulta = consultaDataBases.getGeneratedKeys();
					
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
				DbConexao.closeStatement(consultaDataBases);
				DbConexao.closeConexao();
			}
		}
		  
	  }
	  
	  
	  
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getIbge() {
		return ibge;
	}
	public void setIbge(String ibge) {
		this.ibge = ibge;
	}
	public String getGia() {
		return gia;
	}
	public void setGia(String gia) {
		this.gia = gia;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getSiafi() {
		return siafi;
	}
	public void setSiafi(String siafi) {
		this.siafi = siafi;
	}
	
	
	@Override
	public String toString() {
		return "Endereco [cep=" + cep + ", logradouro=" + logradouro + ", complemento=" + complemento + ", bairro="
				+ bairro + ", localidade=" + localidade + ", uf=" + uf + ", ibge=" + ibge + ", gia=" + gia + ", ddd="
				+ ddd + ", siafi=" + siafi + "]";
	}
	
	 
	  
}
