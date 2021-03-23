package com.proposta.carteira;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.proposta.cartao.Cartao;

@Entity
public class Carteira {

	@Id
	private String id;
	private String email;
	private LocalDate associadaEm;
	private String emissor;
	@ManyToOne
    private Cartao cartao;

	@Deprecated
	public Carteira() {
		
	}
	
	public Carteira(String id, String email, LocalDate associadaEm, String emissor) {
		this.id = id;
		this.email = email;
		this.associadaEm = associadaEm;
		this.emissor = emissor;
		// TODO Auto-generated constructor stub
	}
	
	public Carteira(String id, String email, String emissor, Cartao cartao) {
		this.id = id;
		this.email = email;
		this.associadaEm = LocalDate.now();
		this.emissor = emissor;
		this.cartao = cartao;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Carteira [id=" + id + ", email=" + email + ", associadaEm=" + associadaEm + ", emissor=" + emissor
				+ "]";
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

	public String getEmail() {
		return email;
	}

	public LocalDate getAssociadaEm() {
		return associadaEm;
	}

	public String getEmissor() {
		return emissor;
	}
	
	

}
