package org.blogpessoal.Beatriz.Service;

import java.util.Optional;

import org.blogpessoal.Beatriz.Model.Postagem;
import org.blogpessoal.Beatriz.Model.Tema;
import org.blogpessoal.Beatriz.Repository.PostagemRepository;
import org.blogpessoal.Beatriz.Repository.TemaRepository;
import org.blogpessoal.Beatriz.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostagemService {
	
	private @Autowired PostagemRepository repositoryP;
	private @Autowired UsuarioRepository repositoryU;
	private @Autowired TemaRepository repositoryT;
	
	/**
	 * Método utilizado para cadastrar uma postagem nova no banco validando se o
	 * usuario criador é existente. O id do usuario criador e o id do tema deve ser
	 * passado dentro do objeto postagem para que a criação seja efetuada. Caso id
	 * do usuario não for passado ou não existir no banco retorna um
	 * Optional.empty()
	 * 
	 * @param novaPostagem do tipo Postagem
	 * @return Optional com Postagem
	 * @version 1.5
	 * @author Bia
	 */
	
	public Optional<?> cadastrarPostagem(Postagem novaPostagem) {
		Optional<Tema> objetoExistente = repositoryT.findById(novaPostagem.getTemaRelacionado().getIdTema());
		return repositoryU.findById(novaPostagem.getCriador().getIdUsuario()).map(usuarioExistente -> {
			
			if (objetoExistente.isPresent()) {
				novaPostagem.setCriador(usuarioExistente);
				novaPostagem.setTemaRelacionado(objetoExistente.get());
				return Optional.ofNullable(repositoryP.save(novaPostagem));
			} else {
				return Optional.empty();
			}
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
	
	/**
	 * Método utilizado para alterar uma postagem. O mesmo retorna um Optional com
	 * Postagem caso correto ou um Optional.empyt() caso id da postagem não exista.
	 * 
	 * @param postagemParaAlterar do tipo Postagem
	 * @return Optional com Postagem alterada
	 * @since 1.0
	 * @author Bia
	 */
	
	public Optional<Postagem> alterarPostagem(Postagem postagemParaAlterar) {
		return repositoryP.findById(postagemParaAlterar.getId()).map(postagemExistente -> {
			postagemExistente.setTitulo(postagemParaAlterar.getTitulo());
			postagemExistente.setTexto(postagemParaAlterar.getTexto());
			return Optional.ofNullable(repositoryP.save(postagemExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}


}
