package com.emerson.vacina.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emerson.vacina.api.domain.model.Vacinacao;
@Repository
public interface VacinacaoRepository extends JpaRepository<Vacinacao, Long>{
	
}
