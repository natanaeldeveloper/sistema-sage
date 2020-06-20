package br.com.projeto.estoque.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import br.com.projeto.estoque.model.Categoria;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ControllerValidationGrupo {
	private static EntityManager manager;

	// CadastroDoProduto
	@SuppressWarnings("null")
	public static void cadastrarGrupo(JTextField tfNome, JEditorPane tpDescricao, JComboBox cbCategoria,
			JTextField tfQtdMin, JTextField tfQtdMax) {
		if (conferirDados(tfNome, tpDescricao, cbCategoria, tfQtdMin, tfQtdMax)) {
			String nome = tfNome.getText();
			String descricao = tpDescricao.getText();
			Integer categoria_id = pegarCategorias(cbCategoria);
			int qtdMin = (Integer) null;
			try {
				qtdMin = Integer.parseInt(tfQtdMin.getText());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Preencha a quantidade mínima corretamente!",
						"Quantidade mínima inválida", JOptionPane.ERROR_MESSAGE);
				tfQtdMin.transferFocus();
				return;
			}
			int qtdMax = (Integer) null;
			try {
				qtdMax = Integer.parseInt(tfQtdMax.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Preencha a quantidade máxima corretamente!",
						"Quantidade máxima inválida", JOptionPane.ERROR_MESSAGE);
				tfQtdMax.transferFocus();
			}

			// Essa parte do código serve para verificar se não estão tentando cadastrar um
			// grupo com o nome de um que já existe
			Query q = manager.createQuery("select nome from Grupo g where g.nome=:nomeDuplicado");
			q.setParameter("nomeDuplicado", nome);
			Object testador = null;
			try {
				testador = q.getSingleResult();
				if (testador != null) {
					JOptionPane.showMessageDialog(null, "Esse Grupo já existe!", "Operação inválida",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			} catch (Exception e) {
			}

			try {
				ControllerGrupo cp = new ControllerGrupo();
				cp.cadastrarGrupo(nome, descricao, categoria_id, qtdMin, qtdMax);

				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Produto cadastrado",
						JOptionPane.INFORMATION_MESSAGE);
				limparDados(tfNome, tpDescricao, cbCategoria, tfQtdMin, tfQtdMax);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro inesperado ao cadastrar o grupo!\nSe ele persistir, entre em contato!",
						"Erro desconhecido", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	// Confere se os dados não estão vazios
	public static boolean conferirDados(JTextField tfNome, JEditorPane tpDescricao, JComboBox cbCategoria,
			JTextField tfQtdMin, JTextField tfQtdMax) {
		if (!(StringUtils.isBlank(tfNome.getText()) || StringUtils.isBlank(tpDescricao.getText())
				|| cbCategoria.getItemCount() <= 0 || StringUtils.isBlank(tfQtdMin.getText())
				|| StringUtils.isBlank(tfQtdMax.getText()))) {
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

	// Limpa os campos da interface
	public static void limparDados(JTextField tfNome, JEditorPane tpDescricao, JComboBox cbCategoria,
			JTextField tfQtdMin, JTextField tfQtdMax) {
		tfNome.setText("");
		tpDescricao.setText("");
		cbCategoria.setSelectedIndex(0);
		tfQtdMin.setText("");
		tfQtdMax.setText("");
	}
}
