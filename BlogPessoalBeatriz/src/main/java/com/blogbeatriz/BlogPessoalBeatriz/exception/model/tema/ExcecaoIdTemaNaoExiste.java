package com.blogbeatriz.BlogPessoalBeatriz.exception.model.tema;

public class ExcecaoIdTemaNaoExiste extends RuntimeException {
	
	/**
	 * Classe reponsavel por construtor de excecao caso id de tema seja invalido no
	 * sistema
	 * 
	 * @version 1.0
	 * @author Beatriz
	 *
	 */
	
	private static final long serialVersionUID = 1L;

	public ExcecaoIdTemaNaoExiste(Long idTema) {
		super("O ID " + idTema + " não existe.");
	}

}
