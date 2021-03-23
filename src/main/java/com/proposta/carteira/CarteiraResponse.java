package com.proposta.carteira;

import java.time.LocalDate;

public class CarteiraResponse {
	private String id;
	private String email;
	private LocalDate associadaEm;
	private String emissor;
	
	@Deprecated
	public CarteiraResponse() {
		
	}
	
	public CarteiraResponse(String id, String email, LocalDate associadaEm, String emissor) {
		super();
		this.id = id;
		this.email = email;
		this.associadaEm = associadaEm;
		this.emissor = emissor;
	}
	public String getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public LocalDate getAssociadaEm() {
		return associadaEm;
	}
	public String getEmissor() {
		return emissor;
	}

	public Carteira toModel() {
		// TODO Auto-generated method stub
		return new Carteira(id, email, associadaEm, emissor);
	}
	
}
