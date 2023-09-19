package br.com.symples;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import Dao.CRUD;
import br.com.symples.modelo.dao.DaoFactory;
import br.com.symples.modelo.dao.EventosDao;
import br.com.symples.modelo.dao.ParticipanteEventoDao;
import br.com.symples.modelo.dao.ParticipantesDao;
import br.com.symples.modelo.entidades.TabEventos;
import br.com.symples.modelo.entidades.TabParticipanteEvento;
import br.com.symples.modelo.entidades.TabParticipantes;

public class Ingressos{	
	
	private LocalDateTime timeLocalIngresso;

	
	private TabParticipantes insertParticipante() {
		
		TabParticipantes tabParticipante = new TabParticipantes();
		
		System.out.print("Nome: ");
		tabParticipante.setNomeParticipante(LeitorTeclado.getInputLine());
		
		System.out.print("CPF: ");
		tabParticipante.setCpf(LeitorTeclado.getInputLine());
		
		System.out.print("Email: ");
		tabParticipante.setEmail(LeitorTeclado.getInputLine());
		
		ParticipantesDao participanteDao = DaoFactory.createParticipantes();
		participanteDao.insert(tabParticipante);
		
		return tabParticipante;
			
		}
	


	private void insertParticipanteEvento(TabParticipantes objetoParticipante, TabEventos objetoEvento) {
		
		ParticipanteEventoDao participanteEventoDao = DaoFactory.createParticipanteEvento();
		TabParticipanteEvento participanteEvento = new TabParticipanteEvento();
		
		participanteEvento.setCodigo_idParticipante(objetoParticipante);
		participanteEvento.setCodigo_idEvento(objetoEvento);
		
		participanteEventoDao.insert(participanteEvento);
		
	}


	public void cadastrarIngresso(TabEventos objetoEvento) {
		
//		ParticipantesDao participanteDao = DaoFactory.createParticipantes(); // talvez remover e deixar apenas em 'insertParticipante
		TabParticipantes novoParticipante = insertParticipante();
		
//		novoParticipante.setCodigoEvento(objetoEvento); // talvez remover pois não será necessário
//		
//		participanteDao.insert(novoParticipante); // talvez remover e deixar apenas em 'insertParticipante

		insertParticipanteEvento(novoParticipante, objetoEvento);
		
	}	
	
//	--------------------------------- TERMINAR ESTA PARTE ABAIXO ----------------------------
	
	public void ingressoComprado(Integer idParticipante, Integer codEvento) {
		
		ResultSet resultadoConsulta = null;
		
		try {
			
			CRUD crud = new CRUD();
			resultadoConsulta = crud.getSelect("eventos", "endereco", "participantes", "participante_evento");
			

			while (resultadoConsulta.next()) {
				if (resultadoConsulta.getInt("Id_participante") == idParticipante 
						&& resultadoConsulta.getInt("Id_evento") == codEvento) {
				System.out.println(
						"Evento: " + resultadoConsulta.getString("Nome_evento")
					+	"\nRua: " + resultadoConsulta.getString("Logradouro")
					+	", " + resultadoConsulta.getString("NumCasa")
					+	"\nBairro: " + resultadoConsulta.getString("Bairro")
					+	"\nCidade: " + resultadoConsulta.getString("Localidade")
					+	"/" + resultadoConsulta.getString("Uf")
					+	"\nParticipante: " + resultadoConsulta.getString("Nome_participante"));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void selectParticipante() {
		
		ResultSet resultadoConsulta = null;
		
		try {
			
			CRUD crud = new CRUD();
			resultadoConsulta = crud.getSelect("participantes");
			
			while (resultadoConsulta.next()) {
				System.out.println(resultadoConsulta.getInt("Id_participante") + ", " + resultadoConsulta.getString("Nome_participante"));
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
			
		} 
		
	}


	public LocalDateTime registroCadastro() {
//		return getParticipante().getTimeLocalIngresso();
		return null;
	}

	
	
	public LocalDateTime getTimeLocalIngresso() {
		return timeLocalIngresso;
	}

	public void setTimeLocalIngresso(LocalDateTime timeLocalIngresso) {
		this.timeLocalIngresso = timeLocalIngresso;
	}
	
	
//	--------------- TESTE --------------------------

	public static void main(String[] args) {
		
		Ingressos ingresso = new Ingressos();
		EventosDao eventoDao = DaoFactory.createEventos();
		TabEventos tabEvento = new TabEventos();
		
		tabEvento = eventoDao.findById(1);
		
		ingresso.cadastrarIngresso(tabEvento);

		
	}

	
}
