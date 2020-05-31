package br.com.projeto.estoque.viewUpdate.tableModels;

import java.awt.Component;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import br.com.projeto.estoque.controller.ControllerFornecedor;
import br.com.projeto.estoque.model.Fornecedor;

public class TableModelFornecedores {
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
			tableModel.addRow(new Object[] {
					fornecedor.getId(),
					fornecedor.getCnpj(),
					fornecedor.getNome(),
					fornecedor.getRazaoSocial(),
					fornecedor.getEmail(),
					fornecedor.getTelefone(),
					fornecedor.getEndereco().getEstado(),
					fornecedor.getEndereco().getCidade(),
					fornecedor.getEndereco().getBairro(),
					fornecedor.getEndereco().getCep(),
					fornecedor.getEndereco().getLogradouro(),
					fornecedor.getEndereco().getNumero(),
					fornecedor.getEndereco().getComplemento()
			});
		}
		
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumnWidth(tabela);
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
