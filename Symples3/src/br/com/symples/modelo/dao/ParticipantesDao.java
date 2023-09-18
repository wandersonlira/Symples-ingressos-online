package br.com.symples.modelo.dao;

import java.util.List;

import br.com.symples.modelo.entidades.TabParticipantes;

public interface ParticipantesDao {
	
	void insert(TabParticipantes objeto);
	void update(TabParticipantes objeto);
	void deleteById(Integer id);
	TabParticipantes findById(Integer id);
	List<TabParticipantes> findAll();

}
