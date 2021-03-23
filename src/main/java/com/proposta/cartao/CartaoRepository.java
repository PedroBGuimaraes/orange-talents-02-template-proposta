package com.proposta.cartao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, String>{
	Optional<Cartao> findById(String id);
}
