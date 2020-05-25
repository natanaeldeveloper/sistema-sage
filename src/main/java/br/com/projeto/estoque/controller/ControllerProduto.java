package br.com.projeto.estoque.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import br.com.projeto.estoque.model.Categoria;
import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.model.Movimentacao;
import br.com.projeto.estoque.model.Produto;
import br.com.projeto.estoque.model.TipoMovimentacao;
import br.com.projeto.estoque.util.JPAUtil;
import br.com.projeto.estoque.util.RecursoNaoEncontradoException;

@SuppressWarnings("unchecked")
public class ControllerProduto {
	private static EntityManager manager;
	private static Random rand;
	private static Date data;
	private static long tempo;
	private static Timestamp ts;

	public ControllerProduto() {
		data = new Date();
		tempo = data.getTime();
		ts = new Timestamp(tempo);
	}

	public Produto cadastrarProduto(String nome, int qtd, String unidade, double peso, BigDecimal preco,
			Calendar dataFabricacao, Calendar dataVencimento, Categoria c, Fornecedor f, String descricao) {
		manager = new JPAUtil().getEntityManager();
		rand = new Random();

		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setQuantidade(qtd);
		produto.setPeso(peso);
		produto.setUnidade(unidade);
		produto.setPreco(preco);
		produto.setDataFabricacao(dataFabricacao);
		produto.setDataVencimento(dataVencimento);
		produto.setCategoria(c);
		produto.setFornecedor(f);

		int valorRandomico = rand.nextInt(0x1000000);
		String codigoGerado = Integer.toHexString(valorRandomico);

		produto.setCodigo(codigoGerado);

		Movimentacao m = new Movimentacao();
		m.setData(ts);
		m.setDescricao(descricao);
		m.setQtdProduto(produto.getQuantidade());
		m.setProduto(produto);
		m.setTipoMovimentacao(TipoMovimentacao.ENTRADA);

		manager.getTransaction().begin();
		manager.persist(produto);
		manager.persist(m);
		manager.getTransaction().commit();

		manager.close();

		return produto;
	}

	public Produto deletarProduto(Integer id) {
		manager = new JPAUtil().getEntityManager();

		Produto produtoDeletado = manager.find(Produto.class, id);

		try {
			if (produtoDeletado.getCodigo().equals(id)) {
				manager.getTransaction().begin();
				manager.remove(produtoDeletado);
				manager.getTransaction().commit();
				manager.close();
			}
		} catch (Exception e) {
			throw new RecursoNaoEncontradoException();
		}
		return null;
	}

	public Produto encontrarProdutoPeloCodigo(Integer id) {
		manager = new JPAUtil().getEntityManager();

		Produto produtoEncontrado = manager.find(Produto.class, id);

		try {
			if (produtoEncontrado.getCodigo() != null) {

				Query query = manager.createQuery("select p from Produto p where p.id=:idProduto");
				query.setParameter("idProduto", produtoEncontrado.getCodigo());

				List<Produto> produtos = query.getResultList();

				for (Produto produto : produtos) {
					System.out.println(produto.getNome());
				}
			}
		} catch (Exception e) {
			throw new RecursoNaoEncontradoException();
		}
		return null;
	}

	public List<Produto> encontrarTodosOsProdutos() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select p from Produto p");
		List<Produto> produtos = query.getResultList();
		return produtos;
	}

	public void atualizarProduto(String codigo, Integer qtd, Double peso, String unidade, BigDecimal preco,
			Fornecedor fornecedor, Categoria categoria, String descricao) {
		manager = new JPAUtil().getEntityManager();
		Query query0 = manager.createQuery("select id from Produto p where p.codigo=:codigoEncontrado");
		query0.setParameter("codigoEncontrado", codigo);
		Object idBruto = query0.getSingleResult();
		Integer id = Integer.parseInt(idBruto.toString());
		Produto produtoAtualizado = manager.find(Produto.class, id);
		try {
			if (qtd > produtoAtualizado.getQuantidade()) {
				int qtdNova = qtd - produtoAtualizado.getQuantidade();
				Movimentacao novaMovimentacao = new Movimentacao();
				manager.getTransaction().begin();
				novaMovimentacao.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
				novaMovimentacao.setQtdProduto(qtdNova);
				novaMovimentacao.setData(ts);
				novaMovimentacao.setProduto(produtoAtualizado);
				novaMovimentacao.setDescricao(descricao);
				manager.persist(novaMovimentacao);
				manager.getTransaction().commit();
			} else if (qtd < produtoAtualizado.getQuantidade()) {
				int qtdNova = produtoAtualizado.getQuantidade() - qtd;
				Movimentacao novaMovimentacao = new Movimentacao();
				manager.getTransaction().begin();
				novaMovimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
				novaMovimentacao.setQtdProduto(qtdNova);
				novaMovimentacao.setData(ts);
				novaMovimentacao.setProduto(produtoAtualizado);
				novaMovimentacao.setDescricao(descricao);
				manager.persist(novaMovimentacao);
				manager.getTransaction().commit();
			}
			produtoAtualizado.setPeso(peso);
			produtoAtualizado.setUnidade(unidade);
			produtoAtualizado.setPreco(preco);
			produtoAtualizado.setFornecedor(fornecedor);
			produtoAtualizado.setCategoria(categoria);
			produtoAtualizado.setQuantidade(qtd);
			manager.getTransaction().begin();
			manager.merge(produtoAtualizado);
			manager.getTransaction().commit();
			manager.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean checarVencimento() {
		for (Produto produto : encontrarTodosOsProdutos()) {
			if (produto.getDataVencimento().getTime().before(Calendar.getInstance().getTime())) {
				JOptionPane.showMessageDialog(null,
						"Produto vencido!\nID: " + produto.getId() + "\nNome: " + produto.getNome(), "Produto vencido",
						JOptionPane.WARNING_MESSAGE);
				return true;
			}
		}
		return false;
	}
}
