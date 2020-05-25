package br.com.projeto.estoque.viewUpdate.tableModels;

import java.awt.Component;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import br.com.projeto.estoque.controller.ControllerMovimentacao;
import br.com.projeto.estoque.model.Movimentacao;

public class TableModelMovimentacoes {
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
			tableModel.addRow(new Object[] {
					movimentacao.getId(),
					movimentacao.getData(),
					movimentacao.getDescricao(),
					movimentacao.getQtdProduto(),
					movimentacao.getTipoMovimentacao(),
					movimentacao.getFornecedor().getNome(),
					movimentacao.getProduto().getGrupo().getNome()
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
