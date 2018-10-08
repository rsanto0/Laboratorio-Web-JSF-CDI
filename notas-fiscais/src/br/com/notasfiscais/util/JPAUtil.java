package br.com.notasfiscais.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("notas");

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}
