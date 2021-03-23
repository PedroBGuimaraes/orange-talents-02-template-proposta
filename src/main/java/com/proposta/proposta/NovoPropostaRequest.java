package com.proposta.proposta;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.proposta.endereco.EnderecoRequest;
import com.proposta.validator.CpfOrCnpj;

public class NovoPropostaRequest {
	
	@CpfOrCnpj @NotBlank
	private String documento;
	@NotBlank @Email
	private String email;
	@NotBlank
	private String nome;
	@NotNull @Valid
	private EnderecoRequest endereco;
	@NotNull @Positive
	private BigDecimal salario;
	
	public NovoPropostaRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotNull @Positive BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.salario = salario;
	}

	public EnderecoRequest getEndereco() {
		return endereco;
	}

	
	
	public String getDocumento() {
		return documento;
	}

	@Override
	public String toString() {
		return "NovoPropostaRequest [documento=" + documento + ", email=" + email + ", nome=" + nome + ", endereco="
				+ endereco + ", salario=" + salario + "]";
	}

	public Proposta toModel() {
		return new Proposta(documento, email, nome, salario, endereco);
	}

	public String getNome() {
		// TODO Auto-generated method stub
		return nome;
	}
	
}
