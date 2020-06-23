package br.com.projeto.estoque.controller;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import br.com.projeto.estoque.model.Produto;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings("rawtypes")
public class ControllerInativarProduto {
	private static EntityManager manager;

	public void buscarProdutoInativado(JButton btnBuscar, JButton btnResetar, JTextField tfId,
			JFormattedTextField tfPreco, JSpinner jsQuantidade, JEditorPane epDescricao, JComboBox cbGrupo,
			JTextField tfMedida, JComboBox cbUnidade, JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento,
			JButton btnLimpar, JButton btnInativar) {

		manager = new JPAUtil().getEntityManager();

		Integer idBuscado = null;

		try {
			idBuscado = Integer.parseInt(tfId.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "O campo de ID precisa estar preenchido e ser coerente!", "ID inválido",
					JOptionPane.ERROR_MESSAGE);
			tfId.transferFocus();
			return;
		}

		Produto produto = manager.find(Produto.class, idBuscado);

		// Se o EntityManager não encontrar nenhum Produto com esse ID, o programa para
		// e exibe esse erro
		if (produto == null) {
			JOptionPane.showMessageDialog(null, "Esse registro não existe!", "Registro inexistente",
					JOptionPane.ERROR_MESSAGE);
			return;
			// Se o Produto existir, os campos serão populados com seus dados
		} else {
			tfPreco.setText(produto.getPreco() + "");
			jsQuantidade.setValue(produto.getQuantidade());
			epDescricao.setText(produto.getDescricao());
			cbGrupo.setSelectedItem(ControllerGrupo.encontrarGrupoPeloProduto(produto.getId()));
			tfMedida.setText(produto.getMedida() + "");
			cbUnidade.setSelectedItem(produto.getUnidade());
			dcDataFabricacao.setDate(produto.getDataFabricacao().getTime());
			dcDataVencimento.setDate(produto.getDataVencimento().getTime());
			tfId.setEnabled(false);
			btnBuscar.setEnabled(false);
			btnResetar.setEnabled(true);
			btnInativar.setEnabled(true);
			btnLimpar.setEnabled(true);
		}
		manager.close();
	}

	public void inativarProduto(JTextField tfId, JFormattedTextField tfPreco, JSpinner jsQuantidade,
			JEditorPane epDescricao, JComboBox cbGrupo, JTextField tfMedida, JComboBox cbUnidade,
			JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento) {

		try {
			ControllerProduto cp = new ControllerProduto();
			cp.inativarProduto(Integer.parseInt(tfId.getText()));

			ControllerAuxiliar.resetarTodosOsCampos(tfPreco, jsQuantidade, epDescricao, dcDataFabricacao,
					dcDataVencimento, cbGrupo, tfMedida, cbUnidade);

			JOptionPane.showMessageDialog(null, "Produto inativado com sucesso!", "Produto inativado",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Ocorreu um erro ao inativar o produto.\nSe o erro persistir, entre em contato.",
					"Erro desconhecido", JOptionPane.ERROR);
			return;
		}
	}

	public void desabilitarInativacao(JButton btnBuscar, JButton btnResetar, JTextField tfId,
			JFormattedTextField tfPreco, JSpinner jsQuantidade, JEditorPane epDescricao, JComboBox cbGrupo,
			JTextField tfMedida, JComboBox cbUnidade, JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento,
			JButton btnLimpar, JButton btnInativar) {
		ControllerAuxiliar.resetarTodosOsCampos(tfPreco, jsQuantidade, epDescricao, dcDataFabricacao, dcDataVencimento,
				cbGrupo, tfMedida, cbUnidade);
		btnBuscar.setEnabled(true);
		btnResetar.setEnabled(false);
		tfId.setEnabled(true);
		btnLimpar.setEnabled(false);
		btnInativar.setEnabled(false);
	}
}
