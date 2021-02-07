package br.com.zup.mercadolivre.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zup.mercadolivre.validation.validator.CategoriaExistenteValidator;

@Documented
@Constraint(validatedBy = CategoriaExistenteValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoriaExistente {

	String message() default "";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
