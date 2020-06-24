package br.com.projeto.estoque.viewUpdate;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import br.com.projeto.estoque.util.JPAUtil;

public class Janela_inicial extends JFrame {

	private static final long serialVersionUID = 1L;

	private JProgressBar barraDeCarregamento;
	private JPanel contentPane;
	public String loanding = "";
	private JLabel lblCarregando;
	JButton btnContinuar;
	public Boolean result = true;

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

	public Janela_inicial() {
		setTitle("SAGE - Inicialização");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Janela_login jl = new Janela_login();
				jl.setVisible(true);
				dispose();
			}
		});
		btnContinuar.setEnabled(false);
		btnContinuar.setBackground(SystemColor.controlHighlight);
		btnContinuar.setBounds(247, 381, 104, 33);
		contentPane.add(btnContinuar);
		lblCarregando = new JLabel("Carregando");
		lblCarregando.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblCarregando.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarregando.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCarregando.setForeground(Color.LIGHT_GRAY);
		lblCarregando.setBounds(171, 306, 256, 14);
		contentPane.add(lblCarregando);

		barraDeCarregamento = new JProgressBar();
		barraDeCarregamento.setStringPainted(false);
		// progressBar.setMaximum(1000);
		barraDeCarregamento.setBounds(171, 331, 256, 14);
		contentPane.add(barraDeCarregamento);
		barraDeCarregamento.setIndeterminate(true);

		JLabel imgFundo = new JLabel("");
		imgFundo.setIcon(new ImageIcon(getClass().getResource("/sage_icons/fundo-carregamento.png")));
		imgFundo.setBounds(0, 0, 598, 452);
		contentPane.add(imgFundo);
		new loanding().start();
		new temporizador().start();

	}

	public class temporizador extends Thread {
		EntityManager manager;

		public void run() {
			try {
				// Essencial.useDb();
				manager = new JPAUtil().getEntityManager();
			} catch (ExceptionInInitializerError e) {
				result = false;
				lblCarregando.setForeground(Color.red);
				lblCarregando.setText("Erro na conex�o!");
				barraDeCarregamento.setIndeterminate(false);
			}
			if (manager == null) {
				result = false;
				lblCarregando.setForeground(Color.red);
				lblCarregando.setText("Erro na conex�o!");
				barraDeCarregamento.setIndeterminate(false);
			} else {
				barraDeCarregamento.setIndeterminate(false);
				barraDeCarregamento.setStringPainted(true);
				while (barraDeCarregamento.getValue() < 100) {
					barraDeCarregamento.setValue(barraDeCarregamento.getValue() + 1);
					try {
						sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	public class loanding extends Thread {
		public void run() {
			while (barraDeCarregamento.getValue() < 90 && result == true) {
				try {
					lblCarregando.setText("Conectando.");
					sleep(500);
					lblCarregando.setText("Conectando..");
					sleep(500);
					lblCarregando.setText("Conectando...");
					sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (result == true) {
				lblCarregando.setText("Conectado!");
				btnContinuar.setEnabled(true);
			} else {
			}

		}
	}
}
