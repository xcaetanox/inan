package br.com.andrecaetano.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.andrecaetano.models.Cliente;
import br.com.andrecaetano.models.MovimentoProdutoCliente;
import br.com.andrecaetano.models.Produto;

@RepositoryRestResource(excerptProjection = MovimentoProjection.class, path = "movimento")
public interface RepositoryMovimentoProdutoCliente extends CrudRepository<MovimentoProdutoCliente, Long> {

	Cliente findByCliente(@Param("Cliente") Cliente cliente);
	MovimentoProdutoCliente findById(@Param("id") long id);
	
}
