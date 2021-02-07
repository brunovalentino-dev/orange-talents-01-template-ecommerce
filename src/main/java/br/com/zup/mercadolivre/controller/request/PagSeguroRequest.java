package br.com.zup.mercadolivre.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.model.Compra;
import br.com.zup.mercadolivre.model.GatewayPagamentoRequest;
import br.com.zup.mercadolivre.model.StatusPagamentoPagSeguro;
import br.com.zup.mercadolivre.model.Transacao;

public class PagSeguroRequest implements GatewayPagamentoRequest {

	@NotBlank
	private String transacaoId;
	
	@NotNull
	private StatusPagamentoPagSeguro statusPagamento;

	public PagSeguroRequest(@NotBlank String transacaoId, @NotNull StatusPagamentoPagSeguro statusPagamento) {
		this.transacaoId = transacaoId;
		this.statusPagamento = statusPagamento;
	}

	@Override
	public Transacao toTransacao(Compra compra) {
		return new Transacao(statusPagamento.normalizar(), transacaoId, compra);
	}
	
	@Override
	public String toString() {
		return "PagSeguroRequest [transacaoId=" + transacaoId + ", statusPagamento=" + statusPagamento + "]";
	}
	
}
