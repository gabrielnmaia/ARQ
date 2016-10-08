package br.cefetrj.arq;

import java.io.DataInput;
import java.io.DataOutput;

public class Elemento {
	private long cep;
	private long endereco;
	private long proximo;
	
	public long getCep() {
		return cep;
	}
	public void setCep(long cep) {
		this.cep = cep;
	}
	public long getEndereco() {
		return endereco;
	}
	public void setEndereco(long endereco) {
		this.endereco = endereco;
	}
	public long getProximo() {
		return proximo;
	}
	public void setProximo(long proximo) {
		this.proximo = proximo;
	}
	
	public void escreveCep(DataOutput dout) throws Exception{
		dout.writeLong(this.cep);
		dout.writeLong(this.endereco);
		dout.writeLong(this.proximo);
	}
	
	public void leCep(DataInput din) throws Exception{
		this.cep = din.readLong();
        this.endereco = din.readLong();
        this.proximo = din.readLong();
	}
}
