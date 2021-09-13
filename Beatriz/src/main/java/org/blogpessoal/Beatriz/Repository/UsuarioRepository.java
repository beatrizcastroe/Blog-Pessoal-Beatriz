package org.blogpessoal.Beatriz.Repository;

import java.util.List;
import java.util.Optional;

import org.blogpessoal.Beatriz.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByEmail(String email);

	List<Usuario> findAllByNomeContainingIgnoreCase(String nome);

}
