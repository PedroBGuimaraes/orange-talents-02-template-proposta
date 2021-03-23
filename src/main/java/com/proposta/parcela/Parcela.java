package com.proposta.parcela;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.proposta.cartao.Cartao;

@Entity
public class Parcela {

	@Id
	private String id;
	private Integer quantidade;
	private BigDecimal valor;
	@ManyToOne
    private Cartao cartao;

	public Parcela(String id, Integer quantidade, BigDecimal valor) {
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
		// TODO Auto-generated constructor stub
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
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
	
	

}
