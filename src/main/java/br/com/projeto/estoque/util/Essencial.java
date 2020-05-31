package br.com.projeto.estoque.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class Essencial {
	private static EntityManager manager;

	public static void setManager(EntityManager manager) {
		Essencial.manager = manager;
	}

	private static Query query;

	public static Query getQuery() {
		return query;
	}

	public static void setQuery(Query query) {
		Essencial.query = query;
	}

	public static EntityManager getManager() {
		return manager;
	}

	public static void useDb() {

		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.getManager().getTransaction().begin();
		Essencial.getManager().createNativeQuery("use estoquetecfour");
		Essencial.getManager().getTransaction().commit();
		Essencial.getManager().close();
	}

}
