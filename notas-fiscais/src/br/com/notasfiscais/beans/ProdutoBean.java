package br.com.notasfiscais.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import br.com.notasfiscais.dao.DAO;
import br.com.notasfiscais.modelo.Produto;

@ViewScoped
@ManagedBean(name = "produtoBean")
public class ProdutoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Produto produto;

	private List<Produto> produtos;

	private Double total;

	@PostConstruct
	public void inicializarObjetos() {
		this.produto = new Produto();
		this.total = 0.0;
	}

	public String grava() {
		System.out.println("Será que vai passar por aqui?");
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		if (this.produto.getId() == null) {
			
			dao.adiciona(this.produto);
		}else{
			
			dao.atualiza(this.produto);
		}
		this.produto = new Produto();
		//para atualiza a lista após a gravação de um novo registro
		this.produtos = dao.listaTodos();
		
		return "produto?faces-redirect=true";
	}
	
	public void remove(Produto produto) {
		DAO<Produto>dao = new DAO<Produto>(Produto.class);
		dao.remove(produto);
		//para atualiza a lista após a remoção de um registro
		this.produtos = dao.listaTodos();
	}
	
	public void cancelarEdicao() {
		this.produto = new Produto();
	}

	public List<Produto> getProdutos() {
		if (this.produtos == null) {
			System.out.println("Carregando lista de Produtos...");
			this.produtos = new DAO<Produto>(Produto.class).listaTodos();
		}
		getTotal();
		return produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Double getTotal() {
		double somaVlr = 0.0;
		for (Produto produto : produtos) {
			somaVlr = somaVlr + produto.getPreco();
		}
		this.total = somaVlr;
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}


}
