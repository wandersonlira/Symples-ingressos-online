package producao;

import modelo.Endereco;
import modelo.Ingressos;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class Eventos {
	
	private String nomeEvento;
	private LocalDateTime dataEvento;
	private int quantidadeIngresso;
	private Endereco enderecoEvento;
	private Ingressos ingresso;
	int cont = 0;
	
	public Eventos() {}
	
	
	public Eventos(String nomeEvento, LocalDateTime dataEvento, int quantiInteger, Endereco enderecoEvento) {
		this.setNomeEvento(nomeEvento);
		this.setDataEvento(dataEvento);
		this.setQuantidadeIngresso(quantiInteger);
		this.setEnderecoEvento(enderecoEvento);
	}
	
	
	public String exibirDataHoraLocal(Instant dataHora) {
		LocalDateTime dataHoraMundial = LocalDateTime.ofInstant(dataHora, ZoneId.systemDefault());
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
		return formatoDataHora.format(dataHoraMundial);
	}
	
	
	public String converteDateTime(LocalDateTime dataHora) {
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return dataHora.format(formatoDataHora);
	}

	
	public void exibirIngresso() {
		
		
		if (comprarIngresso() == true) {
	
			System.out.println("Nome Evento: " + this.getNomeEvento());
			System.out.println("Local Evento: " + this.converteDateTime(getDataEvento()));
			System.out.println("Endereço: " + this.getEnderecoEvento().getLogradouro() + ", " + this.getEnderecoEvento().getBairro()
					+ ", " + this.getEnderecoEvento().getLocalidade() + ", " + this.getEnderecoEvento().getUf());
			System.out.println("Participante: " + this.ingresso.getParticipante().getNomePessoa());
		
		} else {
			System.out.println("Não há ingressos comprados");
		}
	}
	
	
	public boolean comprarIngresso() {
		
		if (this.quantidadeIngresso > 0) {
			setIngresso(ingresso = new Ingressos());
			this.ingresso.cadastrarIngresso();
			this.quantidadeIngresso--;	
//			this.cont++;
			return true;
			
		} else {
			System.out.println(" --- INGRESSO ESGOTADO! --- ");
			return false;
		}

	}

	
	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
	

	public LocalDateTime getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(LocalDateTime dataEvento) {
		this.dataEvento = dataEvento;
	}


	public int getQuantidadeIngresso() {
		return quantidadeIngresso;
	}

	public void setQuantidadeIngresso(int quantidadeIngresso) {
		this.quantidadeIngresso = quantidadeIngresso;
	}
	
	public Endereco getEnderecoEvento() {
		return enderecoEvento;
	}

	public void setEnderecoEvento(Endereco enderecoEvento) {
		this.enderecoEvento = enderecoEvento;
	}

	
	public Ingressos getIngresso() {
		return ingresso;
	}

	public void setIngresso(Ingressos ingresso) {
		this.ingresso = ingresso;
	}
}
