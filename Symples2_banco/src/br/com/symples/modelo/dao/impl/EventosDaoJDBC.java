package br.com.symples.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.symples.DbException;
import br.com.symples.modelo.dao.EventosDao;
import br.com.symples.modelo.entidades.Endereco;
import br.com.symples.modelo.entidades.Eventos;
import br.com.symples.service.DbConexao;

public class EventosDaoJDBC implements EventosDao{
	
	
	Connection conexao;
	
	public EventosDaoJDBC (Connection conexao) {
		this.conexao = conexao;
	}
	
	

	@Override
	public void insert(Eventos objeto) {
		
		PreparedStatement stConsulta = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"INSERT INTO Eventos "
					+ "(nomeEvento, dataEvento, horaEvento, ingressos, categoria, codigoEndereco) "
					+ "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			stConsulta.setString(1, objeto.getNomeEvento());
			stConsulta.setDate(2, new java.sql.Date(objeto.getDataEvento().getTime()));
			stConsulta.setTime(3, new java.sql.Time(objeto.getHoraEvento().getTime()));
			stConsulta.setInt(4, objeto.getIngressos());
//			stConsulta.setInt(5, objeto.getIngressoComprado()); Coluna não usada no campo pois foi definido defult
			stConsulta.setString(5, objeto.getCategoria());
			stConsulta.setInt(6, objeto.getCodigoEndereco().getIdEndereco());
			
			int rowsAffected = stConsulta.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rsResultado = stConsulta.getGeneratedKeys();
				
				if (rsResultado.next()) {
					int novoIdTabela = rsResultado.getInt(1);
					objeto.setIdEvento(novoIdTabela);
				}
				
				DbConexao.closeResultSet(rsResultado);
				
			} else {
				System.out.println("Houve um erro inesperado... nenhuma linha afetada!!");
			}
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DbConexao.closeStatement(stConsulta);
		}
		
	}
	
	

	@Override
	public void update(Eventos objeto) {
		
		PreparedStatement stConsulta = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"UPDATE Eventos "
					+ "SET nomeEvento = ?, dataEvento = ?, horaEvento = ?, ingressos = ?, "
					+ "ingressoComprado = ?, categoria = ?, codigoEndereco = ? "
					+ "WHERE idEvento = ?");
			
			stConsulta.setString(1, objeto.getNomeEvento());
			stConsulta.setDate(2, new java.sql.Date(objeto.getDataEvento().getTime()));
			stConsulta.setTime(3, new java.sql.Time(objeto.getHoraEvento().getTime()));
			stConsulta.setInt(4, objeto.getIngressos());
			stConsulta.setInt(5, objeto.getIngressoComprado());
			stConsulta.setString(6, objeto.getCategoria());
			stConsulta.setInt(7, objeto.getCodigoEndereco().getIdEndereco());
			stConsulta.setInt(8, objeto.getIdEvento());
			
			int rowsAffected = stConsulta.executeUpdate();
			
			if (rowsAffected > 0) {
				System.out.println("Update realizado com sucesso!");
				
			} else {
				System.out.println("Houve um erro inesperado... nenhuma linha afetada!!");
			}
		}catch (SQLException e) {
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
					"DELETE FROM Eventos "
					+ "WHERE idEvento = ?");
			
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
	public Eventos findById(Integer id) {
		
		PreparedStatement stConsulta = null;
		ResultSet rsResultado = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"SELECT * FROM Eventos INNER JOIN Endereco "
					+ "ON Eventos.codigoEndereco = Endereco.idEndereco "
					+ "WHERE idEvento = ? ");
			
			stConsulta.setInt(1, id);
			
			rsResultado = stConsulta.executeQuery();
			
			if (rsResultado.next()) {
				
				Endereco endereco = instanciaEndereco(rsResultado);
				
				Eventos evento = instanciaEventos(rsResultado, endereco);
				return evento;
			}
			
			return null;
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DbConexao.closeStatement(stConsulta);
			DbConexao.closeResultSet(rsResultado);	
		}
	}
	
	

	@Override
	public List<Eventos> findAll() {
		
		PreparedStatement stConsulta = null;
		ResultSet rsResultado = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"SELECT Eventos.* ,Endereco.* FROM Eventos INNER JOIN Endereco "
					+ "ON Eventos.codigoEndereco = Endereco.idEndereco "
					+ "ORDER BY nomeEvento");
			
			
			rsResultado = stConsulta.executeQuery();
			
			List<Eventos> listEvento = new ArrayList<>();
			Map<Integer, Endereco> map = new HashMap<>();
			
			while(rsResultado.next()) {
				
				Endereco pegaEndereco = map.get(rsResultado.getInt("codigoEndereco"));
				
				if (pegaEndereco == null) {
					pegaEndereco = instanciaEndereco(rsResultado);
					map.put(rsResultado.getInt("codigoEndereco"), pegaEndereco);
				}
				
				Eventos evento = instanciaEventos(rsResultado, pegaEndereco);
				listEvento.add(evento);
			}
			
			return listEvento;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DbConexao.closeResultSet(rsResultado);
			DbConexao.closeStatement(stConsulta);
		}
		
	}
	
	
	private Eventos instanciaEventos(ResultSet resultadoTab, Endereco endereco) throws SQLException {
		
		Eventos novoEvento = new Eventos();
		
		novoEvento.setIdEvento(resultadoTab.getInt("idEvento"));
		novoEvento.setNomeEvento(resultadoTab.getString("nomeEvento"));
		novoEvento.setDataEvento(resultadoTab.getDate("dataEvento"));
		novoEvento.setHoraEvento(resultadoTab.getTime("horaEvento"));
		novoEvento.setIngressos(resultadoTab.getInt("ingressos"));
		novoEvento.setIngressoComprado(resultadoTab.getInt("ingressoComprado"));
		novoEvento.setCategoria(resultadoTab.getString("categoria"));
		novoEvento.setCodigoEndereco(endereco);
		
		return novoEvento;
		
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
