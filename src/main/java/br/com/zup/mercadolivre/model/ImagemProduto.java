package br.com.zup.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
public class ImagemProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;
	
	@URL
	private String link;

	@Deprecated
	public ImagemProduto() {}
	
	public ImagemProduto(Produto produto, String link) {
		this.produto = produto;
		this.link = link;
	}
	
	public String getLink() {
		return link;
	}

	@Override
	public String toString() {
		return "ImagemProduto [id=" + id + ", link=" + link + "]";
	}
	
}
