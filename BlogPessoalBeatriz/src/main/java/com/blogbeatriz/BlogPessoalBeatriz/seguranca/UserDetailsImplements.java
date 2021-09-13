package com.blogbeatriz.BlogPessoalBeatriz.seguranca;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.blogbeatriz.BlogPessoalBeatriz.model.Usuario;

public class UserDetailsImplements implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String senha;
	private List<GrantedAuthority> autorizacoes;
	
	
	public UserDetailsImplements (Usuario user) {
		this.email = user.getEmail ();
		this.senha = user.getSenha ();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return autorizacoes;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
