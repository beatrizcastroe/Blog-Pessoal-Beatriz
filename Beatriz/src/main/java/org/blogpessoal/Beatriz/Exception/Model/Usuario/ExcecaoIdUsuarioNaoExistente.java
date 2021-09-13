package org.blogpessoal.Beatriz.Exception.Model.Usuario;

public class ExcecaoIdUsuarioNaoExistente extends RuntimeException{
	

	/**
	 * Classe reponsavel por construtor de excecao caso id de usuario seja invalido
	 * no sistema
	 * 
	 * @since 1.0
	 * @author Beatriz
	 *
	 */

		private static final long serialVersionUID = 1L;

		public ExcecaoIdUsuarioNaoExistente(Long idUsuario) {
			super("O ID " + idUsuario + " não existe.");
		}

}
