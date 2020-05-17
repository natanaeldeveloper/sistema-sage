package br.com.projeto.estoque.controller;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import br.com.projeto.estoque.model.Produto;

public class ControllerViewProduto {
	public static void popularTabelaProdutos(JTable tabelaProduto, List<Produto> listaProdutos) {
		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("ID:");
		modelo.addColumn("Código:");
		modelo.addColumn("Nome:");
		modelo.addColumn("Categoria:");
		modelo.addColumn("Quantidade:");
		modelo.addColumn("Preço:");
		modelo.addColumn("Peso:");
		modelo.addColumn("Unidade:");
		modelo.addColumn("Fornecedor:");
		modelo.addColumn("Data de Fabricação:");
		modelo.addColumn("Data de Vencimento:");

		for (Produto produto : listaProdutos) {
			SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
			modelo.addRow(new Object[] { produto.getId(), produto.getCodigo(), produto.getNome(),
					produto.getCategoria().getNome(), produto.getQuantidade(), produto.getPreco(), produto.getPeso(),
					produto.getUnidade(), produto.getFornecedor().getNome(),
					format1.format(produto.getDataFabricacao().getTime()),
					format1.format(produto.getDataVencimento().getTime()) });
			;
		}
		tabelaProduto.setModel(modelo);
		tabelaProduto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(tabelaProduto);
	}

	public static void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 120; // Min width
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
