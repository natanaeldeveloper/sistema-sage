package br.com.projeto.estoque.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.projeto.estoque.viewUpdate.Janela_login;

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

	public String mensagem;
	public String icon = "/icons/add-pacote-30.png";
	private JPanel contentPane;
	
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
	public Janela_de_erros() {
		//ImageIcon ico =  new ImageIcon(getClass().getResource("/icons/pacote02-30.png"));
		//setIconImage(ico.getImage());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 226);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_icon = new JLabel(new ImageIcon(getClass().getResource(icon)));
		label_icon.setHorizontalAlignment(SwingConstants.CENTER);
		label_icon.setBounds(10, 11, 350, 62);
		contentPane.add(label_icon);
		
		JLabel label_msg = new JLabel(mensagem);
		label_msg.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		label_msg.setHorizontalAlignment(SwingConstants.CENTER);
		label_msg.setBounds(10, 84, 360, 40);
		contentPane.add(label_msg);
		
		JButton btn_voltar = new JButton("Voltar");
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_voltar.setBackground(SystemColor.control);
		btn_voltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_voltar.setBounds(111, 147, 137, 29);
		contentPane.add(btn_voltar);
	
		setLocationRelativeTo(null);
	}
}
