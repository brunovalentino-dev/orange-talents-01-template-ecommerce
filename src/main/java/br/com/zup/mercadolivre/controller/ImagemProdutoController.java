package br.com.zup.mercadolivre.controller;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.mercadolivre.controller.request.ImagensRequest;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.service.ImageUploader;

@RestController
public class ImagemProdutoController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ImageUploader imageUploader;
	
	@PostMapping(value = "produtos/{id}/imagens")
	@Transactional
	public String adicionarImagens(@PathVariable Long id, @Valid ImagensRequest request,
			@AuthenticationPrincipal Usuario usuario) {
		Set<String> links = imageUploader.envia(request.getImagens());
		
		Produto produto = entityManager.find(Produto.class, id);

		if (!produto.pertenceAoUsuario(usuario)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		produto.associarImagens(links);
	
		entityManager.merge(produto);
		
		return produto.toString();
	}
	
}
