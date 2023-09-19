package br.com.symples.producao;

import java.io.IOException;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import br.com.symples.Ingressos;
import br.com.symples.LeitorTeclado;
import br.com.symples.modelo.dao.DaoFactory;
import br.com.symples.modelo.dao.EnderecoDao;
import br.com.symples.modelo.dao.EventosDao;
import br.com.symples.modelo.entidades.TabEndereco;
import br.com.symples.modelo.entidades.TabEventos;
import br.com.symples.service.ViacepService;


public class Eventos {
	
	public static TabEndereco enderecoEvento;


	private static TabEventos insereEvento() {
		
		
//		SimpleDateFormat formatoDate = new SimpleDateFormat("dd/MM/yyyy");
		
		TabEventos novoEvento = new TabEventos();
		
		System.out.print("Nome Evento: ");
		novoEvento.setNomeEvento(LeitorTeclado.getInputLine());
		
		System.out.print("Data Evento: ");		
		novoEvento.setDataEvento(new Date());
		
		System.out.print("Hora Evento: ");
		novoEvento.setHoraEvento(new Time(0));
		
		System.out.print("Qtd Ingressos: ");
		novoEvento.setIngressos(LeitorTeclado.getInputInteger());
		
		System.out.print("Categoria: ");
		LeitorTeclado.clearInput();
		novoEvento.setCategoria(LeitorTeclado.getInputLine());
		
		
		return novoEvento;
		
	}
	
	
	
	private static TabEndereco insereEndereco() {
		
		EnderecoDao novoEndereco = DaoFactory.createEndereco();
		
		System.out.print("Local: ");
		LeitorTeclado.clearInput();
		String nomeLocal = LeitorTeclado.getInputLine();
		
		System.out.print("numeroLocal: ");
		String numerolocal = LeitorTeclado.getInputLine();

		System.out.print("CEP: ");
		String cepCadastro = LeitorTeclado.getInputLine();
		
		try {
			ViacepService apiCep = new ViacepService();

			enderecoEvento = apiCep.getEndereco(cepCadastro);
			enderecoEvento.setNomeLocal(nomeLocal);
			enderecoEvento.setNumLocal(numerolocal);

			novoEndereco.insert(enderecoEvento);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		return enderecoEvento;
		
		
	}
	
	
	private static void cadastraEvento() {
		
		
		TabEventos tabEventos = insereEvento();
		TabEndereco tabEdenreco = insereEndereco();
		
		tabEventos.setCodigoEndereco(tabEdenreco);
		
		EventosDao eventosDao = DaoFactory.createEventos();
		eventosDao.insert(tabEventos);
		
	}
	
	
	public static void criarEvento() {

		cadastraEvento();	
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
	
	
	
	public static void comprarIngresso(Integer codEvento) {
		
		EventosDao eventoDao = DaoFactory.createEventos();
		TabEventos tabEventos = new TabEventos();
		
		tabEventos = eventoDao.findById(codEvento);
		
		if (tabEventos.getIngressoComprado() < tabEventos.getIngressos()) {
			
			Ingressos ingresso = new Ingressos();
			ingresso.cadastrarIngresso(tabEventos);
			
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
