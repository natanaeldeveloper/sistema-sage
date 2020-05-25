package br.com.projeto.estoque.teste;

import javax.persistence.EntityManager;

import br.com.projeto.estoque.controller.ControllerMovimentacao;
import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.model.Produto;
import br.com.projeto.estoque.util.JPAUtil;

public class TesteJPA {

	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();

		ControllerMovimentacao cm = new ControllerMovimentacao();
		cm.cadastrarMovimentacao(manager.find(Produto.class, 8), manager.find(Fornecedor.class, 1));
	}
}
