package br.com.projeto.estoque.view;

import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import br.com.projeto.estoque.controller.ControllerValidationFornecedor;

public class Janela_persist_fornecedor extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	private JFormattedTextField tfCnpj;
	private JTextField tfNome;

	public Janela_persist_fornecedor() {
		setBounds(-5, 0, 788, 466);
		setResizable(true);
		setIconifiable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel_4 = new JLabel(new ImageIcon(getClass().getResource("/icons/user01-60.png")));
		lblNewLabel_4.setBounds(10, 24, 752, 95);
		getContentPane().add(lblNewLabel_4);

		JLabel lblTitulo = new JLabel("Adicionar novo fornecedor");
		lblTitulo.setBounds(10, 120, 753, 45);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		getContentPane().add(lblTitulo);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(119, 176, 253, 25);
		lblSenha.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblSenha);

		JLabel lblConfirmar = new JLabel("Confirmar Senha:");
		lblConfirmar.setBounds(119, 249, 253, 27);
		lblConfirmar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblConfirmar);

		JLabel lblCnpj = new JLabel("CNPJ do fornecedor:");
		lblCnpj.setBounds(405, 176, 253, 27);
		lblCnpj.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblCnpj);

		JLabel lblNomeDoFornecedor = new JLabel("Nome do fornecedor:");
		lblNomeDoFornecedor.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNomeDoFornecedor.setBounds(405, 251, 253, 25);
		getContentPane().add(lblNomeDoFornecedor);

		final JPasswordField tfSenha = new JPasswordField();
		tfSenha.setBounds(119, 200, 253, 25);
		tfSenha.setBackground(SystemColor.controlHighlight);
		getContentPane().add(tfSenha);
		tfSenha.setColumns(10);

		final JPasswordField tfConfirmar = new JPasswordField();
		tfConfirmar.setBounds(119, 274, 253, 25);
		tfConfirmar.setBackground(SystemColor.controlHighlight);
		getContentPane().add(tfConfirmar);
		tfConfirmar.setColumns(10);

		tfCnpj = new JFormattedTextField();

		tfCnpj.setColumns(10);
		tfCnpj.setBackground(SystemColor.controlHighlight);
		getContentPane().add(tfCnpj);
		tfCnpj.setBounds(405, 200, 253, 25);
		
		MaskFormatter ms = null;
		try {
			ms = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultFormatterFactory df = new DefaultFormatterFactory(ms);
		tfCnpj.setFormatterFactory(df);
		getContentPane().add(tfCnpj);

		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBackground(SystemColor.controlHighlight);
		tfNome.setBounds(405, 276, 253, 25);
		getContentPane().add(tfNome);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnLimpar.setBounds(241, 353, 131, 31);
		btnLimpar.setBackground(SystemColor.control);
		getContentPane().add(btnLimpar);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCadastrar.setBackground(SystemColor.control);
		btnCadastrar.setBounds(405, 353, 131, 31);
		getContentPane().add(btnCadastrar);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerValidationFornecedor.cadastrarFornecedor(tfCnpj, tfNome, tfSenha, tfConfirmar);
			}
		});
	}
}
