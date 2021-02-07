package br.com.zup.mercadolivre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(unique = true, nullable = false)
	private String nome;
	
	@ManyToOne
	private Categoria superCategoria;

	@Deprecated
	public Categoria() {}
	
	public Categoria(String nome, Categoria superCategoria) {
		this.nome = nome;
		this.superCategoria = superCategoria;
	}

	@Override
	public String toString() {
		return "Categoria [nome=" + nome + ", superCategoria=" + superCategoria + "]";
	}
	
}
