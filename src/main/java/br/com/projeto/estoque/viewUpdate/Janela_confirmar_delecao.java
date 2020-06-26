package br.com.projeto.estoque.viewUpdate;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.projeto.estoque.controller.ControllerPermissao;
import br.com.projeto.estoque.controller.ControllerSupervisor;
import br.com.projeto.estoque.util.SupervisorDeletado;

public class Janela_confirmar_delecao extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ControllerSupervisor ctrlSuper = new ControllerSupervisor();

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_confirmar_delecao frame = new Janela_confirmar_delecao();
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
	public Janela_confirmar_delecao() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Janela_login.class.getResource("/sage_icons/logoTransparente.png")));
		setTitle("Confirmar deleção");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 206);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 373, 167);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				Janela_confirmar_delecao.class.getResource("/javax/swing/plaf/metal/icons/ocean/warning.png")));
		lblNewLabel.setBounds(162, 11, 47, 44);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Essa ação excluirá todos os dados desse usuário");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(49, 52, 314, 29);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("permanentemente, deseja continuar?");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(77, 77, 259, 14);
		panel.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(77, 118, 89, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Deletar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrlSuper.excluirConta(SupervisorDeletado.getId());
				ControllerPermissao ctrlPermissao = new ControllerPermissao();
				if (ctrlPermissao.chamarVerificacao() == true) {
					dispose();
				} else {
					Janela_principal jprincipal = new Janela_principal();
					Janela_login jlogin = new Janela_login();
					dispose();
					jlogin.setVisible(true);
					jprincipal.dispose();
				}
			}
		});
		btnNewButton_1.setBounds(208, 118, 89, 23);
		panel.add(btnNewButton_1);
	}
}
