package com.blogbeatriz.BlogPessoalBeatriz.exception.model.postagem;

/**
 * Classe reponsavel por construtor de excecao caso id Usuario ou de tema seja
 * invalido no sistema
 * 
 * @version 1.0
 * @author Bia
 *
 */

public class ExcecaoIdUsuarioOuIdTemaNaoExistente extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcecaoIdUsuarioOuIdTemaNaoExistente() {
		super("O ID do Usuario ou do Tema é inválido.");
	}
	
}
