package com.proposta.status;

public class StatusResponse {
	private final Long idProposta;
	private final String documento;
	private final String nome;
	private final TipoStatus resultadoSolicitacao;

	public StatusResponse(Long idProposta, String documento, String nome, TipoStatus resultadoSolicitacao) {
		this.idProposta = idProposta;
		this.documento = documento;
		this.nome = nome;
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public TipoStatus getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}
}