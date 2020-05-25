package br.com.projeto.estoque.teste;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;

public class TesteInterface {

	private JFrame frame;
	private JDateChooser dateChooser;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesteInterface window = new TesteInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TesteInterface() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(168, 87, 87, 20);
		frame.getContentPane().add(dateChooser);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println(df.format(dateChooser.getDate()));
			}
		});
		btnEnviar.setBounds(168, 208, 89, 23);
		frame.getContentPane().add(btnEnviar);
	}
}
