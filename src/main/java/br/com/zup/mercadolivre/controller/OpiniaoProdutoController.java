package br.com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.controller.request.NovaOpiniaoRequest;
import br.com.zup.mercadolivre.model.OpiniaoProduto;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

@RestController
public class OpiniaoProdutoController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@PostMapping(value = "produtos/{id}/opinioes")
	@Transactional
	public String adicionarOpinioes(@PathVariable Long id, @RequestBody @Valid NovaOpiniaoRequest request, 
			@AuthenticationPrincipal Usuario usuario) {
		Produto produto = entityManager.find(Produto.class, id);
		
		OpiniaoProduto opiniaoProduto = request.toEntity(produto, usuario);
		
		entityManager.persist(opiniaoProduto);
		
		return opiniaoProduto.toString();
	}
	
}
