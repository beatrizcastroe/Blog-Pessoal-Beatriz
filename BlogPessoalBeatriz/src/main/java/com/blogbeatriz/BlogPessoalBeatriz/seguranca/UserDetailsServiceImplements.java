package com.blogbeatriz.BlogPessoalBeatriz.seguranca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogbeatriz.BlogPessoalBeatriz.model.Usuario;
import com.blogbeatriz.BlogPessoalBeatriz.repository.UsuarioRepository;

@Service
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
