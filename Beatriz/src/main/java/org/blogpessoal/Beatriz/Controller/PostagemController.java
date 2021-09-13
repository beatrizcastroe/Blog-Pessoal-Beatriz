package org.blogpessoal.Beatriz.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.blogpessoal.Beatriz.Exception.Model.Postagem.ExcecaoIdPostagemNaoExistente;
import org.blogpessoal.Beatriz.Exception.Model.Postagem.ExcecaoIdUsuarioOuIdTemaNaoExistente;
import org.blogpessoal.Beatriz.Model.Postagem;
import org.blogpessoal.Beatriz.Repository.PostagemRepository;
import org.blogpessoal.Beatriz.Service.PostagemService;
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
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	@Autowired
	private PostagemService servicos;
	
	@GetMapping
	public ResponseEntity<List<Postagem>>GetAll (){
		return ResponseEntity.ok(repository.findAll());
	}
		
	@GetMapping("/id/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>>GetByTitulo (@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}

	@PostMapping("/salvar")
	public ResponseEntity<Object> cadastrarPostagem(@Valid @RequestBody Postagem novaPostagem) {
		Optional<?> objetoCadastrado = servicos.cadastrarPostagem(novaPostagem);

		if (objetoCadastrado.isPresent()) {
			return ResponseEntity.status(201).body(objetoCadastrado.get());
		} else {
			throw new ExcecaoIdUsuarioOuIdTemaNaoExistente();
		}

	}
	
	@PutMapping("/alterarpostagem")
	public ResponseEntity<Object> alterar(@Valid @RequestBody Postagem postagemParaAlterar) {
		Optional<Postagem> objetoAlterado = servicos.alterarPostagem(postagemParaAlterar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			throw new ExcecaoIdPostagemNaoExistente(postagemParaAlterar.getId());
		}
	}
	
	@DeleteMapping("/deletar/{id_postagem}")
	public ResponseEntity<Object> deletarPorId(@PathVariable(value = "id_postagem") Long idPostagem) {
		Optional<Postagem> objetoExistente = repository.findById(idPostagem);
		if (objetoExistente.isPresent()) {
			repository.deleteById(idPostagem);
			return ResponseEntity.status(200).build();
		} else {
			throw new ExcecaoIdPostagemNaoExistente(idPostagem);
		}
		
	}
}
