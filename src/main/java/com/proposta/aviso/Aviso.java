package com.proposta.aviso;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.proposta.cartao.Cartao;

@Entity
public class Aviso {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate validoAte;
	private String destino;
	@ManyToOne
    private Cartao cartao;
	
	@Deprecated
	public Aviso() {
		
	}

	public Aviso(LocalDate validoAte, String destino, Cartao cartao) {
		this.validoAte = validoAte;
		this.destino = destino;
		this.cartao = cartao;
		// TODO Auto-generated constructor stub
	}

	public Aviso(LocalDate validoAte2, String destino2) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Aviso [id=" + id + ", validoAte=" + validoAte + ", destino=" + destino + "]";
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

	public String getDestino() {
		return destino;
	}
	

}
