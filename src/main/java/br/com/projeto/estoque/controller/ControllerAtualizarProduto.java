package br.com.projeto.estoque.controller;

import java.math.BigDecimal;
import java.util.Calendar;

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
import br.com.projeto.estoque.model.Status;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings({ "rawtypes" })
public class ControllerAtualizarProduto {
	private static EntityManager manager;

	protected static int atualizou = 0;

	// Esse método busca o produto pelo ID inserido no JTextField
	public void buscarProduto(JButton btnBuscar, JButton btnResetar, JTextField tfId, JFormattedTextField tfPreco,
			JSpinner jsQuantidade, JEditorPane epDescricao, JComboBox cbGrupo, JTextField tfMedida, JComboBox cbUnidade,
			JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento, JTextField tfSubtotal, JTextField tfQtdMax,
			JButton btnLimpar, JButton btnAtualizar) {

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
			tfId.transferFocus();
			return;
			// Se o Produto estiver inativo, não será possível alterá-lo
		} else if (produto.getStatus() == Status.INATIVO) {
			JOptionPane.showMessageDialog(null, "Esse registro está inativo, portanto, não pode ser atualizado!",
					"Registro inativo", JOptionPane.ERROR_MESSAGE);
			tfId.transferFocus();
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
			tfSubtotal.setText(produto.getGrupo().getSubtotal() + "");
			tfQtdMax.setText(produto.getGrupo().getQtdMaxima() + "");

			tfId.setEnabled(false);
			habilitarAtualizacao(btnBuscar, btnResetar, tfId, tfPreco, epDescricao, cbGrupo, tfMedida, cbUnidade,
					dcDataFabricacao, dcDataVencimento, tfSubtotal, tfQtdMax, btnLimpar, btnAtualizar);
		}
		manager.close();
	}

	// Esse método efetua a atualização do Produto. Ainda não está com todas as
	// validações
	public void atualizarProduto(JTextField tfId, JFormattedTextField tfPreco, JSpinner jsQuantidade,
			JEditorPane epDescricao, JComboBox cbGrupo, JTextField tfMedida, JComboBox cbUnidade,
			JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento) {
		manager = new JPAUtil().getEntityManager();
		if (ControllerAuxiliar.dadosConferem(tfPreco, jsQuantidade, epDescricao, dcDataFabricacao, dcDataVencimento,
				cbGrupo, tfMedida, cbUnidade)) {
			Integer idAtualizado = Integer.parseInt(tfId.getText());
			Integer idGrupo = ControllerAuxiliar.pegarIdGrupoSelecionado(cbGrupo);
			BigDecimal preco = null;
			try {
				preco = new BigDecimal(tfPreco.getText());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "O preço inserido é inválido", "Preço inválido",
						JOptionPane.ERROR_MESSAGE);
				tfPreco.transferFocus();
				return;
			}

			String descricao = epDescricao.getText();

			Double medida = Double.parseDouble(tfMedida.getText());

			if (medida <= 0) {
				JOptionPane.showMessageDialog(null, "A medida não pode ser nula ou negativa!", "Medida inválida",
						JOptionPane.ERROR_MESSAGE);
				tfMedida.transferFocus();
				return;
			}

			String unidade = cbUnidade.getSelectedItem().toString();

			// Por padrão, o JDateChooser retorna um objeto do tipo Date, que precisa ser
			// convertido
			// com o método auxiliar ".toCalendar" para um objeto do tipo Calendar
			Calendar dataFabricacao = ControllerAuxiliar.toCalendar(dcDataFabricacao.getDate());
			Calendar dataVencimento = ControllerAuxiliar.toCalendar(dcDataVencimento.getDate());

			// Aqui, as variáveis são inseridas no método que de fato atualiza o Produto no
			// banco

			try {
				if (!ControllerAuxiliar.dataErrada(dcDataFabricacao, dcDataVencimento)) {
					ControllerProduto cp = new ControllerProduto();
					cp.atualizarProduto(idAtualizado, preco, medida, unidade, descricao, dataFabricacao, dataVencimento,
							idGrupo);

					if (atualizou == 1) {
						ControllerAuxiliar.resetarTodosOsCampos(tfPreco, jsQuantidade, epDescricao, dcDataFabricacao,
								dcDataVencimento, cbGrupo, tfMedida, cbUnidade);
						atualizou = 0;
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro ao atualizar o produto. Cheque os dados\ne tente novamente. Se o erro persistir, entre em contato.",
						"Erro desconhecido", JOptionPane.ERROR);
				return;
			}
		}
		manager.close();
	}

	// Esse método desabilita todos os campos, com excessão do de buscar e do de
	// informar o ID, para buscar um novo registro
	public void desabilitarAtualizacao(JButton btnBuscar, JButton btnResetar, JTextField tfId,
			JFormattedTextField tfPreco, JSpinner jsQuantidade, JEditorPane epDescricao, JComboBox cbGrupo,
			JTextField tfMedida, JComboBox cbUnidade, JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento,
			JTextField tfSubtotal, JTextField tfQtdMax, JButton btnLimpar, JButton btnAtualizar) {
		ControllerAuxiliar.resetarTodosOsCampos(tfPreco, jsQuantidade, epDescricao, dcDataFabricacao, dcDataVencimento,
				cbGrupo, tfMedida, cbUnidade);
		btnBuscar.setEnabled(true);
		btnResetar.setEnabled(false);
		tfId.setEnabled(true);
		tfPreco.setEnabled(false);
		epDescricao.setEnabled(false);
		cbGrupo.setEnabled(false);
		tfMedida.setEnabled(false);
		cbUnidade.setEnabled(false);
		dcDataFabricacao.setEnabled(false);
		dcDataVencimento.setEnabled(false);
		tfSubtotal.setEnabled(false);
		tfQtdMax.setEnabled(false);
		btnLimpar.setEnabled(false);
		btnAtualizar.setEnabled(false);
	}

	// Esse método habilita todos os campos, com excessão do de buscar e do de
	// informar o ID, para atualizar o registro atual
	public void habilitarAtualizacao(JButton btnBuscar, JButton btnResetar, JTextField tfId,
			JFormattedTextField tfPreco, JEditorPane epDescricao, JComboBox cbGrupo, JTextField tfMedida,
			JComboBox cbUnidade, JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento, JTextField tfSubtotal,
			JTextField tfQtdMax, JButton btnLimpar, JButton btnAtualizar) {
		btnBuscar.setEnabled(false);
		btnResetar.setEnabled(true);
		tfId.setEnabled(false);
		tfPreco.setEnabled(true);
		epDescricao.setEnabled(true);
		cbGrupo.setEnabled(true);
		tfMedida.setEnabled(true);
		cbUnidade.setEnabled(true);
		dcDataFabricacao.setEnabled(true);
		dcDataVencimento.setEnabled(true);
		tfSubtotal.setEnabled(true);
		tfQtdMax.setEnabled(true);
		btnLimpar.setEnabled(true);
		btnAtualizar.setEnabled(true);
	}
}
