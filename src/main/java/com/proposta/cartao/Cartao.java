package com.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proposta.aviso.Aviso;
import com.proposta.aviso.AvisosResponse;
import com.proposta.bloqueio.Bloqueio;
import com.proposta.bloqueio.BloqueioResponse;
import com.proposta.carteira.Carteira;
import com.proposta.carteira.CarteiraResponse;
import com.proposta.parcela.Parcela;
import com.proposta.parcela.ParcelaResponse;
import com.proposta.renegociacao.Renegociacao;
import com.proposta.renegociacao.RenegociacaoResponse;
import com.proposta.vencimento.Vencimento;
import com.proposta.vencimento.VencimentoResponse;

@Entity
public class Cartao {
	@Id
	private String id;
	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyy-MM-dd@HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "America/Sao_Paulo")
	private LocalDate emitidoEm;
	
	@Column(nullable = false)
	private String titular;
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    @LazyCollection(value = LazyCollectionOption.FALSE)
	private List<Bloqueio> bloqueios;
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    @LazyCollection(value = LazyCollectionOption.FALSE)
	private List<Aviso> avisos;
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    @LazyCollection(value = LazyCollectionOption.FALSE)
	private List<Carteira> carteiras;
	
	@OneToMany(mappedBy = "cartao")
    @LazyCollection(value = LazyCollectionOption.FALSE)
	private List<Parcela> parcelas;
	
	@Column(nullable = false)
	private BigDecimal limite;
	
	@OneToOne(mappedBy = "cartao", cascade = CascadeType.PERSIST)
	private Renegociacao renegociacao;
	
	@OneToOne(mappedBy = "cartao", cascade = CascadeType.PERSIST)
	private Vencimento vencimento;
	
	@Column(nullable = false)
	private String idProposta;
	
	@Deprecated
	public Cartao() {
		
	}
	
	public Cartao(String id, LocalDate emitidoEm, String titular, List<BloqueioResponse> bloqueios,
			List<AvisosResponse> avisos, List<CarteiraResponse> carteiras, List<ParcelaResponse> parcelas,
			BigDecimal limite, RenegociacaoResponse renegociacao, VencimentoResponse vencimento, String idProposta) {
				this.id = id;
				this.emitidoEm = emitidoEm;
				this.titular = titular;
				this.bloqueios = bloqueios.stream().map(Bloqueio -> Bloqueio.toModel()).collect(Collectors.toList());
				this.avisos = avisos.stream().map(Aviso -> Aviso.toModel()).collect(Collectors.toList());
				this.carteiras = carteiras.stream().map(Carteira -> Carteira.toModel()).collect(Collectors.toList());
				this.parcelas = parcelas.stream().map(Parcela -> Parcela.toModel()).collect(Collectors.toList());
				this.limite = limite;
				
				if(!(renegociacao == null)) {
					this.renegociacao = renegociacao.toModel();
				}
				
				this.vencimento = vencimento.toModel();
				this.idProposta = idProposta;
	}

	@Override
	public String toString() {
		return "Cartao [id=" + id + ", emitidoEm=" + emitidoEm + ", titular=" + titular + ", bloqueios=" + bloqueios
				+ ", avisos=" + avisos + ", carteiras=" + carteiras + ", parcelas=" + parcelas + ", limite=" + limite
				+ ", renegociacao=" + renegociacao + ", vencimento=" + vencimento + ", idProposta=" + idProposta + "]";
	}

	public void setAddBloqueio(Bloqueio bloqueio) {
		this.bloqueios.add(bloqueio);
	}
	
	public void setAddAviso(Aviso aviso) {
		this.avisos.add(aviso);
	}

	public Bloqueio getBloqueios() {
		return bloqueios.get(bloqueios.size()-1);
	}
	
	public Aviso getAvisos() {
		return avisos.get(avisos.size()-1);
	}

	public List<Carteira> getCarteiras() {
		return carteiras;
	}
	
	
}
