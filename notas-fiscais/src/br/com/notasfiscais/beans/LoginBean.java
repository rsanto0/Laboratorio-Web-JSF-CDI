package br.com.notasfiscais.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.notasfiscais.dao.UsuarioDAO;
import br.com.notasfiscais.modelo.Usuario;

@SessionScoped
@ManagedBean(name = "loginBean")
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;

	@PostConstruct
	public void inicializarObjetos() {
		this.usuario = new Usuario();
	}

	public String efetualogin() {

		UsuarioDAO dao = new UsuarioDAO();
		boolean loginValido = dao.existe(this.usuario);
		System.out.println("O login é válido: " + loginValido);
		if (loginValido)
			return "produto?faces-redirect=true";
		else
			this.usuario = new Usuario();
			return "login";
	}

	public String efetualogout() {

		this.usuario = new Usuario();
		return "login?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public Boolean isLogado() {
		
		if(this.usuario == null)
			this.usuario = new Usuario();
		
		return usuario.getLogin() != null;
	}

}
