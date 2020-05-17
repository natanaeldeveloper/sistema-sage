package br.com.projeto.estoque.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import br.com.projeto.estoque.controller.ControllerAuxiliar;
import br.com.projeto.estoque.controller.ControllerSupervisor;

public class Janela_delete_supervisor extends JInternalFrame {
	JFormattedTextField c_cpf;
	public JFormattedTextField formattedTextField_1 = new JFormattedTextField();
	ControllerSupervisor ctrlSuper = new ControllerSupervisor();
	ControllerAuxiliar ctrlAux = new ControllerAuxiliar();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_delete_supervisor frame = new Janela_delete_supervisor();
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
	public Janela_delete_supervisor() {
		setResizable(true);
		setIconifiable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Deletar dados do supervisor\r\n");
		lblNewLabel.setBounds(10, 81, 753, 45);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Digite Sua Senha:");
		lblNewLabel_2.setBounds(119, 137, 253, 25);
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel_2);
		
		final JPasswordField c_senha = new JPasswordField();
		c_senha.setBounds(119, 163, 253, 25);
		c_senha.setBackground(SystemColor.controlHighlight);
		getContentPane().add(c_senha);
		c_senha.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel(new ImageIcon(getClass().getResource("/icons/user01-60.png")));
		lblNewLabel_4.setBounds(11, 0, 752, 95);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Digite Seu CPF:");
		lblNewLabel_5.setBounds(405, 137, 253, 27);
		lblNewLabel_5.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel_5);
		
		c_cpf = new JFormattedTextField();
	
		c_cpf.setColumns(10);
		c_cpf.setBackground(SystemColor.controlHighlight);
		getContentPane().add(c_cpf);
		
		c_cpf.setBounds(405, 163, 253, 25);
		MaskFormatter ms = null;
		try {
			ms = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultFormatterFactory df = new DefaultFormatterFactory(ms);
		c_cpf.setFormatterFactory(df);
		getContentPane().add(c_cpf);
		
		JButton b_limpar = new JButton("Limpar");
		b_limpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlAux.limparCampos(c_cpf, formattedTextField_1, c_senha, null, formattedTextField_1);
			}
		});
		b_limpar.setFont(new Font("Arial", Font.PLAIN, 14));
		b_limpar.setBounds(241, 353, 131, 31);
		b_limpar.setBackground(SystemColor.control);
		getContentPane().add(b_limpar);
		
		JButton b_cadastrar = new JButton("Deletar Conta");
		b_cadastrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				ctrlSuper.excluirContaSupervisor(formattedTextField_1, c_cpf.getText(), c_senha.getText(), c_cpf, c_senha, formattedTextField_1);
			}
		});
		b_cadastrar.setFont(new Font("Arial", Font.PLAIN, 14));
		b_cadastrar.setBackground(SystemColor.control);
		b_cadastrar.setBounds(405, 353, 131, 31);
		getContentPane().add(b_cadastrar);
		
		JLabel lblNewLabel_3 = new JLabel(new ImageIcon(getClass().getResource("/icons/aviso-30.png")));
		lblNewLabel_3.setBounds(10, 0, 30, 31);
		getContentPane().add(lblNewLabel_3);
		
		JTextPane txtpnEssaAoExcluira = new JTextPane();
		txtpnEssaAoExcluira.setEditable(false);
		txtpnEssaAoExcluira.setText("Essa a\u00E7\u00E3o excluir\u00E1 permanentimente sua conta, ap\u00F3s sua inicializa\u00E7\u00E3o n\u00E3o ser\u00E1 poss\u00EDvel recupera seus dados.");
		txtpnEssaAoExcluira.setBounds(10, 42, 213, 79);
		getContentPane().add(txtpnEssaAoExcluira);
		
		JLabel lblNewLabel_2_1 = new JLabel("ID do Supervisor");
		lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(119, 234, 253, 25);
		getContentPane().add(lblNewLabel_2_1);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setEditable(false);
		formattedTextField.setColumns(10);
		formattedTextField.setBackground(SystemColor.controlHighlight);
		formattedTextField.setBounds(405, 261, 253, 25);
		getContentPane().add(formattedTextField);
		
		JLabel lblCpfDoSupervisor = new JLabel("CPF do Supervisor");
		lblCpfDoSupervisor.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblCpfDoSupervisor.setBounds(405, 234, 253, 25);
		getContentPane().add(lblCpfDoSupervisor);
		
		formattedTextField_1.setColumns(10);
		formattedTextField_1.setBackground(SystemColor.controlHighlight);
		formattedTextField_1.setBounds(119, 263, 253, 25);
		getContentPane().add(formattedTextField_1);
		setBounds(-5,0, 788, 466);
	}
}
