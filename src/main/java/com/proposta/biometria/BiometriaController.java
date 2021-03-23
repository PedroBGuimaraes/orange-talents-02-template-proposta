package com.proposta.biometria;

import java.net.URI;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.proposta.cartao.Cartao;
import com.proposta.cartao.CartaoRepository;

@RestController
@RequestMapping("/cartoes")
public class BiometriaController {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private EntityManager manager;

	@PostMapping("/{id}/biometria")
	@Transactional
	public ResponseEntity<?> postNovaBiometria(@PathVariable("id") String id, @RequestBody @Valid NovaBiometriaRequest request, UriComponentsBuilder uriBuilder){
		Optional<Cartao> buscaCartao = cartaoRepository.findById(id);
		if(buscaCartao.isEmpty()) {
			return ResponseEntity.badRequest().body("Esse cartão não está cadastrado em nossa base de dados");
		}
		Biometria novaBiometria = request.toModel(id, buscaCartao.get());
		manager.persist(novaBiometria);
		URI uri = uriBuilder.path("/biometria/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}
}
