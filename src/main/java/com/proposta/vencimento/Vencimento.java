package com.proposta.vencimento;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.proposta.cartao.Cartao;

@Entity
public class Vencimento {

	@Id
	private String id;
	private int dia;
	private LocalDate dataDeCriacao;
	@OneToOne
	private Cartao cartao;

	public Vencimento(String id, int dia, LocalDate dataDeCriacao) {
		this.id = id;
		this.dia = dia;
		this.dataDeCriacao = dataDeCriacao;
		// TODO Auto-generated constructor stub
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	
	
}
