package br.com.notasfiscais.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.notasfiscais.dao.DAO;
import br.com.notasfiscais.modelo.Usuario;

@ViewScoped
@ManagedBean(name = "usuarioBean")
public class UsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	private List<Usuario> usuarios;

	@PostConstruct
	public void inicializarObjetos() {
		this.usuario = new Usuario();
	}
	
	public String cadastrar(){
		
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		if (this.usuario.getId() == null) {
			dao.adiciona(this.usuario);
		}else{
			dao.atualiza(this.usuario);
		}
		
		this.usuario = new Usuario();
		//para atualiza a lista após a gravação de um novo registro
		this.usuarios = dao.listaTodos();
		
		return "usuario?faces-redirect=true";
	}
	
	public void remover(Usuario usuario){
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		dao.remove(usuario);
		this.usuarios = dao.listaTodos();
	}
	
	public void cancelarEdicao() {
		this.usuario = new Usuario();
	}
	
	//getters and setters
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		if (this.usuarios == null) {
			DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
			System.out.println("Carregando lista de Usuários ...");
			this.usuarios = dao.listaTodos();
		}
		
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
