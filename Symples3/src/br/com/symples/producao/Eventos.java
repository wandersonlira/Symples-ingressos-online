package br.com.symples.producao;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import br.com.symples.Ingressos;
import br.com.symples.modelo.dao.DaoFactory;
import br.com.symples.modelo.dao.EnderecoDao;
import br.com.symples.modelo.dao.EventosDao;
import br.com.symples.modelo.entidades.TabEndereco;
import br.com.symples.modelo.entidades.TabEventos;
import br.com.symples.modelo.entidades.TabParticipantes;
import br.com.symples.service.ViacepService;


public class Eventos {
	
	private static TabEndereco enderecoEvento;
	private static TabEventos tabEventos;
	


//	private static TabEventos insereEvento() {
	private static void insereEvento(TabEventos objetoEvento) {
		
		
//		SimpleDateFormat formatoDate = new SimpleDateFormat("dd/MM/yyyy");
		
//		TabEventos novoEvento = new TabEventos();
		tabEventos = new TabEventos();
		
//		System.out.print("Nome Evento: ");
//		novoEvento.setNomeEvento(LeitorTeclado.getInputLine());
		tabEventos.setNomeEvento(objetoEvento.getNomeEvento());
		
//		System.out.print("Data Evento: ");		
//		novoEvento.setDataEvento(new Date());
		tabEventos.setDataEvento(objetoEvento.getDataEvento());
		
//		System.out.print("Hora Evento: ");
//		novoEvento.setHoraEvento(new Time(0));
		tabEventos.setHoraEvento(objetoEvento.getHoraEvento());
		
//		System.out.print("Qtd Ingressos: ");
//		novoEvento.setIngressos(LeitorTeclado.getInputInteger());
		tabEventos.setIngressos(objetoEvento.getIngressos());
		
//		System.out.print("Categoria: ");
//		LeitorTeclado.clearInput();
//		novoEvento.setCategoria(LeitorTeclado.getInputLine());
		tabEventos.setCategoria(objetoEvento.getCategoria());
		
//		return novoEvento;
		
	}
	
	
	
	private static void insereEndereco(String[] novoEndereco) {
		
		EnderecoDao novoEnderecoDao = DaoFactory.createEndereco();
		TabEndereco findEndereco = novoEnderecoDao.findByCep(novoEndereco[2]); // 'novoEndereco[2]' contém a String com o Cep
		
		if (findEndereco != null) {
			System.out.println("Diferente de NULL");
			enderecoEvento = findEndereco;
			System.out.println("Existe Endereco: " + enderecoEvento);
			
		} else {
			System.out.println("Igual a NULL");
			
			try {
				ViacepService findCep = new ViacepService();
	
				enderecoEvento = findCep.getEndereco(novoEndereco[2]);
				enderecoEvento.setNomeLocal(novoEndereco[0]);
				enderecoEvento.setNumLocal(novoEndereco[1]);
	
				novoEnderecoDao.insert(enderecoEvento);
			
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			
			} catch (IOException e) {
				e.printStackTrace();
			
			}
		}
			
	}
	
	
	private static void cadastraEvento(TabEventos objetoEvento, String[] novoEndereco) {
		
		
//		TabEventos tabEventos = insereEvento();
		insereEvento(objetoEvento);
		
//		TabEndereco tabEdenreco = insereEndereco();
		insereEndereco(novoEndereco);
		
		tabEventos.setCodigoEndereco(enderecoEvento);
		
		EventosDao eventosDao = DaoFactory.createEventos();
		eventosDao.insert(tabEventos);
		
	}
	
	
	public static void criarEvento(TabEventos objetoEvento, String[] novoEndereco) {

		cadastraEvento(objetoEvento, novoEndereco);	
	}


	public String exibirDataHoraLocal(Instant dataHora) {
		LocalDateTime dataHoraMundial = LocalDateTime.ofInstant(dataHora, ZoneId.systemDefault());
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
		return formatoDataHora.format(dataHoraMundial);
	}
	
	
	public String converteDateTime(LocalDate dataHora) {
		DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dataHora.format(formatoDataHora);
	}
	
	
	
	public static void exibirEventos() {
		
		EventosDao eventosDao = DaoFactory.createEventos();
		
		List<TabEventos> listTabEventos = eventosDao.findAll();
		
		for (TabEventos evento : listTabEventos) {
				
			System.out.println(
					"Nº evento: " + evento.getIdEvento()
					+ "\nNome: " + evento.getNomeEvento()
					+ "\nData: " + evento.getDataEvento()
					+ "\nHora: " + evento.getHoraEvento()
					+ "\nLocal: " + evento.getCodigoEndereco().getLocalidade() + "/" + evento.getCodigoEndereco().getUf());
					System.out.println("-----------");	
		}
		
	}

	
	
	public void exibirIngresso(Integer idTabela) {
		
		EventosDao eventoDao = DaoFactory.createEventos();
		TabEventos tabEvento = new TabEventos();
		
		tabEvento = eventoDao.findById(idTabela);
		
		if (tabEvento.getIngressoComprado() > 0) {
			
			System.out.println(
					"Nº evento: " + tabEvento.getIdEvento()
					+ "\nNome: " + tabEvento.getNomeEvento()
					+ "\nData: " + tabEvento.getDataEvento()
					+ "\nLocal: " + tabEvento.getCodigoEndereco().getLocalidade() + "/" + tabEvento.getCodigoEndereco().getUf()
					+ "\nIngressos Vendidos: " + tabEvento.getIngressoComprado());
					System.out.println("-----------");
			
		} else {
			System.out.println(" --- NÃO HÁ INGRESSOS COMPRADOS! --- ");
		}
				
	}
	
	
	
	public static void comprarIngresso(Integer codEvento, TabParticipantes novoParticipante) {
		
		EventosDao eventoDao = DaoFactory.createEventos();
		TabEventos tabEventos = new TabEventos();
		
		tabEventos = eventoDao.findById(codEvento);
		
		if (tabEventos.getIngressoComprado() < tabEventos.getIngressos()) {
			
			Ingressos ingresso = new Ingressos();
			ingresso.cadastrarIngresso(tabEventos, novoParticipante);
			
			int novoIngresso = (tabEventos.getIngressoComprado() + 1);
			
			tabEventos.setIngressoComprado(novoIngresso);
			tabEventos.setIdEvento(codEvento);
			
			eventoDao.update(tabEventos);
			
			System.out.println("Qtd disponível: " + (tabEventos.getIngressos() - tabEventos.getIngressoComprado()));
			
		} else {
			System.out.println(" --- INGRESSO ESGOTADO! --- ");	
			}	

	}

	
}
