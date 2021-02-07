package br.com.zup.mercadolivre.controller.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.mercadolivre.model.GatewayPagamento;

public class NovaCompraRequest {

	@Positive
	private Integer quantidadeProduto;
	
	@NotNull
	private Long produtoId;
	
	@NotNull
	private GatewayPagamento gatewayPagamento;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public NovaCompraRequest(@Positive Integer quantidadeProduto, @NotNull Long produtoId,
			@NotNull GatewayPagamento gatewayPagamento) {
		this.quantidadeProduto = quantidadeProduto;
		this.produtoId = produtoId;
		this.gatewayPagamento = gatewayPagamento;
	}

	public Long getProdutoId() {
		return produtoId;
	}
	
	public Integer getQuantidadeProduto() {
		return quantidadeProduto;
	}
	
	public GatewayPagamento getGatewayPagamento() {
		return gatewayPagamento;
	}
	
	@Override
	public String toString() {
		return "NovaCompraRequest [quantidadeProduto=" + quantidadeProduto + ", produtoId=" + produtoId + "]";
	}

}
