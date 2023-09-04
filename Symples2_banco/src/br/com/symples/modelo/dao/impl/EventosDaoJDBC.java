package br.com.symples.modelo.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.symples.DbException;
import br.com.symples.modelo.dao.EventosDao;
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
					+ "(nomeEvento, dataEvento, horaEvento, ingressos, ingressoComprado, categoria, codigoEndereco) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			stConsulta.setString(1, objeto.getNomeEvento());
			stConsulta.setDate(2, new java.sql.Date(objeto.getDataEvento().getTime()));
			stConsulta.setTime(3, new java.sql.Time(objeto.getHoraEvento().getTime()));
			stConsulta.setInt(4, objeto.getIngressos());
			stConsulta.setInt(5, objeto.getIngressoComprado());
			stConsulta.setString(6, objeto.getCategoria());
			stConsulta.setInt(7, objeto.getCodigoEndereco().getIdEndereco());
			
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Eventos findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Eventos> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
