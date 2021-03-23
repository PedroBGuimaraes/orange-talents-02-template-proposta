package com.proposta.bloqueio;

import javax.validation.constraints.NotBlank;

public class BloquearCartaoRequest {
	private @NotBlank String sistemaResponsavel;

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
	
}
