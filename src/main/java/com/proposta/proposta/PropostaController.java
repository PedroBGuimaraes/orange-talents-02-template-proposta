package com.proposta.proposta;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.proposta.aviso.Aviso;
import com.proposta.aviso.NovoAvisoRequest;
import com.proposta.aviso.NovoAvisoResponse;
import com.proposta.aviso.ResultadoNovoAviso;
import com.proposta.aviso.viagem.AvisoViagem;
import com.proposta.aviso.viagem.NovaViagemRequest;
import com.proposta.bloqueio.BloquearCartaoRequest;
import com.proposta.bloqueio.Bloqueio;
import com.proposta.bloqueio.StatusBloqueio;
import com.proposta.bloqueio.StatusBloqueioResponse;
import com.proposta.cartao.Cartao;
import com.proposta.cartao.CartaoRepository;
import com.proposta.cartao.CartoesRequest;
import com.proposta.cartao.CartoesResponse;
import com.proposta.cartao.DadosCartoes;
import com.proposta.carteira.Carteira;
import com.proposta.carteira.NovaCarteiraRequest;
import com.proposta.carteira.NovaCarteiraResponse;
import com.proposta.carteira.ResultadoCarteira;
import com.proposta.status.StatusCliente;
import com.proposta.status.StatusResponse;
import com.proposta.status.TipoStatus;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
    private StatusCliente statusCliente;
	
	@Autowired
	private DadosCartoes dadosCartoes;
	
	@Autowired
	private EntityManager manager;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	private List<Proposta> propostasSemCartao = new ArrayList();

	@PostMapping
	@Transactional
	public ResponseEntity<?> postNovaProposta(@RequestBody @Valid NovoPropostaRequest request, UriComponentsBuilder uriBuilder) {
		Optional<Proposta> validatorUniqueDocumento = propostaRepository.findByDocumento(request.getDocumento());
		if(validatorUniqueDocumento.isPresent()) {
			return ResponseEntity.status(422).body("Esse documento já está vinculado em outra proposta");
		}
		
		Proposta novaProposta = request.toModel();
		
		StatusResponse response = statusCliente.status(novaProposta.toStatus());
		novaProposta.setPropostaStatus(response.getResultadoSolicitacao());
		
		propostaRepository.save(novaProposta);
		if(response.getResultadoSolicitacao().equals(TipoStatus.SEM_RESTRICAO)) {
			propostasSemCartao.add(novaProposta);
		}
		
		URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(novaProposta.getId()).toUri();
		return ResponseEntity.created(uri).body(novaProposta.toString());
	}
	
	@PostMapping("cartoes/{id}/bloqueios")
	@Transactional
	public ResponseEntity<?> postBloqueiaCartao(@RequestBody @Valid BloquearCartaoRequest request, @PathVariable("id") String id){
		Optional<Cartao> cartaoBloquear = cartaoRepository.findById(id);
		if(cartaoBloquear.isEmpty()) {
			return ResponseEntity.badRequest().body("Cartao invalido");
		}
		
		StatusBloqueioResponse statusBloqueio = dadosCartoes.bloquearCartao(request, id);
		if(statusBloqueio.equals(StatusBloqueio.FALHA)) {
			return ResponseEntity.badRequest().body("Falha ao tentar bloquear");
		}
		
		cartaoBloquear.get().setAddBloqueio(new Bloqueio(request.getSistemaResponsavel(), true, cartaoBloquear.get()));
		manager.persist(cartaoBloquear.get().getBloqueios());
		return ResponseEntity.status(200).body(cartaoBloquear.get().toString());
	}
	
	@PostMapping("cartoes/{id}/bloqueios/avisos")
	@Transactional
	public ResponseEntity<?> postAvisoBloqueio(@RequestBody @Valid NovoAvisoRequest request, @PathVariable("id") String id){
		Optional<Cartao> cartaoAviso = cartaoRepository.findById(id);
		if(cartaoAviso.isEmpty()) {
			return ResponseEntity.badRequest().body("Cartao invalido");
		}
		
		NovoAvisoResponse novoAvisoResponse = dadosCartoes.novoAviso(request, id);
		if(novoAvisoResponse.getResultado().equals(ResultadoNovoAviso.FALHA)) {
			return ResponseEntity.badRequest().body("Falha ao fazer o aviso");
		}
		
		cartaoAviso.get().setAddAviso(new Aviso(request.getValidoAte(), request.getDestino(), cartaoAviso.get()));
		manager.persist(cartaoAviso.get().getAvisos());
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("cartoes/{id}/viagens")
	@Transactional
	public ResponseEntity<?> postViagem(@RequestBody @Valid NovaViagemRequest request, @PathVariable("id") String id){
		Optional<Cartao> cartaoAviso = cartaoRepository.findById(id);
		if(cartaoAviso.isEmpty()) {
			return ResponseEntity.badRequest().body("Cartao invalido");
		}
		
		NovoAvisoResponse novoAvisoResponse = dadosCartoes.novoAviso(new NovoAvisoRequest(request.getDestino(), request.getTermino()), id);
		if(novoAvisoResponse.getResultado().equals(ResultadoNovoAviso.FALHA)) {
			return ResponseEntity.badRequest().body("Falha ao fazer o aviso");
		}
		
		AvisoViagem novoAvisoViagem = request.toModel(cartaoAviso.get());
		manager.persist(novoAvisoViagem);
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("cartoes/{id}/carteiras")
	@Transactional
	public ResponseEntity<?> postCarteira(@RequestBody @Valid NovaCarteiraRequest request, @PathVariable("id") String id){
		Optional<Cartao> cartaoCarteira = cartaoRepository.findById(id);
		if(cartaoCarteira.isEmpty()) {
			return ResponseEntity.badRequest().body("Cartao invalido");
		}
		for (Carteira carteira : cartaoCarteira.get().getCarteiras()) {
			System.out.println(request.getTipoCarteira());
			System.out.println(carteira.getEmissor());
			if(carteira.getEmissor().equals("PAYPAL")) {
				return ResponseEntity.badRequest().body("Este cartao ja esta associado ao paypal");
			}
		}
		
		NovaCarteiraResponse carteiraResponse = dadosCartoes.novaCarteira(request, id);
		if(carteiraResponse.getResultado().equals(ResultadoCarteira.FALHA)) {
			return ResponseEntity.badRequest().body("Falha ao associar a carteira no cartao");
		}
		
		Carteira novaCarteira = request.toModel(cartaoCarteira.get(), carteiraResponse);
		manager.persist(novaCarteira);
		
		return ResponseEntity.ok().build();
	}
	
	
	// Gerar Responses para o retorno dos detalhes mais tarde.
	@GetMapping("/{id}/detalhes")
	public ResponseEntity<?> getAcompanhaProposta(@PathVariable("id") Long id){
		Optional<Proposta> propostaDetalhes = propostaRepository.findById(id);
		if(propostaDetalhes.isEmpty()) {
			return ResponseEntity.status(404).body("Não existe essa proposta");
		}
		return ResponseEntity.ok().body(propostaDetalhes.get().toString());
	}
	
	@Transactional
	@Scheduled(fixedDelayString = "${periodicidade.executa-operacao-cartao}")
	public void associoarCartao() {
		for (Proposta proposta : propostasSemCartao) {
			if(proposta.getCartao()==null&&proposta.getPropostaStatus().equals(PropostaStatus.ELEGIVEL)) {
				CartoesResponse cartoesResponse = dadosCartoes.associaCartao(new CartoesRequest(proposta.getDocumento(), proposta.getNome(), proposta.getId()));
				Cartao cartao = cartoesResponse.toModel();
				System.out.println(cartao.toString());
				System.out.println("Vai salvar?");
				manager.persist(cartao);
				proposta.setCartao(cartao);
				manager.merge(proposta);
				System.out.println("Salvou!");
			}
		}
	}
	
}
