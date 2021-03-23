package com.proposta.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.proposta.cartao.Cartao;

public class NovaCarteiraRequest {
	@NotBlank @Email
	private String email;
	private TipoCarteira carteira;
	public NovaCarteiraRequest(@NotBlank @Email String email, @NotBlank TipoCarteira carteira) {
		super();
		this.email = email;
		this.carteira = carteira;
	}
	public String getEmail() {
		return email;
	}
	public TipoCarteira getTipoCarteira() {
		return carteira;
	}
	public Carteira toModel(Cartao cartao, NovaCarteiraResponse carteiraResponse) {
		// TODO Auto-generated method stub
		return new Carteira(carteiraResponse.getId(), email, carteira.toString(), cartao);
	}
	
}
