package com.proposta.renegociacao;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.proposta.cartao.Cartao;

@Entity
public class Renegociacao {

	@Id
	private Long id;
	private Integer quantidade;
	private BigDecimal valor;
	private LocalDate dataDeCriacao;
	@OneToOne
	private Cartao cartao;


	public Renegociacao(Long id, Integer quantidade, BigDecimal valor, LocalDate dataDeCriacao) {
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
		this.dataDeCriacao = dataDeCriacao;
		// TODO Auto-generated constructor stub
	}


	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	
	

}
