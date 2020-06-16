package br.com.projeto.estoque.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class Janela_inicial extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_inicial frame = new Janela_inicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Janela_inicial() {
		ImageIcon ico =  new ImageIcon(getClass().getResource("/icons/pacote02-30.png")) ;
		setIconImage(ico.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 336, 277);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/pacote02-60.png"));
		JLabel labelIcon = new JLabel(icon);
		labelIcon.setBackground(Color.WHITE);
		labelIcon.setBounds(10, 11, 300, 91);
		contentPane.add(labelIcon);
		
		JLabel lblSistemaDeEstoque = new JLabel("Sistema De Estoque");
		lblSistemaDeEstoque.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblSistemaDeEstoque.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaDeEstoque.setBounds(10, 113, 300, 32);
		contentPane.add(lblSistemaDeEstoque);
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Janela_conexao jc = new Janela_conexao();
				jc.setVisible(true);
				dispose();
			}
		});
		btnEntrar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEntrar.setBackground(SystemColor.control);
		btnEntrar.setBounds(100, 167, 114, 32);
		contentPane.add(btnEntrar);
	}
}
