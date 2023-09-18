package br.com.symples.modelo.dao;

import java.util.List;

import br.com.symples.modelo.entidades.TabEndereco;

	public interface EnderecoDao {void insert(TabEndereco objeto);
	void update(TabEndereco objeto);
	void deleteById(Integer id);
	TabEndereco findById(Integer id);
	List<TabEndereco> findAll();
	
}
