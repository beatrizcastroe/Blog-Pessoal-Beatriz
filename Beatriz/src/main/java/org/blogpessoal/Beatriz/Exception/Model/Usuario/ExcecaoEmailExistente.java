package org.blogpessoal.Beatriz.Exception.Model.Usuario;

public class ExcecaoEmailExistente extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcecaoEmailExistente(String emailExistente) {
		super("O email " + emailExistente + " já existe.");
	}

}
