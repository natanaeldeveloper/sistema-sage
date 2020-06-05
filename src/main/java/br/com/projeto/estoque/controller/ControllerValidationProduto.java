package br.com.projeto.estoque.controller;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings({ "rawtypes" })
public class ControllerValidationProduto {

	// Esse método pega os dados dos campos e coloca em variáveis, que serão
	// inseridas no método de cadastrar no banco de dados
	public void enviarDadosParaCadastrar(JFormattedTextField tfPreco, JSpinner spQuantidade, JEditorPane epDescricao,
			JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento, JComboBox cbGrupo) {
		// O código só vai rodar se todos os campos estiverem preenchidos
		if (dadosConferem(tfPreco, spQuantidade, epDescricao, dcDataFabricacao, dcDataVencimento, cbGrupo)) {
			BigDecimal preco = null;
			try {
				preco = new BigDecimal(tfPreco.getText());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "O preço inserido é inválido!", "Preço inválido",
						JOptionPane.ERROR_MESSAGE);
			}
			// Falta validar se o valor de quantidade inserido é realmente INTEIRO
			int quantidade = Integer.parseInt(spQuantidade.getValue().toString());
			String descricao = epDescricao.getText();
			Calendar dataFabricacao = ControllerAuxiliar.toCalendar(dcDataFabricacao.getDate());
			Calendar dataVencimento = ControllerAuxiliar.toCalendar(dcDataVencimento.getDate());
			Integer idGrupo = ControllerAuxiliar.pegarIdGrupoSelecionado(cbGrupo);

			// O comando de cadastrar no banco só será executado se as datas estiverem
			// coerentes
			if (!ControllerAuxiliar.dataErrada(dcDataFabricacao, dcDataVencimento)) {
				ControllerProduto cp = new ControllerProduto();
				cp.cadastrarProduto(idGrupo, descricao, preco, quantidade, dataFabricacao, dataVencimento);

				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Produto cadastrado",
						JOptionPane.INFORMATION_MESSAGE);

				resetarTodosOsCampos(tfPreco, spQuantidade, epDescricao, dcDataFabricacao, dcDataVencimento, cbGrupo);
			}
		} else {
			return;
		}
	}

	// Esse método checa se existe algum campo vazio
	public static boolean dadosConferem(JFormattedTextField tfPreco, JSpinner spQuantidade, JEditorPane epDescricao,
			JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento, JComboBox cbGrupo) {
		if (tfPreco.getText().isEmpty() || spQuantidade.getValue().toString().isEmpty()
				|| epDescricao.getText().isEmpty() || dcDataFabricacao.getDate() == null
				|| dcDataVencimento.getDate() == null || cbGrupo.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Algum dos campos está vazio! Preencha e tente novamente.",
					"Campo Vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	// Esse método reseta todos os campos referentes ao grupo. É chamado quando o
	// usuário não seleciona nenhum grupo
	public static void resetarCamposGrupoProduto(JTextField tfNome, JTextField tfCodigo, JTextField tfMedida,
			JComboBox cbUnidade) {
		tfNome.setText("");
		tfCodigo.setText("");
		tfMedida.setText("");
		cbUnidade.removeAllItems();
	}

	// Esse método reseta todos os campos, e coloca a JComboBox do grupo para não
	// selecionar nenhum, consequentemente chamando
	// o método de resetar os campos do grupo
	public static void resetarTodosOsCampos(JFormattedTextField tfPreco, JSpinner spQuantidade, JEditorPane epDescricao,
			JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento, JComboBox cbGrupo) {
		tfPreco.setText("");
		spQuantidade.setValue(0);
		epDescricao.setText("");
		dcDataFabricacao.setDate(null);
		dcDataVencimento.setDate(null);
		cbGrupo.setSelectedIndex(0);
	}
}
