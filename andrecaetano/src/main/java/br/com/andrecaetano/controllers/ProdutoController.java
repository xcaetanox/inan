package br.com.andrecaetano.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.andrecaetano.models.Produto;
import br.com.andrecaetano.repositorys.RepositoryProduto;

@RestController
public class ProdutoController {

	@Autowired
	RepositoryProduto repoPrd;

	@RequestMapping(value = "/produtocontroller", method = RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody Produto produto) {

		if (repoPrd.findByDescricao(produto.getDescricao()).size()==0) {

			repoPrd.save(produto);

		}else{
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Produto Já Cadastrado com essa descrição");
		}

		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

}
