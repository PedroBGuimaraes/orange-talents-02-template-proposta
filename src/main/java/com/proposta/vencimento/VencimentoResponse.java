package com.proposta.vencimento;

import java.time.LocalDate;

public class VencimentoResponse {
	private String id;
	private int dia;
	private LocalDate dataDeCriacao;
	
	@Deprecated
	public VencimentoResponse() {
		
	}

	public VencimentoResponse(String id, int dia, LocalDate dataDeCriacao) {
		super();
		this.id = id;
		this.dia = dia;
		this.dataDeCriacao = dataDeCriacao;
	}

	public String getId() {
		return id;
	}

	public int getDia() {
		return dia;
	}

	public LocalDate getDataDeCriacao() {
		return dataDeCriacao;
	}

	public Vencimento toModel() {
		// TODO Auto-generated method stub
		return new Vencimento(id, dia, dataDeCriacao);
	}
	
}
