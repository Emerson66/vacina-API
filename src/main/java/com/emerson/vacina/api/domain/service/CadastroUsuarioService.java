package com.emerson.vacina.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emerson.vacina.api.domain.exception.NegocioException;
import com.emerson.vacina.api.domain.model.Usuario;
import com.emerson.vacina.api.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public void validaAtributosUnicos(Usuario usuario) {
		
		Usuario cpfExistente = usuarioRepository.findByCpf(usuario.getCpf());
		Usuario emailExistente = usuarioRepository.findByEmail(usuario.getEmail());
		
		if((cpfExistente != null && !cpfExistente.equals(usuario))&&
				(emailExistente != null && !emailExistente.equals(usuario))) {
			throw new NegocioException("Já existe um usuário com esse Email ou CPF cadastrado!");
		}
	}
	 	
	public Usuario salvar(Usuario usuario) {
		validaAtributosUnicos(usuario);
		
		return usuarioRepository.save(usuario);
	}
	
	public void excluir(Long id) {
		usuarioRepository.deleteById(id);
	}
}
