package br.com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.controller.request.NovoProdutoRequest;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.validation.validator.CaracteristicaDuplicadaValidator;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private CaracteristicaDuplicadaValidator caracteristicaDuplicadaValidator;
	
	@InitBinder(value = "novoProdutoRequest")
	public void iniciarWebDataBinder(WebDataBinder binder) {
		binder.addValidators(caracteristicaDuplicadaValidator);
	}
	
	@PostMapping
	@Transactional
	public String cadastrarNovoProduto(@RequestBody @Valid NovoProdutoRequest request,
			@AuthenticationPrincipal Usuario usuario) {
		Produto produto = request.toEntity(entityManager, usuario);
		
		entityManager.persist(produto);
		
		return produto.toString();
	}
	
}
