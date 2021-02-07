package br.com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.controller.dto.DetalheProdutoDTO;
import br.com.zup.mercadolivre.model.Produto;

@RestController
public class DetalheProdutoController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@GetMapping("/produtos/{id}")
	public DetalheProdutoDTO buscarDetalhesProduto(@PathVariable Long id) {
		Produto produto = entityManager.find(Produto.class, id);
		
		return new DetalheProdutoDTO(produto);
	}
	
}
