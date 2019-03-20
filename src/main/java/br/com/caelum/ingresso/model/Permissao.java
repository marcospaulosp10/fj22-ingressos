package br.com.caelum.ingresso.model;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Permissao implements GrantedAuthority {
	
	@Id
	private String nome;
	
	public Permissao(String nome) {
		this.nome = nome;
	}
	
	public Permissao() {
		
	}
	
	@Override
	public String getAuthority() {
		return nome;
	}
}
