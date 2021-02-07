package br.com.zup.mercadolivre.controller.request;

import javax.validation.constraints.NotBlank;

import br.com.zup.mercadolivre.model.CaracteristicaProduto;
import br.com.zup.mercadolivre.model.Produto;

public class NovaCaracteristicaRequest {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;

	public NovaCaracteristicaRequest(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return "NovaCaracteristicaRequest [nome=" + nome + ", descricao=" + descricao + "]";
	}

	public CaracteristicaProduto toEntity(Produto produto) {
		return new CaracteristicaProduto(nome, descricao, produto);
	}

}
