import java.sql.Time;
import java.util.Date;

import br.com.symples.LeitorTeclado;
import br.com.symples.modelo.entidades.TabEventos;
import br.com.symples.modelo.entidades.TabParticipantes;
import br.com.symples.producao.Eventos;
import br.com.symples.service.DbConexao;

public class Main {
	

	public static void main(String[] args) {
		
		Integer opcao;
		
		do {
			
			System.out.println(
					"\n* ============ MENU =========== *\n\n"
			+	"[1] - Cadastrar Evento   \n"
			+	"[2] - Exibir Eventos     \n"
			+   "[3] - Comprar Ingresso   \n"
			+   "[4] - Imprimir Ingresso  \n"
			+   "[0] - Sair\n"
			);
			
			System.out.print("Escolha uma Opção: ");
			opcao = LeitorTeclado.getInputInteger();
			LeitorTeclado.clearInput();
			
			switch (opcao) {
			
			case 1:
				
				TabEventos novoEvento = new TabEventos();
				String[] novoEndereco = new String[3];
				
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
				
//				---------------- Captura Endereco ---------------
				
				System.out.print("Local: ");
				novoEndereco[0] =  LeitorTeclado.getInputLine();
				
				System.out.print("numeroLocal: ");
				novoEndereco[1] = LeitorTeclado.getInputLine();

				System.out.print("CEP: ");
				novoEndereco[2] = LeitorTeclado.getInputLine();
				
				Eventos.criarEvento(novoEvento, novoEndereco);
				
				break;
				
			case 2:
				Eventos.exibirEventos();
				
				System.out.print("Comprar [sim]/[não]: ");
				String resposta = LeitorTeclado.getInputLine().toUpperCase().replace(" ", "");
				
				if (resposta.equals("SIM") == true) {
					
					System.out.println("Digite codEvento: ");
					Integer codEvento = LeitorTeclado.getInputInteger();
					LeitorTeclado.clearInput();
					
					TabParticipantes novoParticipante = new TabParticipantes();
					
					
					System.out.print("Nome: ");
					novoParticipante.setNomeParticipante(LeitorTeclado.getInputLine());
					
					System.out.print("CPF: ");
					novoParticipante.setCpf(LeitorTeclado.getInputLine());
					
					System.out.print("Email: ");
					novoParticipante.setEmail(LeitorTeclado.getInputLine());
					
					Eventos.comprarIngresso(codEvento, novoParticipante);
				}
				
				break;
				
			case 3:
				Eventos.exibirEventos();
				
				System.out.println("Digite codEvento: ");
				Integer codEvento = LeitorTeclado.getInputInteger();
				LeitorTeclado.clearInput();
				
				TabParticipantes novoParticipante = new TabParticipantes();
				
				
				System.out.print("Nome: ");
				novoParticipante.setNomeParticipante(LeitorTeclado.getInputLine());
				
				System.out.print("CPF: ");
				novoParticipante.setCpf(LeitorTeclado.getInputLine());
				
				System.out.print("Email: ");
				novoParticipante.setEmail(LeitorTeclado.getInputLine());
				
				Eventos.comprarIngresso(codEvento, novoParticipante);
				
				break;
				
			case 4:
				System.out.println("Opcão em desemvolvimento");
				break;
				
			default:
				if (opcao == 0) {
					System.out.println("\nATÉ LOGO!!");
					LeitorTeclado.closeInput();
					DbConexao.closeConexao();
					
				} else {
					System.out.println("\nOpcao invalida!! ");
					}
			}
				
			} while (opcao != 0);
		
		
		
		
// ------------------------------------------------------------------------------------------------------------------------		
			
//			System.out.println(
//			"\n* ============ SYMPLES INGRESSOS ONLINE =========== *\n\n"
//			+	"[1] - Sou Participante   \n"
//			+	"[2] - Sou Produtor de eventos  \n"
//			+   "[0] - Sair\n");
//	
//			System.out.print("Queremos saber: ");
//			opcao = LeitorTeclado.getInputInteger();
//			LeitorTeclado.clearInput();
//			
//			
//			if (opcao == 1) {
//				
//				Integer opcaoParticipante;
//				
//					System.out.println(
//							"\n* ============ MENU =========== *\n\n"
//				+	"[1] - Exibir Eventos     \n"
//				+   "[2] - Comprar Ingresso   \n"
//				+   "[3] - Imprimir Ingresso  \n"
//				+ 	"[0] - Sair\n");
//				
//				opcaoParticipante = LeitorTeclado.getInputInteger();
//				LeitorTeclado.clearInput();
//				
//				switch (opcaoParticipante) {
//				
//				case 1:
////					Eventos.exibirEventos();
////					
////					System.out.print("Comprar [sim]/[não]: ");
////					String resposta = LeitorTeclado.getInputLine().toUpperCase().replace(" ", "");
////					
////					if (resposta.equals("SIM") == true) {
////						Eventos.comprarIngresso();
////					}
//					System.out.println("---- MENU participante case 1 ----- ");
//					break;
//					
//				case 2:
////					Eventos.exibirEventos();
////					Eventos.comprarIngresso();
//					System.out.println("---- MENU participante case 2 ----- ");
//					break;
//					
//				case 3:
//					System.out.println("--> Opção ainda não implementada! <-- ");
//					break;
//				}
//				
//			}
//			
//			
//			else if (opcao == 2) {
//				
//				Integer opcaoProdutor;
//				
//				do {
//				
//					System.out.println(
//							"\n* ============ MENU =========== *\n\n"
//					+	"[1] - Cadastrar Evento   \n"
//					+	"[2] - Imprimir Ingresso   \n"
//					+	"[3] - Menu principal   \n"
//					+   "[0] - Sair\n");
//					
//					opcaoProdutor = LeitorTeclado.getInputInteger();
//					LeitorTeclado.clearInput();
//					
//					switch (opcaoProdutor) {
//					
//					case 1:
//						System.out.println("---- MENU produtor case 1 ----- ");
//						break;
//					case 2:
//						System.out.println("---- MENU produtor case 2 ----- ");
//						break;
//					}
//					
//				} while(opcaoProdutor != 3);
//				
//			}
//
//					
//		} while (opcao != 0);
		
// ----------------------------------------------- TESTANDO OS METODOS -----------------------------------------------------------------------------------		
	
//		------------------------------------------ PARTICIPANTES ---------------------------------------------------
			
//		ParticipantesDao novoParticipante = DaoFactory.createParticipantes();

	
//		TabParticipantes participante = novoParticipante.findById(3);
//	
//		System.out.println(participante);
		
//		List<TabParticipantes> listParticipante = novoParticipante.findAll();
//	
//		for (TabParticipantes participante : listParticipante) {
//			System.out.println(participante);
//		}
		
		

//		
//		
//		
//		Eventos evento = new Eventos();
//		
//		evento =  novoEvento.findById(1);
//		
//		System.out.println(evento);
//		
//		Participantes participante = new Participantes(1, "Elon Musk", "00000000001", "bilionario.gmail.com", evento);
//		
//		novoParticipante.update(participante);
		
		

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