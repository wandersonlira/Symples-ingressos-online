package controller.producao;

import java.util.Objects;

import controller.exception.ViaCepException;
import controller.exception.ViaCepFormatException;


public class CepUtils {
	
	public static void validaCep(String cep) {
		
		if (Objects.isNull(cep) || cep.isEmpty() || cep.isBlank()) {
			 throw new ViaCepException("O cep informado não pode ser nulo ou vazio");
		}
		
		else if (cep.length() > 8) {
			throw new ViaCepFormatException("CEP fora do formato ou números a mais");
		}
		
		else if (cep.length() < 8) {
			 throw new ViaCepException("CEP faltando numeros");
			
		}

	}
	
	
	public static String removeMascaraCep(String cep) {
		
        try {
            validaCep(cep);
            return cep;
        } catch (ViaCepFormatException e){
        	return cep.replace("-", "");
            
        }
	}
	
	
    public static String mascararCep(String cep){
        try {
            validaCep(cep);
            return cep.substring(0, 5) + "-" + cep.substring(5);
        } catch (ViaCepFormatException e){
            throw new ViaCepException("Cep ja formatado ou fora do padrao");
        }
    }

}