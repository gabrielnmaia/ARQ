# ARQ_Hash

Trabalho para a matéria Organização de Estrutura de Arquivos do Bacharelado em Ciência da Computação no CEFET/RJ, ministrada pelo professor Renato Mauro.

##O Trabalho

* O trabalho consiste na criação de um índice, gerado a partir de uma função hash, para o a arquivo cep.dat, cedido pelo professor Renato. 
* Após a criação do índice devemos efetuar buscas de ceps nele e exibirmos os dados completos daquele cep, oriundos do arquivo original.
* O programa também exibe informações sobre o hash, tal como: 
    * Número de colisões
    * Média de passos para achar um cep
    * Probabilidades

##Código

* O Programa é divido em 4 arquivos, sendo 2 deles para auxílio na leitura e escrita dos arquivos.

  * O arquivo Endereco.java, cedido pelo professor Renato, é utilizado na leitura do arquivo binário cep.dat.

  * O arquivo Elemento.java é utilizado na leitura e na escrita do arquivo indice.
  
  
* O arquivo Hash.cep contém os métodos da Classe Hash para a criação do indice baseada em uma função hash.
  * O método abaixo cria um arquivo apartir da Classe Elemento, que tem como parametros: cep, endereco e proximo. O endereco é a posição do cep no arquivo cep.dat e o proximo e um ponteiro para o proximo Elemento, caso haja colisão.
  
	```java
	  public static void criaHash(RandomAccessFile f, long n) throws Exception{
			Elemento h = new Elemento();
			h.setCep(-1);
			h.setEndereco(-1);
			h.setProximo(-1);
			for(int i = 0; i < n; i++){
				h.escreveCep(f);				
			}
		}
	```
 * Outro método presente na Classe Hash cria o índice, adicionando os ceps ao novo arquivo.
    ```java
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
    ```
   
   	 Com isso percorremos o arquivo cep.dat lendo os ceps, atraves da Classe Endereco. Depois de ler o cep é aplicado a função hash,  a qual é dada pelo resto da divisão do cep por 900001(valor fornecido pelo professor Renato). Movemos a cabeça de leitura para a posição corresposdente ao resultado da função, no arquivo índice e lemos o que há nessa posição.
 	```java
		if(h.getCep() == -1){
				h.setCep(Long.parseLong(e.getCep()));
				h.setEndereco(i);
				h.setProximo(-1);
				r.seek(p*24);
				h.escreveCep(r);				
			}
	```
	Dentro do While há um if, que se nessa posição do arquivo índice o cep for igual a -1 nós setamos os atributos de um objeto Elemento. Cep, como o cep oriundo do arquivo cep.dat. A posição damos o número do registro daquele cep no aquivo original, pois há uma leitura sequencial do arquivo. Enquanto lemos guardamos a posição em uma variável, que incrementa uma unidade ao final de cada passagem pelo While. Retornamos a cabeça de leitura a posição inical do registro, pois ao lermos um registro a cabeça de leitura estará no registro seguinte, e escrevemos no arquivo com o auxílio da Classe Elemento.
	```java
		else{
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
	```
	Se a condição do if não for satisfeita é porque há um registro escrito nessa posição, portanto temos uma colisão. Com isso começamos o tratamento dessa colisão criando uma variável prox, que armazena o campo proximo escrito nesse regisrto do arquivo. Alteramos apenas o atributo proximo do registro lido como o final do arquivo e o reescrevemos. Depois disso movemos a cabeça de leitura para o final do arquivo e escrevemos o registro do aqruivo original. Após isso como já dito a cima há o incremento da variável de controle de registros e o loop continua até chegar ao fim do arquivo cep.dat.
