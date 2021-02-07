package br.com.zup.mercadolivre.model;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opinioes {

	private Set<OpiniaoProduto> opinioes;

	public Opinioes(Set<OpiniaoProduto> opinioes) {
		this.opinioes = opinioes;
	}
	
	public double calcularMedia() {
		Set<Integer> notasProduto = mapearOpinioes(opiniao -> opiniao.getNota());
		OptionalDouble media = notasProduto.stream().mapToInt(nota -> nota).average();
		
		return media.orElse(0.0);
	}
	
	public <T> Set<T> mapearOpinioes(Function<OpiniaoProduto, T> mapperFunction) {
		return this.opinioes.stream().map(mapperFunction).collect(Collectors.toSet());
	}
	
	public int totalDeOpinioes() {
		return this.opinioes.size();
	}
	
}
