package br.cefetrj.arq;

import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Hash {
	public static void criaHash(RandomAccessFile f, long n) throws Exception{
		Elemento h = new Elemento();
		h.setCep(-1);
		h.setEndereco(-1);
		h.setProximo(-1);
		for(int i = 0; i < n; i++){
			h.escreveCep(f);				
		}
	}
	
	public static void criaIndice(RandomAccessFile f, RandomAccessFile r, long n) throws Exception{
		long i = 0;
		long p = 0;
		Elemento h = new Elemento();
		Endereco e = new Endereco();
		while(f.getFilePointer() < f.length()){
			e.leEndereco(f);
			p = Long.parseLong(e.getCep()) % n;
			r.seek(p*24);
			h.leCep(r);
			if(h.getCep() == -1){
				h.setCep(Long.parseLong(e.getCep()));
				h.setEndereco(i);
				h.setProximo(-1);
				r.seek(p*24);
				h.escreveCep(r);				
			}else{
				long prox = h.getProximo();
				h.setProximo(r.length());
				r.seek(p*24);
				h.escreveCep(r);
				r.seek(r.length());
				h.setCep(Long.parseLong(e.getCep()));
				h.setEndereco(i);
				h.setProximo(prox);
				h.escreveCep(r);				
			}i++;		
		}
	}
	
	public static void buscaHash(RandomAccessFile r, RandomAccessFile f, long cep, long n) throws Exception{
		Elemento h = new Elemento();
		long p = cep % n;
		r.seek(p*24);
		h.leCep(r);
		while(h.getCep() != cep && h.getProximo() != -1){
			r.seek(h.getProximo());
			h.leCep(r);
		}
		if(h.getCep() == cep){
			f.seek(h.getEndereco()*300);
			Endereco e = new Endereco();
			e.leEndereco(f);
			System.out.println(e.getLogradouro());
			System.out.println(e.getBairro());
			System.out.println(e.getCidade());
			System.out.println(e.getEstado());
			System.out.println(e.getSigla());
			System.out.println(e.getCep());			
		}else{
			System.out.println("Cep não encontrado!");
		}
	}
	
	public static void estatisticasHash(RandomAccessFile r, long n) throws Exception{
		System.out.println("-----------------------------------------------------");
		Endereco e = new Endereco();
		ArrayList<Integer> hash = new ArrayList<>();
		for(int i = 0; i < n; i++){
			hash.add(0);
		}
		
		int cep = 0;
		int fHash = 0 ;
		
		while(r.getFilePointer() < r.length()){
			e.leEndereco(r);
			cep = Integer.parseInt(e.getCep());
			fHash = (int) (cep % n);
			Integer novo = hash.get(fHash) + 1;
			hash.set(fHash, novo);			
		}
		
		int colisoes = 0;
		
		for(int i = 0; i < hash.size();i++){
			int proc  = hash.get(i);
			if(proc>1){
				int aux = proc -1;
				colisoes+=aux;
			}
		}
		
		double x = 0;
		int max = Collections.max(hash);
		
		for(int i = 0; i <= max; i++){
			int freq = Collections.frequency(hash, i);
			x += (i * freq);
			System.out.println("Existem "+ freq + " casas com "+ i +" elementos!");
		}
		System.out.println("-----------------------------------------------------");
		System.out.println("O número total de colisões é de: " + colisoes);
		double buscas = 0;
		int total = 0;
		double somatorio = 0;
		int fator = 0;
		DecimalFormat df = new DecimalFormat("#0.0000000000");
		System.out.println("-----------------------------------------------------");
		
		for(int i = 1; i <= max; i++){
			for(int j = i; j <= max; j++){
				int casos = Collections.frequency(hash, j);
				buscas+=casos;
			}
			double prob = (buscas/x);
			total += buscas;
			fator+=i;
			somatorio += Collections.frequency(hash, i) * fator;
			System.out.println("A probabilidade de achar o cep com "+ i + " passos e de : "+  df.format(prob));
			buscas = 0;
		}
		System.out.println("-----------------------------------------------------");
		double media = somatorio/x;		
		System.out.println("A média de passos para se achar um cep é de :" + df.format(media) );
	}	
}
