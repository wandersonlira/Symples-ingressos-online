package br.com.symples.modelo.dao;

import java.util.List;

import br.com.symples.modelo.entidades.ParticipanteEvento;

public interface ParticipanteEventoDao {
	
	void insert(ParticipanteEvento objeto);
	void update(ParticipanteEvento objeto);
	void deleteById(Integer id);
	ParticipanteEvento findById(Integer id);
	List<ParticipanteEvento> findAll();

}
