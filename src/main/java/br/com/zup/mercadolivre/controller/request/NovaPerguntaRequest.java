package br.com.zup.mercadolivre.controller.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.mercadolivre.model.PerguntaProduto;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

public class NovaPerguntaRequest {

	@NotBlank
	private String titulo;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public NovaPerguntaRequest(@NotBlank String titulo) {
		this.titulo = titulo;
	}

	public PerguntaProduto toEntity(Produto produto, Usuario usuario) {
		return new PerguntaProduto(titulo, produto, usuario);
	}

	@Override
	public String toString() {
		return "NovaPerguntaRequest [titulo=" + titulo + "]";
	}
	
}
