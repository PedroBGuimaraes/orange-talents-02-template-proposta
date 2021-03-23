package com.proposta.aviso;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

public class NovoAvisoRequest {
	@NotBlank
	private String destino;
	private LocalDate validoAte;
	public NovoAvisoRequest(@NotBlank String destino, LocalDate validoAte) {
		super();
		this.destino = destino;
		this.validoAte = validoAte;
	}
	public String getDestino() {
		return destino;
	}
	public LocalDate getValidoAte() {
		return validoAte;
	}
	
}
