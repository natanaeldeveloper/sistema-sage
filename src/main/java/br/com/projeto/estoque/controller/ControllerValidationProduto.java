package br.com.projeto.estoque.controller;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import com.toedter.calendar.JDateChooser;

import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings({ "rawtypes" })
public class ControllerValidationProduto {
	private static EntityManager manager;
	protected static int cadastrou = 0;

	// Esse método pega os dados dos campos e coloca em variáveis, que serão
	// inseridas no método de cadastrar no banco de dados
	public void enviarDadosParaCadastrar(JFormattedTextField tfPreco, JSpinner spQuantidade, JEditorPane epDescricao,
			JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento, JComboBox cbGrupo, JTextField tfMedida,
			JComboBox cbUnidade, JEditorPane epDescricaoMovimentacaoCadastro, JComboBox cbFornecedorCadastro) {

		// O código só vai rodar se todos os campos estiverem preenchidos
		if (ControllerAuxiliar.dadosConferem(tfPreco, spQuantidade, epDescricao, dcDataFabricacao, dcDataVencimento,
				cbGrupo, tfMedida, cbUnidade)) {
			if (StringUtils.isBlank(epDescricaoMovimentacaoCadastro.getText()) == false) {

				manager = new JPAUtil().getEntityManager();

				BigDecimal preco = null;
				try {
					preco = new BigDecimal(tfPreco.getText());
				} catch (Exception e) {
					System.out.println(e.getMessage());
					JOptionPane.showMessageDialog(null, "O preço inserido é inválido!", "Preço inválido",
							JOptionPane.ERROR_MESSAGE);
					tfPreco.transferFocus();
					return;
				}
				// Falta validar se o valor de quantidade inserido é realmente INTEIRO
				int quantidade = Integer.parseInt(spQuantidade.getValue().toString());

				if (quantidade <= 0) {
					JOptionPane.showMessageDialog(null, "A quantidade não pode ser nula ou negativa!",
							"Quantidade inválida", JOptionPane.ERROR_MESSAGE);
					spQuantidade.transferFocus();
					return;
				}

				String descricao = epDescricao.getText();
				Calendar dataFabricacao = ControllerAuxiliar.toCalendar(dcDataFabricacao.getDate());
				Calendar dataVencimento = ControllerAuxiliar.toCalendar(dcDataVencimento.getDate());
				Integer idGrupo = ControllerAuxiliar.pegarIdGrupoSelecionado(cbGrupo);

				// Falta validar se o valor de medida inserido é realmente DOUBLE
				Double medida = Double.parseDouble(tfMedida.getText());

				if (medida <= 0) {
					JOptionPane.showMessageDialog(null, "A medida não pode ser nula ou negativa!", "Medida inválida",
							JOptionPane.ERROR_MESSAGE);
					tfMedida.transferFocus();
					return;
				}

				String unidade = cbUnidade.getSelectedItem().toString();

				String descricaoMovimentacao = epDescricaoMovimentacaoCadastro.getText();
				Fornecedor fornecedorMovimentacao = manager.find(Fornecedor.class,
						ControllerAuxiliar.pegarIdFornecedorSelecionado(cbFornecedorCadastro));

				// O comando de cadastrar no banco só será executado se as datas estiverem
				// coerentes
				if (!ControllerAuxiliar.dataErrada(dcDataFabricacao, dcDataVencimento)) {
					try {
						ControllerProduto cp = new ControllerProduto();

						cp.cadastrarProduto(idGrupo, descricao, preco, quantidade, medida, unidade, dataFabricacao,
								dataVencimento, descricaoMovimentacao, fornecedorMovimentacao);

						if (cadastrou == 1) {
							ControllerAuxiliar.resetarTodosOsCampos(tfPreco, spQuantidade, epDescricao,
									dcDataFabricacao, dcDataVencimento, cbGrupo, tfMedida, cbUnidade);
							ControllerMovimentacao.limparNoCadastro(epDescricaoMovimentacaoCadastro,
									cbFornecedorCadastro);
							cadastrou = 0;
						}

						manager.close();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"Ocorreu um erro ao cadastrar o Produto, cheque os dados e tente novamente.\nSe o erro persistir, entre em contato.",
								"Erro inesperado", JOptionPane.ERROR_MESSAGE);
					}
				}
				manager.close();
			} else {
				JOptionPane.showMessageDialog(null, "A descrição da movimentação precisa ser preenchida!",
						"Descrição da movimentação vazia", JOptionPane.ERROR_MESSAGE);
				epDescricaoMovimentacaoCadastro.transferFocus();
				return;
			}
		} else {
			return;
		}
	}
}
