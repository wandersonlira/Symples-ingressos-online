package br.com.symples.modelo.dao;

import java.util.List;

import br.com.symples.modelo.entidades.Participantes;

public interface ParticipantesDao {
	
	void insert(Participantes objeto);
	void update(Participantes objeto);
	void deleteById(Integer id);
	Participantes findById(Integer id);
	List<Participantes> findAll();

}
