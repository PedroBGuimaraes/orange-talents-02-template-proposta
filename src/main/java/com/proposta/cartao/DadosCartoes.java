package com.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.proposta.aviso.NovoAvisoRequest;
import com.proposta.aviso.NovoAvisoResponse;
import com.proposta.bloqueio.BloquearCartaoRequest;
import com.proposta.bloqueio.StatusBloqueioResponse;
import com.proposta.carteira.NovaCarteiraRequest;
import com.proposta.carteira.NovaCarteiraResponse;

@FeignClient(name = "cartoes", url = "${associar.cartao.elegivel}")
public interface DadosCartoes {

	@PostMapping("/cartoes")
	CartoesResponse associaCartao(@RequestBody CartoesRequest request);
	
	@PostMapping("/cartoes/{id}/bloqueios")
	StatusBloqueioResponse bloquearCartao(@RequestBody BloquearCartaoRequest request, @PathVariable String id);
	
	@PostMapping("/cartoes/{id}/avisos")
	NovoAvisoResponse novoAviso(@RequestBody NovoAvisoRequest request, @PathVariable String id);
	
	@PostMapping("/cartoes/{id}/carteiras")
	NovaCarteiraResponse novaCarteira(@RequestBody NovaCarteiraRequest request, @PathVariable String id);
	
}
