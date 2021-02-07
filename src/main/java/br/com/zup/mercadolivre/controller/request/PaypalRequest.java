package br.com.zup.mercadolivre.controller.request;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import br.com.zup.mercadolivre.model.Compra;
import br.com.zup.mercadolivre.model.GatewayPagamentoRequest;
import br.com.zup.mercadolivre.model.StatusTransacao;
import br.com.zup.mercadolivre.model.Transacao;

public class PaypalRequest implements GatewayPagamentoRequest {

	@Range(min = 0, max = 1)
	private Integer status;
	
	@NotBlank
	private String transacaoId;

	public PaypalRequest(@Range(min = 0, max = 1) Integer status, @NotBlank String transacaoId) {
		this.status = status;
		this.transacaoId = transacaoId;
	}

	@Override
	public Transacao toTransacao(Compra compra) {
		StatusTransacao statusTransacao = this.status == 0 ? StatusTransacao.ERRO 
				: StatusTransacao.SUCESSO; 
		
		return new Transacao(statusTransacao, transacaoId, compra);
	}
	
}
