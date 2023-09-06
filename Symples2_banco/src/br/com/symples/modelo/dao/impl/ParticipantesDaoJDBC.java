package br.com.symples.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.symples.DbException;
import br.com.symples.modelo.dao.ParticipantesDao;
import br.com.symples.modelo.entidades.Eventos;
import br.com.symples.modelo.entidades.Participantes;
import br.com.symples.service.DbConexao;

public class ParticipantesDaoJDBC implements ParticipantesDao{
	
	
	public Connection conexao;
	
	public ParticipantesDaoJDBC (Connection conexao) {
		this.conexao = conexao;
	}
	
	

	@Override
	public void insert(Participantes objeto) {
		
		PreparedStatement stConsulta = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"INSERT INTO Participantes (nomeParticipante, cpf, email, codigoEvento) "
					+ "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			stConsulta.setString(1, objeto.getNomeParticipante());
			stConsulta.setString(2, objeto.getCpf());
			stConsulta.setString(3, objeto.getEmail());
			stConsulta.setInt(4, objeto.getCodigoEventos().getIdEvento());
			
			int rowsAffected = stConsulta.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rsResultado = stConsulta.getGeneratedKeys();
				
				if (rsResultado.next()) {
					int idNovo = rsResultado.getInt(1);
					objeto.setIdParticipante(idNovo);
				}
				
				DbConexao.closeResultSet(rsResultado);
				
			} else {
				throw new DbException("Aconteceu algum erro... nenhuma linha alterada!!");
				}
		
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		
		} finally {
			DbConexao.closeStatement(stConsulta);
			}
		
	}
		
		
		

	@Override
	public void update(Participantes objeto) {
		
		PreparedStatement stConsulta = null;
		
		try {
			stConsulta = conexao.prepareStatement(
					"UPDATE Participantes "
					+ "SET nomeParticipante = ?, cpf = ?, email = ?, codigoEvento = ? "
					+ "WHERE idParticipante = ?");
			
			stConsulta.setString(1, objeto.getNomeParticipante());
			stConsulta.setString(2, objeto.getCpf());
			stConsulta.setString(3, objeto.getEmail());
			stConsulta.setInt(4, objeto.getCodigoEventos().getIdEvento());
			stConsulta.setInt(5, objeto.getIdParticipante());
			
			int rowsAffected = stConsulta.executeUpdate();
			
			if (rowsAffected > 0) {
				System.out.println("Update realizado com sucesso!");
				
			} else {
				System.out.println("Houve um erro inesperado... nenhuma linha afetada!!");
			}
		
		} catch (SQLException e) {
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
					"DELETE FROM Participantes "
					+ "WHERE idParticipantes = ?");
			
			stConsulta.setInt(1, id);
			
			int rowsAffected = stConsulta.executeUpdate();
			
			if (rowsAffected == 0) {
				System.out.println("Erro inesperado... 'id' informado não existe na tabela");
			}
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DbConexao.closeStatement(stConsulta);
		}
		
	}
	
	

	@Override
	public Participantes findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Participantes> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private Participantes instanciaParticipantes(ResultSet resultadoTab, Eventos eventos) throws SQLException {
		
		Participantes novoParticipante = new Participantes();
		
		novoParticipante.setIdParticipante(resultadoTab.getInt("idParticipante"));
		novoParticipante.setNomeParticipante(resultadoTab.getString("nomeParticipante"));
		novoParticipante.setCpf(resultadoTab.getString("cpf"));
		novoParticipante.setEmail(resultadoTab.getString("email")); 
		novoParticipante.setCodigoEvento(eventos);
		
		return novoParticipante;
		
	}
	
	
	private Eventos instanciaEventos(ResultSet resultadoTab) throws SQLException {
		Eventos novoEvento = new Eventos();
		
		novoEvento.setIdEvento(resultadoTab.getInt("idEvento"));
		novoEvento.setNomeEvento(resultadoTab.getString("nomeEvento"));
		novoEvento.setDataEvento(resultadoTab.getDate("dataEvento"));
		novoEvento.setHoraEvento(resultadoTab.getTime("horaEvento"));
		novoEvento.setIngressos(resultadoTab.getInt("ingressos"));
		novoEvento.setIngressoComprado(resultadoTab.getInt("ingressoComprado"));
		novoEvento.setCategoria(resultadoTab.getString("categoria"));
//		novoEvento.setCodigoEndereco(endereco);
		
		return novoEvento;
	}

}
