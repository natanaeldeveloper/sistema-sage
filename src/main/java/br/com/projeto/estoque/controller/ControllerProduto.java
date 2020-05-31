package br.com.projeto.estoque.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import br.com.projeto.estoque.model.Grupo;
import br.com.projeto.estoque.model.Produto;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings("unchecked")
public class ControllerProduto {
	private static EntityManager manager;
	
	public Produto cadastrarProduto(Integer id_grupo, Integer id_fornecedor, String descricao, BigDecimal preco, int quantidade, Calendar dataFabricacao, Calendar dataVencimento) {
		manager = new JPAUtil().getEntityManager();
		
		Grupo g = manager.find(Grupo.class, id_grupo);
		
		Produto produto = new Produto();
		produto.setGrupo(g);
		produto.setDescricao(descricao);
		produto.setPreco(preco);
		produto.setQuantidade(quantidade);
		produto.setDataFabricacao(dataFabricacao);
		produto.setDataVencimento(dataVencimento);
		
		manager.getTransaction().begin();
		manager.persist(produto);
		if(g.isEstocado() == false) {
			g.setEstocado(true);
			manager.merge(g);
		}
		manager.getTransaction().commit();

		manager.close();
		return produto;
	}
	
	public static List<Produto> listarProdutos() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select p from Produto p");
		List<Produto> produtos = query.getResultList();
		manager.close();
		return produtos;
	}
	
	public boolean checarVencimento() {
		for (Produto produto : listarProdutos()) {
			if (produto.getDataVencimento().getTime().before(Calendar.getInstance().getTime())) {
				JOptionPane.showMessageDialog(null,
						"Produto vencido!\nID: " + produto.getId() + "\nNome: " + produto.getGrupo().getNome(), "Item vencido",
						JOptionPane.WARNING_MESSAGE);
				return true;
			}
		}
		return false;
	}
}
