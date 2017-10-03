package br.com.andrecaetano.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Produto extends AbstractModel<Long> {

	public Produto() {
	}

	public Produto(String descricao, String tipo) {

		this.descricao = descricao;
		this.tipo = tipo;
		
		
	}


	private String descricao;
	private String tipo;

	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
