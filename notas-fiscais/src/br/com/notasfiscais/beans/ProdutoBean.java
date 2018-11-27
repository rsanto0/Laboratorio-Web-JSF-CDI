package br.com.notasfiscais.beans;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.notasfiscais.dao.DAO;
import br.com.notasfiscais.modelo.Produto;

@RequestScoped
@ManagedBean(name = "produtoBean")
public class ProdutoBean {

	private Produto produto;
	
	private List<Produto>produtos;
	
	private Double total;
	
	
	@PostConstruct
	public void inicializarObjetos(){
	    this.produto = new Produto();
	    this.total = 0.0;
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
		this.produtos = dao.listaTodos();
	}

	public List<Produto> getProdutos() {
		if (produtos == null) {
			System.out.println("Carregando lista de Produtos...");
			produtos = new DAO<Produto>(Produto.class).listaTodos();
		}
		getTotal();
		return produtos;
	}

	public Double getTotal() {
		double somaVlr = 0.0;
		for (Produto produto : produtos) {
			somaVlr = somaVlr + produto.getPreco();
		}
		return this.total = somaVlr;
	}
	

}
