package modelo;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Cadastro {
	
	private String nomePessoa;
	private String cpfPessoa;
	private String email;
	private LocalDateTime timeLocalIngresso;

	
	public void cadastrar() {
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.print("Nome: ");
		setNomePessoa(nomePessoa = input.nextLine());
		System.out.print("CPF: ");
		setCpfPessoa(cpfPessoa = input.nextLine());
		System.out.print("E-mail: ");
		setEmail(email = input.nextLine());
		this.timeLocalIngresso = LocalDateTime.now();
	}
	
	
	public String getNomePessoa() {
		return nomePessoa;
	}
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
	public String getCpfPessoa() {
		return cpfPessoa;
	}
	public void setCpfPessoa(String cpfPessoa) {
		this.cpfPessoa = cpfPessoa;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getTimeLocalIngresso() {
		return timeLocalIngresso;
	}

	public void setTimeLocalIngresso(LocalDateTime timeLocalIngresso) {
		this.timeLocalIngresso = timeLocalIngresso;
	}

	
}
