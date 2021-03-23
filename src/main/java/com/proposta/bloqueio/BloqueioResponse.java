package com.proposta.bloqueio;

import java.time.LocalDate;

public class BloqueioResponse {
	private String id;
	private LocalDate bloqueadoEm;
	private String sistemaResponsavel;
	private Boolean ativo;
	
	@Deprecated
	public BloqueioResponse() {
		
	}
	
	public BloqueioResponse(String id, LocalDate bloqueadoEm, String sistemaResponsavel, Boolean ativo) {
		super();
		this.id = id;
		this.bloqueadoEm = bloqueadoEm;
		this.sistemaResponsavel = sistemaResponsavel;
		this.ativo = ativo;
	}
	public String getId() {
		return id;
	}
	public LocalDate getBloqueadoEm() {
		return bloqueadoEm;
	}
	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	
	public Bloqueio toModel() {
		return new Bloqueio(id, bloqueadoEm, sistemaResponsavel, ativo);
	}
	
	
}
