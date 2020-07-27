package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entidades.Funcionario;

public class Programa {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite o caminho completo do arquivo: ");
		String pacote = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(pacote))){
			
			List<Funcionario> lista = new ArrayList<>();
			
			String linha = br.readLine();
			while(linha!=null) {
				String[] campo = linha.split(",");
				lista.add(new Funcionario(campo[0], campo[1], Double.parseDouble(campo[2])));
				linha = br.readLine();
			}
			System.out.print("Digite o salário: ");
			Double salario = sc.nextDouble();
			
			List<String> emails = lista.stream().filter(x -> x.getSalario() > salario).map(x -> x.getEmail()).sorted().collect(Collectors.toList());
		
			System.out.println("E-mail de pessoas cujo salário é superior a " + String.format("%.2f", salario));
			emails.forEach(System.out::println);
			
			double soma = lista.stream().filter(x -> x.getNome().charAt(0) == 'M').map(x -> x.getSalario()).reduce(0.0, (x,y) -> x+ y);
			
			System.out.println("Soma do salário de pessoas cujo nome começa com 'M': " + String.format("%.2f", soma));
		
		}catch(IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		sc.close();

	}

}
