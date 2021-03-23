package com.proposta.aviso.viagem;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;

import com.proposta.cartao.Cartao;

public class NovaViagemRequest {
	@NotBlank
	private String destino;
	@FutureOrPresent
	private LocalDate termino;
	public NovaViagemRequest(@NotBlank String destino, @FutureOrPresent LocalDate termino) {
		super();

		this.destino = destino;
		this.termino = termino;
	}
	public AvisoViagem toModel(Cartao cartao) {
		// TODO Auto-generated method stub
		return new AvisoViagem(destino, termino, cartao);
	}
	public String getDestino() {
		return destino;
	}
	public LocalDate getTermino() {
		return termino;
	}
	
	
	
}
