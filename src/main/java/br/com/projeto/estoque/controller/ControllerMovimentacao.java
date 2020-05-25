package br.com.projeto.estoque.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.model.Movimentacao;
import br.com.projeto.estoque.model.Produto;
import br.com.projeto.estoque.model.TipoMovimentacao;
import br.com.projeto.estoque.util.JPAUtil;

public class ControllerMovimentacao {
	private static EntityManager manager;
	private static Date data;
	private static long tempo;
	@SuppressWarnings("unused")
	private static Timestamp ts;

	public ControllerMovimentacao() {
		data = new Date();
		tempo = data.getTime();
		ts = new Timestamp(tempo);
	}

	public Movimentacao cadastrarMovimentacao(Produto produto, Fornecedor fornecedor) {
		manager = new JPAUtil().getEntityManager();
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setData(ts);
		movimentacao.setDescricao("Entrada de " + produto.getGrupo().getNome());
		movimentacao.setFornecedor(fornecedor);
		movimentacao.setProduto(produto);
		movimentacao.setQtdProduto(produto.getQuantidade());
		movimentacao.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
		
		manager.getTransaction().begin();
		manager.persist(movimentacao);
		manager.getTransaction().commit();
		
		manager.close();
		return movimentacao;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Movimentacao> listarMovimentacoes() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select m from Movimentacao m");
		List<Movimentacao> movimentacoes = query.getResultList();
		manager.close();
		return movimentacoes;
	}
}
