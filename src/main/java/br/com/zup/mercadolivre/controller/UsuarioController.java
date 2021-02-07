package br.com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.controller.request.NovoUsuarioRequest;
import br.com.zup.mercadolivre.model.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@PostMapping
	@Transactional
	public String cadastrarNovoUsuario(@RequestBody @Valid NovoUsuarioRequest request) {
		Usuario usuario = request.toEntity();
		
		entityManager.persist(usuario);
		
		return usuario.toString();
	}
	
}
