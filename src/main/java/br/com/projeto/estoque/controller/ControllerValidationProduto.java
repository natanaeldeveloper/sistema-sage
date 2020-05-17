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
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import br.com.projeto.estoque.model.Categoria;
import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ControllerValidationProduto {
	private static EntityManager manager;

	// Executa o cadastro da Persist Produto
	public static void executarCadastro(JTextField tfNome, JTextField tfQuantidade, JTextField tfPeso,
			JComboBox cbUnidade, JTextField tfPreco, JEditorPane tpDescricao, JDateChooser dcFabricacao,
			JDateChooser dcVencimento, JComboBox cbCategoria, JComboBox cbFornecedor) {
		if (conferirDados(tfNome, tfQuantidade, tfPeso, cbUnidade, tfPreco, tpDescricao, dcFabricacao, dcVencimento,
				cbCategoria, cbFornecedor)) {
			String nome = tfNome.getText();
			Integer qtd = null;
			String unidade = cbUnidade.getSelectedItem().toString();
			Double peso = null;
			BigDecimal preco = null;
			try {
				qtd = Integer.parseInt(tfQuantidade.getText());
				peso = Double.parseDouble(tfPeso.getText());
				preco = new BigDecimal(tfPreco.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Valor inválido inserido! (Não use vírgulas, apenas pontos, e a quantidade não pode ser decimal)",
						"Valor inválido", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Calendar dataFabricacao = toCalendar(dcFabricacao.getDate());
			Calendar dataVencimento = toCalendar(dcVencimento.getDate());
			Integer categoria_id = pegarCategorias(cbCategoria);
			Integer fornecedor_id = pegarFornecedores(cbFornecedor);
			String descricao = tpDescricao.getText();
			try {
				cadastrarDados(nome, qtd, unidade, peso, preco, descricao, dataFabricacao, dataVencimento, categoria_id,
						fornecedor_id);
			} catch (Exception exceptionCadastro) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o Produto!", "Erro de cadastro",
						JOptionPane.ERROR_MESSAGE);
				System.out.println(exceptionCadastro.getMessage());
				return;
			}
			limparDados(tfNome, tfQuantidade, tfPeso, tfPreco, tpDescricao);
		}
	}

	public static void cadastrarDados(String nome, int qtd, String unidade, Double peso, BigDecimal preco,
			String descricao, Calendar dataValidade, Calendar dataVencimento, Integer categoria_id,
			Integer fornecedor_id) {
		manager = new JPAUtil().getEntityManager();
		Categoria c = manager.find(Categoria.class, categoria_id);
		Fornecedor f = manager.find(Fornecedor.class, fornecedor_id);
		if (f == null) {
			System.out.println("Fornecedor inexistente");
			return;
		} else if (c == null) {
			System.out.println("Categoria inexistente");
		} else {
			ControllerProduto daoP = new ControllerProduto();
			daoP.cadastrarProduto(nome, qtd, unidade, peso, preco, dataValidade, dataVencimento, c, f, descricao);
			JOptionPane.showMessageDialog(null, "Produto cadastrado!", "Produto Cadastrado",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// Confere os dados da Persist Produto
	public static boolean conferirDados(JTextField tfNome, JTextField tfQuantidade, JTextField tfPeso,
			JComboBox cbUnidade, JTextField tfPreco, JEditorPane tpDescricao, JDateChooser dcFabricacao,
			JDateChooser dcVencimento, JComboBox cbCategoria, JComboBox cbFornecedor) {
		if (!(tfNome.getText().isEmpty() || tfQuantidade.getText().isEmpty() || cbUnidade.getItemCount() < 1
				|| tfPeso.getText().isEmpty() || tfPreco.getText().isEmpty() || tpDescricao.getText().isEmpty()
				|| dcFabricacao.getDate() == null || dcVencimento.getDate() == null || cbCategoria.getItemCount() < 1
				|| cbFornecedor.getItemCount() < 1)) {
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

	// Preenche as Categorias da JComboBox da Persis Produto
	public static List<Categoria> preencherCategorias() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select nome from Categoria c where c.id > -1");
		List<Categoria> categorias = query.getResultList();
		return categorias;
	}

	// Pega o ID da Categoria da JComboBox, da Perist Produto
	public static Integer pegarCategorias(JComboBox cbCategoria) {
		Integer idCategoria = 0;
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select c from Categoria c where c.nome=:nomeCategoria");
		query.setParameter("nomeCategoria", cbCategoria.getSelectedItem());
		List<Categoria> categorias = query.getResultList();
		for (Categoria categoria : categorias) {
			idCategoria = categoria.getId();
		}
		return idCategoria;
	}

	// Preenche as Categorias da JComboBox da Persis Produto
	public static List<Fornecedor> preencherFornecedores() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select nome from Fornecedor f where f.id > -1");
		List<Fornecedor> fornecedores = query.getResultList();
		return fornecedores;
	}

	// Pega o ID da Categoria da JComboBox, da Perist Produto
	public static Integer pegarFornecedores(JComboBox cbFornecedor) {
		Integer idFornecedor = 0;
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select f from Fornecedor f where f.nome=:nomeFornecedor");
		query.setParameter("nomeFornecedor", cbFornecedor.getSelectedItem());
		List<Fornecedor> fornecedores = query.getResultList();
		for (Fornecedor fornecedor : fornecedores) {
			idFornecedor = fornecedor.getId();
		}
		return idFornecedor;
	}

	// Limpa os dados dos campos da Persist Produto
	public static void limparDados(JTextField tfNome, JTextField tfQuantidade, JTextField tfPeso, JTextField tfPreco,
			JEditorPane tpDescricao) {
		tfNome.setText("");
		tpDescricao.setText("");
		tfQuantidade.setText("");
		tfPeso.setText("");
		tfPreco.setText("");
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
