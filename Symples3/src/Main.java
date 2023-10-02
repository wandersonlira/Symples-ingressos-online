import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.symples.CepUtils;
import br.com.symples.Ingressos;
import br.com.symples.LeitorTeclado;
import br.com.symples.modelo.entidades.TabEventos;
import br.com.symples.modelo.entidades.TabParticipantes;
import br.com.symples.producao.Eventos;
import br.com.symples.service.DbConexao;

public class Main {
	

	public static void main(String[] args) throws ParseException {
		
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
//			+   "[0] - Sair\n"
//			);
//			
//			System.out.print("Escolha uma Opção: ");
//			opcao = LeitorTeclado.getInputInteger();
//			LeitorTeclado.clearInput();
//			
//			switch (opcao) {
//			
//			case 1:
//				
//				TabEventos novoEvento = new TabEventos();
//				Date data = new Date();
//				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
//				String[] novoEndereco = new String[3];
//				
//				System.out.print("Nome Evento: ");
//				novoEvento.setNomeEvento(LeitorTeclado.getInputLine());
//				
//				try {
//					System.out.print("Data Evento: ");
//					data = formatoData.parse(LeitorTeclado.getInputLine());
//					novoEvento.setDataEvento(data);
//				} catch (ParseException e) {
//					data = formatoData.parse(LeitorTeclado.getInputLine().replace("-", "/"));
//					novoEvento.setDataEvento(data);
////					e.printStackTrace();
//				}
//				
//				
//				System.out.print("Hora Evento: ");
//				novoEvento.setHoraEvento(LeitorTeclado.getInputLine());
//				
//				System.out.print("Qtd Ingressos: ");
//				novoEvento.setIngressos(LeitorTeclado.getInputInteger());
//				
//				System.out.print("Categoria: ");
//				LeitorTeclado.clearInput();
//				novoEvento.setCategoria(LeitorTeclado.getInputLine());
//				
////				---------------- Captura Endereco ---------------
//				
//				System.out.print("Local: ");
//				novoEndereco[0] =  LeitorTeclado.getInputLine();
//				
//				System.out.print("numeroLocal: ");
//				novoEndereco[1] = LeitorTeclado.getInputLine();
//
//				System.out.print("CEP: ");
//				novoEndereco[2] = LeitorTeclado.getInputLine();
//				novoEndereco[2] = CepUtils.removeMascaraCep(novoEndereco[2]); // Remove o travessão que divide os três últimos digitos do CEP
//				CepUtils.validaCep(novoEndereco[2]); // Verifica se está dentro do formato e com 8 digitos
//				
//				Eventos.criarEvento(novoEvento, novoEndereco);
//				
//				break;
//				
//			case 2:
//				Eventos.exibirEventos();
//				
//				System.out.print("Comprar [sim]/[não]: ");
//				String resposta = LeitorTeclado.getInputLine().toUpperCase().replace(" ", "");
//				
//				if (resposta.equals("SIM") == true) {
//					
//					System.out.println("Digite codEvento: ");
//					Integer codEvento = LeitorTeclado.getInputInteger();
//					LeitorTeclado.clearInput();
//					
//					TabParticipantes novoParticipante = new TabParticipantes();
//					
//					
//					System.out.print("Nome: ");
//					novoParticipante.setNomeParticipante(LeitorTeclado.getInputLine());
//					
//					System.out.print("CPF: ");
//					novoParticipante.setCpf(LeitorTeclado.getInputLine());
//					
//					System.out.print("Email: ");
//					novoParticipante.setEmail(LeitorTeclado.getInputLine());
//					
//					Eventos.comprarIngresso(codEvento, novoParticipante);
//				}
//				
//				break;
//				
//			case 3:
//				Eventos.exibirEventos();
//				
//				System.out.println("Digite codEvento: ");
//				Integer codEvento = LeitorTeclado.getInputInteger();
//				LeitorTeclado.clearInput();
//				
//				TabParticipantes novoParticipante = new TabParticipantes();
//				
//				
//				System.out.print("Nome: ");
//				novoParticipante.setNomeParticipante(LeitorTeclado.getInputLine());
//				
//				System.out.print("CPF: ");
//				novoParticipante.setCpf(LeitorTeclado.getInputLine());
//				
//				System.out.print("Email: ");
//				novoParticipante.setEmail(LeitorTeclado.getInputLine());
//				
//				Eventos.comprarIngresso(codEvento, novoParticipante);
//				
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
			
			Integer opcao;
			
			do {
			
			System.out.println(
			"\n* ============ SYMPLES INGRESSOS ONLINE =========== *\n\n"
			+	"[1] - Sou Participante   \n"
			+	"[2] - Sou Produtor de eventos  \n"
			+   "[0] - Sair\n");
	
			System.out.print("Queremos saber: ");
			opcao = LeitorTeclado.getInputInteger();
			LeitorTeclado.clearInput();
			
			
			if (opcao == 1) {
				
				Integer opcaoParticipante;
				
				do {
					
					System.out.println(
							"\n* ============ MENU PARTICIPANTE =========== *\n\n"
				+	"[1] - Exibir Eventos     \n"
				+   "[2] - Comprar Ingresso   \n"
				+   "[3] - Exibir Ingresso  \n"
				+ 	"[0] - Sair\n");
				
				System.out.print("Olá Participante!\nDigite sua opão: ");
				opcaoParticipante = LeitorTeclado.getInputInteger();
				LeitorTeclado.clearInput();
				
					switch (opcaoParticipante) {
				
						case 1:
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
							
						case 2:
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
							
						case 3:
							System.out.print("Qual o CPF: ");
							String cpf = LeitorTeclado.getInputLine();
							Ingressos ingresso = new Ingressos();
							ingresso.buscaParticipanteEvento(cpf);
							break;
							
						default:
							if (opcaoParticipante == 0) {
								System.out.println("\nATÉ LOGO!!");
							} else {
								System.out.println("\n----> Opção Inválida! <----");
								}
					}
					
				} while( opcaoParticipante != 0);
				
			}
			
			
			else if (opcao == 2) {
				
				Integer opcaoProdutor;
				
				do {
				
					System.out.println(
							"\n* ============ MENU PRODUTOR =========== *\n\n"
					+	"[1] - Cadastrar Evento   \n"
					+	"[2] - Exibir Ingressos por Evento   \n"
					+	"[3] - Menu principal   \n"
					+   "[0] - Sair\n");
					
					System.out.print("Olá Produtor!\nDigite sua opão: ");
					opcaoProdutor = LeitorTeclado.getInputInteger();
					LeitorTeclado.clearInput();
					
					switch (opcaoProdutor) {
					
						case 1:
							TabEventos novoEvento = new TabEventos();
							Date data = new Date();
							SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
							String[] novoEndereco = new String[3];
							
							System.out.print("Nome Evento: ");
							novoEvento.setNomeEvento(LeitorTeclado.getInputLine());
							
							try {
								System.out.print("Data Evento: ");
								data = formatoData.parse(LeitorTeclado.getInputLine());
								novoEvento.setDataEvento(data);
							} catch (ParseException e) {
								data = formatoData.parse(LeitorTeclado.getInputLine().replace("-", "/"));
								novoEvento.setDataEvento(data);
//								e.printStackTrace();
							}
							
							
							System.out.print("Hora Evento: ");
							novoEvento.setHoraEvento(LeitorTeclado.getInputLine());
							
							System.out.print("Qtd Ingressos: ");
							novoEvento.setIngressos(LeitorTeclado.getInputInteger());
							
							System.out.print("Categoria: ");
							LeitorTeclado.clearInput();
							novoEvento.setCategoria(LeitorTeclado.getInputLine());
							
//							---------------- Captura Endereco ---------------
							
							System.out.print("Local: ");
							novoEndereco[0] =  LeitorTeclado.getInputLine();
							
							System.out.print("numeroLocal: ");
							novoEndereco[1] = LeitorTeclado.getInputLine();
			
							System.out.print("CEP: ");
							novoEndereco[2] = LeitorTeclado.getInputLine();
							novoEndereco[2] = CepUtils.removeMascaraCep(novoEndereco[2]); // Remove o travessão que divide os três últimos digitos do CEP
							CepUtils.validaCep(novoEndereco[2]); // Verifica se está dentro do formato e com 8 digitos
							
							Eventos.criarEvento(novoEvento, novoEndereco);
							break;
							
						case 2:
							System.out.println("Digite o ID do Evento: ");
							Integer idBusca = LeitorTeclado.getInputInteger();
							Ingressos ingresso = new Ingressos();
							ingresso.buscaEventoParticipante(idBusca);
							break;
							
						case 3:
							System.out.println("---- MENU produtor case 3 ----- ");
							break;
							
						default:
							if (opcaoProdutor == 0) {
									System.out.println("\nATÉ LOGO!!");
							} else {
								System.out.println("\n----> Opção Inválida! <----");
								}
					}
					
				} while(opcaoProdutor != 0);
				
			}
			
			
			else if (opcao == 0) {
				System.out.println("\nATÉ LOGO!!");
				LeitorTeclado.closeInput();
				DbConexao.closeConexao();
			}
			
			else {
				System.out.println("\n ----> Opção Inválida! <----");
			}

					
		} while (opcao != 0);	

		
		
	}
	
}