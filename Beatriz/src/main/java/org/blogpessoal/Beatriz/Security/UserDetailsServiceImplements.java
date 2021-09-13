package org.blogpessoal.Beatriz.Security;

import java.util.Optional;

import org.blogpessoal.Beatriz.Model.Usuario;
import org.blogpessoal.Beatriz.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImplements implements UserDetailsService {
	
	@Autowired 
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		Optional <Usuario> user = repository.findByEmail(username);
		
		if (user.isPresent()) {
			return new UserDetailsImplements(user.get());	
			
		} else {
			
			throw new UsernameNotFoundException("O email " + username + " não existe!");
		}
	}
}
