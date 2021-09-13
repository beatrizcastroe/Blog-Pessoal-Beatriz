package com.blogbeatriz.BlogPessoalBeatriz.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blogbeatriz.BlogPessoalBeatriz.exception.model.postagem.ExcecaoIdPostagemNaoExistente;
import com.blogbeatriz.BlogPessoalBeatriz.exception.model.postagem.ExcecaoIdUsuarioOuIdTemaNaoExistente;
import com.blogbeatriz.BlogPessoalBeatriz.exception.model.tema.ExcecaoIdTemaNaoExiste;
import com.blogbeatriz.BlogPessoalBeatriz.exception.model.usuario.ExcecaoEmailExistente;
import com.blogbeatriz.BlogPessoalBeatriz.exception.model.usuario.ExcecaoErroEmailouSenha;
import com.blogbeatriz.BlogPessoalBeatriz.exception.model.usuario.ExcecaoIdUsuarioNaoExistente;


/**
 * Classe responsavel pelo gerenciamento das respostas caso haja erro em requisiçoes.
 * 
 * @since 1.0
 * @author Beatriz
 */

@ControllerAdvice
public class ManipuladorDeExceptions {
	
	/**
	 * Método responsavel por alterar mensagem de erro do tipo response BAD_REQUEST
	 * tentativa de email cadastrado ja existe no sistema (400) para saída
	 * padronizada
	 *
	 * @param e do tipo ExcecaoEmailExistente
	 * @return Map com resposta padrão
	 */
	
	@ExceptionHandler(ExcecaoEmailExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> emailExistenteNotFoundHandler(ExcecaoEmailExistente e) {
		Map<String, String> response = new HashMap<>();
		response.put("Status", "400");
		response.put("Mensagem", "Digite um email diferente.");
		response.put("Problema", e.getMessage());

		return response;
	}
	
	/**
	 * Método responsavel por retornar excecao caso o id do usuario não seja
	 * identificado no sistema
	 * 
	 * @param e do tipo ExcecaoIdUsuarioNaoExistente
	 * @return Map com resposta padrão
	 */
	
	@ExceptionHandler(ExcecaoErroEmailouSenha.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> emailOuSenhaInvalidaNotFoundHandler(ExcecaoErroEmailouSenha e) {
		Map<String, String> response = new HashMap<>();
		response.put("Status", "400");
		response.put("Mensagem", "Digite as credenciais corretamente ou faça um novo cadastro.");
		response.put("Problema", e.getMessage());

		return response;
	}
	
	/**
	 * Método responsavel por retornar excecao caso o id do usuario não seja
	 * identificado no sistema
	 * 
	 * @param e do tipo ExcecaoIdUsuarioNaoExistente
	 * @return Map com resposta padrão
	 */
	
	@ExceptionHandler(ExcecaoIdUsuarioNaoExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> idUsuarioInvalidoNotFoundHandler(ExcecaoIdUsuarioNaoExistente e) {
		Map<String, String> response = new HashMap<>();
		response.put("Status", "400");
		response.put("Mensagem", "Digite um ID válido.");
		response.put("Problema", e.getMessage());

		return response;
	}
	
	/**
	 * Método responsavel por retornar excecao caso o id do tema não seja
	 * identificado no sistema
	 * 
	 * @param e do tipo ExcecaoIdTemaNaoExiste
	 * @return Map com resposta padrão
	 */
	
	@ExceptionHandler(ExcecaoIdTemaNaoExiste.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> idTemaInvalidoNotFoundHandler(ExcecaoIdTemaNaoExiste e) {
		Map<String, String> response = new HashMap<>();
		response.put("Status", "400");
		response.put("Mensagem", "Digite um ID de tema válido como parâmetro.");
		response.put("Problema", e.getMessage());

		return response;
	}
	
	/**
	 * Método responsavel por retornar excecao caso o id da postagem não seja
	 * identificado no sistema
	 * 
	 * @param e do tipo ExcecaoIdPostagemNaoExistente
	 * @return Map com resposta padrão
	 */
	
	@ExceptionHandler(ExcecaoIdPostagemNaoExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> idPostagemInvalidoNotFoundHandler(ExcecaoIdPostagemNaoExistente e) {
		Map<String, String> response = new HashMap<>();
		response.put("Status", "400");
		response.put("Mensagem", "Digite um ID de Postágem existente como parâmetro.");
		response.put("Problema", e.getMessage());

		return response;
	}
	
	/**
	 * Método responsavel por retornar excecao caso o id do usuario ou tema não seja
	 * identificado no sistema
	 * 
	 * @param e do tipo ExcecaoIdTemaNaoExiste
	 * @return Map com resposta padrão
	 */
	
	@ExceptionHandler(ExcecaoIdUsuarioOuIdTemaNaoExistente.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> idTemaInvalidoNotFoundHandler(ExcecaoIdUsuarioOuIdTemaNaoExistente e) {
		Map<String, String> response = new HashMap<>();
		response.put("Status", "400");
		response.put("Mensagem", "Digite um ID Tema ou ID Usuário existente como parâmetro.");
		response.put("Problema", e.getMessage());

		return response;
	}

}
