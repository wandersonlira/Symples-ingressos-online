package br.com.symples.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.symples.DbException;
import br.com.symples.modelo.dao.EnderecoDao;
import br.com.symples.modelo.entidades.Endereco;
import br.com.symples.service.DbConexao;

public class EnderecoDaoJDBC implements EnderecoDao{
	
	
	Connection conexao;
	
	public EnderecoDaoJDBC (Connection conexao) {
		this.conexao = conexao;
	}
	

	@Override
	public void insert(Endereco objeto) {
		
		PreparedStatement stConsulta = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"INSERT INTO Endereco "
					+ "(nomeLocal, logradouro, numLocal, complemento, bairro, "
					+ "localidade, uf, cep, ddd, ibge,	gia, siafi) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);		

			stConsulta.setString(1, objeto.getNomeLocal());		
			stConsulta.setString(2, objeto.getLogradouro());
			stConsulta.setString(3, objeto.getNumLocal());
			stConsulta.setString(4, objeto.getComplemento());
			stConsulta.setString(5, objeto.getBairro());
			stConsulta.setString(6, objeto.getLocalidade());
			stConsulta.setString(7, objeto.getUf());
			stConsulta.setString(8, objeto.getCep());
			stConsulta.setString(9, objeto.getDdd());
			stConsulta.setString(10, objeto.getIbge());
			stConsulta.setString(11, objeto.getGia());
			stConsulta.setString(12, objeto.getSiafi());
			
			int rowsAffected = stConsulta.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rsResultado = stConsulta.getGeneratedKeys();
				
				if (rsResultado.next()) {
					
					int novoIdTabela = rsResultado.getInt(1);
					objeto.setIdEndereco(novoIdTabela);
				}
				
				DbConexao.closeResultSet(rsResultado);
				
			} else {
				System.out.println("Erro inesperado... nenhum endereço criado!!");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
			
		}finally {
			DbConexao.closeStatement(stConsulta);
		}
		
	}
	
	

	@Override
	public void update(Endereco objeto) {
		
		PreparedStatement stConsulta = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"UPDATE Endereco "
					+ "SET nomeLocal = ?, logradouro = ?, numLocal = ?, complemento = ?, bairro = ?, "
					+ "localidade = ?, uf = ?, cep = ?, ddd = ?, ibge = ?,	gia = ?, siafi = ? "
					+ "WHERE idEndereco = ?");
			
			
			stConsulta.setString(1, objeto.getNomeLocal());		
			stConsulta.setString(2, objeto.getLogradouro());
			stConsulta.setString(3, objeto.getNumLocal());
			stConsulta.setString(4, objeto.getComplemento());
			stConsulta.setString(5, objeto.getBairro());
			stConsulta.setString(6, objeto.getLocalidade());
			stConsulta.setString(7, objeto.getUf());
			stConsulta.setString(8, objeto.getCep());
			stConsulta.setString(9, objeto.getDdd());
			stConsulta.setString(10, objeto.getIbge());
			stConsulta.setString(11, objeto.getGia());
			stConsulta.setString(12, objeto.getSiafi());
			stConsulta.setInt(13, objeto.getIdEndereco());
			
			
			stConsulta.executeUpdate();
			
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DbConexao.closeStatement(stConsulta);
		}
		
	}
	
	

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement stConsulta = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"DELETE FROM Endereco "
					+ "WHERE idEndereco = ?");
			
			stConsulta.setInt(1, id);
			
			int rowsAffected = stConsulta.executeUpdate();
			
			if (rowsAffected == 0) {
				System.out.println("Erro inesperado... 'id' informado não existe na tabela");
			}
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
			
		}finally {
			DbConexao.closeStatement(stConsulta);
		}
		
	}
	
	

	@Override
	public Endereco findById(Integer id) {
		
		PreparedStatement stConsulta = null;
		ResultSet rsResultado = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"SELECT * FROM Endereco "
					+ "WHERE idEndereco = ? "
					+ "ORDER BY logradouro");
			
			stConsulta.setInt(1, id);
			
			rsResultado = stConsulta.executeQuery();
			
			if (rsResultado.next()) {
				Endereco endereco = instanciaEndereco(rsResultado);
				return endereco;
			}
			
			return null;
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	

	@Override
	public List<Endereco> findAll() {
		
		PreparedStatement stConsulta = null;
		ResultSet rsResultado = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"SELECT * FROM Endereco "
					+ "ORDER BY logradouro");
			
			rsResultado = stConsulta.executeQuery();
			
			List<Endereco> listEndereco = new ArrayList<>();
			
			while (rsResultado.next()) {
				Endereco enderecoTab = instanciaEndereco(rsResultado);
				listEndereco.add(enderecoTab);
			}
			
			return listEndereco;
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	
	
	private Endereco instanciaEndereco(ResultSet resultadoTab) throws SQLException {
		
		Endereco novoEndereco = new Endereco();
		
		novoEndereco.setIdEndereco(resultadoTab.getInt("idEndereco"));
		novoEndereco.setNomeLocal(resultadoTab.getString("nomeLocal"));
		novoEndereco.setLogradouro(resultadoTab.getString("logradouro"));
		novoEndereco.setNumLocal(resultadoTab.getString("numLocal"));
		novoEndereco.setComplemento(resultadoTab.getString("complemento"));
		novoEndereco.setBairro(resultadoTab.getString("bairro"));
		novoEndereco.setLocalidade(resultadoTab.getString("localidade"));
		novoEndereco.setUf(resultadoTab.getString("uf"));
		novoEndereco.setCep(resultadoTab.getString("cep"));
		novoEndereco.setDdd(resultadoTab.getString("ddd"));
		novoEndereco.setIbge(resultadoTab.getString("ibge"));
		novoEndereco.setGia(resultadoTab.getString("gia"));
		novoEndereco.setSiafi(resultadoTab.getString("siafi"));
		
		return novoEndereco;
	}

}
