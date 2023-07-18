package modelo;

public class Ingressos{	
	
	private Cadastro participante;
	

	public Cadastro getParticipante() {
		return participante;
	}

	public void setParticipante(Cadastro participante) {
		this.participante = participante;
	}
	
		
	public void comprarIngresso() {
		setParticipante(participante = new Cadastro());
		getParticipante().cadastrar();
	}
	
}
