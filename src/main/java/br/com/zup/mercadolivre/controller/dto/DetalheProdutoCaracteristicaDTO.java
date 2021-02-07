package br.com.zup.mercadolivre.controller.dto;

import br.com.zup.mercadolivre.model.CaracteristicaProduto;

public class DetalheProdutoCaracteristicaDTO {

	private String nome;
	private String descricao;

	public DetalheProdutoCaracteristicaDTO(CaracteristicaProduto caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return "DetalheProdutoCaracteristicaDTO [nome=" + nome + ", descricao=" + descricao + "]";
	}
	
}
