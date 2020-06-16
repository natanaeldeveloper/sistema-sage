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
	public Produto cadastrarProduto(Integer id_grupo, String descricao, BigDecimal preco,
			int quantidade, Calendar dataFabricacao, Calendar dataVencimento) {
		manager = new JPAUtil().getEntityManager();

		Grupo g = manager.find(Grupo.class, id_grupo);

		Produto produto = new Produto();
		produto.setGrupo(g);
		produto.setDescricao(descricao);
		produto.setPreco(preco);
		produto.setQuantidade(quantidade);
		produto.setDataFabricacao(dataFabricacao);
		produto.setDataVencimento(dataVencimento);
		// O produto já é altomaticamente definido como ativo, após entrar no banco de
		// dados
		produto.setStatus(Status.ATIVO);

		manager.getTransaction().begin();
		manager.persist(produto);

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
	public Produto atualizarProduto(Integer id, BigDecimal preco, int quantidade, String descricao,
			Calendar dataFabricacao, Calendar dataVencimento, Integer id_grupo) {
		manager = new JPAUtil().getEntityManager();
		Grupo grupoEncontrado = manager.find(Grupo.class, id_grupo);
		Produto produtoAtualizado = manager.find(Produto.class, id);
		produtoAtualizado.setPreco(preco);
		produtoAtualizado.setQuantidade(quantidade);
		produtoAtualizado.setDescricao(descricao);
		produtoAtualizado.setDataFabricacao(dataFabricacao);
		produtoAtualizado.setDataVencimento(dataVencimento);
		produtoAtualizado.setGrupo(grupoEncontrado);
		manager.getTransaction().begin();
		manager.merge(produtoAtualizado);
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
