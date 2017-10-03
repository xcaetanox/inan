package br.com.andrecaetano.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity

public class MovimentoProdutoCliente  extends AbstractModel<Long>{

	public MovimentoProdutoCliente(){
		
	}
	
	public MovimentoProdutoCliente(Produto produto, Cliente cliente, double qtde, int operacao, Date dataMovimento){
		
		this.produto = produto;
		this.cliente = cliente;
		this.qtde =  qtde;
		this.operacao = operacao;
		this.dataMovimento = dataMovimento;
	}
	

	@OneToOne
	private Produto produto;
	
	@OneToOne
	private Cliente cliente;
	
	private double  qtde;
		
	private int operacao;
	
	private Date dataMovimento;
	
	private String pedido;
	
	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public Date getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public double getQtde() {
		return qtde;
	}
	public void setQtde(double qtde) {
		this.qtde = qtde;
	}
	public int getOperacao() {
		return operacao;
	}
	public void setOperacao(int operacao) {
		this.operacao = operacao;
	}
	
}
