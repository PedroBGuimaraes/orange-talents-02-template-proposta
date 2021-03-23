package com.proposta.carteira;

public class NovaCarteiraResponse {
	private ResultadoCarteira resultado;
	private String id;
	public NovaCarteiraResponse(ResultadoCarteira resultado, String id) {
		super();
		this.resultado = resultado;
		this.id = id;
	}
	public ResultadoCarteira getResultado() {
		return resultado;
	}
	public String getId() {
		return id;
	}
	
	
}
