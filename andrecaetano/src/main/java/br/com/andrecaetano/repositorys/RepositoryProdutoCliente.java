package br.com.andrecaetano.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.andrecaetano.models.Cliente;
import br.com.andrecaetano.models.Produto;
import br.com.andrecaetano.models.ProdutoCliente;

@RepositoryRestResource(excerptProjection = ProdutoClienteProjection.class, path = "produtocliente")
public interface RepositoryProdutoCliente extends CrudRepository<ProdutoCliente, Long> {

	List<ProdutoCliente> findByClienteNome(@Param("cliente") String nome);
	ProdutoCliente findByClienteAndProduto(@Param("cliente") Cliente cliente,@Param("produto") Produto produto);
	

}