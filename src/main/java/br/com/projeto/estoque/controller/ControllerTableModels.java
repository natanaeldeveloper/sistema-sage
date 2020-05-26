package br.com.projeto.estoque.controller;

import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.model.Gerente;
import br.com.projeto.estoque.model.Movimentacao;
import br.com.projeto.estoque.model.Produto;
import br.com.projeto.estoque.model.RegistroGerente;
import br.com.projeto.estoque.util.Essencial;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings("unchecked")
public class ControllerTableModels {

	public ControllerTableModels(JTable table_registros, JTable table_gerente, JTable table_fornecedores,
			JTable table_produtos, JTable table_movimentacoes) {
		popularTableRegistrosGerente(table_registros);
		popularTableGerente(table_gerente);
		popularTabelaFornecedores(table_fornecedores);
		popularTabelaProdutos(table_produtos);
		popularTabelaMovimentacoes(table_movimentacoes);
	}

	public static void popularTableRegistrosGerente(JTable table_registros) {
		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.setQuery(Essencial.getManager().createNamedQuery("buscarRegistrosGerentes"));
		List<RegistroGerente> registrosGerente;
		registrosGerente = Essencial.getQuery().getResultList();

		DefaultTableModel modelo = new DefaultTableModel();
		table_registros.setModel(modelo);
		modelo.addColumn("ID:");
		modelo.addColumn("Gerente:");
		modelo.addColumn("Entrada/Saída:");
		modelo.addColumn("Data e Hora:");

		for (RegistroGerente registro : registrosGerente) {
			modelo.addRow(new Object[] { registro.getId(), registro.getGerente().getId(),
					registro.getTipoComportamento(), registro.getDataEHora() });
		}

		table_registros.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(table_registros, 126);
	}
	
	public void popularTableGerente(JTable table_gerente) {
		Essencial.setQuery(Essencial.getManager().createNamedQuery("buscarGerentes"));
		List<Gerente> registrosGerente;
		registrosGerente = Essencial.getQuery().getResultList();

		DefaultTableModel modelo = new DefaultTableModel();
		table_gerente.setModel(modelo);
		modelo.addColumn("ID:");
		modelo.addColumn("CPF:");

		for (Gerente registro : registrosGerente) {
			modelo.addRow(new Object[] { registro.getId(), registro.getCpf(), });
		}
		
		table_gerente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(table_gerente, 257);
	}

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

		List<Fornecedor> listaTabela = ControllerFornecedor.listarFornecedores();
		for (Fornecedor fornecedor : listaTabela) {
			tableModel.addRow(new Object[] { fornecedor.getId(), fornecedor.getCnpj(), fornecedor.getNome(),
					fornecedor.getRazaoSocial(), fornecedor.getEmail(), fornecedor.getTelefone(),
					fornecedor.getEndereco().getEstado(), fornecedor.getEndereco().getCidade(),
					fornecedor.getEndereco().getBairro(), fornecedor.getEndereco().getCep(),
					fornecedor.getEndereco().getLogradouro(), fornecedor.getEndereco().getNumero(),
					fornecedor.getEndereco().getComplemento() });
		}

		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(tabela, 150);
	}

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
			tableModel.addRow(new Object[] { movimentacao.getId(), movimentacao.getData(), movimentacao.getDescricao(),
					movimentacao.getQtdProduto(), movimentacao.getTipoMovimentacao(),
					movimentacao.getFornecedor().getNome(), movimentacao.getProduto().getGrupo().getNome() });
		}

		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(tabela, 164);
	}

	public static void popularTabelaProdutos(JTable tabela) {
		DefaultTableModel tableModel = new DefaultTableModel();
		tabela.setModel(tableModel);

		tableModel.addColumn("ID:");
		tableModel.addColumn("Grupo:");
		tableModel.addColumn("Descrição:");
		tableModel.addColumn("Preço:");
		tableModel.addColumn("Quantidade:");
		tableModel.addColumn("Data de Fabricação:");
		tableModel.addColumn("Data de Vencimento:");

		List<Produto> listaTabela = ControllerProduto.listarProdutos();

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		for (Produto produto : listaTabela) {
			tableModel.addRow(new Object[] { produto.getId(), produto.getGrupo().getNome(), produto.getDescricao(),
					produto.getPreco(), produto.getQuantidade(), df.format(produto.getDataFabricacao().getTime()),
					df.format(produto.getDataVencimento().getTime()) });
		}

		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(tabela, 164);
	}

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