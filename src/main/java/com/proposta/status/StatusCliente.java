package com.proposta.status;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "status", url = "${solicitacao.cartao.status}")
public interface StatusCliente {

	@PostMapping("/api/solicitacao")
	StatusResponse status(@RequestBody StatusRequest request);
}