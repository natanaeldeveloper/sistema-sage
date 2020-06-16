package br.com.projeto.estoque.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projeto.estoque.model.Categoria;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings("unchecked")
public class ControllerCategoria {
	private static EntityManager manager;

	// Método para cadastrar novas categorias
	public Categoria criarCategoria(String nome) {
		manager = new JPAUtil().getEntityManager();

		Categoria categoria = new Categoria();
		categoria.setNome(nome);
		manager.getTransaction().begin();
		manager.persist(categoria);
		manager.getTransaction().commit();

		manager.close();
		return categoria;
	}

	//Método para listar todas as categorias, retorna uma List<Categoria>
	public static List<Categoria> listarCategorias() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select c from Categoria c");
		List<Categoria> listCategorias = query.getResultList();
		manager.close();
		return listCategorias;
	}
}
