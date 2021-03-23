package com.proposta.cartao;

public class CartoesRequest {
	private String documento;
	private String nome;
	private Long idProposta;
	public CartoesRequest(String documento, String nome, Long idProposta) {
		super();
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}
	public String getDocumento() {
		return documento;
	}
	public String getNome() {
		return nome;
	}
	public Long getIdProposta() {
		return idProposta;
	}
	
	

}
