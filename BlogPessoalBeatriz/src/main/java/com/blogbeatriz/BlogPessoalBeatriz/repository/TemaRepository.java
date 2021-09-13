package com.blogbeatriz.BlogPessoalBeatriz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogbeatriz.BlogPessoalBeatriz.model.Tema;

public interface TemaRepository extends JpaRepository <Tema, Long>{
	
	public List<Tema> findAllByTemaContainingIgnoreCase(String tema);

}
