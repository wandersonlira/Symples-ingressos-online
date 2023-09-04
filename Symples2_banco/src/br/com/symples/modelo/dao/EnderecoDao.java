package br.com.symples.modelo.dao;

import java.util.List;

import br.com.symples.modelo.entidades.Endereco;

	public interface EnderecoDao {void insert(Endereco objeto);
	void update(Endereco objeto);
	void deleteById(Integer id);
	Endereco findById(Integer id);
	List<Endereco> findAll();
	
}
