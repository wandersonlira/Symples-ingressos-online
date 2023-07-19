//import java.time.Instant;
import java.io.IOException;
import java.time.LocalDateTime;

import producao.Eventos;
import service.ViacepService;

public class Main {

	public static void main(String[] args) {
		
		
		String nome = "FIG - Festival de Inverno de Garanhuns";

		String cepCadastro = "52211058";

		LocalDateTime localTime = LocalDateTime.parse("2023-07-21T18:45");
		int ingressos1 = 0;
				
		ViacepService viaCep = new ViacepService();
		
//		------------------------ EVENTO 2 ---------------------------------
		
		String nome2 = "CINEPORTO";

		String cepCadastro2 = "01001000";

		LocalDateTime localTime2 = LocalDateTime.parse("2023-07-21T18:45");
		int ingressos2 = 30;
				
		ViacepService viaCep2 = new ViacepService();
		
		
		try {
			
			Eventos evento = new Eventos(nome, localTime, ingressos1, viaCep.getEndereco(cepCadastro));
			Eventos evento2 = new Eventos(nome2, localTime2, ingressos2, viaCep2.getEndereco(cepCadastro2));

			System.out.println(evento.comprarIngresso());
//			System.out.println("-----------");
//			evento2.comprarIngresso();
			System.out.println("\n* ------ Relatório de Ingressos ------ *\n");
			evento.exibirIngresso();
//			System.out.println("");
//			evento2.exibirIngresso();
//		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
