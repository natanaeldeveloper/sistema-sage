package br.com.projeto.estoque.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.projeto.estoque.util.JPAUtil;

public class Janela_conexao extends JFrame {
	private JProgressBar progressBar = new JProgressBar();
	private JPanel contentPane;
	private static EntityManager manager;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_conexao frame = new Janela_conexao();
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
	public Janela_conexao() {
		ImageIcon ico = new ImageIcon(getClass().getResource("/icons/pacote02-30.png"));
		setIconImage(ico.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 373, 240);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/db-60.png"));
		JLabel lblNewLabel = new JLabel(icon);
		lblNewLabel.setBounds(10, 11, 337, 77);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Verificando conex\u00E3o com o banco de dados");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 90, 337, 30);
		contentPane.add(lblNewLabel_1);

		progressBar.setBounds(69, 142, 221, 22);
		contentPane.add(progressBar);
		setLocationRelativeTo(null);
		progressBar.setStringPainted(true);
		progressBar.setMaximum(1000);
		progressBar.setForeground(new Color(60, 193, 62));
		new temporizador().start();
	}

	public class temporizador extends Thread {
		public void run() {
			try {
				sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			while (progressBar.getValue() < 1000) {
				try {
					sleep(15);
					progressBar.setValue(progressBar.getValue() + 10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			try {
				manager = new JPAUtil().getEntityManager();
				Janela_conexao_ok jc = new Janela_conexao_ok();
				jc.setVisible(true);
				dispose();
			} catch (ExceptionInInitializerError pE) {
				try {
					Thread.sleep(1000);
					JOptionPane.showMessageDialog(null, "Conexão com banco de dados falhou");
					Janela_inicial ji = new Janela_inicial();
					ji.setVisible(true);
					dispose();
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
