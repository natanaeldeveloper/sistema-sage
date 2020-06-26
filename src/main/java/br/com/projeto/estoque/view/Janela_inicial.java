package br.com.projeto.estoque.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Janela_inicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatDarkLaf());
					Janela_inicial frame = new Janela_inicial();
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
	public Janela_inicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 282);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Janela_login.class.getResource("/sage_icons/logoTransparente.png")));
		setTitle("SAGE - Bem Vindo");
		JButton btnNewButton = new JButton("Continuar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Janela_conexao jc = new Janela_conexao();
				dispose();
				jc.setVisible(true);
			}
		});
		btnNewButton.setBounds(236, 194, 140, 35);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Janela_inicial.class.getResource("/sage_icons/bem-vindo.png")));
		lblNewLabel.setBounds(0, 0, 609, 253);
		contentPane.add(lblNewLabel);
	}

}
