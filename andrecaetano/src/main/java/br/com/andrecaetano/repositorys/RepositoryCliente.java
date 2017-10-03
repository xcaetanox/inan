package br.com.andrecaetano.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.andrecaetano.controllers.MovimentacaoController;
import br.com.andrecaetano.models.Cliente;
import br.com.andrecaetano.models.MovimentoProdutoCliente;
import br.com.andrecaetano.models.Produto;

@RepositoryRestResource(excerptProjection = ClienteProjection.class, path = "cliente")
public interface RepositoryCliente extends CrudRepository<Cliente, Long> {

	List<Cliente> findByNome(@Param("nome") String nome);
	
	Cliente findById(@Param("id") long id);

	
	
}
