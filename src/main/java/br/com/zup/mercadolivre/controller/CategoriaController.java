package br.com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.controller.request.NovaCategoriaRequest;
import br.com.zup.mercadolivre.model.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@PersistenceContext
	private EntityManager entityManager;
	
//	@Autowired
//	private SuperCategoriaValidator superCategoriaValidator;
//	
//	@InitBinder
//	public void iniciarWebDataBinder(WebDataBinder binder) {
//		binder.addValidators(superCategoriaValidator);
//	}
	
	@PostMapping
	@Transactional
	public String cadastrarNovaCategoria(@RequestBody @Valid NovaCategoriaRequest request) {
		Categoria categoria = request.toEntity(entityManager);
		
		entityManager.persist(categoria);
		
		return categoria.toString();
	}
	
}
