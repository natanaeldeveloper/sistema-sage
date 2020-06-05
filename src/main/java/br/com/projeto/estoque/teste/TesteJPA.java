package br.com.projeto.estoque.teste;

import javax.persistence.EntityManager;

import br.com.projeto.estoque.util.JPAUtil;

public class TesteJPA {
	private static EntityManager manager;

	public static void main(String[] args) {
		manager = new JPAUtil().getEntityManager();

		// ControllerProduto cp = new ControllerProduto();
		// cp.cadastrarProduto(1, 1, "Brr", new BigDecimal("3.0"), 1,
		// Calendar.getInstance(), Calendar.getInstance());
		// cp.inativarProduto(2);

		// ControllerMovimentacao cm = new ControllerMovimentacao();
		// cm.cadastrarMovimentacao(manager.find(Produto.class, 2),
		// manager.find(Fornecedor.class, 1));
		// for (Movimentacao movimentacao : cm.listarMovimentacoesDeProdutosInativos())
		// {
		// System.out.println(movimentacao.getId());
		// }

		// ControllerFornecedor cf = new ControllerFornecedor();
		// Endereco endereco = new Endereco();
		// endereco.setBairro("São Bento");
		// endereco.setCep("61801-670");
		// endereco.setCidade("Pacatuba");
		// endereco.setEstado("Ceará");
		// endereco.setComplemento("");
		// endereco.setLogradouro("R. Aurora Galdêncio de Sousa");
		// endereco.setNumero("291");
		// cf.criarFornecedor("Fornecedor A", "938217-3298/321", "Joaquim Barbosa",
		// "3345-2440", "joaquim.contato@gmail.com", endereco);
		manager.close();
	}
}
