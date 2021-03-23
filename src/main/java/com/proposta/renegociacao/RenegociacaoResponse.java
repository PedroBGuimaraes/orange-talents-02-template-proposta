package com.proposta.renegociacao;

import java.math.BigDecimal;
import java.time.LocalDate;


public class RenegociacaoResponse {
	private Long id;
	private Integer quantidade;
	private BigDecimal valor;
	private LocalDate dataDeCriacao;
	@Deprecated
	public RenegociacaoResponse() {
		
	}
	public RenegociacaoResponse(Long id, Integer quantidade, BigDecimal valor, LocalDate dataDeCriacao) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
		this.dataDeCriacao = dataDeCriacao;
	}
	public Long getId() {
		return id;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public LocalDate getDataDeCriacao() {
		return dataDeCriacao;
	}
	public Renegociacao toModel() {
		// TODO Auto-generated method stub
		return new Renegociacao(id, quantidade, valor, dataDeCriacao);
	}
	
	
}
