package br.com.symples.modelo.entidades;

import java.io.Serializable;
import java.util.Objects;

public class ParticipanteEvento implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer id_ParticipanteEvento;
	private Participantes codigo_idParticipante;
	private Eventos codigo_idEvento;
	
	
	public ParticipanteEvento() {}
	
	
	public ParticipanteEvento(Integer id_ParticipanteEvento, Participantes codigo_idParticipante, Eventos codigo_idEvento) {
		
		super();
		this.id_ParticipanteEvento = id_ParticipanteEvento;
		this.codigo_idParticipante = codigo_idParticipante;
		this.codigo_idEvento = codigo_idEvento;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(id_ParticipanteEvento);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipanteEvento other = (ParticipanteEvento) obj;
		return Objects.equals(id_ParticipanteEvento, other.id_ParticipanteEvento);
	}

	

	public Integer getId_ParticipanteEvento() {
		return id_ParticipanteEvento;
	}


	public void setId_ParticipanteEvento(Integer id_ParticipanteEvento) {
		this.id_ParticipanteEvento = id_ParticipanteEvento;
	}


	public Participantes getCodigo_idParticipante() {
		return codigo_idParticipante;
	}


	public void setCodigo_idParticipante(Participantes codigo_idParticipante) {
		this.codigo_idParticipante = codigo_idParticipante;
	}


	public Eventos getCodigo_idEvento() {
		return codigo_idEvento;
	}


	public void setCodigo_idEvento(Eventos codigo_idEvento) {
		this.codigo_idEvento = codigo_idEvento;
	}

	

	@Override
	public String toString() {
		return "ParticipanteEvento [id_ParticipanteEvento=" + id_ParticipanteEvento + ", codigo_idParticipante="
				+ codigo_idParticipante + ", codigo_idEvento=" + codigo_idEvento + "]";
	}
	
	
	
	

}
