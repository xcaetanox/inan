package br.com.andrecaetano.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.andrecaetano.models.Cliente;
import br.com.andrecaetano.models.MovimentoProdutoCliente;
import br.com.andrecaetano.models.Produto;
import br.com.andrecaetano.models.ProdutoCliente;
import br.com.andrecaetano.repositorys.RepositoryCliente;
import br.com.andrecaetano.repositorys.RepositoryMovimentoProdutoCliente;
import br.com.andrecaetano.repositorys.RepositoryProduto;
import br.com.andrecaetano.repositorys.RepositoryProdutoCliente;

@RestController
public class MovimentacaoController {

	@Autowired
	RepositoryMovimentoProdutoCliente repoMovimento;

	@Autowired
	RepositoryProdutoCliente repoProdCliente;

	@Autowired
	RepositoryCliente repoClie;

	@Autowired
	RepositoryProduto repoPrd;

	@RequestMapping(value = "/movimentacao", method = RequestMethod.POST)
	public ResponseEntity<String> salve(@RequestBody MovimentoProdutoCliente movi) {

		ProdutoCliente prdCliente;
		MovimentoProdutoCliente movimento;

		List<Cliente> cliente = repoClie.findByNome(movi.getCliente().getNome());
		List<Produto> produto = repoPrd.findByDescricao(movi.getProduto().getDescricao());

		prdCliente = repoProdCliente.findByClienteAndProduto(cliente.get(0), produto.get(0));

		if (prdCliente != null) {
			if (movi.getOperacao() == 1) {
				prdCliente.setSaldo(prdCliente.getSaldo() + movi.getQtde());
			} else {
				prdCliente.setSaldo(prdCliente.getSaldo() - movi.getQtde());
			}
		} else {
			prdCliente = new ProdutoCliente();
			prdCliente.setCliente(cliente.get(0));
			prdCliente.setProduto(produto.get(0));
			if (movi.getOperacao() == 1) {
				prdCliente.setSaldo(movi.getQtde());
			} else {
				prdCliente.setSaldo(-movi.getQtde());
			}
		}
		repoProdCliente.save(prdCliente);

		movimento = new MovimentoProdutoCliente();
		movimento.setCliente(cliente.get(0));
		movimento.setProduto(produto.get(0));
		movimento.setOperacao(movi.getOperacao());
		movimento.setPedido(movi.getPedido());
		movimento.setDataMovimento(new Date());
		movimento.setQtde(movi.getQtde());

		repoMovimento.save(movimento);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@RequestMapping(value = "/movimentacaoremove", method = RequestMethod.POST)
	public ResponseEntity<String> remove(@RequestBody MovimentoProdutoCliente movi) {

		MovimentoProdutoCliente moviPrdClie = repoMovimento.findById(movi.getId());

		ProdutoCliente prdCliente;
		MovimentoProdutoCliente movimento;

		List<Cliente> cliente = repoClie.findByNome(movi.getCliente().getNome());
		List<Produto> produto = repoPrd.findByDescricao(movi.getProduto().getDescricao());

		prdCliente = repoProdCliente.findByClienteAndProduto(cliente.get(0), produto.get(0));

		if (movi.getOperacao() == 1) {
			prdCliente.setSaldo(prdCliente.getSaldo() - movi.getQtde());
		} else {
			prdCliente.setSaldo(prdCliente.getSaldo() + movi.getQtde());
		}

		repoProdCliente.save(prdCliente);
		repoMovimento.delete(movi);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
