package br.com.projeto.estoque.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import br.com.projeto.estoque.model.Categoria;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings("unchecked")
public class ControllerCategoria {
	private static EntityManager manager;

	// Método para cadastrar novas categorias
	@SuppressWarnings("rawtypes")
	public Categoria criarCategoria(String nome, String descricao, JComboBox cbCategoriaGrupo) {
		manager = new JPAUtil().getEntityManager();

		Categoria categoria = new Categoria();
		categoria.setNome(nome);
		categoria.setDescricao(descricao);

		if (checarCategorias(nome)) {
			manager.getTransaction().begin();
			manager.persist(categoria);
			manager.getTransaction().commit();

			cbCategoriaGrupo.addItem(categoria.getNome());
			JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!", "Categoria criada",
					JOptionPane.INFORMATION_MESSAGE);
			ControllerValidationCategoria.categoriaCriada = true;
		}

		manager.close();
		return categoria;
	}

	// Método para listar todas as categorias, retorna uma List<Categoria>
	public static List<Categoria> listarCategorias() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select c from Categoria c");
		List<Categoria> listCategorias = query.getResultList();
		return listCategorias;
	}

	public boolean checarCategorias(String nome) {
		for (Categoria c : listarCategorias()) {
			if (c.getNome().equals(nome)) {
				JOptionPane.showMessageDialog(null, "Essa categoria já existe!\nID: " + c.getId(),
						"Categoria existente", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}
}
