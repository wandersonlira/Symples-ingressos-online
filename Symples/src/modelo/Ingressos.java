package modelo;

import java.time.LocalDateTime;

public class Ingressos{	
	
	private Cadastro participante;

	
	public void cadastrarIngresso() {
		setParticipante(participante = new Cadastro());
		getParticipante().cadastrar();
	}	
	
	public LocalDateTime registroCadastro() {
		return getParticipante().getTimeLocalIngresso();
	}


	public Cadastro getParticipante() {
		return participante;
	}

	public void setParticipante(Cadastro participante) {
		this.participante = participante;
	}
	
}
