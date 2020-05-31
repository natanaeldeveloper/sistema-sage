package br.com.projeto.estoque.view;


import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.projeto.estoque.controller.ControllerGerente;

public class Janela_login_admin extends JFrame {
	private JPanel contentPane;
	private JPasswordField c_senha;
	private JTextField c_user;
	/**
	 * Launch the application.
	 */
	public static void login() {
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

	/**
	 * Create the frame.
	 */
	public Janela_login_admin() {
		ImageIcon ico =  new ImageIcon(getClass().getResource("/icons/pacote02-30.png"));
		setIconImage(ico.getImage());
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblSistema = new JLabel("Sistema De Estoque");
		lblSistema.setBounds(10, 11, 424, 35);
		lblSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistema.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		contentPane.add(lblSistema);
		
		JLabel lblGerente = new JLabel("Logar Administrador");
		lblGerente.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerente.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblGerente.setBounds(22, 72, 201, 19);
		contentPane.add(lblGerente);
		
		JLabel lblUser = new JLabel("Usu\u00E1rio:");
		lblUser.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblUser.setBounds(23, 107, 50, 14);
		contentPane.add(lblUser);
		
		JLabel lblNewLabel = new JLabel("Senha:");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel.setBounds(33, 132, 46, 14);
		contentPane.add(lblNewLabel);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/arte01.png"));
		JLabel lblNewLabel_1 = new JLabel(icon);
		lblNewLabel_1.setBounds(233, 41, 178, 185);
		contentPane.add(lblNewLabel_1);
		
		JButton b_entrar = new JButton("Entrar");
		b_entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerGerente dao = new ControllerGerente();
				String user = c_user.getText();
				String senha = c_senha.getText();
			}
		});
		b_entrar.setBackground(SystemColor.control);
		b_entrar.setFont(new Font("Arial", Font.PLAIN, 12));
		b_entrar.setBounds(84, 163, 99, 29);
		contentPane.add(b_entrar);
		
		JLabel lblStatusDeConexo = new JLabel("  Status de conex\u00E3o :");
		lblStatusDeConexo.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		lblStatusDeConexo.setBounds(10, 236, 114, 14);
		contentPane.add(lblStatusDeConexo);
		
		JLabel lblNewLabel_2 = new JLabel("Conectado");
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(120, 237, 67, 14);
		contentPane.add(lblNewLabel_2);
		
		c_senha = new JPasswordField();
		c_senha.setBounds(77, 129, 146, 17);
		contentPane.add(c_senha);
		
		c_user = new JTextField();
		c_user.setBounds(77, 102, 146, 17);
		contentPane.add(c_user);
	}
}
