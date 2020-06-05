package br.com.projeto.estoque.controller;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import br.com.projeto.estoque.model.Produto;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings({ "rawtypes" })
public class ControllerAtualizarProduto {
	private static EntityManager manager;

	// Esse método busca o produto pelo id inserido no JTextField do ID
	public void buscarProduto(JTextField tfId, JTextField tfPreco, JSpinner jsQuantidade, JEditorPane epDescricao,
			JComboBox cbGrupo, JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento) {
		manager = new JPAUtil().getEntityManager();
		Integer idBuscado = Integer.parseInt(tfId.getText());
		Produto produto = manager.find(Produto.class, idBuscado);
		// Se o EntityManager não encontrar nenhum produto com esse ID, o programa para
		// e exibe esse erro
		if (produto == null) {
			JOptionPane.showMessageDialog(null, "Esse registro não existe!", "Registro inexistente",
					JOptionPane.ERROR_MESSAGE);
			return;
			// Se o produto existir, os campos serão populados com seus dados
		} else {
			tfPreco.setText(produto.getPreco() + "");
			jsQuantidade.setValue(produto.getQuantidade());
			epDescricao.setText(produto.getDescricao());
			cbGrupo.setSelectedItem(ControllerGrupo.encontrarGrupoPeloProduto(produto.getId()));
			dcDataFabricacao.setDate(produto.getDataFabricacao().getTime());
			dcDataVencimento.setDate(produto.getDataVencimento().getTime());
		}
		manager.close();
	}

	// Esse método efetua a atualização do Produto. Ainda não está com todas as
	// validações
	public void atualizarProduto(JTextField tfId, JTextField tfPreco, JSpinner jsQuantidade, JEditorPane epDescricao,
			JComboBox cbGrupo, JDateChooser dcDataFabricacao, JDateChooser dcDataVencimento) {
		manager = new JPAUtil().getEntityManager();
		Integer idAtualizado = Integer.parseInt(tfId.getText());
		Integer idGrupo = ControllerAuxiliar.pegarIdGrupoSelecionado(cbGrupo);
		BigDecimal preco = null;
		try {
			preco = new BigDecimal(tfPreco.getText());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "O preço inserido é inválido", "Preço inválido",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		int quantidade = (Integer) jsQuantidade.getValue();
		String descricao = epDescricao.getText();
		// Por padrão, o JDateChooser retorna um objeto do tipo Date, que precisa ser
		// convertido
		// com o método auxiliar ".toCalendar" para um objeto do tipo Calendar
		Calendar dataFabricacao = ControllerAuxiliar.toCalendar(dcDataFabricacao.getDate());
		Calendar dataVencimento = ControllerAuxiliar.toCalendar(dcDataVencimento.getDate());

		// Aqui, as variáveis são inseridas no método que de fato atualiza o Produto no
		// banco
		ControllerProduto cp = new ControllerProduto();
		cp.atualizarProduto(idAtualizado, preco, quantidade, descricao, dataFabricacao, dataVencimento, idGrupo);

		JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!", "Produto atualizado",
				JOptionPane.INFORMATION_MESSAGE);
		manager.close();
	}
}
