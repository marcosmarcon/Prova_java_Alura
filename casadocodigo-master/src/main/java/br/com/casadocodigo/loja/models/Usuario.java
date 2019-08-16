package br.com.casadocodigo.loja.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import br.com.casadocodigo.loja.models.Role;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String email;
	
	@NotNull @Size(min=5)
	private String senha;

	@Size(min=5)
	private String senhaRepetida;
	
	private String nome;
	
	
	@ManyToMany(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
	private List<Role> roles = new ArrayList<>();
	
	public static boolean checkSenha(Usuario usuario) {
		if(usuario.getSenha().equals(usuario.getSenhaRepetida())) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((senhaRepetida == null) ? 0 : senhaRepetida.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (senhaRepetida == null) {
			if (other.senhaRepetida != null)
				return false;
		} else if (!senhaRepetida.equals(other.senhaRepetida))
			return false;
   
		return true;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public String getSenhaRepetida() {
		//return new BCryptPasswordEncoder().encode(senhaRepetida);
		return senhaRepetida ;
	}

	public String getSenha() {
		//return new BCryptPasswordEncoder().encode(senha);
		return senha;
	}
	
	public void setSenhaRepetida(String senhaRepetida) {
		//this.senhaRepetida = senhaRepetida;
		//String senhaCriptografado = new BCryptPasswordEncoder().encode(senhaRepetida);
		//senhaRepetida = senhaCriptografado;
		this.senhaRepetida = senhaRepetida;
		 
		 
	}	
	
	public void setSenha(String senha) {
		//this.senha = senha;
		//String senhaCriptografado = new BCryptPasswordEncoder().encode(senha);
		//senha = senhaCriptografado;
		this.senha = senha;
	}
	
	@Override
	public String getPassword() {
		return this.senha;
	}

	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}


	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
