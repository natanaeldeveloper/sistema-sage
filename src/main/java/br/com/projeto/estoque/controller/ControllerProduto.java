package br.com.projeto.estoque.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import br.com.projeto.estoque.model.Grupo;
import br.com.projeto.estoque.model.Produto;
import br.com.projeto.estoque.model.Status;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings("unchecked")
public class ControllerProduto {
	private static EntityManager manager;

	// Método para cadastrar um novo Produto
	public Produto cadastrarProduto(Integer id_grupo, String descricao, BigDecimal preco, int quantidade, Double medida,
			String unidade, Calendar dataFabricacao, Calendar dataVencimento) {
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
		if (quantidade + g.getSubtotal() > g.getQtdMaxima()) {
			JOptionPane.showMessageDialog(null,
					"Essa quantidade de produtos excederia a quantidade máxima deles no estoque!",
					"Quantidade máxima excedida", JOptionPane.ERROR_MESSAGE);
			return null;
		} else if (quantidade + g.getSubtotal() == g.getQtdMaxima()) {
			JOptionPane.showMessageDialog(null, "A quantidade máxima desses produtos no estoque foi atingida!",
					"Quantidade máxima atingida", JOptionPane.WARNING_MESSAGE);
			g.setSubtotal(g.getSubtotal() + quantidade);
		} else {
			g.setSubtotal(g.getSubtotal() + quantidade);
		}

		manager.getTransaction().begin();

		manager.persist(produto);
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

	// Método para atualizar o Produto
	public Produto atualizarProduto(Integer id, BigDecimal preco, int quantidade, Double medida, String unidade,
			String descricao, Calendar dataFabricacao, Calendar dataVencimento, Integer id_grupo) {
		manager = new JPAUtil().getEntityManager();
		Grupo grupoEncontrado = manager.find(Grupo.class, id_grupo);
		Produto produtoAtualizado = manager.find(Produto.class, id);
		produtoAtualizado.setPreco(preco);
		produtoAtualizado.setQuantidade(quantidade);
		produtoAtualizado.setMedida(medida);
		produtoAtualizado.setUnidade(unidade);
		produtoAtualizado.setDescricao(descricao);
		produtoAtualizado.setDataFabricacao(dataFabricacao);
		produtoAtualizado.setDataVencimento(dataVencimento);
		produtoAtualizado.setGrupo(grupoEncontrado);

		if (quantidade + grupoEncontrado.getSubtotal() > grupoEncontrado.getQtdMaxima()) {
			JOptionPane.showMessageDialog(null,
					"Essa quantidade de produtos excederia a quantidade máxima deles no estoque!",
					"Quantidade máxima excedida", JOptionPane.ERROR_MESSAGE);
			return null;
		} else if (quantidade + grupoEncontrado.getSubtotal() == grupoEncontrado.getQtdMaxima()) {
			JOptionPane.showMessageDialog(null, "A quantidade máxima desses produtos no estoque foi atingida!",
					"Quantidade máxima atingida", JOptionPane.WARNING_MESSAGE);
			grupoEncontrado.setSubtotal(grupoEncontrado.getSubtotal() + quantidade);
		} else {
			grupoEncontrado.setSubtotal(grupoEncontrado.getSubtotal() + quantidade);
		}

		manager.getTransaction().begin();
		manager.merge(produtoAtualizado);
		manager.merge(grupoEncontrado);
		manager.getTransaction().commit();
		manager.close();
		return produtoAtualizado;
	}

	// Como os Produtos não podem ser definitivamente excluídos do banco, esse
	// método apenas define o Produto como "INATIVO"
	public void inativarProduto(Integer id) {
		manager = new JPAUtil().getEntityManager();
		Produto produtoDesativado = manager.find(Produto.class, id);
		produtoDesativado.setStatus(Status.INATIVO);
		manager.getTransaction().begin();
		manager.merge(produtoDesativado);
		manager.getTransaction().commit();
		manager.close();
	}

	// Esse método compara as datas de Fabricação e de Vencimento de todos os
	// Produtos para ver se algum deles está vencido
	public boolean checarVencimento() {
		// o método de listar todos os produtos é chamado aqui, para olhar cada produto
		// dentro da lista e checar individualmente
		for (Produto produto : listarProdutos()) {
			if (produto.getDataVencimento().getTime().before(Calendar.getInstance().getTime())) {
				JOptionPane.showMessageDialog(null,
						"Produto vencido!\nID: " + produto.getId() + "\nNome: " + produto.getGrupo().getNome(),
						"Item vencido", JOptionPane.WARNING_MESSAGE);
				return true;
			}
		}
		return false;
	}
}
