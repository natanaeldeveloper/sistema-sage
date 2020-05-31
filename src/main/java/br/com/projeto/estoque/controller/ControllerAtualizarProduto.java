package br.com.projeto.estoque.controller;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("rawtypes")
public class ControllerAtualizarProduto {
//	private static EntityManager manager;

	public static boolean conferirDados(JTextField tfCodigo, JTextField tfQuantidade, JEditorPane tpDescricao,
			JTextField tfPeso, JTextField tfPreco) {
		if (!(tfCodigo.getText().isEmpty() || tfQuantidade.getText().isEmpty() || tpDescricao.getText().isEmpty()
				|| tfPeso.getText().isEmpty() || tfPreco.getText().isEmpty())) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Algum dos campos está vazio! Preencha e tente novamente.",
					"Campo Vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public static void limparDados(JTextField tfCodigo, JTextField tfNome, JEditorPane tpDescricao, JTextField tfPeso,
			JComboBox cbUnidade, JTextField tfPreco, JTextField tfQuantidade, JTextField tfFornecedorAtual,
			JComboBox cbFornecedorNovo, JTextField tfCategoriaAtual, JComboBox cbCategoriaNova, JButton btnResetar,
			JButton btnAtualizar) {
		tfCodigo.setText("");
		tfNome.setText("");
		tpDescricao.setText("");
		tfPeso.setText("");
		tfPreco.setText("");
		tfQuantidade.setText("");
		tfFornecedorAtual.setText("");
		tfCategoriaAtual.setText("");
		desativarCampos(tfNome, tfQuantidade, tpDescricao, tfPeso, cbUnidade, tfPreco, tfFornecedorAtual,
				cbFornecedorNovo, tfCategoriaAtual, cbCategoriaNova, btnResetar, btnAtualizar);
	}

	public static void ativarCampos(JTextField tfNome, JTextField tfQuantidade, JEditorPane tpDescricao,
			JTextField tfPeso, JComboBox cbUnidade, JTextField tfPreco, JTextField tfFornecedorAtual,
			JComboBox cbFornecedorNovo, JTextField tfCategoriaAtual, JComboBox cbCategoriaNova, JButton btnResetar,
			JButton btnAtualizar) {
		tfNome.setEnabled(true);
		tfQuantidade.setEnabled(true);
		tpDescricao.setEnabled(true);
		tfPeso.setEnabled(true);
		cbUnidade.setEnabled(true);
		tfPreco.setEnabled(true);
		tfFornecedorAtual.setEnabled(true);
		cbFornecedorNovo.setEnabled(true);
		tfCategoriaAtual.setEnabled(true);
		cbCategoriaNova.setEnabled(true);
		btnResetar.setEnabled(true);
		btnAtualizar.setEnabled(true);
	}

	public static void desativarCampos(JTextField tfNome, JTextField tfQuantidade, JEditorPane tpDescricao,
			JTextField tfPeso, JComboBox cbUnidade, JTextField tfPreco, JTextField tfFornecedorAtual,
			JComboBox cbFornecedorNovo, JTextField tfCategoriaAtual, JComboBox cbCategoriaNova, JButton btnResetar,
			JButton btnAtualizar) {
		tfNome.setEnabled(false);
		tfQuantidade.setEnabled(false);
		tpDescricao.setEnabled(false);
		tfPeso.setEnabled(false);
		cbUnidade.setEnabled(false);
		tfPreco.setEnabled(false);
		tfFornecedorAtual.setEnabled(false);
		cbFornecedorNovo.setEnabled(false);
		tfCategoriaAtual.setEnabled(false);
		cbCategoriaNova.setEnabled(false);
		btnResetar.setEnabled(false);
		btnAtualizar.setEnabled(false);
	}
}
