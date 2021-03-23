package com.proposta.endereco;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.proposta.proposta.Proposta;

@Entity
public class Endereco {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
	private @NotBlank String rua;
	private @NotBlank String bairro;
	private @NotBlank String cidade;
	@OneToOne
	private @NotNull Proposta proposta;
	
	@Deprecated
	public Endereco() {
		
	}

	public Endereco(@NotBlank String rua, @NotBlank String bairro, @NotBlank String cidade, Proposta proposta) {
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.proposta = proposta;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", rua=" + rua + ", bairro=" + bairro + ", cidade=" + cidade + "]";
	}

	public long getId() {
		return id;
	}

	public String getRua() {
		return rua;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public Proposta getProposta() {
		return proposta;
	}
	
	

}
