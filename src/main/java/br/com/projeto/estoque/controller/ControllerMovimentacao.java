package br.com.projeto.estoque.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.model.Grupo;
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
	private static Timestamp ts;

	// No método construtor, é criado um novo Timestamp que será usado para definir
	// o horário da movimentação
	public ControllerMovimentacao() {
		data = new Date();
		tempo = data.getTime();
		ts = new Timestamp(tempo);
	}

	// Método responsável por criar novas movimentações
	public Movimentacao cadastrarMovimentacao(TipoMovimentacao tipoMovimentacao, Integer idFornecedor,
			Integer idProduto, int quantidade, String descricao) {

		manager = new JPAUtil().getEntityManager();

		// É instanciada uma nova movimentação
		Movimentacao movimentacao = new Movimentacao();

		// O Fornecedor é tido inicialmente como nulo. Ele é instanciado apenas se for
		// uma movimentação de ENTRADA
		Fornecedor fornecedor = null;
		if (ControllerValidationMovimentacao.movimentacaoTipoSaida == false) {
			fornecedor = manager.find(Fornecedor.class, idFornecedor);
		}

		Produto produto = manager.find(Produto.class, idProduto);

		// A data é definida pelo Timestamp do exato momento em que a movimentação é
		// criada
		movimentacao.setData(ts);

		movimentacao.setTipoMovimentacao(tipoMovimentacao);
		movimentacao.setFornecedor(fornecedor);
		movimentacao.setProduto(produto);
		movimentacao.setDescricao(descricao);

		movimentacao.setQtdProduto(quantidade);

		// É necessário mudar o subtotal do Grupo do Produto movimentado, por isso ele é
		// pego aqui.
		Grupo grupoDoProdutoMovimentado = produto.getGrupo();

		// Código de execução caso seja uma movimentação de SAÍDA
		if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.SAIDA) {

			// O subtotal do grupo é subtraído de acordo com a quantidade movimentada
			grupoDoProdutoMovimentado
					.setSubtotal(grupoDoProdutoMovimentado.getSubtotal() - movimentacao.getQtdProduto());

			// Se o subtotal ficar abaixo do limite mínimo, essa mensagem é mostrada
			if (grupoDoProdutoMovimentado.getSubtotal() < grupoDoProdutoMovimentado.getQtdMinima()) {
				JOptionPane.showMessageDialog(null,
						"Operação inválida! O Grupo deste Produto ficaria abaixo do estoque mínimo!",
						"Movimentação inválida", JOptionPane.ERROR_MESSAGE);
				return null;

				// Se o subtotal for igual ao limite mínimo, essa mensagem é mostrada
			} else if (grupoDoProdutoMovimentado.getSubtotal() == grupoDoProdutoMovimentado.getQtdMinima()) {
				JOptionPane.showMessageDialog(null, "O Grupo deste Produto atingiu sua capacidade mínima!",
						"Capacidade mínima atingida", JOptionPane.WARNING_MESSAGE);
			}

			// Após as verificações do subtotal do Grupo, aqui é verificado se o usuário
			// está tentando tirar mais produtos do que a quantidade que existe
			if (movimentacao.getQtdProduto() > produto.getQuantidade()) {
				JOptionPane.showMessageDialog(null, "Você está tentando retirar mais produtos do que existem!",
						"Saída inválida", JOptionPane.ERROR_MESSAGE);
				return null;

				// Se ele retirar todos os Produtos, o Produto é definido como INATIVO e o
				// programa continua
			} else if (movimentacao.getQtdProduto() == produto.getQuantidade()) {
				produto.setQuantidade(produto.getQuantidade() - movimentacao.getQtdProduto());
				JOptionPane.showMessageDialog(null, "Todos os produtos desta leva foram retirados!",
						"Produtos retirados", JOptionPane.WARNING_MESSAGE);
				produto.setStatus(Status.INATIVO);

				// Se não houver nenhuma peculiaridade na operação, é mostrada a mensagem
				// padrão. E por fim, a quantidade do Produto é recalculada
			} else {
				produto.setQuantidade(produto.getQuantidade() - movimentacao.getQtdProduto());

				JOptionPane.showMessageDialog(null, "Produtos retirados com sucesso!", "Produtos retirados",
						JOptionPane.INFORMATION_MESSAGE);
			}

			// O código abaixo é executado caso seja uma movimentação de ENTRADA
		} else if (movimentacao.getTipoMovimentacao() == TipoMovimentacao.ENTRADA) {

			// O subtotal do Grupo desse Produto é incrementado baseado na quantidade
			// movimentada
			grupoDoProdutoMovimentado
					.setSubtotal(grupoDoProdutoMovimentado.getSubtotal() + movimentacao.getQtdProduto());

			// Se o subtotal ficar maior que o limite máximo, essa mensagem é mostrada
			if (grupoDoProdutoMovimentado.getSubtotal() > grupoDoProdutoMovimentado.getQtdMaxima()) {
				JOptionPane.showMessageDialog(null,
						"Operação inválida! O Grupo deste Produto ficaria acima do estoque máximo!",
						"Movimentação inválida", JOptionPane.ERROR_MESSAGE);
				return null;

				// Se o subtotal ficar igual ao limite máximo, essa mensagem é mostrada e o
				// programa continua
			} else if (grupoDoProdutoMovimentado.getSubtotal() == grupoDoProdutoMovimentado.getQtdMaxima()) {
				JOptionPane.showMessageDialog(null, "O Grupo deste Produto atingiu seu estoque máximo!",
						"Estoque máximo atingido", JOptionPane.WARNING_MESSAGE);
			}

			// Se não houver nenhum erro, o Produto tem por fim sua quantidade recalculada e
			// o método tem sua execução finalizada
			produto.setQuantidade(produto.getQuantidade() + movimentacao.getQtdProduto());
			JOptionPane.showMessageDialog(null, "Novos Produtos entraram no estoque com sucesso!",
					"Entrada de Produtos", JOptionPane.INFORMATION_MESSAGE);
		}

		// A movimentação é criada, e o Produto e seu Grupo são atualizados
		manager.getTransaction().begin();
		manager.persist(movimentacao);
		manager.merge(produto);
		manager.merge(grupoDoProdutoMovimentado);
		manager.getTransaction().commit();

		// A variável indicando que a operação foi um sucesso é retornada para este
		// Controller
		ControllerValidationMovimentacao.sucessoMovimentacao = true;

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

	// Código para limpar os inputs da movimentação na tela de CADASTRAR PRODUTO
	@SuppressWarnings("rawtypes")
	public static void limparNoCadastro(JEditorPane epDescricaoMovimentacaoCadastro, JComboBox cbFornecedorCadastro) {
		epDescricaoMovimentacaoCadastro.setText("");
		cbFornecedorCadastro.setSelectedIndex(0);
	}
}