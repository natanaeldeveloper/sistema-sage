package br.com.projeto.estoque.viewUpdate;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Label;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Frame;
import javax.swing.JToolBar;

public class Janela_relatorio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatDarkLaf());
					Janela_relatorio frame = new Janela_relatorio();
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
	public Janela_relatorio() {
		setResizable(false);
		setFocusableWindowState(false);
		setFocusable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 349, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setName("Menu");
		toolBar.setRollover(true);
		toolBar.setBounds(10, 11, 329, 49);
		contentPane.add(toolBar);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Janela_relatorio.class.getResource("/sage_icons/baseline_get_app_black_24dp.png")));
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(Janela_relatorio.class.getResource("/sage_icons/baseline_get_app_white_24dp.png")));
		toolBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		toolBar.add(btnNewButton_3);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(239, 442, 100, 27);
		contentPane.add(btnNewButton_1);
		setLocationRelativeTo(null);
	}
}
