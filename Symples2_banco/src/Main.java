import java.util.Scanner;

import br.com.symples.producao.Eventos;

public class Main {

	public static void main(String[] args) {
		
		int opcao;
		
		Scanner input = new Scanner(System.in);
		
		do {
			System.out.println(
					"\n* ============ MENU =========== *\n\n"
			+	"[1] - Cadastrar Evento   \n"
			+   "[2] - Comprar Ingresso   \n"
			+   "[3] - Imprimir Ingresso  \n"
			+   "[0] - Sair\n");
			
			System.out.print("Escolha uma Opção: ");
			opcao = input.nextInt();
			
			switch (opcao) {
			
			case 1:
				Eventos evento = new Eventos();
				evento.criarEvento();
				break;
				
			case 2:
				Eventos evento2 = new Eventos();
				evento2.comprarIngresso();
				break;
				
			case 3:
				System.out.println("Escolheu  Opcao: " + opcao);
				break;
				
			default:
				if (opcao == 0) {
					System.out.println("\nATÉ LOGO!!");

					
				} else {
					System.out.println("\nOpcao invalida!! ");
				}
			}
					
		} while (opcao != 0);

		
	}
	
}