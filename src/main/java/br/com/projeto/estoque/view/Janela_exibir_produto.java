package br.com.projeto.estoque.view;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import br.com.projeto.estoque.controller.ControllerProduto;
import br.com.projeto.estoque.controller.ControllerViewProduto;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Janela_exibir_produto extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable tabelaProduto;
	private ControllerProduto cp;

	public Janela_exibir_produto() {
		cp = new ControllerProduto();
		initialize();
		cp.checarVencimento();
	}

	private void initialize() {
		setBounds(-5, 0, 788, 488);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerViewProduto.popularTabelaProdutos(tabelaProduto, cp.encontrarTodosOsProdutos());
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(349)
							.addComponent(btnAtualizar)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAtualizar)
					.addGap(95)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
					.addGap(44))
		);

		tabelaProduto = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tabelaProduto.setFillsViewportHeight(true);
		tabelaProduto.setColumnSelectionAllowed(true);
		tabelaProduto.setCellSelectionEnabled(true);
		tabelaProduto.setRowSelectionAllowed(false);
		tabelaProduto.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(tabelaProduto);
		getContentPane().setLayout(groupLayout);

		ControllerViewProduto.popularTabelaProdutos(tabelaProduto, cp.encontrarTodosOsProdutos());
	}
}
