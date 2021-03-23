package com.proposta.biometria;

import javax.validation.constraints.NotBlank;

import com.proposta.cartao.Cartao;

public class NovaBiometriaRequest {
	@NotBlank
	private String fingerprint;

	public String getFingerprint() {
		return fingerprint;
	}

	public Biometria toModel(String id, Cartao cartao) {
		// TODO Auto-generated method stub
		return new Biometria(id, fingerprint, cartao);
	}
	
}
