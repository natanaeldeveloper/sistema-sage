package br.com.projeto.estoque.controller;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.projeto.estoque.model.Categoria;
import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.model.Produto;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings("rawtypes")
public class ControllerAtualizarProduto {
	private static EntityManager manager;

	public static void carregarRegistro(JTextField tfCodigo, JTextField tfNome, JTextField tfQuantidade,
			JEditorPane tpDescricao, JTextField tfPeso, JComboBox cbUnidade, JTextField tfPreco,
			JTextField tfFornecedorAtual, JComboBox cbFornecedorNovo, JTextField tfCategoriaAtual,
			JComboBox cbCategoriaNova, JButton btnResetar, JButton btnAtualizar) {
		manager = new JPAUtil().getEntityManager();
		String codigoCarregado = "";
		if (tfCodigo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Você precisa preencher o campo!", "Campo vazio",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			codigoCarregado = tfCodigo.getText();
		}
		Query query0 = manager.createQuery("select id from Produto p where p.codigo=:codigoCarregado");
		query0.setParameter("codigoCarregado", codigoCarregado);
		Object idBruto = null;
		try {
			idBruto = query0.getSingleResult();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Produto inexistente!", "Produto inexistente",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		Integer idCarregado = Integer.parseInt(idBruto.toString());
		Produto produtoCarregado = manager.find(Produto.class, idCarregado);
		if (produtoCarregado == null) {
			JOptionPane.showMessageDialog(null, "Produto inexistente!", "Produto inexistente",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			tfNome.setText(produtoCarregado.getNome());
			tfQuantidade.setText(produtoCarregado.getQuantidade() + "");
			tfPeso.setText(produtoCarregado.getPeso() + "");
			cbUnidade.setSelectedItem(produtoCarregado.getUnidade());
			tfPreco.setText(produtoCarregado.getPreco() + "");
			try {
				Query query = manager.createQuery("select nome from Fornecedor f where f.id = :idFornecedorEncontrado");
				query.setParameter("idFornecedorEncontrado", produtoCarregado.getFornecedor().getId());
				Object fornecedorEncontrado = query.getSingleResult();
				tfFornecedorAtual.setText(fornecedorEncontrado.toString());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Fornecedor não encontrado. Inconsistência no banco de dados.",
						"Fornecedor inexistente", JOptionPane.WARNING_MESSAGE);
				return;
			}

			try {
				Query query = manager.createQuery("select nome from Categoria c where c.id = :idCategoriaEncontrada");
				query.setParameter("idCategoriaEncontrada", produtoCarregado.getCategoria().getId());
				Object categoriaEncontrada = query.getSingleResult();
				tfCategoriaAtual.setText(categoriaEncontrada.toString());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Categoria não encontrada. Inconsistência no banco de dados.",
						"Categoria inexistente", JOptionPane.WARNING_MESSAGE);
				return;
			}

			ativarCampos(tfNome, tfQuantidade, tpDescricao, tfPeso, cbUnidade, tfPreco, tfFornecedorAtual,
					cbFornecedorNovo, tfCategoriaAtual, cbCategoriaNova, btnResetar, btnAtualizar);
		}
	}

	public static void atualizarRegistro(JTextField tfCodigo, JTextField tfNome, JTextField tfQuantidade,
			JEditorPane tpDescricao, JTextField tfPeso, JComboBox cbUnidade, JTextField tfPreco,
			JTextField tfFornecedorAtual, JComboBox cbFornecedorNovo, JTextField tfCategoriaAtual,
			JComboBox cbCategoriaNova, JButton btnResetar, JButton btnAtualizar) {
		if (conferirDados(tfCodigo, tfQuantidade, tpDescricao, tfPeso, tfPreco)) {
			manager = new JPAUtil().getEntityManager();
			ControllerProduto daoProduto = new ControllerProduto();
			String codigo = tfCodigo.getText();
			int qtd = -1;
			Double peso = null;
			BigDecimal preco = null;
			try {
				qtd = Integer.parseInt(tfQuantidade.getText());
				peso = Double.parseDouble(tfPeso.getText());
				preco = new BigDecimal(tfPreco.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Algum dos valores inseridos é inválido!!",
						"Valor inserido inválido", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String unidade = cbUnidade.getSelectedItem().toString();
			int idFornecedorNovo = ControllerValidationProduto.pegarFornecedores(cbFornecedorNovo);
			int idCategoriaNova = ControllerValidationProduto.pegarCategorias(cbCategoriaNova);
			String descricao = tpDescricao.getText();

			Fornecedor fornecedor = manager.find(Fornecedor.class, idFornecedorNovo);
			Categoria categoria = manager.find(Categoria.class, idCategoriaNova);
			daoProduto.atualizarProduto(codigo, qtd, peso, unidade, preco, fornecedor, categoria, descricao);

			JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso!", "Registro atualziado",
					JOptionPane.INFORMATION_MESSAGE);

			limparDados(tfCodigo, tfNome, tpDescricao, tfPeso, cbUnidade, tfPreco, tfQuantidade, tfFornecedorAtual,
					cbFornecedorNovo, tfCategoriaAtual, cbCategoriaNova, btnResetar, btnAtualizar);
		}
	}

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
