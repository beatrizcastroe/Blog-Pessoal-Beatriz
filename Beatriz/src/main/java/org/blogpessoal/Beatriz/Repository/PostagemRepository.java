package org.blogpessoal.Beatriz.Repository;

import java.util.List;

import org.blogpessoal.Beatriz.Model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);

}
