package br.com.projeto.estoque.viewUpdate.tableModels;

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

import br.com.projeto.estoque.controller.ControllerProduto;
import br.com.projeto.estoque.model.Produto;

public class TableModelProdutos {
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
			tableModel.addRow(new Object[] {
					produto.getId(),
					produto.getGrupo().getNome(),
					produto.getDescricao(),
					produto.getPreco(),
					produto.getQuantidade(),
					df.format(produto.getDataFabricacao().getTime()),
					df.format(produto.getDataVencimento().getTime())
			});
		}
		
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(tabela);
	}
	
	public static void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 164; // Min width
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
