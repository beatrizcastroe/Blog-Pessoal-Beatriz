package com.blogbeatriz.BlogPessoalBeatriz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogbeatriz.BlogPessoalBeatriz.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByEmail(String email);

	List<Usuario> findAllByNomeContainingIgnoreCase(String nome);

}
