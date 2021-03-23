package com.proposta.parcela;

import java.math.BigDecimal;

public class ParcelaResponse {
	private String id;
	private Integer quantidade;
	private BigDecimal valor;
	
	@Deprecated
	public ParcelaResponse() {
		
	}

	public ParcelaResponse(String id, Integer quantidade, BigDecimal valor) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public String getId() {
		return id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Parcela toModel() {
		// TODO Auto-generated method stub
		return new Parcela(id, quantidade, valor);
	}
	
	
}
