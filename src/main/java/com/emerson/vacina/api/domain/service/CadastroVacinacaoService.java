package com.emerson.vacina.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emerson.vacina.api.domain.exception.NegocioException;
import com.emerson.vacina.api.domain.model.Usuario;
import com.emerson.vacina.api.domain.model.Vacinacao;
import com.emerson.vacina.api.domain.repository.UsuarioRepository;
import com.emerson.vacina.api.domain.repository.VacinacaoRepository;

@Service
public class CadastroVacinacaoService {
	
	@Autowired
	VacinacaoRepository vacinacaoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Vacinacao salvar(Vacinacao vacinacao) {
		Usuario usuario = usuarioRepository.findById(vacinacao.getUsuario().getId())
				.orElseThrow(() -> new NegocioException("Usuario não encontrado"));
		vacinacao.setUsuario(usuario);
		
		vacinacao.setEmail(vacinacao.getUsuario().getEmail());
		return vacinacaoRepository.save(vacinacao);
	}
	
	public void excluir(Long id) {
		vacinacaoRepository.deleteById(id);
	}
}
