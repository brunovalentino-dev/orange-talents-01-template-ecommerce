package br.com.zup.mercadolivre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Entity
public class OpiniaoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Range(min = 1, max = 5)	
	private Integer nota;
	
	@NotBlank
	@Column(nullable = false)
	private String titulo;
	
	@NotBlank
	@Column(nullable = false)
	@Length(max = 500)
	private String descricao;
	
	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuario;

	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;
	
	@Deprecated
	public OpiniaoProduto() {}

	public OpiniaoProduto(Integer nota, String titulo, String descricao,
			Produto produto, Usuario usuario) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public Integer getNota() {
		return nota;
	}

	@Override
	public String toString() {
		return "OpiniaoProduto [nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + ", usuario="
				+ usuario + ", produto=" + produto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		OpiniaoProduto other = (OpiniaoProduto) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
}
