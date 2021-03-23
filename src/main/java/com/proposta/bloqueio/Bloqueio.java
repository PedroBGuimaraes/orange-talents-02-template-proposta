package com.proposta.bloqueio;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proposta.cartao.Cartao;

@Entity
public class Bloqueio {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDate bloqueadoEm;
	private String sistemaResponsavel;
	private Boolean ativo;
	@ManyToOne
    private Cartao cartao;

	public Bloqueio(String id, LocalDate bloqueadoEm, String sistemaResponsavel, Boolean ativo) {
		this.id = id;
		this.bloqueadoEm = bloqueadoEm;
		this.sistemaResponsavel = sistemaResponsavel;
		this.ativo = ativo;
	}
	public Bloqueio(String sistemaResponsavel, Boolean ativo, Cartao cartao) {
		this.bloqueadoEm = LocalDate.now();
		this.sistemaResponsavel = sistemaResponsavel;
		this.ativo = ativo;
		this.cartao = cartao;
	}
	
	@Deprecated
	public Bloqueio() {
		
	}
	
	

	@Override
	public String toString() {
		return "Bloqueio [id=" + id + ", bloqueadoEm=" + bloqueadoEm + ", sistemaResponsavel=" + sistemaResponsavel
				+ ", ativo=" + ativo +"]";
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
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

	public Cartao getCartao() {
		return cartao;
	}
}
