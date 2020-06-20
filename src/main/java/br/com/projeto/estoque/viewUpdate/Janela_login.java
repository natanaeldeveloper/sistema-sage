package br.com.projeto.estoque.viewUpdate;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import br.com.projeto.estoque.controller.ControllerGerente;
import br.com.projeto.estoque.controller.ControllerSupervisor;
import br.com.projeto.estoque.model.TipoComportamento;

public class Janela_login extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_senha_super;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatDarkLaf());
					Janela_login frame = new Janela_login();
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
	public Janela_login() {

		setTitle("SAGE - Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 697);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setOpaque(true);
		tabbedPane.setBounds(37, 290, 520, 349);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Entrar como gerente", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("LOGIN:");
		lblNewLabel_2.setBackground(SystemColor.control);
		lblNewLabel_2.setForeground(SystemColor.controlHighlight);
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(90, 63, 330, 14);
		panel.add(lblNewLabel_2);

		// cpf gerente
		final JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setForeground(Color.BLACK);
		formattedTextField.setBackground(SystemColor.control);
		formattedTextField.setBounds(90, 84, 330, 35);
		panel.add(formattedTextField);

		JLabel lblNewLabel_2_1 = new JLabel("SENHA:");
		lblNewLabel_2_1.setBackground(SystemColor.control);
		lblNewLabel_2_1.setForeground(SystemColor.controlHighlight);
		lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(90, 130, 330, 14);
		panel.add(lblNewLabel_2_1);

		// senha gerente
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.BLACK);
		passwordField.setBackground(SystemColor.control);
		passwordField.setBounds(90, 152, 330, 35);
		panel.add(passwordField);

		JButton btnVoltar = new JButton("Voltar");
		// btnVoltar.setBackground(SystemColor.menu);
		btnVoltar.setBounds(115, 216, 135, 35);
		panel.add(btnVoltar);

		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ControllerGerente controller = new ControllerGerente();
				@SuppressWarnings("deprecation")
				String senha = passwordField.getText();
				String cpf = formattedTextField.getText();
				if (controller.fazerLogin(cpf, senha, TipoComportamento.ENTRADA) == true) {
					Janela_principal jp = new Janela_principal();
					jp.setVisible(true);
					dispose();
				}

			}
		});
		// btnNewButton.setBackground(SystemColor.menu);
		btnNewButton.setBounds(273, 216, 135, 35);
		panel.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("Entrar como supervisor", null, panel_1, null);

		JLabel lblNewLabel_2_2 = new JLabel("CPF:");
		lblNewLabel_2_2.setForeground(SystemColor.controlHighlight);
		lblNewLabel_2_2.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(90, 63, 330, 14);
		panel_1.add(lblNewLabel_2_2);

		final JFormattedTextField formattedTextField_cpf_super = new JFormattedTextField();
		formattedTextField_cpf_super.setForeground(Color.BLACK);
		formattedTextField_cpf_super.setBackground(SystemColor.control);
		formattedTextField_cpf_super.setBounds(90, 84, 330, 35);
		panel_1.add(formattedTextField_cpf_super);

		JLabel lblNewLabel_2_1_1 = new JLabel("SENHA:");
		lblNewLabel_2_1_1.setForeground(SystemColor.controlHighlight);
		lblNewLabel_2_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(90, 130, 330, 14);
		panel_1.add(lblNewLabel_2_1_1);

		passwordField_senha_super = new JPasswordField();
		passwordField_senha_super.setForeground(Color.BLACK);
		passwordField_senha_super.setBackground(SystemColor.control);
		passwordField_senha_super.setBounds(90, 152, 330, 35);
		panel_1.add(passwordField_senha_super);

		JButton btnVoltar_1 = new JButton("Voltar");
		// btnVoltar_1.setBackground(SystemColor.menu);
		btnVoltar_1.setBounds(115, 216, 135, 35);
		panel_1.add(btnVoltar_1);

		JButton btnNewButton_1 = new JButton("Entrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				ControllerSupervisor ctrlSuper = new ControllerSupervisor();
				if (ctrlSuper.fazerLogin(formattedTextField_cpf_super.getText(), passwordField_senha_super.getText(),
						TipoComportamento.ENTRADA) == true) {
					Janela_principal jp = new Janela_principal();
					jp.setVisible(true);
					dispose();
				}

			}
		});
		// btnNewButton_1.setBackground(SystemColor.menu);
		btnNewButton_1.setBounds(273, 216, 135, 35);
		panel_1.add(btnNewButton_1);
		JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("/sage_icons/fundo-login.png")));
		lblNewLabel.setBounds(0, 0, 594, 672);
		contentPane.add(lblNewLabel);

		formattedTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ControllerGerente controller = new ControllerGerente();
					@SuppressWarnings("deprecation")
					String senha = passwordField.getText();
					String cpf = formattedTextField.getText();
					if (controller.fazerLogin(cpf, senha, TipoComportamento.ENTRADA) == true) {
						Janela_principal jp = new Janela_principal();
						jp.setVisible(true);
						dispose();
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

		});

		passwordField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ControllerGerente controller = new ControllerGerente();
					@SuppressWarnings("deprecation")
					String senha = passwordField.getText();
					String cpf = formattedTextField.getText();
					if (controller.fazerLogin(cpf, senha, TipoComportamento.ENTRADA) == true) {
						Janela_principal jp = new Janela_principal();
						jp.setVisible(true);
						dispose();
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

		});

		passwordField_senha_super.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					ControllerSupervisor ctrlSuper = new ControllerSupervisor();
					if (ctrlSuper.fazerLogin(formattedTextField_cpf_super.getText(),
							passwordField_senha_super.getText(), TipoComportamento.ENTRADA) == true) {
						Janela_principal jp = new Janela_principal();
						jp.setVisible(true);
						dispose();
					}

				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		formattedTextField_cpf_super.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					ControllerSupervisor ctrlSuper = new ControllerSupervisor();
					if (ctrlSuper.fazerLogin(formattedTextField_cpf_super.getText(),
							passwordField_senha_super.getText(), TipoComportamento.ENTRADA) == true) {
						Janela_principal jp = new Janela_principal();
						jp.setVisible(true);
						dispose();
					}

				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

	}
}