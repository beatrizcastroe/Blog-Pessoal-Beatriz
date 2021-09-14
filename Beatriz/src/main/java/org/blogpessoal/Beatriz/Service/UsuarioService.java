package org.blogpessoal.Beatriz.Service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.blogpessoal.Beatriz.Model.Usuario;
import org.blogpessoal.Beatriz.Model.UsuarioLogin;
import org.blogpessoal.Beatriz.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	/**
	 * Método utilizado para criação de um novo usuario no sistema com criptografia da
	 * senha.
	 * 
	 * @param novoUsuario do tipo Usuario
	 * @return Um novo usuário salvo no repositório.
	 * @author Beatriz Castro.
	 * @version 1.5
	 * 
	 */
	
	public Optional<Object> cadastrarUsuario(Usuario novoUsuario) {
		return repository.findByEmail(novoUsuario.getEmail()).map(usuarioExistente -> {
			return Optional.empty();
			
		}).orElseGet(() -> {
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String result = encoder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(result);
			return Optional.ofNullable(repository.save(novoUsuario));
		});
	}
	
	/**
	 * Metodo utilizado para pegar credenciais do usuario com Token (Formato Basic).
	 * Esse método será utilizado para retornar ao front o token, útil para se ter
	 * acesso aos dados do usuario e mante-lo logado no sistema.
	 * 
	 * @param usuarioParaAutenticar do tipo UsuarioLogin. Necessario email e senha
	 * para validar.
	 * @return UsuarioLogin preenchido com informações mais o Token.
	 * @version 2.0
	 * @author Beatriz Castro.
	 */
	
	public Optional<?> Logar (UsuarioLogin usuarioParaAutenticar) {
		return repository.findByEmail(usuarioParaAutenticar.getEmail()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(usuarioParaAutenticar.getSenha(), usuarioExistente.getSenha())) {

				String estruturaBasic = usuarioParaAutenticar.getEmail() + ":" + usuarioParaAutenticar.getSenha();
				byte[] autorizacaoBase64 = Base64.encodeBase64(estruturaBasic.getBytes(Charset.forName("US-ASCII"))); 
				String autorizacaoHeader = "Basic " + new String(autorizacaoBase64); 

				usuarioParaAutenticar.setToken(autorizacaoHeader);
				usuarioParaAutenticar.setId(usuarioExistente.getIdUsuario());
				usuarioParaAutenticar.setNome(usuarioExistente.getNome());
				usuarioParaAutenticar.setSenha(usuarioExistente.getSenha());
				return Optional.ofNullable(usuarioParaAutenticar); // Usuario Credenciado

			} else {
				return Optional.empty(); // Senha esteja incorreta
			}

		}).orElseGet(() -> {
			return Optional.empty(); // Email não existente
		});
	}
	
	/**
	 * Metodo utilizado para alterar um usuario fornecido pelo FRONT; o mesmo
	 * retorna um Optional com entidade Usuario dentro e senha criptografada. Caso
	 * falho retorna um Optional vazio.
	 * 
	 * @param usuarioParaAlterar do tipo UsuarioLogin
	 * @return Optional com Usuario Alterado
	 * @version 1.0
	 * @author Beatriz Castro
	 */
	
	public Optional<?> alterarUsuario(UsuarioLogin usuarioParaAlterar) {
		return repository.findById(usuarioParaAlterar.getId()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(usuarioParaAlterar.getSenha());

			usuarioExistente.setNome(usuarioParaAlterar.getNome());
			usuarioExistente.setSenha(senhaCriptografada);
			return Optional.ofNullable(repository.save(usuarioExistente));
			
		}).orElseGet(() -> {
			
			return Optional.empty();
		});
	}
}