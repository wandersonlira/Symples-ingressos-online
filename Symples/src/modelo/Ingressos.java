package modelo;

public class Ingressos{	
	
	private Cadastro participante;
	
	public void cadastrarIngresso() {
		setParticipante(participante = new Cadastro());
		getParticipante().cadastrar();
	}	
	

	public Cadastro getParticipante() {
		return participante;
	}

	public void setParticipante(Cadastro participante) {
		this.participante = participante;
	}
	
}
