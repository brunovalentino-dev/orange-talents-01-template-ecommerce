package br.com.zup.mercadolivre.controller.request;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.mercadolivre.model.OpiniaoProduto;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

public class NovaOpiniaoRequest {

	@Range(min = 1, max = 5)	
	private Integer nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Length(max = 500)
	private String descricao;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public NovaOpiniaoRequest(Integer nota, String titulo,
			String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public OpiniaoProduto toEntity(Produto produto, Usuario consumidor) {
		return new OpiniaoProduto(nota, titulo, descricao, produto, consumidor);
	}
	
	@Override
	public String toString() {
		return "NovaOpiniaoRequest [nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + "]";
	}

}
