package com.proposta.biometria;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.proposta.cartao.Cartao;

@Entity
public class Biometria {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
	private @NotBlank String idCartao;
	private @NotBlank String fingerprint;
	private LocalDateTime instanteCriacao = LocalDateTime.now();
	@ManyToOne
	private Cartao cartao;

	public Biometria(@NotBlank String id, @NotBlank String fingerprint, Cartao cartao) {
		this.idCartao = id;
		this.fingerprint = fingerprint;
		this.cartao = cartao;
	}

}
