package com.blogbeatriz.BlogPessoalBeatriz.exception.model.usuario;

public class ExcecaoEmailExistente extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ExcecaoEmailExistente(String emailExistente) {
		super("O email " + emailExistente + " já existe.");
	}

}
