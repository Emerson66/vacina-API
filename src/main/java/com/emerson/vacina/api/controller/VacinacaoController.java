package com.emerson.vacina.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emerson.vacina.api.domain.model.Vacinacao;
import com.emerson.vacina.api.domain.repository.VacinacaoRepository;
import com.emerson.vacina.api.domain.service.CadastroVacinacaoService;

@RestController
@RequestMapping("/vacinas")
public class VacinacaoController {
	
	@Autowired
	VacinacaoRepository vacinacaoRepository;
	@Autowired
	CadastroVacinacaoService cadastroVacinacaoService;
	
	@PostMapping
	public Vacinacao criar(@Valid @RequestBody Vacinacao vacinacao) {
		
		return cadastroVacinacaoService.salvar(vacinacao);
	}
	
	@GetMapping("/{vacinacaoId}")
	public ResponseEntity<Vacinacao> buscar(@PathVariable Long vacinacaoId) {
		Optional<Vacinacao> vacinacao = vacinacaoRepository.findById(vacinacaoId);
		
		if(vacinacao.isPresent()) {
			return ResponseEntity.ok(vacinacao.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{vacinacaoId}")
	public ResponseEntity<Vacinacao> atualizar(@Valid @PathVariable Long vacinacaoId,
			@RequestBody Vacinacao vacinacao){
		
		if(!vacinacaoRepository.existsById(vacinacaoId)) {
			return ResponseEntity.notFound().build();
		}
		vacinacao.setId(vacinacaoId);
		cadastroVacinacaoService.salvar(vacinacao);
		
		return ResponseEntity.ok(vacinacao);
	}   
	
	@DeleteMapping("/{vacinacaoId}")
	public ResponseEntity<Void> remover(@PathVariable Long vacinacaoId){
		
		if(!vacinacaoRepository.existsById(vacinacaoId)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroVacinacaoService.excluir(vacinacaoId);
		
		return ResponseEntity.noContent().build();
	}
}
