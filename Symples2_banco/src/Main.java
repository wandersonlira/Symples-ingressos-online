import java.util.Scanner;

import br.com.symples.producao.Eventos;
import br.com.symples.modelo.LeitorTeclado;

public class Main {

	public static void main(String[] args) {
		
		Integer opcao;
		
		do {
			
			System.out.println(
					"\n* ============ MENU =========== *\n\n"
			+	"[1] - Cadastrar Evento   \n"
			+   "[2] - Comprar Ingresso   \n"
			+   "[3] - Imprimir Ingresso  \n"
			+   "[0] - Sair\n");
			
			System.out.print("Escolha uma Opção: ");
			opcao = LeitorTeclado.getInputInteger();
			LeitorTeclado.clearInput();
			
			switch (opcao) {
			
			case 1:
				Eventos evento = new Eventos();
				evento.criarEvento();
//				
				break;
				
			case 2:
				Eventos evento2 = new Eventos();
				evento2.comprarIngresso();
				break;
				
			case 3:
				Eventos evento3 = new Eventos();
				evento3.exibirIngresso();
				break;
				
			default:
				if (opcao == 0) {
					System.out.println("\nATÉ LOGO!!");
					LeitorTeclado.closeInput();					
					
				} else {
					System.out.println("\nOpcao invalida!! ");
				}
			}
					
		} while (opcao != 0);
		
	}
	
}