package br.com.andrecaetano.models;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Cliente extends AbstractModel<Long> {

	public Cliente() {

	}

	public Cliente(String nome, List<ProdutoCliente> listaProdutoCliente) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private String nome;

}
