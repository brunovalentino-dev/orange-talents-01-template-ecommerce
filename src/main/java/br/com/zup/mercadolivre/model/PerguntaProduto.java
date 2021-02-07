package br.com.zup.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class PerguntaProduto implements Comparable<PerguntaProduto> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String titulo;
	
	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;
	
	@ManyToOne
	@NotNull
	@Valid
	private Usuario usuario;
	
	@NotNull
	@Column(nullable = false)
	private LocalDateTime dataHoraCadastro = LocalDateTime.now();

	@Deprecated
	public PerguntaProduto() {}
	
	public PerguntaProduto(@NotBlank String titulo, @NotNull @Valid Produto produto, @NotNull @Valid Usuario usuario) {
		this.titulo = titulo;
		this.produto = produto;
		this.usuario = usuario;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Produto getProduto() {
		return produto;
	}

	@Override
	public String toString() {
		return "Pergunta [titulo=" + titulo + ", produto=" + produto + ", usuario=" + usuario + ", dataHoraCadastro="
				+ dataHoraCadastro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		PerguntaProduto other = (PerguntaProduto) obj;
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

	@Override
	public int compareTo(PerguntaProduto o) {
		return this.titulo.compareTo(o.titulo);
	}
	
}
