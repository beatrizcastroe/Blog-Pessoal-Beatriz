package org.blogpessoal.Beatriz.Repository;

import java.util.List;

import org.blogpessoal.Beatriz.Model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository <Tema, Long> {
	
	public List<Tema> findAllByTemaContainingIgnoreCase(String tema);

}
