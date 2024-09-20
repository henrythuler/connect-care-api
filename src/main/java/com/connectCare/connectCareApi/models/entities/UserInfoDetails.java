package com.connectCare.connectCareApi.models.entities;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfoDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private String email; 
    private String password; 
    private List<GrantedAuthority> authorities;
    
    public UserInfoDetails(Usuario usuario) { 
        email = usuario.getEmail(); 
        password = usuario.getPassword(); 
        authorities = Arrays.stream(usuario.getRole().split(",")) 
                .map(SimpleGrantedAuthority::new) 
                .collect(Collectors.toList()); 
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}
	
}
