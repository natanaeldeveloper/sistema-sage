package br.com.projeto.estoque.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;

import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.model.Movimentacao;
import br.com.projeto.estoque.model.Produto;
import br.com.projeto.estoque.model.Status;
import br.com.projeto.estoque.model.TipoMovimentacao;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings("unchecked")
public class ControllerMovimentacao {
	private static EntityManager manager;
	private static Date data;
	private static long tempo;
	@SuppressWarnings("unused")
	private static Timestamp ts;

	// No método construtor, é criado um novo Timestamp que será usado para definir
	// o horário da movimentação
	public ControllerMovimentacao() {
		data = new Date();
		tempo = data.getTime();
		ts = new Timestamp(tempo);
	}

	// Método responsável por criar novas movimentações
	public Movimentacao cadastrarMovimentacao(Produto produto, Fornecedor fornecedor) {
		manager = new JPAUtil().getEntityManager();
		Movimentacao movimentacao = new Movimentacao();
		// A data é definida pelo Timestamp do exato momento em que a movimentação é
		// criada
		movimentacao.setData(ts);
		// A descrição está automática, mas acho isso será mudado no futuro
		movimentacao.setDescricao("Entrada de " + produto.getGrupo().getNome());
		movimentacao.setFornecedor(fornecedor);
		movimentacao.setProduto(produto);
		movimentacao.setQtdProduto(produto.getQuantidade());
		// Como a movimentação está sendo criada a partir da inserção de um Produto, ela
		// só pode ser primeiramente de ENTRADA
		movimentacao.setTipoMovimentacao(TipoMovimentacao.ENTRADA);

		manager.getTransaction().begin();
		manager.persist(movimentacao);
		manager.getTransaction().commit();

		manager.close();
		return movimentacao;
	}

	// Esse método lista todas as movimentações, retorna uma List<Movimentacao>
	public static List<Movimentacao> listarMovimentacoes() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select m from Movimentacao m");
		List<Movimentacao> listMovimentacoes = query.getResultList();
		manager.close();
		return listMovimentacoes;
	}

	public static List<Movimentacao> listarMovimentacoesDeProdutosInativos() {
		manager = new JPAUtil().getEntityManager();
		Query query0 = manager.createQuery("select p from Produto p where p.status=:statusInativo");
		query0.setParameter("statusInativo", Status.INATIVO);
		List<Produto> produtosInativos = query0.getResultList();
		List<Movimentacao> movimentacoesReferentes = null;
		for (Produto produto : produtosInativos) {
			Query query1 = manager.createQuery("select m from Movimentacao m where m.produto.id=:idProdutoInativo");
			query1.setParameter("idProdutoInativo", produto.getId());
			movimentacoesReferentes = query1.getResultList();
		}
		manager.close();
		return movimentacoesReferentes;
	}

	@SuppressWarnings("rawtypes")
	public static void limparNoCadastro(JEditorPane epDescricaoMovimentacaoCadastro, JComboBox cbFornecedorCadastro) {
		epDescricaoMovimentacaoCadastro.setText("");
		cbFornecedorCadastro.setSelectedIndex(0);
	}
}
