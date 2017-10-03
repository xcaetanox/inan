package br.com.andrecaetano.repositorys;

import org.springframework.data.rest.core.config.Projection;

import br.com.andrecaetano.models.Cliente;
import br.com.andrecaetano.models.Produto;
import br.com.andrecaetano.models.ProdutoCliente;

@Projection(name = "produtocliente", types = { ProdutoCliente.class })
public interface ProdutoClienteProjection {

	Long getId();
	Cliente getCliente();
    Produto getProduto();
    double getSaldo();
    
    void setCliente();
    void setProduto();

}