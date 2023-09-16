package br.com.symples.modelo.dao;

import java.util.List;

import br.com.symples.modelo.entidades.Eventos;

public interface EventosDao {
	
	void insert(Eventos objeto);
	void update(Eventos objeto);
	void deleteById(Integer id);
	Eventos findById(Integer id);
	List<Eventos> findAll();

}
