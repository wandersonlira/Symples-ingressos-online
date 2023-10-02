import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import br.com.symples.LeitorTeclado;

public class TesteTudo {
	
	public static void main(String[] args) {
		
//----------------------------------------------- TESTANDO OS METODOS -----------------------------------------------------------------------------------		
		
//		------------------------------------------ PARTICIPANTES LIST ---------------------------------------------------
			
//		ParticipantesDao novoParticipante = DaoFactory.createParticipantes();

	
//		TabParticipantes participante = novoParticipante.findById(3);
//	
//		System.out.println(participante);
		
//		List<TabParticipantes> listParticipante = novoParticipante.findAll();
//	
//		for (TabParticipantes participante : listParticipante) {
//			System.out.println(participante);
//		}
		
		

//			-------------------------------------- PARTICIPANTE-EVENTO-------------------------------------------------
		
		
//		ParticipanteEventoDao novoParticipanteEvento = DaoFactory.createParticipanteEvento();
//		ParticipantesDao novoParticipante = DaoFactory.createParticipantes();
		
//		Endereco endere = new Endereco();
//		endere.setIdEndereco(1);
		
		
//		------------ new Eventos -------------
//		Eventos evento = new Eventos();
//		evento.setIdEvento(3);
//		
////		------------- new Participantes ----------------
//		Participantes participante = new Participantes();
//		participante.setIdParticipante(4);
//		participante.setNomeParticipante("Renato Cariani");
//		participante.setCpf("00000000003");
//		participante.setEmail("cariani@peso.com");
//		participante.setCodigoEvento(evento);
//		
//		novoParticipante.update(participante);
//		
////		----------------- new ParticipanteEvento -----------------
//		ParticipanteEvento participanteEvento = new ParticipanteEvento();
//		participanteEvento.setCodigo_idParticipante(participante);
//		participanteEvento.setCodigo_idEvento(evento);
//		participanteEvento.setId_ParticipanteEvento(5);
//	
//		novoParticipanteEvento.update(participanteEvento);
		
		
//		----------------- Lista ParticipanteEvento -----------------
//		ParticipanteEventoDao newParticipanteEvento = DaoFactory.createParticipanteEvento();
//		
//		List<ParticipanteEvento> listParticipanteEvento = newParticipanteEvento.findAll();
//		
//		for (ParticipanteEvento pv : listParticipanteEvento) {
//			System.out.println(pv);
//		}
		
		
//        -------------------------------------- Lista Eventos -------------------------------------------------	
		
//		EventosDao novoEvento = DaoFactory.createEventos();
//		
//		List<TabEventos> listEvento = novoEvento.findAll();
//		
//		for (TabEventos evento : listEvento) {
//			System.out.println(evento);
//		}
		
		
		
		
		
//		 -------------------------------------- Cadastra Endereco -------------------------------------------------	
		
//		EnderecoDao novoEndereco = DaoFactory.createEndereco();
//
//		System.out.print("Local: ");
//		String local = LeitorTeclado.getInputLine();
//		
//		
////		System.out.print("numeroLocal: ");
////		String numerolocal = LeitorTeclado.getInputLine();
//		
//		ViacepService novoCep = new ViacepService();
//		
//		try {
//			
//			Endereco endereco = new Endereco();
//			
//			endereco =  novoCep.getEndereco("52211058");
//			endereco.setNomeLocal(local);
////			endereco.setNumLocal(numerolocal);
//			
//			novoEndereco.insert(endereco);
//			
//			
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		List<Endereco> listEnderecoTab = novoEndereco.findAll();
//		
//		for (Endereco objEndereco : listEnderecoTab) {
//			System.out.println(objEndereco);
//		}
		
		
		
		
	}

}
