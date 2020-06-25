package br.com.projeto.estoque.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import br.com.projeto.estoque.model.Produto;
import br.com.projeto.estoque.model.Status;
import br.com.projeto.estoque.model.TipoMovimentacao;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings("rawtypes")
public class ControllerValidationMovimentacao {
	private EntityManager manager;
	protected static boolean sucessoMovimentacao = false;
	public static boolean movimentacaoTipoSaida = false;

	// Método que pega os dados da view de Movimentação e os usa no
	// ControllerMovimentacao
	public void realizarMovimentacao(JComboBox cbTipoMovimentacao, JComboBox cbFornecedor, JComboBox cbProduto,
			JTextField tfQuantidade, JEditorPane epDescricao, JTextField tfQtdAtual) {

		// Se não houver nenhum campo vazio, o código executado dentro desse "if" é
		// executado
		if (camposPreenchidos(cbTipoMovimentacao, cbFornecedor, cbProduto, tfQuantidade, epDescricao, tfQtdAtual)) {

			// Se a quantidade não for do tipo Integer, a mensagem do catch é exibida
			int quantidade;
			try {
				quantidade = Integer.parseInt(tfQuantidade.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Por favor, insira uma quantidade válida!", "Quantidade inválida",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Se a quantidade for menor ou igual a zero é exibida esta mensagem de erro
			if (quantidade <= 0) {
				JOptionPane.showMessageDialog(null, "A quantidade precisa ser maior que 0!", "Quantidade inválida",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// O tipo de movimentação é pego pela JComboBox referente, assim como o
			// Fornecedor e o Produto
			TipoMovimentacao tipo = (TipoMovimentacao) cbTipoMovimentacao.getSelectedItem();

			// O id do Fornecedor é inicialmente tido como nulo, e só é definido se a
			// movimentação for do tipo de ENTRADA
			Integer idFornecedor;
			if (movimentacaoTipoSaida == false) {
				idFornecedor = ControllerAuxiliar.pegarIdFornecedorSelecionado(cbFornecedor);
			} else {
				idFornecedor = null;
			}

			// Tanto o idProduto como o idFornecedor são pegos usando um método do
			// ControllerAuxiliar se utilizando dos valores DENTRO da JComboBox referente
			Integer idProduto = ControllerAuxiliar.pegarIdProdutoSelecionado(cbProduto);
			String descricao = epDescricao.getText();

			// Verificação extra, apenas para caso de erros inesperados
			try {
				ControllerMovimentacao tm = new ControllerMovimentacao();
				tm.cadastrarMovimentacao(tipo, idFornecedor, idProduto, quantidade, descricao);

				// Se o ControllerMovimentacao retornar que o commit foi um sucesso, o código
				// abaixo é executado e a variável é resetada
				if (sucessoMovimentacao) {
					limparCampos(cbTipoMovimentacao, cbFornecedor, cbProduto, tfQuantidade, epDescricao);
					checarInativacao(cbProduto);
					sucessoMovimentacao = false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Um erro inesperado aconteceu. Se ele persistir, entre em contato!",
						"Erro desconhecido", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	// Método para checar se os campos estão preenchidos
	public boolean camposPreenchidos(JComboBox cbTipoMovimentacao, JComboBox cbFornecedor, JComboBox cbProduto,
			JTextField tfQuantidade, JEditorPane epDescricao, JTextField tfQtdAtual) {
		if (cbTipoMovimentacao.getItemCount() <= 0 || cbFornecedor.getItemCount() <= 0 || cbProduto.getItemCount() <= 0
				|| StringUtils.isBlank(tfQuantidade.getText()) || StringUtils.isBlank(epDescricao.getText())
				|| StringUtils.isBlank(tfQtdAtual.getText())) {
			JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!", "Campo vazio",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	// Método para limpar os campos da view de Movimentações
	public void limparCampos(JComboBox cbTipoMovimentacao, JComboBox cbFornecedor, JComboBox cbProduto,
			JTextField tfQuantidade, JEditorPane epDescricao) {
		cbTipoMovimentacao.setSelectedIndex(0);
		cbFornecedor.setSelectedIndex(0);
		cbProduto.setSelectedIndex(0);
		tfQuantidade.setText("");
		epDescricao.setText("");
	}

	// Esse método checa se algum Produto foi inativado durante uma movimentação de
	// SAÍDA, e atualiza a JComboBox para retirar esse Produto das opções possíveis
	private void checarInativacao(JComboBox cbProduto) {
		manager = new JPAUtil().getEntityManager();

		Query query = manager.createQuery("select p from Produto p");
		@SuppressWarnings("unchecked")
		List<Produto> produtos = query.getResultList();

		ComboBoxModel modelo = cbProduto.getModel();
		for (int i = 0; i < modelo.getSize(); i++) {
			for (Produto produto : produtos) {
				if (produto.getDescricao().equals(modelo.getElementAt(i).toString())
						&& produto.getStatus() == Status.INATIVO) {
					cbProduto.removeItemAt(i);
				}
			}
		}

		manager.close();
	}
}
