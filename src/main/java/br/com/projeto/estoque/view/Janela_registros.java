package br.com.projeto.estoque.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

import br.com.projeto.estoque.teste.Relatorio;
import javax.swing.JComboBox;

public class Janela_registros extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JComboBox<Object> comboBox;

	public Janela_registros() {
		setResizable(true);
		setIconifiable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setBounds(-5, 0, 788, 430);

		JButton btnGerarRelatrio = new JButton("Gerar Relatório");
		btnGerarRelatrio.setBounds(356, 182, 134, 26);
		getContentPane().add(btnGerarRelatrio);

		String[] relatorios = new String[] { "Relatório de Movimentações", "Relatório de Produtos" };
		comboBox = new JComboBox<Object>(relatorios);
		comboBox.setBounds(324, 120, 198, 25);
		getContentPane().add(comboBox);

		btnGerarRelatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedIndex() == 0) {
//					Relatorio.gerarRelatorio("RelatorioMovimentacoes", "Relatório de Movimentações");
				} else if (comboBox.getSelectedIndex() == 1) {
//					Relatorio.gerarRelatorio("RelatorioCriacaoProdutos", "Relatório de Produtos");
				}
			}
		});
	}
}
