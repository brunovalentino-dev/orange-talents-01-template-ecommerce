package br.com.zup.mercadolivre.model;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

	PAYPAL {
		@Override
		public String criarUrlRetorno(Compra compra, UriComponentsBuilder uriBuilder) {
			String retornoPaypal = uriBuilder.path("/retorno-paypal/{id}")
					  .buildAndExpand(compra.getId()).toString();
			
			return "paypal.com/" + compra.getId() + "?redirectUrl=" + retornoPaypal;
		}
	}, 
	PAGSEGURO {
		@Override
		public String criarUrlRetorno(Compra compra, UriComponentsBuilder uriBuilder) {
			String retornoPagseguro = uriBuilder.path("/retorno-pagseguro/{id}")
					  .buildAndExpand(compra.getId()).toString();
			
			return "pagseguro.com.br/" + compra.getId() + "?redirectUrl=" + retornoPagseguro;
		}
	};
	
	public abstract String criarUrlRetorno(Compra compra, UriComponentsBuilder uriBuilder);
	
}
