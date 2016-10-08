package br.cefetrj.arq;

import java.io.RandomAccessFile;
import java.util.Scanner;

public class Principal {
	public static void main(String[] args) throws Exception{
		Scanner s1 = new Scanner(System.in);
		System.out.println("Digite 1 para criar o indice Hash de um arquivo");
		System.out.println("Digite 2 para procurar um cep");
		System.out.println("Digite 3 para para ver as estaticas simuladas para o hash");
		int i = s1.nextInt();
		int conf = 0;
		switch (i)
			{
			case 1:
				System.out.println("Entre com o arquivo cep");
				Scanner s2 = new Scanner(System.in);
				String arq = s2.nextLine();
				System.out.println("Entre com o nome do arquivo que sera criado");
				Scanner s3 = new Scanner(System.in);
				String hash = s3.nextLine();
				RandomAccessFile r = new RandomAccessFile(arq,"r");
				RandomAccessFile f = new RandomAccessFile(hash,"rw");
				System.out.println("A função de Hash usada é cep % n, entre com o n desejado");
				Scanner s4 = new Scanner(System.in);
				long n = s4.nextLong();
				System.out.println("Criando...");
				Hash.criaHash(f, n);
				Hash.criaIndice(r, f, n);
				System.out.println("Criado com sucesso!");
				break;				
			
			case 2:
				System.out.println("Entre com o arquivo cep");
				Scanner s5 = new Scanner(System.in);
				String arq1 = s5.nextLine();
				System.out.println("Entre com o indice hash");
				Scanner s6 = new Scanner(System.in);
				String hash1 = s6.nextLine();
				System.out.println("A função de Hash usada é cep % n, entre com o n desejado");
				Scanner s9 = new Scanner(System.in);
				long n1 = s9.nextLong();
				RandomAccessFile r1 = new RandomAccessFile(arq1,"r");
				RandomAccessFile f1 = new RandomAccessFile(hash1,"rw");
				System.out.println("Entre com o cep desejado");
				Scanner s7 = new Scanner(System.in);
				long cep = s7.nextLong();
				Hash.buscaHash(f1, r1, cep,n1);
				System.out.println("Se você desejar procurar outro cep nesse mesmo aqrquivo digite 1, se não, digite 2");
				Scanner s8 = new Scanner(System.in);
				conf = s8.nextInt();
				while(conf == 1){
					System.out.println("Entre com o cep desejado");
					s7 = new Scanner(System.in);
					cep = s7.nextLong();
					Hash.buscaHash(f1, r1, cep,n1);
					System.out.println("Se você desejar procurar outro cep nesse mesmo aqrquivo digite 1, se não, digite 2");
					s8 = new Scanner(System.in);
					conf = s8.nextInt();
				}
				break;	
				
			case 3:
				System.out.println("Entre com o arquivo cep");
				Scanner s10 = new Scanner(System.in);
				String arq2 = s10.nextLine();
				RandomAccessFile r2 = new RandomAccessFile(arq2,"r");
				System.out.println("A função de Hash usada é cep % n, entre com o n desejado");
				Scanner s11 = new Scanner(System.in);
				long n2 = s11.nextLong();
				Hash.estatisticasHash(r2, n2);
		}		
	}
}
