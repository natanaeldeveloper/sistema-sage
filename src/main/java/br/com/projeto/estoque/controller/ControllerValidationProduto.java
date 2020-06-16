package br.com.projeto.estoque.controller;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings({ "rawtypes" })
public class ControllerValidationProduto {

	// Esse método pega os dados dos campos e coloca em variáveis, que serão
	// inseridas no método de cadastrar no banco de dados
	public void enviarDadosParaCadastrar(JFormattedTextField tfPreco, JSpinner spQuantidade, JEditorPane epDescricao,
			JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento, JComboBox cbGrupo) {
		// O código só vai rodar se todos os campos estiverem preenchidos
		if (ControllerAuxiliar.dadosConferem(tfPreco, spQuantidade, epDescricao, dcDataFabricacao, dcDataVencimento,
				cbGrupo)) {
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

			// O comando de cadastrar no banco só será executado se as datas estiverem
			// coerentes
			if (!ControllerAuxiliar.dataErrada(dcDataFabricacao, dcDataVencimento)) {
				try {
					ControllerProduto cp = new ControllerProduto();
					cp.cadastrarProduto(idGrupo, descricao, preco, quantidade, dataFabricacao, dataVencimento);

					JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Produto cadastrado",
							JOptionPane.INFORMATION_MESSAGE);

					ControllerAuxiliar.resetarTodosOsCampos(tfPreco, spQuantidade, epDescricao, dcDataFabricacao,
							dcDataVencimento, cbGrupo);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Ocorreu um erro ao cadastrar o Produto, cheque os dados e tente novamente.\nSe o erro persistir, entre em contato.",
							"Erro inesperado", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			return;
		}
	}
}