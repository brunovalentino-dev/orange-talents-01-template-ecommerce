package br.com.zup.mercadolivre.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import br.com.zup.mercadolivre.controller.dto.DetalheProdutoCaracteristicaDTO;
import br.com.zup.mercadolivre.controller.request.NovaCaracteristicaRequest;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@NotNull
	@Column(nullable = false)
	@Positive	
	private BigDecimal valor;
	
	@NotNull
	@Column(nullable = false)
	@PositiveOrZero
	private int quantidade;
	
	@NotBlank
	@Column(nullable = false)
	@Length(max = 1000)
	private String descricao;
	
	@NotNull
	@ManyToOne
	private Categoria categoria;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<CaracteristicaProduto>();
	
	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<ImagemProduto>();

	@OneToMany(mappedBy = "produto")
	@OrderBy("titulo asc")
	private SortedSet<PerguntaProduto> perguntas = new TreeSet<PerguntaProduto>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<OpiniaoProduto> opinioes = new HashSet<OpiniaoProduto>();
	
	@Deprecated
	public Produto() {}
	
	public Produto(String nome, BigDecimal valor, int quantidade,
			String descricao, Categoria categoria, Collection<NovaCaracteristicaRequest> caracteristicas, Usuario usuario) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.caracteristicas.addAll(caracteristicas.stream()
				   .map(caracteristica -> caracteristica.toEntity(this))
				   .collect(Collectors.toSet()));
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void associarImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream()
			 .map(link -> new ImagemProduto(this, link))
			 .collect(Collectors.toSet());
		
		this.imagens.addAll(imagens);
	}
	
	public boolean pertenceAoUsuario(Usuario usuario) {
		return this.usuario.equals(usuario);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade + ", descricao=" + descricao
				+ ", categoria=" + categoria + ", caracteristicas=" + caracteristicas + ", usuario=" + usuario
				+ ", imagens=" + imagens + "]";
	}

	public String getDescricao() {
		return descricao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public Set<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}
	
	public Set<PerguntaProduto> getPerguntas() {
		return perguntas;
	}
	
	public Set<DetalheProdutoCaracteristicaDTO> mapearCaracteristicas(Function<CaracteristicaProduto,
			DetalheProdutoCaracteristicaDTO> mapperFunction) {
		return this.caracteristicas.stream().map(mapperFunction).collect(Collectors.toSet());
	}
	
	public Set<String> mapearImagens(Function<ImagemProduto, String> mapperFunction) {
		return this.imagens.stream().map(mapperFunction).collect(Collectors.toSet());
	}

	public SortedSet<String> mapearPerguntas(Function<PerguntaProduto, String> mapperFunction) {
		return this.perguntas.stream().map(mapperFunction).collect(Collectors.toCollection(TreeSet :: new));
	}

	public Opinioes getOpinioes() {
		return new Opinioes(this.opinioes);
	}

	public boolean abaterEstoque(Integer quantidadeProduto) {
		if (quantidadeProduto <= this.quantidade) {
			this.quantidade -= quantidadeProduto;
			return true;
		}
		
		return false;
	}
	
}
