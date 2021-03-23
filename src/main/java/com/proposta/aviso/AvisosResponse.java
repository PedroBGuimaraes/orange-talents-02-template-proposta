package com.proposta.aviso;

import java.time.LocalDate;

public class AvisosResponse {
	private LocalDate validoAte;
	private String destino;
	
	@Deprecated
	public AvisosResponse() {
		
	}
	
	public AvisosResponse(LocalDate validoAte, String destino) {
		super();
		this.validoAte = validoAte;
		this.destino = destino;
	}
	public LocalDate getValidoAte() {
		return validoAte;
	}
	public String getDestino() {
		return destino;
	}
	
	public Aviso toModel() {
		return new Aviso(validoAte, destino);
	}
	
	
}
