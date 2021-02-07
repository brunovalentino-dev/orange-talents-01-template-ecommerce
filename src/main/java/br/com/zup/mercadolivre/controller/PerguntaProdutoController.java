package br.com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.controller.request.NovaPerguntaRequest;
import br.com.zup.mercadolivre.model.PerguntaProduto;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.service.impl.Emails;

@RestController
public class PerguntaProdutoController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private Emails emails;
	
	@PostMapping(value = "produtos/{id}/perguntas")
	@Transactional
	public String adicionarPergunta(@PathVariable Long id, @RequestBody @Valid NovaPerguntaRequest request, 
			@AuthenticationPrincipal Usuario usuario) {
		Produto produto = entityManager.find(Produto.class, id);
		
		PerguntaProduto pergunta = request.toEntity(produto, usuario);
		
		entityManager.persist(pergunta);
		
		emails.enviarNovaPergunta(pergunta);
		
		return pergunta.toString();
	}
	
}
