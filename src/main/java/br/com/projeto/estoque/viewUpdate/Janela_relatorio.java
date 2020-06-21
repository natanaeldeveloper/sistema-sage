package br.com.projeto.estoque.viewUpdate;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Janela_relatorio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_relatorio frame = new Janela_relatorio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Janela_relatorio() {
		setTitle("Relatórios");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Gerar");
		btnNewButton.setBounds(257, 412, 125, 36);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Gerar Relatório");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		lblNewLabel.setBounds(10, 11, 378, 42);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 64, 378, 9);
		contentPane.add(separator);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Lista - Produtos", "Lista - Movimentações de Produtos"}));
		comboBox.setBounds(130, 84, 247, 27);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de Relatório :");
		lblNewLabel_1.setBounds(10, 84, 110, 27);
		contentPane.add(lblNewLabel_1);
		setLocationRelativeTo(null);
	}
}
