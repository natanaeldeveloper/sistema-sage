package br.com.projeto.estoque.view;


import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import br.com.projeto.estoque.controller.ControllerGerente;
import br.com.projeto.estoque.model.TipoComportamento;
import javax.swing.JPasswordField;

public class Janela_login_gerente extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField c_senha;
	private JFormattedTextField c_cpf;
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
	public Janela_login_gerente() {
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
		
		JLabel lblGerente = new JLabel("Logar Gerente");
		lblGerente.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerente.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblGerente.setBounds(64, 72, 146, 19);
		contentPane.add(lblGerente);
		
		JLabel lblUser = new JLabel("CPF:");
		lblUser.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblUser.setBounds(31, 107, 29, 14);
		contentPane.add(lblUser);
		
		JLabel lblNewLabel = new JLabel("Senha:");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel.setBounds(20, 132, 46, 14);
		contentPane.add(lblNewLabel);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/arte01.png"));
		JLabel lblNewLabel_1 = new JLabel(icon);
		lblNewLabel_1.setBounds(233, 41, 178, 185);
		contentPane.add(lblNewLabel_1);
		
		JButton b_entrar = new JButton("Entrar");
		b_entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				ControllerGerente controller = new ControllerGerente();
				String cpf = c_cpf.getText();
				@SuppressWarnings("deprecation")
				String senha = c_senha.getText();
				if(controller.fazerLogin(cpf, senha, TipoComportamento.ENTRADA)==true) {
					Janela_home menu = new Janela_home();
					
					menu.setVisible(true);
					dispose();	
				}		
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
		c_senha.setBounds(64, 129, 146, 17);
		contentPane.add(c_senha);
		
		c_cpf = new JFormattedTextField();
		c_cpf.setBounds(64, 102, 146, 20);
		MaskFormatter ms = null;
		try {
			ms = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultFormatterFactory df = new DefaultFormatterFactory(ms);
		c_cpf.setFormatterFactory(df);
		contentPane.add(c_cpf);
	}
}
