package br.com.zup.mercadolivre.controller.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import br.com.zup.mercadolivre.model.Categoria;
import br.com.zup.mercadolivre.model.Produto;
import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.validation.annotation.CategoriaExistente;
import br.com.zup.mercadolivre.validation.annotation.ValorUnico;

public class NovoProdutoRequest {

	@NotBlank
	@ValorUnico(campo = "nome", dominio = Produto.class)
	private String nome;
	
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@NotNull
	@PositiveOrZero
	private int quantidade;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	@CategoriaExistente	
	private Long categoriaId;
	
	@Size(min = 3)
	@Valid
	private List<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();
	
	public NovoProdutoRequest(String nome, BigDecimal valor, int quantidade,
			String descricao, Long categoriaId, List<NovaCaracteristicaRequest> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
		this.caracteristicas.addAll(caracteristicas);
	}

	public List<NovaCaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}
	
	public Produto toEntity(EntityManager entityManager, Usuario usuario) {
		Categoria categoria = entityManager.find(Categoria.class, this.categoriaId);
		
		return new Produto(nome, valor, quantidade, descricao,
				categoria, caracteristicas, usuario);
	}

	public Set<String> buscarCaracteristicasDuplicadas() {
		HashSet<String> caracteristicasDuplicadas = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();
		
		for (NovaCaracteristicaRequest caracteristica : caracteristicas) {
			if (!caracteristicasDuplicadas.add(caracteristica.getNome())) {
				resultados.add(caracteristica.getNome());
			}
		}
		
		return resultados;
	}
	
	@Override
	public String toString() {
		return "NovoProdutoRequest [nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade + ", descricao="
				+ descricao + ", categoriaId=" + categoriaId + ", caracteristicas=" + caracteristicas + "]";
	}
	
}
