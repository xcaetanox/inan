package br.com.andrecaetano.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.andrecaetano.models.Cliente;
import br.com.andrecaetano.models.Produto;
import br.com.andrecaetano.repositorys.RepositoryCliente;
import br.com.andrecaetano.repositorys.RepositoryProduto;

@RestController
public class ClienteController {

	@Autowired
	RepositoryCliente repo;

	@RequestMapping(value = "/clientecontroller", method = RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody Cliente cliente) {

		if (repo.findByNome(cliente.getNome()).size() == 0 ) {
			
			repo.save(cliente);

		}else{
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Cliente Já Cadastrado esse nome");
		}

		return ResponseEntity.status(HttpStatus.CREATED).build();

	}
	
	
	@RequestMapping(value = "/clientecontroller", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@RequestBody Cliente cliente) {

		if (repo.findByNome(cliente.getNome()).size() == 0 ) {
			
			repo.save(cliente);

		}else{
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Cliente Já Cadastrado esse nome");
		}

		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

}
