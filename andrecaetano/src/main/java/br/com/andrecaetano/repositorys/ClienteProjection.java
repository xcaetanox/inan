package br.com.andrecaetano.repositorys;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import br.com.andrecaetano.models.Cliente;
import br.com.andrecaetano.models.MovimentoProdutoCliente;
import br.com.andrecaetano.models.Produto;

@Projection(name = "cliente", types = { Cliente.class })
public interface ClienteProjection {

	Long getId();
	String getNome();
    

}