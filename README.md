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
