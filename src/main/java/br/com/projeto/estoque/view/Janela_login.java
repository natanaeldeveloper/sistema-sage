package br.com.projeto.estoque.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
import java.awt.Toolkit;

@SuppressWarnings("deprecation")
public class Janela_login extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPasswordField pfSenhaGerente;
	private JPasswordField pfSenhaSupervisor;

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

	public Janela_login() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Janela_login.class.getResource("/sage_icons/logoTransparente.png")));
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

		JPanel painelGerente = new JPanel();
		tabbedPane.addTab("Entrar como Gerente", null, painelGerente, null);
		painelGerente.setLayout(null);

		JLabel lblLoginGerente = new JLabel("LOGIN ou CPF:");
		lblLoginGerente.setBackground(SystemColor.control);
		lblLoginGerente.setForeground(SystemColor.controlHighlight);
		lblLoginGerente.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblLoginGerente.setBounds(90, 63, 330, 14);
		painelGerente.add(lblLoginGerente);

		// cpf gerente
		final JFormattedTextField tfLoginGerente = new JFormattedTextField();
		tfLoginGerente.setForeground(Color.BLACK);
		tfLoginGerente.setBackground(SystemColor.control);
		tfLoginGerente.setBounds(90, 84, 330, 35);
		painelGerente.add(tfLoginGerente);

		// Isso serve pro input do CPF do gerente já ser focado assim que a janela abrir
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				tfLoginGerente.requestFocusInWindow();
			}
		});

		JLabel lblSenhaGerente = new JLabel("SENHA:");
		lblSenhaGerente.setBackground(SystemColor.control);
		lblSenhaGerente.setForeground(SystemColor.controlHighlight);
		lblSenhaGerente.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblSenhaGerente.setBounds(90, 130, 330, 14);
		painelGerente.add(lblSenhaGerente);

		// senha gerente
		pfSenhaGerente = new JPasswordField();
		pfSenhaGerente.setForeground(Color.BLACK);
		pfSenhaGerente.setBackground(SystemColor.control);
		pfSenhaGerente.setBounds(90, 152, 330, 35);
		painelGerente.add(pfSenhaGerente);

		JButton btnVoltarGerente = new JButton("Voltar");
		// btnVoltar.setBackground(SystemColor.menu);
		btnVoltarGerente.setBounds(115, 216, 135, 35);
		painelGerente.add(btnVoltarGerente);

		JButton btnEntrarGerente = new JButton("Entrar");
		btnEntrarGerente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ControllerGerente controller = new ControllerGerente();
				String senha = pfSenhaGerente.getText();
				String cpf = tfLoginGerente.getText();
				if (controller.fazerLogin(cpf, senha, TipoComportamento.ENTRADA) == true) {
					Janela_principal jp = new Janela_principal();
					jp.setVisible(true);
					dispose();
				}

			}
		});
		// btnNewButton.setBackground(SystemColor.menu);
		btnEntrarGerente.setBounds(273, 216, 135, 35);
		painelGerente.add(btnEntrarGerente);

		JPanel painelSupervisor = new JPanel();
		painelSupervisor.setLayout(null);
		tabbedPane.addTab("Entrar como Supervisor", null, painelSupervisor, null);

		JLabel lblCpfSupervisor = new JLabel("CPF ou LOGIN:");
		lblCpfSupervisor.setForeground(SystemColor.controlHighlight);
		lblCpfSupervisor.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblCpfSupervisor.setBounds(90, 63, 330, 14);
		painelSupervisor.add(lblCpfSupervisor);

		final JFormattedTextField tfCpfSupervisor = new JFormattedTextField();
		tfCpfSupervisor.setForeground(Color.BLACK);
		tfCpfSupervisor.setBackground(SystemColor.control);
		tfCpfSupervisor.setBounds(90, 84, 330, 35);
		painelSupervisor.add(tfCpfSupervisor);

		JLabel lblSenhaSupervisor = new JLabel("SENHA:");
		lblSenhaSupervisor.setForeground(SystemColor.controlHighlight);
		lblSenhaSupervisor.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblSenhaSupervisor.setBounds(90, 130, 330, 14);
		painelSupervisor.add(lblSenhaSupervisor);

		pfSenhaSupervisor = new JPasswordField();
		pfSenhaSupervisor.setForeground(Color.BLACK);
		pfSenhaSupervisor.setBackground(SystemColor.control);
		pfSenhaSupervisor.setBounds(90, 152, 330, 35);
		painelSupervisor.add(pfSenhaSupervisor);

		JButton btnVoltarSupervisor = new JButton("Voltar");
		// btnVoltar_1.setBackground(SystemColor.menu);
		btnVoltarSupervisor.setBounds(115, 216, 135, 35);
		painelSupervisor.add(btnVoltarSupervisor);

		JButton btnEntrarSupervisor = new JButton("Entrar");
		btnEntrarSupervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerSupervisor ctrlSuper = new ControllerSupervisor();
				if (ctrlSuper.fazerLogin(tfCpfSupervisor.getText(), pfSenhaSupervisor.getText(),
						TipoComportamento.ENTRADA) == true) {
					Janela_principal jp = new Janela_principal();
					jp.setVisible(true);
					dispose();
				}

			}
		});
		// btnNewButton_1.setBackground(SystemColor.menu);
		btnEntrarSupervisor.setBounds(273, 216, 135, 35);
		painelSupervisor.add(btnEntrarSupervisor);
		JLabel imagemFundo = new JLabel(new ImageIcon(getClass().getResource("/sage_icons/fundo-login.png")));
		imagemFundo.setBounds(0, 0, 594, 672);
		contentPane.add(imagemFundo);

		tfLoginGerente.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ControllerGerente controller = new ControllerGerente();
					String senha = pfSenhaGerente.getText();
					String cpf = tfLoginGerente.getText();
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

		pfSenhaGerente.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ControllerGerente controller = new ControllerGerente();
					String senha = pfSenhaGerente.getText();
					String cpf = tfLoginGerente.getText();
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

		pfSenhaSupervisor.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					ControllerSupervisor ctrlSuper = new ControllerSupervisor();
					if (ctrlSuper.fazerLogin(tfCpfSupervisor.getText(), pfSenhaSupervisor.getText(),
							TipoComportamento.ENTRADA) == true) {
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

		tfCpfSupervisor.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					ControllerSupervisor ctrlSuper = new ControllerSupervisor();
					if (ctrlSuper.fazerLogin(tfCpfSupervisor.getText(), pfSenhaSupervisor.getText(),
							TipoComportamento.ENTRADA) == true) {
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