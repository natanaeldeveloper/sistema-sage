package br.com.projeto.estoque.controller;

import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.model.Movimentacao;
import br.com.projeto.estoque.model.Produto;
import br.com.projeto.estoque.model.RegistroSupervisor;
import br.com.projeto.estoque.model.Status;
import br.com.projeto.estoque.model.Supervisor;
import br.com.projeto.estoque.util.Essencial;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings("unchecked")
public class ControllerTableModels {
	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");

	// Méotdo construtor, popula todas as tabelas baseado nos parâmetros
	public ControllerTableModels(JTable table_registros, JTable table_fornecedores, JTable table_produtos,
			JTable table_movimentacoes, JTable table_supervisores) {
		popularTableRegistrosSupervisor(table_registros);
		popularTabelaFornecedores(table_fornecedores);
		popularTabelaProdutos(table_produtos);
		popularTabelaMovimentacoes(table_movimentacoes);
		popularTableSupervisor(table_supervisores);
	}

	// Método para popular a tabela de registros do gerente
	public static void popularTableRegistrosSupervisor(JTable table_registros) {
		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.setQuery(Essencial.getManager().createNamedQuery("buscarRegistrosSupervisores"));
		List<RegistroSupervisor> registrosSupervisor;
		registrosSupervisor = Essencial.getQuery().getResultList();

		DefaultTableModel modelo = new DefaultTableModel();
		table_registros.setModel(modelo);
		modelo.addColumn("ID:");
		modelo.addColumn("Supervisor:");
		modelo.addColumn("Entrada/Saída:");
		modelo.addColumn("Data e Hora:");

		for (RegistroSupervisor registro : registrosSupervisor) {
			modelo.addRow(new Object[] { registro.getId(), registro.getSupervisor().getId(),
					registro.getTipoComportamento(), dateFormat.format(registro.getDataEHora()) });
		}

		table_registros.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(table_registros, 129);
	}

	public static void popularTableSupervisor(JTable tableSupervisor) {
		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.setQuery(Essencial.getManager().createNamedQuery("supervisores"));
		List<Supervisor> supervisores;
		supervisores = Essencial.getQuery().getResultList();
		DefaultTableModel modelo = new DefaultTableModel();
		tableSupervisor.setModel(modelo);
		modelo.addColumn("ID:");
		modelo.addColumn("CPF:");
		modelo.addColumn("Login:");
		for (Supervisor supervisor : supervisores) {
			modelo.addRow(new Object[] { supervisor.getId(), supervisor.getCpf(), supervisor.getLogin() });
		}

		tableSupervisor.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(tableSupervisor, 172);

	}

	// Método para popular a tabela de fornecedores
	public static void popularTabelaFornecedores(JTable tabela) {
		DefaultTableModel tableModel = new DefaultTableModel();
		tabela.setModel(tableModel);

		tableModel.addColumn("ID:");
		tableModel.addColumn("CNPJ:");
		tableModel.addColumn("Nome:");
		tableModel.addColumn("Razão Social:");
		tableModel.addColumn("Email:");
		tableModel.addColumn("Telefone:");
		tableModel.addColumn("Estado:");
		tableModel.addColumn("Cidade:");
		tableModel.addColumn("Bairro:");
		tableModel.addColumn("CEP:");
		tableModel.addColumn("Logradouro:");
		tableModel.addColumn("Número:");
		tableModel.addColumn("Complemento:");
		tableModel.addColumn("Status:");

		// São listados nesta tabela apenas os Fornecedores ativos
		List<Fornecedor> listaTabela = ControllerFornecedor.listarApenasFornecedoresAtivos();

		for (Fornecedor fornecedor : listaTabela) {
			tableModel.addRow(new Object[] { fornecedor.getId(), fornecedor.getCnpj(), fornecedor.getNome(),
					fornecedor.getRazaoSocial(), fornecedor.getEmail(), fornecedor.getTelefone(),
					fornecedor.getEndereco().getEstado(), fornecedor.getEndereco().getCidade(),
					fornecedor.getEndereco().getBairro(), fornecedor.getEndereco().getCep(),
					fornecedor.getEndereco().getLogradouro(), fornecedor.getEndereco().getNumero(),
					fornecedor.getEndereco().getComplemento(), fornecedor.getStatus() });
		}

		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(tabela, 150);
	}

	// Método para popular a tabela de movimentações
	public static void popularTabelaMovimentacoes(JTable tabela) {
		DefaultTableModel tableModel = new DefaultTableModel();
		tabela.setModel(tableModel);

		tableModel.addColumn("ID:");
		tableModel.addColumn("Data:");
		tableModel.addColumn("Descrição:");
		tableModel.addColumn("Quantidade Movimentada:");
		tableModel.addColumn("Tipo de Movimentação:");
		tableModel.addColumn("Fornecedor:");
		tableModel.addColumn("Grupo do Produto:");

		List<Movimentacao> listaTabela = ControllerMovimentacao.listarMovimentacoes();
		for (Movimentacao movimentacao : listaTabela) {
			if (movimentacao.getFornecedor().getStatus() == Status.ATIVO) {
				tableModel.addRow(new Object[] { movimentacao.getId(), dateFormat.format(movimentacao.getData()),
						movimentacao.getDescricao(), movimentacao.getQtdProduto(), movimentacao.getTipoMovimentacao(),
						movimentacao.getFornecedor().getNome(), movimentacao.getProduto().getGrupo().getNome() });
			} else {
				tableModel.addRow(new Object[] { "*" + movimentacao.getId(), dateFormat.format(movimentacao.getData()),
						movimentacao.getDescricao(), movimentacao.getQtdProduto(), movimentacao.getTipoMovimentacao(),
						movimentacao.getFornecedor().getNome() + " - inativado", movimentacao.getProduto().getGrupo().getNome() });
			}
		}

		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(tabela, 164);
	}

	// Método para popular a tabela de produtos
	public static void popularTabelaProdutos(JTable tabela) {
		DefaultTableModel tableModel = new DefaultTableModel();
		tabela.setModel(tableModel);

		tableModel.addColumn("ID:");
		tableModel.addColumn("Grupo:");
		tableModel.addColumn("Preço:");
		tableModel.addColumn("Quantidade:");
		tableModel.addColumn("Medida:");
		tableModel.addColumn("Unidade:");
		tableModel.addColumn("Data de Fabricação:");
		tableModel.addColumn("Data de Vencimento:");
		tableModel.addColumn("Descrição:");
		tableModel.addColumn("STATUS:");

		// São listados apenas os protudos ativos nesta tabela
		List<Produto> listaTabela = ControllerProduto.listarApenasProdutosAtivos();

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		for (Produto produto : listaTabela) {
			tableModel.addRow(new Object[] { produto.getId(), produto.getGrupo().getNome(), produto.getPreco(),
					produto.getQuantidade(), produto.getMedida(), produto.getUnidade(),
					df.format(produto.getDataFabricacao().getTime()), df.format(produto.getDataVencimento().getTime()),
					produto.getDescricao(), produto.getStatus() });
		}

		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(tabela, 164);
	}

	// Método para redimencionar e centralizar as colunas, tomando a tabela como
	// primeiro parâmetro e o tamanho
	// de todas as suas colunas como o segundo
	public static void resizeColumnWidth(JTable table, int minW) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = minW; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			if (width > 300)
				width = 300;
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}
}