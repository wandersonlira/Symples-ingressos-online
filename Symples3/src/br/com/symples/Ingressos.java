package br.com.symples;

import java.time.LocalDateTime;
import java.util.List;

import br.com.symples.modelo.dao.DaoFactory;
import br.com.symples.modelo.dao.ParticipanteEventoDao;
import br.com.symples.modelo.dao.ParticipantesDao;
import br.com.symples.modelo.entidades.TabEventos;
import br.com.symples.modelo.entidades.TabParticipanteEvento;
import br.com.symples.modelo.entidades.TabParticipantes;

public class Ingressos{	
	
	private LocalDateTime timeLocalIngresso;
	private TabParticipantes objetoParticipante;
	private TabParticipanteEvento objetoParticipanteEvento;
//	private Integer idPE;

	
	private void inserirParticipante(TabParticipantes novoParticipante) {
		
		this.objetoParticipante = new TabParticipantes();
		
//		System.out.print("Nome: ");
//		this.objetoParticipante.setNomeParticipante(LeitorTeclado.getInputLine());
		this.objetoParticipante.setNomeParticipante(novoParticipante.getNomeParticipante());
		
//		System.out.print("CPF: ");
//		this.objetoParticipante.setCpf(LeitorTeclado.getInputLine());
		this.objetoParticipante.setCpf(novoParticipante.getCpf());
		
//		System.out.print("Email: ");
//		this.objetoParticipante.setEmail(LeitorTeclado.getInputLine());
		this.objetoParticipante.setEmail(novoParticipante.getEmail());
		
		ParticipantesDao participanteDao = DaoFactory.createParticipantes();
		participanteDao.insert(this.objetoParticipante);
			
		}
	


	
	private void inserirParticipanteEvento(TabParticipantes objetoParticipante, TabEventos objetoEvento) {
		
		ParticipanteEventoDao participanteEventoDao = DaoFactory.createParticipanteEvento();
		this.objetoParticipanteEvento = new TabParticipanteEvento();
		
		this.objetoParticipanteEvento.setCodigo_idParticipante(objetoParticipante);
		this.objetoParticipanteEvento.setCodigo_idEvento(objetoEvento);
		
		participanteEventoDao.insert(this.objetoParticipanteEvento);
		
//		this.idPE = objetoParticipanteEvento.getId_ParticipanteEvento();
		
	}


	
	
	public void cadastrarIngresso(TabEventos objetoEvento, TabParticipantes novoParticipante) {
		
		inserirParticipante(novoParticipante);
		System.out.println("");
		inserirParticipanteEvento(this.objetoParticipante, objetoEvento);
		
		Integer codParticipanteEvento = this.objetoParticipanteEvento.getId_ParticipanteEvento();
		ingressoComprado(codParticipanteEvento);
		
	}	
	
	
	
	public void ingressoComprado(Integer codParticipanteEvento) {
		
		ParticipanteEventoDao participanteEventoDao = DaoFactory.createParticipanteEvento();
//		TabParticipanteEvento tabParticipanteEvento = new TabParticipanteEvento();
		
		this.objetoParticipanteEvento = participanteEventoDao.findById(codParticipanteEvento);
		
		System.out.println(
				"Evento: " + this.objetoParticipanteEvento.getCodigo_idEvento().getNomeEvento()
			+	"\nRua: " + this.objetoParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getLogradouro()
			+	", " + this.objetoParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getNumLocal()
			+	"\nBairro: " + this.objetoParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getBairro()
			+	"\nCidade: " + this.objetoParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getLocalidade()
			+	"/" + this.objetoParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getUf()
			+	"\nParticipante: " + this.objetoParticipanteEvento.getCodigo_idParticipante().getNomeParticipante()
			
			);
	}
	
	
	
	public void selectParticipanteEvento() {
		
		ParticipanteEventoDao participanteEventoDao = DaoFactory.createParticipanteEvento();
		List<TabParticipanteEvento> listParticipanteEvento = participanteEventoDao.findAll();
		
		for (TabParticipanteEvento tabParticipanteEvento : listParticipanteEvento) {
			System.out.println(
					 "Nome: " + tabParticipanteEvento.getCodigo_idParticipante().getNomeParticipante()
					+ "\nCPF: " + tabParticipanteEvento.getCodigo_idParticipante().getCpf()
					+ "\nE-mail: " + tabParticipanteEvento.getCodigo_idParticipante().getEmail()
					+ "\n\nEvento: " + tabParticipanteEvento.getCodigo_idEvento().getNomeEvento()
					+ "\nRua: " + tabParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getLogradouro()
					+ ", " + tabParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getNumLocal()
					+ "\nBairro: " + tabParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getBairro()
					+ "\nCidade: " + tabParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getLocalidade()
					+ "/" + tabParticipanteEvento.getCodigo_idEvento().getCodigoEndereco().getUf()
					+ "\n---------------"
					
					);
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

	
	public static void main(String[] args) {
		Ingressos ingre = new Ingressos();
		
		ingre.ingressoComprado(16);
		
	}
	
}
