package br.com.projeto.estoque.viewUpdate;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.projeto.estoque.controller.ControllerRegistroProdutos;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Janela_relatorio extends JFrame {
	private JComboBox tipo;
	private JPanel contentPane;
	private JLabel lblNewLabel_2;
	private JTextField nome;
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
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setTitle("Relatórios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 341, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton gerar = new JButton("Gerar");
		gerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControllerRegistroProdutos cr = new ControllerRegistroProdutos();
				cr.gerarRegistros(nome.getText());
			}
		});
		gerar.setBounds(190, 437, 125, 36);
		contentPane.add(gerar);
		
		JLabel lblNewLabel = new JLabel("Gerar Relatório");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		lblNewLabel.setBounds(10, 11, 314, 42);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 64, 314, 9);
		contentPane.add(separator);
		
		tipo = new JComboBox();
		tipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tipo.getSelectedIndex() == 1) {
					lblNewLabel_2.setIcon(new ImageIcon(Janela_relatorio.class.getResource("/images/img1.png")));
				}
				else if(tipo.getSelectedIndex() == 0){
					lblNewLabel_2.setIcon(new ImageIcon(Janela_relatorio.class.getResource("/images/img2.png")));
				}
			}
		});
		tipo.setModel(new DefaultComboBoxModel(new String[] {"Lista - Produtos", "Lista - Movimentações de Produtos"}));
		tipo.setBounds(134, 84, 175, 27);
		contentPane.add(tipo);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de Relatório :");
		lblNewLabel_1.setBounds(10, 84, 110, 27);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(59, 161, 216, 265);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nome :");
		lblNewLabel_1_1.setBounds(10, 122, 110, 27);
		contentPane.add(lblNewLabel_1_1);
		
		nome = new JTextField();
		nome.setBounds(134, 122, 175, 27);
		contentPane.add(nome);
		nome.setColumns(10);
		setLocationRelativeTo(null);
	}
}
