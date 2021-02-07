package br.com.zup.mercadolivre.validation.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zup.mercadolivre.model.Categoria;
import br.com.zup.mercadolivre.validation.annotation.CategoriaExistente;

public class CategoriaExistenteValidator implements ConstraintValidator<CategoriaExistente, Object> {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value != null) {
			List<Categoria> superCategorias = 
					entityManager.createQuery("SELECT c FROM Categoria c WHERE c.superCategoria.id =:superCategoriaId", Categoria.class)
					.setParameter("superCategoriaId", value)
					.getResultList();
			
			return !superCategorias.isEmpty();
		}
		
		return true;
	}

}
