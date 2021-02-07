package br.com.zup.mercadolivre.validation.validator;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.mercadolivre.controller.request.NovoProdutoRequest;

@Component
public class CaracteristicaDuplicadaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) { 
		return NovoProdutoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		NovoProdutoRequest request = (NovoProdutoRequest) target;
		
		Set<String> caracteristicasDuplicadas = request.buscarCaracteristicasDuplicadas();
		
		if (!caracteristicasDuplicadas.isEmpty()) {
			errors.rejectValue("caracteristicas", null, 
					"O produto não deve conter características iguais: " + caracteristicasDuplicadas);
		}
	}

}
