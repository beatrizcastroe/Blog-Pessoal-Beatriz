package org.blogpessoal.Beatriz.Controller;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.blogpessoal.Beatriz.Exception.Model.Usuario.ExcecaoEmailExistente;
import org.blogpessoal.Beatriz.Exception.Model.Usuario.ExcecaoErroEmailouSenha;
import org.blogpessoal.Beatriz.Exception.Model.Usuario.ExcecaoIdUsuarioNaoExistente;
import org.blogpessoal.Beatriz.Model.Usuario;
import org.blogpessoal.Beatriz.Model.UsuarioLogin;
import org.blogpessoal.Beatriz.Repository.UsuarioRepository;
import org.blogpessoal.Beatriz.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Métodos da controller: pegarTodos, login, cadastrar, alterar, buscarpornome, buscarporid, deletar

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> pegarTodos() {
		List<Usuario> objetoLista = repository.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
	
	@PutMapping ("/login")
	public ResponseEntity<Object> Login (@Valid @RequestBody UsuarioLogin loginSenha) {
		Optional <?> objetoLogin = service.Logar(loginSenha);
		
		if (objetoLogin.isPresent()) {
			return ResponseEntity.status(201).body(objetoLogin.get());
		} else {
			throw new ExcecaoErroEmailouSenha();
		}
	}

	@PostMapping ("/cadastro")
	public ResponseEntity<Object> cadastrar (@Valid @RequestBody Usuario novoUsuario) {
		Optional<Object> objetoCadastro = service.cadastrarUsuario(novoUsuario);

		if (objetoCadastro.isPresent()) {
			return ResponseEntity.status(201).body(objetoCadastro.get());
		} else {
			throw new ExcecaoEmailExistente(novoUsuario.getEmail());
		}
	}
	
	@PutMapping("/alterarusuario")
	public ResponseEntity<Object> alterar(@Valid @RequestBody UsuarioLogin usuarioParaAlterar) {
		Optional<?> objetoAlterado = service.alterarUsuario(usuarioParaAlterar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			throw new ExcecaoErroEmailouSenha();
			
		}
	}
	
	@GetMapping("/pesquisapornome")
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(defaultValue = "") String nome) {
		List<Usuario> objetoLista = repository.findAllByNomeContainingIgnoreCase(nome);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
			
		} else {
			
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
	
	@GetMapping("/buscarid/{idUsuario}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long idUsuario) {
		Optional<Usuario> objetoUsuario = repository.findById(idUsuario);
		
		if (objetoUsuario.isPresent()) {
			return ResponseEntity.status(200).body(objetoUsuario.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}
		
		
	@DeleteMapping("/deletar/{idUsuario}")
	public ResponseEntity<Object> deleteById (@PathVariable Long idUsuario) {
		Optional<Usuario> objetoExistente = repository.findById(idUsuario);
		if (objetoExistente.isPresent()) {
			repository.deleteById(idUsuario);
			return ResponseEntity.status(200).build();
		} else {
			throw new ExcecaoIdUsuarioNaoExistente(idUsuario);
		}
	}
	
	

}
