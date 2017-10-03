package br.com.andrecaetano.repositorys;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import br.com.andrecaetano.models.Cliente;
import br.com.andrecaetano.models.MovimentoProdutoCliente;
import br.com.andrecaetano.models.Produto;

@Projection(name = "movimento", types = { MovimentoProdutoCliente.class })
public interface MovimentoProjection {

	Long getId();
	double getQtde();
	int getOperacao();
	Date getDataMovimento();
	Cliente getCliente();
    Produto getProduto();
    String  getPedido();
    

}