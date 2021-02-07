package br.com.zup.mercadolivre.controller.dto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import br.com.zup.mercadolivre.model.Opinioes;
import br.com.zup.mercadolivre.model.Produto;

public class DetalheProdutoDTO {

	private String nome;
	private String descricao;
	private BigDecimal valor;
	private Set<DetalheProdutoCaracteristicaDTO> caracteristicas;
	private Set<String> linksImagens;
	private SortedSet<String> perguntas;
	private Set<Map<String, String>> opinioes;
	private double mediaDasNotas;
	private int totalDeOpinioes;
	
	public DetalheProdutoDTO(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.valor = produto.getValor();
		this.caracteristicas = produto.mapearCaracteristicas(DetalheProdutoCaracteristicaDTO::new);
		this.linksImagens = produto.mapearImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapearPerguntas(pergunta -> pergunta.getTitulo());
		
		Opinioes opinioes = produto.getOpinioes();
		
		this.opinioes = opinioes.mapearOpinioes(opiniao -> {
			return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
		});
		
		this.mediaDasNotas = opinioes.calcularMedia();
		this.totalDeOpinioes = opinioes.totalDeOpinioes();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Set<DetalheProdutoCaracteristicaDTO> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<String> getLinksImagens() {
		return linksImagens;
	}

	public SortedSet<String> getPerguntas() {
		return perguntas;
	}

	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}

	public double getMediaDasNotas() {
		return mediaDasNotas;
	}
	
	public int getTotalDeOpinioes() {
		return totalDeOpinioes;
	}
	
	@Override
	public String toString() {
		return "DetalheProdutoDTO [nome=" + nome + ", descricao=" + descricao + ", valor=" + valor
				+ ", caracteristicas=" + caracteristicas + ", linksImagens=" + linksImagens + ", perguntas=" + perguntas
				+ ", opinioes=" + opinioes + ", mediaDasNotas=" + mediaDasNotas + "]";
	}
	
}
