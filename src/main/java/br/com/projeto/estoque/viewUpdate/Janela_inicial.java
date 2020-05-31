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

	private JProgressBar progressBar;
	private JPanel contentPane;
	public String loanding = "";
	private JLabel lblNewLabel_1;
	JButton btnNewButton;
	public Boolean result = true;
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
		setTitle("Conexão com banco de dados");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		btnNewButton = new JButton("Continuar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Janela_login jl = new Janela_login();
				jl.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setEnabled(false);
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setBounds(247, 381, 104, 33);
		contentPane.add(btnNewButton);
		lblNewLabel_1 = new JLabel("Carregando");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setBounds(171, 306, 256, 14);
		contentPane.add(lblNewLabel_1);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(false);
		//progressBar.setMaximum(1000);
		progressBar.setBounds(171, 331, 256, 14);
		contentPane.add(progressBar);
		progressBar.setIndeterminate(true);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/sage_icons/fundo-carregamento.png")));
		lblNewLabel.setBounds(0, 0, 598, 452);
		contentPane.add(lblNewLabel);
		new loanding().start();
		new temporizador().start();

	}
	public class temporizador extends Thread{
		EntityManager manager;
		public void run() {
			try {
				//Essencial.useDb();
				manager = new JPAUtil().getEntityManager();	
			}catch(ExceptionInInitializerError e) {
				result = false;
				lblNewLabel_1.setForeground(Color.red);
				lblNewLabel_1.setText("Erro na conex�o!");
				progressBar.setIndeterminate(false);
			}
			if(manager == null) {
				result = false;
				lblNewLabel_1.setForeground(Color.red);
				lblNewLabel_1.setText("Erro na conex�o!");
				progressBar.setIndeterminate(false);
			}else {
				progressBar.setIndeterminate(false);
				progressBar.setStringPainted(true);
				while(progressBar.getValue() < 100) {
					progressBar.setValue(progressBar.getValue()+1);
					try {
						sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	public class loanding extends Thread{
		public void run() {
			while(progressBar.getValue() < 90 && result == true) {
				try {
					lblNewLabel_1.setText("Conectando.");
					sleep(500);
					lblNewLabel_1.setText("Conectando..");
					sleep(500);
					lblNewLabel_1.setText("Conectando...");
					sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(result == true) {
				lblNewLabel_1.setText("Conectado!");
				btnNewButton.setEnabled(true);
			}else {}
			
		}
	}
}
