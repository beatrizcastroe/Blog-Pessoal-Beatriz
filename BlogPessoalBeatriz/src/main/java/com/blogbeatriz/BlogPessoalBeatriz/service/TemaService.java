package com.blogbeatriz.BlogPessoalBeatriz.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogbeatriz.BlogPessoalBeatriz.model.Tema;
import com.blogbeatriz.BlogPessoalBeatriz.repository.TemaRepository;

@Service
public class TemaService {
	
	@Autowired
	private TemaRepository repository;
	
	/**
	 * Método utilizado para alterar um tema. O mesmo retorna um Optional com Tema
	 * caso correto ou um Optional.empyt() caso id do tema não exista.
	 * 
	 * @param temaParaAlterar do tipo Tema
	 * @return Optional com Tema alterado
	 * @version 1.0
	 * @author Beatriz Castro
	 */
	
	public Optional<Tema> alterarTema(Tema temaParaAlterar) {
		return repository.findById(temaParaAlterar.getIdTema()).map(temaExistente -> {
			
			temaExistente.setTema(temaParaAlterar.getTema());
			return Optional.ofNullable(repository.save(temaExistente));
			
		}).orElseGet(() -> {
			
			return Optional.empty();
		});
	}

}
