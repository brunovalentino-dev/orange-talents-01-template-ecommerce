package br.com.zup.mercadolivre.model;

public enum StatusPagamentoPagSeguro {

	SUCESSO, ERRO;

	public StatusTransacao normalizar() {
		if (this.equals(SUCESSO)) {
			return StatusTransacao.SUCESSO;
		}
		
		return StatusTransacao.ERRO;
	}
	
}
