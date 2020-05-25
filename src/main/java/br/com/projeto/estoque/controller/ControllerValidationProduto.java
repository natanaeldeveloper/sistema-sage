package br.com.projeto.estoque.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.model.Grupo;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ControllerValidationProduto {

	private static EntityManager manager;

	// Executa o cadastro da Persist Produto
	public static void cadastrarProduto(JComboBox cbProduto, JComboBox cbFornecedor, JTextField tfDescricao, JTextField tfPreco,
			JTextField tfQuantidade, JDateChooser dcFabricacao, JDateChooser dcVencimento) {
		if (conferirDados(cbProduto, cbFornecedor, tfPreco, tfQuantidade, dcFabricacao, dcVencimento)) {
			Integer id_produto = pegarProdutos(cbProduto);
			Integer id_fornecedor = pegarFornecedores(cbFornecedor);
			BigDecimal preco = null;
			Integer qtd = null;
			try {
				preco = new BigDecimal(tfPreco.getText());
				qtd = Integer.parseInt(tfQuantidade.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Valor inválido inserido! (Não use vírgulas, apenas pontos, e a quantidade não pode ser decimal)",
						"Valor inválido", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String descricao = tfDescricao.getText();
			Calendar dataFabricacao = toCalendar(dcFabricacao.getDate());
			Calendar dataVencimento = toCalendar(dcVencimento.getDate());
			try {
				ControllerProduto cp = new ControllerProduto();
				cp.cadastrarProduto(id_produto, id_fornecedor, descricao, preco, qtd, dataFabricacao, dataVencimento);
			} catch (Exception exceptionCadastro) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar este Item!", "Erro de cadastro",
						JOptionPane.ERROR_MESSAGE);
				System.out.println(exceptionCadastro.getMessage());
				return;
			}
			JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso!", "Cadastro realizado",
					JOptionPane.INFORMATION_MESSAGE);
			limparDados(tfPreco, tfQuantidade);
		}
	}

	// Confere os dados da Persist Produto
	public static boolean conferirDados(JComboBox cbProduto, JComboBox cbFornecedor, JTextField tfPreco,
			JTextField tfQuantidade, JDateChooser dcFabricacao, JDateChooser dcVencimento) {
		if (!(cbProduto.getItemCount() < 1 || cbFornecedor.getItemCount() < 1 || tfPreco.getText().isEmpty()
				|| tfQuantidade.getText().isEmpty() || dcFabricacao.getDate() == null
				|| dcVencimento.getDate() == null)) {
			if (dataErrada(dcFabricacao, dcVencimento)) {
				return false;
			}
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Algum dos campos está vazio! Preencha e tente novamente.",
					"Campo Vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	// Confere a data da Persist Produto
	public static boolean dataErrada(JDateChooser dcFabricacao, JDateChooser dcVencimento) {
		if (dcFabricacao.getDate().after(Calendar.getInstance().getTime())) {
			JOptionPane.showMessageDialog(null, "A data de fabricação inserida é inválida!",
					"Data de fabricação inválida", JOptionPane.ERROR_MESSAGE);
			return true;
		} else if (dcVencimento.getDate().before(Calendar.getInstance().getTime())) {
			JOptionPane.showMessageDialog(null, "A data de vencimento inserida é inválida!",
					"Data de vencimento inválida", JOptionPane.ERROR_MESSAGE);
			return true;
		} else if (dcFabricacao.getDate().after(dcVencimento.getDate())) {
			JOptionPane.showMessageDialog(null, "A data de vencimento não pode ser anterior à data de fabricação!",
					"Data inserida inválida", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}

	// Preenche os Produtos da JComboBox da Persist Item
	public static List<Grupo> preencherProdutos() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select nome from Grupo g where g.id > -1");
		List<Grupo> grupos = query.getResultList();
		manager.close();
		return grupos;
	}

	// Preenche as Categorias da JComboBox da Persist Item
	public static List<Fornecedor> preencherFornecedores() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select nome from Fornecedor f where f.id > -1");
		List<Fornecedor> fornecedores = query.getResultList();
		manager.close();
		return fornecedores;
	}

	// Pega o ID do Produto da JComboBox da Persist Item
	public static Integer pegarProdutos(JComboBox cbProduto) {
		manager = new JPAUtil().getEntityManager();
		Integer idProduto = 0;
		Query query = manager.createQuery("select p from Produto p where p.nome=:nomeProduto");
		query.setParameter("nomeProduto", cbProduto.getSelectedItem());
		List<Grupo> grupos = query.getResultList();
		for (Grupo grupo : grupos) {
			idProduto = grupo.getId();
		}
		manager.close();
		return idProduto;
	}

	// Pega o ID do Fornecedor da JComboBox da Persist Item
	public static Integer pegarFornecedores(JComboBox cbFornecedor) {
		manager = new JPAUtil().getEntityManager();
		Integer idFornecedor = 0;
		Query query = manager.createQuery("select f from Fornecedor f where f.nome=:nomeFornecedor");
		query.setParameter("nomeFornecedor", cbFornecedor.getSelectedItem());
		List<Fornecedor> fornecedores = query.getResultList();
		for (Fornecedor fornecedor : fornecedores) {
			idFornecedor = fornecedor.getId();
		}
		manager.close();
		return idFornecedor;
	}

	// Limpa os dados dos campos da Persist Produto
	public static void limparDados(JTextField tfPreco, JTextField tfQuantidade) {
		tfPreco.setText("");
		tfQuantidade.setText("");
	}

	// Converte Date para Calendar
	public static Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		try {
			date = (Date) df.parse(date.toString());
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		cal.setTime(date);
		return cal;
	}
}
