package com.blogbeatriz.BlogPessoalBeatriz.exception.model.usuario;

public class ExcecaoErroEmailouSenha extends RuntimeException{
	
	/**
	 * Classe reponsavel por construtor de excecao caso email e senha seja invalido
	 * ao tentar recuperar credenciais do usuario
	 * 
	 * @since 1.0
	 * @author Beatriz
	 *
	 */
		private static final long serialVersionUID = 1L;

		public ExcecaoErroEmailouSenha () {
			super ("Email ou senha inválido(s).");
		}

}
