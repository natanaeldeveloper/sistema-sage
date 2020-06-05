package br.com.projeto.estoque.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Janela_de_erros extends JFrame {
	static public String msgErro = "aqui fica qual foi o erro";
	static public String textErro = "texto de ajuda para solucionar o erro";
	static public String iconErro = "/icons/erro-db-60.png";
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_de_erros frame = new Janela_de_erros();
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
	public Janela_de_erros() {
		ImageIcon ico =  new ImageIcon(getClass().getResource("/icons/pacote02-30.png"));
		setIconImage(ico.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 294);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource(iconErro)));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 350, 72);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(msgErro);
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 91, 360, 33);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(SystemColor.control);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(122, 210, 137, 29);
		contentPane.add(btnNewButton);
		
		JTextPane txtpnParaCorrigirEsse = new JTextPane();
		txtpnParaCorrigirEsse.setEditable(false);
		txtpnParaCorrigirEsse.setText(textErro);
		txtpnParaCorrigirEsse.setBackground(SystemColor.control);
		txtpnParaCorrigirEsse.setBounds(20, 127, 327, 72);
		contentPane.add(txtpnParaCorrigirEsse);
		setLocationRelativeTo(null);
	}
}
