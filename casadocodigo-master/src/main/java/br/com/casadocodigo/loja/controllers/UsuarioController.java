package br.com.casadocodigo.loja.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidator;

@Controller
@RequestMapping("/usuarios")
@Transactional
public class UsuarioController {

	@Autowired
	private UsuarioDAO userDao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidator());
	}

	@RequestMapping("/form")
	public ModelAndView form(Usuario Usuario) {
		ModelAndView modelAndView = new ModelAndView("usuarioForm");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	//@CacheEvict(value = "produtosHome", allEntries = true)
	public ModelAndView formUser(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return form(usuario);
		}
			
		if(userDao.findUserForEmail(usuario.getEmail())){
			redirectAttributes.addFlashAttribute("falha", "Usuário já existe");
			return new ModelAndView("redirect:/usuarios/form");
		}
		
		String senhaCriptografado = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografado);
		
		usuario.setRoles(Arrays.asList(new Role("ROLE_USER")));

		userDao.gravar(usuario);
		
		redirectAttributes.addFlashAttribute("senha", "Usuario cadastrado com sucesso");
		return new ModelAndView("redirect:/usuarios");

		
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listarUsers() {
		List<Usuario> usuarios = userDao.listarUsuarios();
		ModelAndView modelAndView = new ModelAndView("listaUsuario");
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
	}

	
	@RequestMapping(value="editarRole")
	public ModelAndView checkBox(String email,String nome) {
		
		System.out.println("email: "+email+" nome: "+nome);
		Usuario usuarios = userDao.findUser(email);
		
		ModelAndView modelAndView = new ModelAndView("role");
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;

	}

	@RequestMapping(value="editado")
	public ModelAndView updateRole(Usuario usuario, RedirectAttributes redirectAttributes) {
		
		if (usuario.getRoles().isEmpty()) {
			redirectAttributes.addFlashAttribute("falha", "Deve possuir alguma permissao");
			return new ModelAndView("redirect:/usuarios");
		}
			
		userDao.updateRole(usuario);
		
		redirectAttributes.addFlashAttribute("senha", "Permissao alterada com sucesso");
		return new ModelAndView("redirect:/usuarios");
	}
	
	
	
}
