package br.com.zup.mercadolivre.controller.request;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.validation.annotation.ValorUnico;

public class NovoUsuarioRequest {

	@NotBlank
	@Email
	@Column(unique = true, nullable = false)
	@ValorUnico(campo = "login", dominio = Usuario.class)
	private String login;

	@NotBlank
	@Column(nullable = false)
	@Length(min = 6)
	private String senha;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public NovoUsuarioRequest(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario toEntity() {
		return new Usuario(login, senha);
	}
	
}
