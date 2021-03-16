package com.emerson.vacina.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emerson.vacina.api.domain.model.Usuario;
import com.emerson.vacina.api.domain.repository.UsuarioRepository;
import com.emerson.vacina.api.domain.service.CadastroUsuarioService;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	CadastroUsuarioService cadastroUsuarioService;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario adicionar(@Valid @RequestBody Usuario usuario){	
		return cadastroUsuarioService.salvar(usuario); 
	}
	
	@GetMapping("/{usuarioId}")
	public ResponseEntity<Usuario> buscar(@PathVariable Long usuarioId) {
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		
		if(usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{usuarioId}")
	public ResponseEntity<Usuario> atualizar(@Valid @PathVariable Long usuarioId,
			@RequestBody Usuario usuario){
		
		if(!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}
		usuario.setId(usuarioId);
		cadastroUsuarioService.salvar(usuario);
		
		return ResponseEntity.ok(usuario);
	}   
	
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Void> remover(@PathVariable Long usuarioId){
		
		if(!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroUsuarioService.excluir(usuarioId);
		
		return ResponseEntity.noContent().build();
	}
}
