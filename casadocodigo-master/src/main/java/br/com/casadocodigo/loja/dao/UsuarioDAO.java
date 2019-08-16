package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService{

	@PersistenceContext
	private EntityManager manager;

	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
				.setParameter("email", email)
				.getResultList();
		
		if(usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuario " + email + " não foi encontrado");
		}
		
		return usuarios.get(0);
		
	}
	
	public boolean findUserForEmail(String email) {
		List<Usuario> usuarios =  manager.createQuery("select distinct(p) from Usuario p where p.email = :email",
				Usuario.class)
				.setParameter("email",email)
				.getResultList();
		
		if(usuarios.isEmpty()) {
			return false;
		}
		
		return true;
	}
		
	public void gravar(Usuario usuario) {
		manager.persist(usuario);
		
	}
	
	public Usuario findUser(String email) {
		return manager.createQuery("select distinct(u) from Usuario u where email = :email", Usuario.class)
				.setParameter("email", email)
				.getSingleResult();
	}

	public List<Usuario> listarUsuarios() {
		return manager.createQuery("select distinct(p) from Usuario p join fetch p.roles",Usuario.class)
				.getResultList();
	}

	public void updateRole(Usuario user) {
		System.out.println("No DAO->"+user.getRoles());
		Usuario usuario = findUser(user.getEmail());
		usuario.setRoles(user.getRoles());
		
		gravar(usuario);
	}
	
}