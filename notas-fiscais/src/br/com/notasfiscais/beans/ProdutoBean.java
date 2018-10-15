package br.com.notasfiscais.beans;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.notasfiscais.dao.DAO;
import br.com.notasfiscais.modelo.Produto;

@RequestScoped
@ManagedBean(name = "produtoBean")
public class ProdutoBean {

	private Produto produto;
	
	@PostConstruct
	public void inicializarObjetos(){
	    this.produto = new Produto();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void grava() {
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		dao.adiciona(produto);
		this.produto = new Produto();
	}

}
