package br.com.projeto.estoque.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.model.Grupo;
import br.com.projeto.estoque.model.Movimentacao;
import br.com.projeto.estoque.model.Produto;
import br.com.projeto.estoque.model.Status;
import br.com.projeto.estoque.model.TipoMovimentacao;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings("unchecked")
public class ControllerProduto {
	private static EntityManager manager;
	private Date data;
	private long tempo;
	private Timestamp ts;

	// Método para cadastrar um novo Produto
	public Produto cadastrarProduto(Integer id_grupo, String descricao, BigDecimal preco, int quantidade, Double medida,
			String unidade, Calendar dataFabricacao, Calendar dataVencimento, String descricaoMovimentacao,
			Fornecedor fornecedorMovimentacao) {
		manager = new JPAUtil().getEntityManager();

		Grupo g = manager.find(Grupo.class, id_grupo);

		Produto produto = new Produto();
		produto.setGrupo(g);
		produto.setDescricao(descricao);
		produto.setPreco(preco);
		produto.setQuantidade(quantidade);
		produto.setMedida(medida);
		produto.setUnidade(unidade);
		produto.setDataFabricacao(dataFabricacao);
		produto.setDataVencimento(dataVencimento);
		// O produto já é altomaticamente definido como ativo, após entrar no banco de
		// dados
		produto.setStatus(Status.ATIVO);

		// Esses ifs verificam se a quantidade inserida não ultrapassaria o valor máximo
		// desse produto no estoque (no Grupo).
		// Se a quantidade for exatamente o limite ou se for menor, a operação será
		// permitida, e a quantidade do Produto será
		// adicionada ao subtotal do Grupo
		g.setSubtotal(g.getSubtotal() + quantidade);
		if (g.getSubtotal() > g.getQtdMaxima()) {
			JOptionPane.showMessageDialog(null,
					"Essa quantidade de produtos excederia a quantidade máxima deles no estoque!",
					"Quantidade máxima excedida", JOptionPane.ERROR_MESSAGE);
			return null;
		} else if (g.getSubtotal() == g.getQtdMaxima()) {
			JOptionPane.showMessageDialog(null, "A quantidade máxima desses produtos no estoque foi atingida!",
					"Quantidade máxima atingida", JOptionPane.WARNING_MESSAGE);
		}

		Movimentacao m = new Movimentacao();
		data = new Date();
		tempo = data.getTime();
		ts = new Timestamp(tempo);
		m.setData(ts);
		m.setDescricao(descricaoMovimentacao);
		m.setFornecedor(fornecedorMovimentacao);
		m.setProduto(produto);
		m.setQtdProduto(quantidade);
		m.setTipoMovimentacao(TipoMovimentacao.ENTRADA);

		manager.getTransaction().begin();

		manager.persist(produto);
		manager.persist(m);
		manager.merge(g);

		// Esse if verifica se o Grupo desse Produto já está "estocado". Se não estiver,
		// só pelo fato de um
		// novo produto estar sendo inserido, o Grupo já é tido como "estocado".
		// Enquanto estocado, um Grupo não pode
		// ser deletado
		if (g.isEstocado() == false) {
			g.setEstocado(true);
			manager.merge(g);
		}
		manager.getTransaction().commit();

		JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Produto cadastrado",
				JOptionPane.INFORMATION_MESSAGE);

		// Aqui, a variável "cadastrou" do ControllerValidationProduto é setada como 1,
		// permitindo que os campos sejam limpos pois o cadastro foi efetuado com
		// sucesso
		ControllerValidationProduto.cadastrou = 1;

		manager.close();
		return produto;
	}

	// Método que lista todos os Produtos. Retorna uma List<Produto>
	public static List<Produto> listarProdutos() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select p from Produto p");
		List<Produto> listaProdutos = query.getResultList();
		manager.close();
		return listaProdutos;
	}

	// Método que lista os Produtos ativos. Retorna uma List<Produto>
	public static List<Produto> listarApenasProdutosAtivos() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select p from Produto p where p.status = :statusProduto");
		query.setParameter("statusProduto", Status.ATIVO);
		List<Produto> listaProdutos = query.getResultList();
		manager.close();
		return listaProdutos;
	}

	// Método para listar apenas a descrição dos Produtos, para a view de
	// Movimentação
	public static List<String> listarDescricaoProdutosAtivos() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select descricao from Produto p where p.status = :statusProduto");
		query.setParameter("statusProduto", Status.ATIVO);
		List<String> listaProdutos = query.getResultList();
		manager.close();
		return listaProdutos;
	}

	// Método para atualizar o Produto
	public Produto atualizarProduto(Integer id, BigDecimal preco, Double medida, String unidade, String descricao,
			Calendar dataFabricacao, Calendar dataVencimento, Integer id_grupo) {
		manager = new JPAUtil().getEntityManager();
		Grupo grupoEncontrado = manager.find(Grupo.class, id_grupo);
		Produto produtoAtualizado = manager.find(Produto.class, id);
		produtoAtualizado.setPreco(preco);
		produtoAtualizado.setMedida(medida);
		produtoAtualizado.setUnidade(unidade);
		produtoAtualizado.setDescricao(descricao);
		produtoAtualizado.setDataFabricacao(dataFabricacao);
		produtoAtualizado.setDataVencimento(dataVencimento);
		// É necessária uma instância do Grupo antigo para comparar o subtotal e
		// validá-lo caso mude
		Grupo grupoAntigo = produtoAtualizado.getGrupo();
		produtoAtualizado.setGrupo(grupoEncontrado);

		if (grupoEncontrado != grupoAntigo) {
			if (produtoAtualizado.getQuantidade() + grupoEncontrado.getSubtotal() > grupoEncontrado.getQtdMaxima()) {
				JOptionPane.showMessageDialog(null,
						"A quantidade atual do Produto entrará em conflito com a quantidade máxima\nde produtos do Grupo atualizado!",
						"Troca de Grupo inválida", JOptionPane.ERROR_MESSAGE);
				manager.close();
				return null;
			} else if (produtoAtualizado.getQuantidade() + grupoEncontrado.getSubtotal() == grupoEncontrado
					.getQtdMaxima()) {
				JOptionPane.showMessageDialog(null,
						"A quantidade atual do Produto atingirá a capacidade máxima de produtos\n do Grupo atualizado!",
						"Capacidade máxima atingida", JOptionPane.WARNING_MESSAGE);
				if (grupoAntigo.getSubtotal() - produtoAtualizado.getQuantidade() > grupoAntigo.getQtdMinima()) {
					grupoAntigo.setSubtotal(grupoAntigo.getSubtotal() - produtoAtualizado.getQuantidade());
					manager.merge(grupoAntigo);
				} else {
					JOptionPane.showMessageDialog(null,
							"Se você trocar o Grupo deste Produto, o Grupo antigo ficará com uma quantidade\nabaixo do mínimo, o que não é permitido\nQuantidade mínima do Grupo: "
									+ grupoAntigo.getQtdMinima(),
							"Troca de Grupo inválida", JOptionPane.ERROR_MESSAGE);
					manager.close();
					return null;
				}
				grupoEncontrado.setSubtotal(grupoEncontrado.getSubtotal() + produtoAtualizado.getQuantidade());
				manager.merge(grupoEncontrado);
			} else {
				if (grupoAntigo.getSubtotal() - produtoAtualizado.getQuantidade() > grupoAntigo.getQtdMinima()) {
					grupoAntigo.setSubtotal(grupoAntigo.getSubtotal() - produtoAtualizado.getQuantidade());
				} else {
					JOptionPane.showMessageDialog(null,
							"Se você trocar o Grupo deste Produto, o Grupo antigo ficará com uma quantidade\nabaixo do mínimo, o que não é permitido\nQuantidade mínima do Grupo: "
									+ grupoAntigo.getQtdMinima(),
							"Troca de Grupo inválida", JOptionPane.ERROR_MESSAGE);
					manager.close();
					return null;
				}
				grupoEncontrado.setSubtotal(grupoEncontrado.getSubtotal() + produtoAtualizado.getQuantidade());

				JOptionPane.showMessageDialog(null,
						"A quantidade atual do Produto será adicionada ao subtotal\n do Grupo atualizado!",
						"Subtotal atualizado", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		manager.getTransaction().begin();

		manager.merge(grupoAntigo);
		manager.merge(grupoEncontrado);
		manager.merge(produtoAtualizado);

		manager.getTransaction().commit();

		JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!", "Produto atualizado",
				JOptionPane.INFORMATION_MESSAGE);

		ControllerAtualizarProduto.atualizou = 1;
		manager.close();
		return produtoAtualizado;
	}

	// Como os Produtos não podem ser definitivamente excluídos do banco, esse
	// método apenas define o Produto como "INATIVO"
	public void inativarProduto(Integer id) {
		manager = new JPAUtil().getEntityManager();

		Produto produtoDesativado = manager.find(Produto.class, id);
		Grupo grupoDoProduto = produtoDesativado.getGrupo();

		grupoDoProduto.setSubtotal(grupoDoProduto.getSubtotal() - produtoDesativado.getQuantidade());

		JOptionPane.showMessageDialog(null,
				"O Grupo deste Produto terá seu subtotal recalculado baseado na retirada deste Produto.",
				"Subtotal do Grupo recalculado", JOptionPane.WARNING_MESSAGE);

		if (grupoDoProduto.getSubtotal() < grupoDoProduto.getQtdMinima()) {
			JOptionPane.showMessageDialog(null,
					"O Grupo deste Produto ficou com uma quantidade abaixo do limite mínimo!", "Escassez de Produtos",
					JOptionPane.WARNING_MESSAGE);
		}

		produtoDesativado.setStatus(Status.INATIVO);

		manager.getTransaction().begin();
		manager.merge(produtoDesativado);
		manager.merge(grupoDoProduto);
		manager.getTransaction().commit();
		manager.close();
	}

	// Esse método compara as datas de Fabricação e de Vencimento de todos os
	// Produtos para ver se algum deles está vencido
	public static boolean checarVencimento() {
		// o método de listar todos os produtos é chamado aqui, para olhar cada produto
		// dentro da lista e checar individualmente
		@SuppressWarnings("rawtypes")
		List<String> produtosVencidos = new ArrayList();

		for (Produto produto : listarApenasProdutosAtivos()) {
			if (produto.getDataVencimento().getTime().before(Calendar.getInstance().getTime())) {
				produtosVencidos.add("ID: " + produto.getId() + " - Grupo: " + produto.getGrupo().getNome() + "\n");

				JOptionPane.showMessageDialog(null, "Produtos vencidos:\n"
						+ produtosVencidos.toString().replace("[", "").replace("]", "\nInative-os o quanto antes!"),
						"Produtos vencidos", JOptionPane.WARNING_MESSAGE);
				return true;
			}
		}

		JOptionPane.showMessageDialog(null, "Todos os Produtos estão em dia!", "Produtos em dia",
				JOptionPane.INFORMATION_MESSAGE);
		return false;
	}
}