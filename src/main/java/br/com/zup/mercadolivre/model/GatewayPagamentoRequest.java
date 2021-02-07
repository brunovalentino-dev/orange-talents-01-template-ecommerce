package br.com.zup.mercadolivre.model;

public interface GatewayPagamentoRequest {

	public Transacao toTransacao(Compra compra);
}
