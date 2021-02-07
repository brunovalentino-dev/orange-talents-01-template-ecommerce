package br.com.zup.mercadolivre.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;
	
	@Positive
	private Integer quantidadeProduto;
	
	@ManyToOne
	@NotNull
	@Valid
	private Usuario comprador;

	@Enumerated(EnumType.STRING)
	@NotNull
	private GatewayPagamento gatewayPagamento;
	
	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<Transacao>();
	
	@Deprecated
	public Compra() {}
	
	public Compra(@NotNull @Valid Produto produto, @Positive Integer quantidadeProduto,
			@NotNull @Valid Usuario comprador, @NotNull GatewayPagamento gatewayPagamento) {
		this.produto = produto;
		this.quantidadeProduto = quantidadeProduto;
		this.comprador = comprador;
		this.gatewayPagamento = gatewayPagamento;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Compra [produto=" + produto + ", quantidadeProduto=" + quantidadeProduto + ", comprador=" + comprador
				+ ", gatewayPagamento=" + gatewayPagamento + ", transacoes=" + transacoes + "]";
	}

	public void adicionarTentativaPagamento(@Valid GatewayPagamentoRequest request) {
		Transacao novaTransacao = request.toTransacao(this);
		
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
							   .filter(Transacao :: concluidaComSucesso)
							   .collect(Collectors.toSet());

		this.transacoes.add(novaTransacao);
	}
	
}
