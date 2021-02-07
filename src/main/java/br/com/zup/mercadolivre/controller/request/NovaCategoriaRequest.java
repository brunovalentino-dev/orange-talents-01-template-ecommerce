package br.com.zup.mercadolivre.controller.request;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.mercadolivre.model.Categoria;
import br.com.zup.mercadolivre.validation.annotation.CategoriaExistente;
import br.com.zup.mercadolivre.validation.annotation.ValorUnico;

public class NovaCategoriaRequest {

	@NotBlank
	@ValorUnico(campo = "nome", dominio = Categoria.class)
	private String nome;

	@CategoriaExistente
	private Long superCategoriaId;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public NovaCategoriaRequest(String nome, Long superCategoriaId) {		
		this.nome = nome;
		this.superCategoriaId = superCategoriaId;
	}
	
	public Long getSuperCategoriaId() {
		return superCategoriaId;
	}
	
	public Categoria toEntity(EntityManager entityManager) {
		Categoria superCategoria = null;
		
		if (this.superCategoriaId != null) {
			superCategoria = entityManager.find(Categoria.class, this.superCategoriaId); 
		}
				
		return new Categoria(this.nome, superCategoria);
	}
	
}
