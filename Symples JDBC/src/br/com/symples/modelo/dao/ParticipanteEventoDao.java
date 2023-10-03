package br.com.symples.modelo.dao;

import java.util.List;

import br.com.symples.modelo.entidades.TabParticipanteEvento;

public interface ParticipanteEventoDao {
	
	void insert(TabParticipanteEvento objeto);
	void update(TabParticipanteEvento objeto);
	void deleteById(Integer id);
	TabParticipanteEvento findById(Integer id);
	List<TabParticipanteEvento> findAll();

}
