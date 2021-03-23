package com.proposta.aviso.viagem;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;

import com.proposta.cartao.Cartao;

@Entity
public class AvisoViagem {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private @NotBlank String destino;
	@FutureOrPresent
	private LocalDate termino;
	private LocalDate instanteCriado = LocalDate.now();
	@ManyToOne
	private Cartao cartao;

	public AvisoViagem(@NotBlank String destino, LocalDate termino, Cartao cartao) {
		this.destino = destino;
		this.termino = termino;
		this.cartao = cartao;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AvisoViagem [id=" + id + ", destino=" + destino + ", termino=" + termino + ", instanteCriado="
				+ instanteCriado + ", cartao=" + cartao + "]";
	}

}
