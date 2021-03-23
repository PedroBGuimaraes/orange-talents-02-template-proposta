package com.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.proposta.aviso.AvisosResponse;
import com.proposta.bloqueio.BloqueioResponse;
import com.proposta.carteira.CarteiraResponse;
import com.proposta.parcela.ParcelaResponse;
import com.proposta.renegociacao.RenegociacaoResponse;
import com.proposta.vencimento.VencimentoResponse;


public class CartoesResponse {
	private String id;
	private LocalDate emitidoEm;
	private String titular;
	private List<BloqueioResponse> bloqueios;
	private List<AvisosResponse> avisos;
	private List<CarteiraResponse> carteiras;
	private List<ParcelaResponse> parcelas;
	private BigDecimal limite;
	private RenegociacaoResponse renegociacao;
	private VencimentoResponse vencimento;
	private String idProposta;
	
	@Deprecated
	public CartoesResponse() {
		
	}

	public CartoesResponse(String id, LocalDate emitidoEm, String titular, List<BloqueioResponse> bloqueios,
			List<AvisosResponse> avisos, List<CarteiraResponse> carteiras, List<ParcelaResponse> parcelas,
			BigDecimal limite, RenegociacaoResponse renegociacao, VencimentoResponse vencimento, String idProposta) {
		super();
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.bloqueios = bloqueios;
		this.avisos = avisos;
		this.carteiras = carteiras;
		this.parcelas = parcelas;
		this.limite = limite;
		this.renegociacao = renegociacao;
		this.vencimento = vencimento;
		this.idProposta = idProposta;
	}
	
	public Cartao toModel() {
		return new Cartao(id, emitidoEm, titular, bloqueios, avisos, carteiras, parcelas, limite, renegociacao, vencimento, idProposta);
	}

	public String getId() {
		return id;
	}

	public LocalDate getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public List<BloqueioResponse> getBloqueios() {
		return bloqueios;
	}

	public List<AvisosResponse> getAvisos() {
		return avisos;
	}

	public List<CarteiraResponse> getCarteiras() {
		return carteiras;
	}

	public List<ParcelaResponse> getParcelas() {
		return parcelas;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public RenegociacaoResponse getRenegociacao() {
		return renegociacao;
	}

	public VencimentoResponse getVencimento() {
		return vencimento;
	}

	public String getIdProposta() {
		return idProposta;
	}
	
	
	
}
