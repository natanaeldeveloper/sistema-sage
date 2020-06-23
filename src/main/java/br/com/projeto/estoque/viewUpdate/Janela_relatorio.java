package br.com.projeto.estoque.viewUpdate;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.projeto.estoque.teste.Relatorio;

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
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class Janela_relatorio extends JFrame {
	private JComboBox tipo;
	private JPanel contentPane;
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
		setBounds(100, 100, 341, 254);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton gerar = new JButton("Gerar");
		gerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Relatorio r = new Relatorio();
				r.gerarRelatorio(nome.getText());
			}
		});
		gerar.setBounds(100, 172, 125, 32);
		contentPane.add(gerar);
		
		JLabel lblNewLabel = new JLabel("Gerar Relatório");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		lblNewLabel.setBounds(10, 11, 305, 42);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 64, 305, 9);
		contentPane.add(separator);
		
		tipo = new JComboBox();
		tipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tipo.getSelectedIndex() == 1) {
//					lblNewLabel_2.setIcon(new ImageIcon(Janela_relatorio.class.getResource("/images/img1.png")));
				}
				else if(tipo.getSelectedIndex() == 0){
//					lblNewLabel_2.setIcon(new ImageIcon(Janela_relatorio.class.getResource("/images/img2.png")));
				}
			}
		});
		tipo.setModel(new DefaultComboBoxModel(new String[] {"Lista - Produtos", "Lista - Movimentações de Produtos"}));
		tipo.setBounds(134, 84, 181, 27);
		contentPane.add(tipo);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de Relatório :");
		lblNewLabel_1.setBounds(10, 84, 125, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Crie um nome :");
		lblNewLabel_1_1.setBounds(10, 122, 125, 27);
		contentPane.add(lblNewLabel_1_1);
		
		nome = new JTextField();
		nome.setBounds(134, 122, 130, 27);
		contentPane.add(nome);
		nome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel(".PDF");
		lblNewLabel_2.setBounds(274, 122, 41, 27);
		contentPane.add(lblNewLabel_2);
		setLocationRelativeTo(null);
	}
}
