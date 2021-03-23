package com.proposta.proposta;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import com.proposta.cartao.Cartao;
import com.proposta.endereco.Endereco;
import com.proposta.endereco.EnderecoRequest;
import com.proposta.status.StatusRequest;
import com.proposta.status.TipoStatus;

@Entity
public class Proposta {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private @NotBlank @Column(nullable = false, unique = true) String documento;
	private @NotBlank @NotNull @Email String email;
	private @NotBlank @NotNull String nome;
	private @NotNull @Positive BigDecimal salario;
	@OneToOne(mappedBy = "proposta", cascade = CascadeType.PERSIST)
	private @Valid Endereco endereco;
	@Enumerated(EnumType.STRING)
    private PropostaStatus propostaStatus;
	@OneToOne
	private Cartao cartao;
	
	@Deprecated
	public Proposta() {
		
	}

	public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotNull @Positive BigDecimal salario, @NotNull @Valid EnderecoRequest endereco) {
			this.email = email;
			this.nome = nome;
			TextEncryptor encryptors = Encryptors.text("proposta", new String(Hex.encode((this.email + this.nome).getBytes(StandardCharsets.UTF_8))));
	        this.documento = encryptors.encrypt(documento);
			this.salario = salario;
			this.endereco = endereco.toModel(this);
	}
	
	public void setPropostaStatus(TipoStatus tipo) {
		if(tipo.equals(TipoStatus.COM_RESTRICAO))
            this.propostaStatus = propostaStatus.NAO_ELEGIVEL;
        else if(tipo.equals(TipoStatus.SEM_RESTRICAO))
            this.propostaStatus = propostaStatus.ELEGIVEL;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Proposta [id=" + id + ", documento=" + documento + ", email=" + email + ", nome=" + nome + ", salario="
				+ salario + ", endereco=" + endereco + ", propostaStatus=" + propostaStatus + ", cartao=" + cartao
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public StatusRequest toStatus() {
		return new StatusRequest(id, documento, nome);
	}

	public Cartao getCartao() {
		return cartao;
	}

	public PropostaStatus getPropostaStatus() {
		return propostaStatus;
	}

	public String getDocumento() {
		TextEncryptor encryptors = Encryptors.text("proposta", new String(Hex.encode((this.email + this.nome).getBytes(StandardCharsets.UTF_8))));
        return encryptors.decrypt(this.documento);
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	
	
	
}
