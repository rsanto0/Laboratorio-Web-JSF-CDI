package br.com.notasfiscais.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.notasfiscais.modelo.Usuario;
import static br.com.notasfiscais.util.JPAUtil.*;

public class UsuarioDAO {

	public boolean existe(Usuario usuario) {

		boolean retorno = false;

		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		Query query = em
				.createQuery("from Usuario u where u.login = :pLogin and u.senha = :pSenha");
		query.setParameter("pLogin", usuario.getLogin());
		query.setParameter("pSenha", usuario.getSenha());

		retorno = !query.getResultList().isEmpty();

		em.getTransaction().commit();
		em.close();

		return retorno;
	}

}
