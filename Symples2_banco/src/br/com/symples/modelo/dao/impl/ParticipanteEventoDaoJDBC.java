package br.com.symples.modelo.dao.impl;

import java.sql.Connection;
import java.util.List;

import br.com.symples.modelo.dao.ParticipanteEventoDao;

public class ParticipanteEventoDaoJDBC implements ParticipanteEventoDao{
	
	Connection conexao;
	
	public ParticipanteEventoDaoJDBC (Connection conexao) {
		this.conexao = conexao;
	}
	
	

	@Override
	public void insert(ParticipanteEventoDao objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ParticipanteEventoDao objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ParticipanteEventoDao findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParticipanteEventoDao> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
