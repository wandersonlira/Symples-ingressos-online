//import java.time.Instant;
import java.io.IOException;
import java.time.LocalDateTime;

//import modelo.Endereco;
import producao.Eventos;
import service.ViacepService;

public class Main {

	public static void main(String[] args) {
		
		String nome = "FIG - Festival de Inverno de Garanhuns";

		String cepCadastro = "52131542";
		
//		LocalDateTime localTime = LocalDateTime.parse("2023-07-21T18:45");
		LocalDateTime localTime = LocalDateTime.of(2023, 07, 21, 18, 35);
		Integer ingressos = 30;
				
		ViacepService viaCep = new ViacepService();
		
		try {
			
			Eventos eventos = new Eventos(nome, localTime, ingressos, viaCep.getEndereco(cepCadastro));
			
//			eventos.setEnderecoEvento();
			System.out.println("Nome: " + eventos.getNomeEvento());
			System.out.println("Local Time: " + eventos.converteDateTime(localTime));
			System.out.println("Ingre: " + eventos.getQuantidadeIngresso());
			System.out.println("");
			System.out.println("Rua: " + eventos.getEnderecoEvento().getLogradouro());
			System.out.println("Bairro: " + eventos.getEnderecoEvento().getBairro());
			System.out.println("Localidade:  " + eventos.getEnderecoEvento().getLocalidade());
			System.out.println("UF: " + eventos.getEnderecoEvento().getUf());
			System.out.println("CEP " + eventos.getEnderecoEvento().getCep());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
