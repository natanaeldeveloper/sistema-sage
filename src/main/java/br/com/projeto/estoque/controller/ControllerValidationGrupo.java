package br.com.projeto.estoque.controller;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

@SuppressWarnings({ "rawtypes" })
public class ControllerValidationGrupo {
	// private static EntityManager manager;
	protected static boolean grupoCadastrado = false;

	// CadastroDoProduto
	public void cadastrarGrupo(JTextField tfNome, JEditorPane tpDescricao, JComboBox cbCategoria, JTextField tfQtdMin,
			JTextField tfQtdMax, JComboBox[] comboBoxDosGrupos) {

		if (conferirDados(tfNome, tpDescricao, cbCategoria, tfQtdMin, tfQtdMax)) {
			String nome = tfNome.getText();
			String descricao = tpDescricao.getText();
			Integer categoria_id = ControllerAuxiliar.pegarIdCategoriaSelecionada(cbCategoria);

			int qtdMin;
			try {
				qtdMin = Integer.parseInt(tfQtdMin.getText());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Preencha a quantidade mínima corretamente!",
						"Quantidade mínima inválida", JOptionPane.ERROR_MESSAGE);
				tfQtdMin.transferFocus();
				return;
			}

			int qtdMax;
			try {
				qtdMax = Integer.parseInt(tfQtdMax.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Preencha a quantidade máxima corretamente!",
						"Quantidade máxima inválida", JOptionPane.ERROR_MESSAGE);
				tfQtdMax.transferFocus();
				return;
			}

			if (qtdMin < 0) {
				JOptionPane.showMessageDialog(null, "A quantidade mínima deve ser no mínimo 0!",
						"Quantidade mínima inválida", JOptionPane.ERROR_MESSAGE);
				tfQtdMin.transferFocus();
				return;
			} else if (qtdMax <= 0) {
				JOptionPane.showMessageDialog(null, "A quantidade máxima deve ser no mínimo 1!",
						"Quantidade máxima inválida", JOptionPane.ERROR_MESSAGE);
				tfQtdMax.transferFocus();
				return;
			} else if (qtdMax == qtdMin) {
				JOptionPane.showMessageDialog(null, "As quantidades mínima e máxima não podem ser iguais!",
						"Quantidade inválida", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Esse if serve para verificar se não estão tentando cadastrar um grupo com um
			// nome que já existe
			if (ControllerGrupo.checarGrupo(nome)) {
				try {
					ControllerGrupo cp = new ControllerGrupo();
					cp.cadastrarGrupo(nome, descricao, categoria_id, qtdMin, qtdMax, comboBoxDosGrupos);

					if (grupoCadastrado) {
						limparDados(tfNome, tpDescricao, cbCategoria, tfQtdMin, tfQtdMax);
						grupoCadastrado = false;
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Ocorreu um erro inesperado ao cadastrar o grupo!\nSe ele persistir, entre em contato!",
							"Erro desconhecido", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	// Confere se os dados não estão vazios
	public static boolean conferirDados(JTextField tfNome, JEditorPane tpDescricao, JComboBox cbCategoria,
			JTextField tfQtdMin, JTextField tfQtdMax) {
		if (!(StringUtils.isBlank(tfNome.getText()) || StringUtils.isBlank(tpDescricao.getText())
				|| cbCategoria.getItemCount() <= 0 || cbCategoria.getSelectedIndex() == 0
				|| StringUtils.isBlank(tfQtdMin.getText()) || StringUtils.isBlank(tfQtdMax.getText()))) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Campo vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		}
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
