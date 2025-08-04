package com.kh.security.model.vo;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class User implements UserDetails {

	private String id;
	private String pwd;
	private String name;
	private String role;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role));
	}
	
	@Override
	public String getPassword() {
		return pwd;
	}
	
	@Override
	public String getUsername() {
		return this.id; // this.id 대신 그냥 id 가능!
	}
	
}
