//import java.time.Instant;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import modelo.Ingressos;
import producao.Eventos;
import service.ViacepService;

public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		String nome = "FIG - Festival de Inverno de Garanhuns";

		String cepCadastro = "52211058";

		LocalDateTime localTime = LocalDateTime.parse("2023-07-21T18:45");
		int ingressos1 = 11;
				
		ViacepService viaCep = new ViacepService();
		
		try {
			
			Eventos evento = new Eventos(nome, localTime, ingressos1, viaCep.getEndereco(cepCadastro));
			
			
////			eventos.setEnderecoEvento();
//			System.out.println("Nome: " + eventos.getNomeEvento());
//			System.out.println("Local Time: " + eventos.converteDateTime(localTime));
//			System.out.println("Ingre: " + eventos.getQuantidadeIngresso());
//			System.out.println("");
//			System.out.println("Rua: " + eventos.getEnderecoEvento().getLogradouro());
//			System.out.println("Bairro: " + eventos.getEnderecoEvento().getBairro());
//			System.out.println("Localidade:  " + eventos.getEnderecoEvento().getLocalidade());
//			System.out.println("UF: " + eventos.getEnderecoEvento().getUf());
//			System.out.println("CEP " + eventos.getEnderecoEvento().getCep());
	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		
	}

}
