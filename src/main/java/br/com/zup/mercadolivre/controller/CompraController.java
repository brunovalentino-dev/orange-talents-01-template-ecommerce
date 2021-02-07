package br.com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.controller.request.NovaCompraRequest;
import br.com.zup.mercadolivre.controller.request.PagSeguroRequest;
import br.com.zup.mercadolivre.controller.request.PaypalRequest;
import br.com.zup.mercadolivre.model.Compra;
import br.com.zup.mercadolivre.model.GatewayPagamento;
import br.com.zup.mercadolivre.model.GatewayPagamentoRequest;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;

@RestController
public class CompraController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@PostMapping(value = "/compras")
	@Transactional
	public String registrarNovaCompra(@RequestBody @Valid NovaCompraRequest request, 
			@AuthenticationPrincipal Usuario comprador, UriComponentsBuilder uriBuilder) throws BindException {
		Produto produto = entityManager.find(Produto.class, request.getProdutoId());
		
		Integer quantidadeProduto = request.getQuantidadeProduto();
		
		boolean estoqueAbatido = produto.abaterEstoque(request.getQuantidadeProduto());
		
		GatewayPagamento formaDePagamento = request.getGatewayPagamento();
		
		if (estoqueAbatido) {
			Compra novaCompra = new Compra(produto, quantidadeProduto, 
					comprador, formaDePagamento);
			
			entityManager.persist(novaCompra);
			
			return formaDePagamento.criarUrlRetorno(novaCompra, uriBuilder);
		}
		
		BindException estoqueInsuficiente = new BindException(request, "novaCompraRequest"); 
		estoqueInsuficiente.reject(null, "Estoque insuficiente. Compra não registrada!");
		
		throw estoqueInsuficiente;
	}
	
	@PostMapping(value = "/retorno-pagseguro/{id}")
	@Transactional
	public String registrarPagamentoPagSeguro(@PathVariable Long id, @Valid PagSeguroRequest request) {
		return this.processarPagamento(id, request);
	}
	
	@PostMapping(value = "/retorno-paypal/{id}")
	@Transactional
	public String registrarPagamentoPaypal(@PathVariable Long id, @Valid PaypalRequest request) {
		return this.processarPagamento(id, request);
	}
	
	private String processarPagamento(Long compraId, GatewayPagamentoRequest request) {
		Compra compra = entityManager.find(Compra.class, compraId);
		compra.adicionarTentativaPagamento(request);
		
		entityManager.merge(compra);
		
		return request.toString();
	}
	
}
