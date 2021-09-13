package org.blogpessoal.Beatriz.Exception.Model.Postagem;

/**
 * Classe reponsavel por construtor de excecao caso id de postagem seja invalido
 * no sistema
 * 
 * @since 1.0
 * @author Bia
 *
 */

public class ExcecaoIdPostagemNaoExistente extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ExcecaoIdPostagemNaoExistente(Long idPostagem) {
		super("O ID " + idPostagem + " da postagem é inválido.");
	}

}
