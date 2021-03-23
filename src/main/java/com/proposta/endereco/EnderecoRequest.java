package com.proposta.endereco;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.proposta.proposta.Proposta;

public class EnderecoRequest {
	@NotBlank
	private String rua;
	@NotBlank
	private String bairro;
	@NotBlank
	private String cidade;
	
	@Override
	public String toString() {
		return "EnderecoRequest [rua=" + rua + ", bairro=" + bairro + ", cidade=" + cidade + "]";
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
	public @NotNull @Valid Endereco toModel(Proposta proposta) {
		return new Endereco(rua, bairro, cidade, proposta);
	}
}
