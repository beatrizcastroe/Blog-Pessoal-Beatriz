package com.blogbeatriz.BlogPessoalBeatriz.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.blogbeatriz.BlogPessoalBeatriz.exception.model.tema.ExcecaoIdTemaNaoExiste;
import com.blogbeatriz.BlogPessoalBeatriz.model.Tema;
import com.blogbeatriz.BlogPessoalBeatriz.repository.TemaRepository;
import com.blogbeatriz.BlogPessoalBeatriz.service.TemaService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/temas")
public class TemaController {
	
	@Autowired
	private TemaRepository repository;
	@Autowired
	private TemaService service;
	
	@GetMapping
	public ResponseEntity<List<Tema>> findAll (){
		List<Tema> listaTema = repository.findAll();

		if (listaTema.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaTema);
		}
	}
	
	@GetMapping("/id/{idTema}")
	public ResponseEntity<Tema> findById (@PathVariable Long idTema){
		return repository.findById(idTema).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/temanome/{tema}")
	public ResponseEntity<List<Tema>> findByTema (@PathVariable String tema){
		return ResponseEntity.ok(repository.findAllByTemaContainingIgnoreCase(tema));
	}
	
	@PostMapping("/novotema")
	public ResponseEntity<Tema> postTema (@Valid @RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
	
	@PutMapping("/alterartema")
	public ResponseEntity<Object> alterar(@Valid @RequestBody Tema temaParaAlterar) {
		Optional<Tema> objetoAlterado = service.alterarTema(temaParaAlterar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			throw new ExcecaoIdTemaNaoExiste(temaParaAlterar.getIdTema());
		}
	}
	
	@DeleteMapping("/deletartema/{idTema}")
	public ResponseEntity<Object> deletarPorId(@PathVariable Long idTema) {
		Optional<Tema> objetoExistente = repository.findById(idTema);
		if (objetoExistente.isPresent()) {
			
			repository.deleteById(idTema);
			return ResponseEntity.status(200).build();
			
		} else {
			
			throw new ExcecaoIdTemaNaoExiste(idTema);
		}

	}
}
