package br.com.symples.service.bancoquery;

abstract interface SqlDML {
	
	abstract void select(String query);
	abstract void insert(String query);
	abstract void delete(String query);
	abstract void update(String query);
}
