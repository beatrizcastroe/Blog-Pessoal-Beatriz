package org.blogpessoal.Beatriz.Security;
import java.util.Optional;

import org.blogpessoal.Beatriz.Model.Usuario;
import org.blogpessoal.Beatriz.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplements implements UserDetailsService{
	
private @Autowired UsuarioRepository repositorio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> objetoOptional = repositorio.findByEmail(username);
		
		if(objetoOptional.isPresent()) {
			return new UserDetailsImplements(objetoOptional.get());			
			
		} else {
			throw new UsernameNotFoundException(username + " não existe!");
		}
	}

}
