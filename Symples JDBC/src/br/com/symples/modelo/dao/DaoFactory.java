package br.com.symples.modelo.dao;

import br.com.symples.modelo.dao.impl.EnderecoDaoJDBC;
import br.com.symples.modelo.dao.impl.EventosDaoJDBC;
import br.com.symples.modelo.dao.impl.ParticipanteEventoDaoJDBC;
import br.com.symples.modelo.dao.impl.ParticipantesDaoJDBC;
import br.com.symples.service.DbConexao;

public class DaoFactory {
	
	public static ParticipantesDao createParticipantes() {
		return new ParticipantesDaoJDBC(DbConexao.getConexao());
	}
	
	
	public static EventosDao createEventos() {
		return new EventosDaoJDBC(DbConexao.getConexao());
	}
	
	public static EnderecoDao createEndereco() {
		return new EnderecoDaoJDBC(DbConexao.getConexao());
	}
	
	public static ParticipanteEventoDao createParticipanteEvento() {
		return new ParticipanteEventoDaoJDBC(DbConexao.getConexao());
	}

}
