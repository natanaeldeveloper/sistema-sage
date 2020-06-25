package br.com.projeto.estoque.controller;

import javax.persistence.EntityManager;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import br.com.projeto.estoque.util.JPAUtil;

public class ControllerValidationCategoria {
	private static EntityManager manager;
	protected static boolean categoriaCriada = false;

	@SuppressWarnings("rawtypes")
	public void cadastrarCategoria(JTextField tfCategoria, JEditorPane epDescricao, JComboBox cbCategoriaGrupo) {
		manager = new JPAUtil().getEntityManager();

		if (StringUtils.isBlank(tfCategoria.getText()) || StringUtils.isBlank(epDescricao.getText())) {
			JOptionPane.showMessageDialog(null, "Você precisa preencher os campos!", "Campos vazios",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			String nome = tfCategoria.getText();
			String descricao = epDescricao.getText();

			try {
				ControllerCategoria cc = new ControllerCategoria();
				cc.criarCategoria(nome, descricao, cbCategoriaGrupo);

				if (categoriaCriada) {
					limparDados(tfCategoria, epDescricao);
					categoriaCriada = false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Algum erro inesperado aconteceu! Se ele persistir, entre em contato.", "Erro desconhecido",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		manager.close();
	}

	public void limparDados(JTextField tfCategoria, JEditorPane epDescricao) {
		tfCategoria.setText("");
		epDescricao.setText("");
	}
}
