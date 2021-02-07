package br.com.zup.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@NotNull
	private StatusTransacao statusTransacao;
	
	@NotBlank
	private String transacaoId;

	@NotNull
	private LocalDateTime dataHoraTransacao;
	
	@NotNull 
	@Valid
	@ManyToOne
	private Compra compra;

	@Deprecated
	public Transacao() {}
	
	public Transacao(@NotNull StatusTransacao statusTransacao,
			@NotBlank String transacaoId, @NotNull @Valid Compra compra) {
		this.statusTransacao = statusTransacao;
		this.transacaoId = transacaoId;
		this.compra = compra;
		this.dataHoraTransacao = LocalDateTime.now();
	}

	public boolean concluidaComSucesso() {
		return this.statusTransacao.equals(StatusTransacao.SUCESSO);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transacaoId == null) ? 0 : transacaoId.hashCode());
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
		Transacao other = (Transacao) obj;
		if (transacaoId == null) {
			if (other.transacaoId != null)
				return false;
		} else if (!transacaoId.equals(other.transacaoId))
			return false;
		return true;
	}

}
