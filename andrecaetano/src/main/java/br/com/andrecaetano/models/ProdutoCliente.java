package br.com.andrecaetano.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
@Entity
public class ProdutoCliente extends AbstractModel<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5793254617479148297L;
	public ProdutoCliente(){
		
	}
	
	public ProdutoCliente(Cliente cliente,Produto produto, double saldo){
		this.cliente = cliente;
		this.produto =  produto;
		this.saldo   =  saldo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@OneToOne
	private Cliente cliente;
	@OneToOne
	private Produto produto;
	private double saldo;
	
}
