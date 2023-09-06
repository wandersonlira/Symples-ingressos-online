import br.com.symples.modelo.dao.DaoFactory;
import br.com.symples.modelo.dao.EventosDao;
import br.com.symples.modelo.dao.ParticipantesDao;
import br.com.symples.modelo.entidades.Eventos;
import br.com.symples.modelo.entidades.Participantes;

public class Main {
	

	public static void main(String[] args) {
		
//		Integer opcao;
//		
//		do {
//			
//			System.out.println(
//					"\n* ============ MENU =========== *\n\n"
//			+	"[1] - Cadastrar Evento   \n"
//			+	"[2] - Exibir Eventos     \n"
//			+   "[3] - Comprar Ingresso   \n"
//			+   "[4] - Imprimir Ingresso  \n"
//			+   "[0] - Sair\n");
//			
//			System.out.print("Escolha uma Opção: ");
//			opcao = LeitorTeclado.getInputInteger();
//			LeitorTeclado.clearInput();
//			
//			switch (opcao) {
//			
//			case 1:				
//				Eventos.criarEvento();			
//				break;
//				
//			case 2:
//				Eventos.exibirEventos();
//				
//				System.out.print("Comprar [sim]/[não]: ");
//				String resposta = LeitorTeclado.getInputLine().toUpperCase().replace(" ", "");
//				
//				if (resposta.equals("SIM") == true) {
//					Eventos.comprarIngresso();
//				}
//				break;
//				
//			case 3:
//				Eventos.exibirEventos();
//				Eventos.comprarIngresso();
//				break;
//				
//			case 4:
//				System.out.println("Opcão em desemvolvimento");
//				break;
//				
//			default:
//				if (opcao == 0) {
//					System.out.println("\nATÉ LOGO!!");
//					LeitorTeclado.closeInput();
//					DbConexao.closeConexao();
//					
//				} else {
//					System.out.println("\nOpcao invalida!! ");
//					}
//			}
//				
//			} while (opcao != 0);
		
		
		
		
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
		
// ----------------------------------------------------------------------------------------------------------------------------------		
		
			
		ParticipantesDao novoParticipante = DaoFactory.createParticipantes();
		EventosDao novoEvento = DaoFactory.createEventos();
		
		
		
		Eventos evento = new Eventos();
		
		evento =  novoEvento.findById(1);
		
		System.out.println(evento);
		
		Participantes participante = new Participantes(1, "Elon Musk", "00000000001", "bilionario.gmail.com", evento);
		
		novoParticipante.update(participante);
		
		
		

		
//		Endereco endere = new Endereco();
//		
//		endere.setIdEndereco(3);
//		
//		Eventos evento = new Eventos();
//		
//		evento.setNomeEvento("Nada tem");
//		evento.setDataEvento(new Date());
//		evento.setHoraEvento(new Time(0));
//		evento.setIngressos(13);
//		evento.setIngressoComprado(0);
//		evento.setCategoria("Excluir");
//		evento.setCodigoEndereco(endere);
//		evento.setIdEvento(1);
//		
//		novoEvento.insert(evento);
		
		
		
		
		
		
		
//		System.out.print("Local: ");
//		String local = LeitorTeclado.getInputLine();
//		
//		
//		System.out.print("numeroLocal: ");
//		String numerolocal = LeitorTeclado.getInputLine();
//		
//		ViacepService novoCep = new ViacepService();
//		
//		try {
//			
//			Endereco endereco = new Endereco();
//			
//			endereco =  novoCep.getEndereco("52211058");
//			endereco.setNomeLocal(local);
//			endereco.setNumLocal(numerolocal);
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