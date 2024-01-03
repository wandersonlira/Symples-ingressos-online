package br.com.symples.modelo.dao;

import java.util.List;

import br.com.symples.modelo.entidades.TabEventos;

public interface EventosDao {
	
	Integer insert(TabEventos objeto);
	void update(TabEventos objeto);
	void deleteById(Integer id);
	TabEventos findById(Integer id);
	List<TabEventos> findAll();

}