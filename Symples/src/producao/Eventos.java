package producao;

import modelo.Endereco;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class Eventos {
	
	private String nomeEvento;
	private LocalDateTime dataEvento;
	private int quantidadeIngresso;
	private Endereco enderecoEvento;
	
	public Eventos() {}
	
	
	public Eventos(String nomeEvento, LocalDateTime dataEvento, int quantiInteger, Endereco enderecoEvento) {
		this.setNomeEvento(nomeEvento);
		this.setDataEvento(dataEvento);
		this.setQuantidadeIngresso(quantiInteger);
		this.setEnderecoEvento(enderecoEvento);
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
	
	
	public String exibirDataHoraLocal(Instant dataHora) {
		LocalDateTime dataHoraMundial = LocalDateTime.ofInstant(dataHora, ZoneId.systemDefault());
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
		return formatoDataHora.format(dataHoraMundial);
	}
	
	
	public String converteDateTime(LocalDateTime dataHora) {
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return dataHora.format(formatoDataHora);
	}
	
	
	public void imprimirIngresso() {
		
		System.out.println("Nome Evento: " + getNomeEvento());
		System.out.println("Local Evento: " + converteDateTime(getDataEvento()));
		System.out.println("Endereço: " + getEnderecoEvento().getLogradouro() + ", " + getEnderecoEvento().getBairro()
				+ ", " + getEnderecoEvento().getLocalidade() + ", " + getEnderecoEvento().getUf());
		
	}
	
	
}
