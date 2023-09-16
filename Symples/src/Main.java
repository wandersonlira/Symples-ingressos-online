import br.com.symples.LeitorTeclado;
import br.com.symples.producao.Eventos;
import br.com.symples.service.DbConexao;

public class Main {
	

	public static void main(String[] args) {
		
		Integer opcao;
//		
		do {
//			
			System.out.println(
					"\n* ============ MENU =========== *\n\n"
			+	"[1] - Cadastrar Evento   \n"
			+	"[2] - Exibir Eventos     \n"
			+   "[3] - Comprar Ingresso   \n"
			+   "[4] - Imprimir Ingresso  \n"
			+   "[0] - Sair\n");
			
			System.out.print("Escolha uma Opção: ");
			opcao = LeitorTeclado.getInputInteger();
			LeitorTeclado.clearInput();
			
			switch (opcao) {
			
			case 1:				
				Eventos.criarEvento();			
				break;
				
			case 2:
				Eventos.exibirEventos();
				
				System.out.print("Comprar [sim]/[não]: ");
				String resposta = LeitorTeclado.getInputLine().toUpperCase().replace(" ", "");
				
				if (resposta.equals("SIM") == true) {
					Eventos.comprarIngresso();
				}
				break;
				
			case 3:
				Eventos.exibirEventos();
				Eventos.comprarIngresso();
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
		
		
	}
	
}