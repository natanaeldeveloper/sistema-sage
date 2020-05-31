package br.com.projeto.estoque.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.projeto.estoque.model.Categoria;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ControllerValidationGrupo {
	private static EntityManager manager;

	// CadastroDoProduto
	public static void cadastrarGrupo(JTextField tfNome, JTextField tfDescricao, JComboBox cbCategoria,
			JTextField tfPeso, JComboBox cbUnidade) {
		if (conferirDados(tfNome, cbCategoria, tfPeso, cbUnidade)) {
			String nome = tfNome.getText();
			String descricao = tfDescricao.getText();
			Integer categoria_id = pegarCategorias(cbCategoria);
			Double peso = null;
			try {
				peso = Double.parseDouble(tfPeso.getText());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Preencha o campo corretamente!", "Valor inserido inválido",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			String unidade = cbUnidade.getSelectedItem().toString();

			Query q = manager.createQuery("select nome from Grupo g where g.nome=:nomeDuplicado");
			q.setParameter("nomeDuplicado", nome);
			Object testador = null;
			try {
				testador = q.getSingleResult();
				if (testador != null) {
					JOptionPane.showMessageDialog(null, "Esse Produto já existe!", "Operação inválida",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			} catch (Exception e) {
			}
			ControllerGrupo cp = new ControllerGrupo();
			cp.cadastrarGrupo(nome, descricao, categoria_id, peso, unidade);

			JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Produto cadastrado",
					JOptionPane.INFORMATION_MESSAGE);
			limparDados(tfNome, tfPeso);
		}

	}

	public static boolean conferirDados(JTextField tfNome, JComboBox cbCategoria, JTextField tfPeso,
			JComboBox cbUnidade) {
		if (!(tfNome.getText().isEmpty() || cbCategoria.getItemCount() <= 0 || tfPeso.getText().isEmpty()
				|| cbUnidade.getItemCount() <= 0)) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Campo vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	// Pega o ID da Categoria da JComboBox, da Perist Produto
	public static Integer pegarCategorias(JComboBox cbCategoria) {
		Integer idCategoria = 0;
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select c from Categoria c where c.nome=:nomeCategoria");
		query.setParameter("nomeCategoria", cbCategoria.getSelectedItem());
		List<Categoria> categorias = query.getResultList();
		for (Categoria categoria : categorias) {
			idCategoria = categoria.getId();
		}
		manager.close();
		return idCategoria;
	}

	// Preenche as Categorias da JComboBox da Persis Produto
	public static List<Categoria> preencherCategorias() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select nome from Categoria c where c.id > -1");
		List<Categoria> categorias = query.getResultList();
		manager.close();
		return categorias;
	}

	public static void limparDados(JTextField tfNome, JTextField tfPeso) {
		tfNome.setText("");
		tfPeso.setText("");
	}
}
