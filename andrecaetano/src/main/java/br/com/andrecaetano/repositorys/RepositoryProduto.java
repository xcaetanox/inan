package br.com.andrecaetano.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.andrecaetano.models.Produto;

@RepositoryRestResource(excerptProjection = ProdutoProjection.class, path = "produto")
public interface RepositoryProduto extends CrudRepository<Produto, Long> {

	List<Produto> findByDescricao(@Param("descricao") String descricao);
	Produto findById(@Param("id") long id);

	
	
}
