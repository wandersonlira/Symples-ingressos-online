package br.com.symples.modelo.dao;

import java.util.List;

public interface ParticipanteEventoDao {
	
	void insert(ParticipanteEventoDao objeto);
	void update(ParticipanteEventoDao objeto);
	void deleteById(Integer id);
	ParticipanteEventoDao findById(Integer id);
	List<ParticipanteEventoDao> findAll();

}
