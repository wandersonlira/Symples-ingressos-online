package producao;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class Eventos {
	
	private String nomeEvento;
	private LocalDateTime dataEvento;
	private Integer quantidadeIngresso; 
	
	public Eventos() {}
	
	
	public Eventos(String nomeEvento, LocalDateTime dataEvento, Integer quantiInteger) {
		this.setNomeEvento(nomeEvento);
		this.setDataEvento(dataEvento);
		this.setQuantidadeIngresso(quantiInteger);
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


	public Integer getQuantidadeIngresso() {
		return quantidadeIngresso;
	}

	public void setQuantidadeIngresso(Integer quantidadeIngresso) {
		this.quantidadeIngresso = quantidadeIngresso;
	}
	
	
	
	public String exibirDataHoraLocal(Instant dataHora) {
		LocalDateTime dataHoraMundial = LocalDateTime.ofInstant(dataHora, ZoneId.systemDefault());
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
		return formatoDataHora.format(dataHoraMundial);
	}

}
