package br.com.projeto.estoque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import br.com.projeto.estoque.controller.ControllerFornecedor;
import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.util.GerenteAtual;
import br.com.projeto.estoque.util.JPAUtil;

public class Janela_update_fornecedor extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private static EntityManager manager;
	private JTextField tfId;
	private JFormattedTextField tfCnpjNovo;
	private JTextField tfNomeNovo;
	private JPasswordField tfSenha;
	private JPasswordField tfConfirmar;

	public Janela_update_fornecedor() {
		setResizable(true);
		setIconifiable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		JLabel label = new JLabel(new ImageIcon(Janela_update_fornecedor.class.getResource("/icons/user01-60.png")));
		label.setBounds(10, 0, 752, 95);
		getContentPane().add(label);

		JLabel lblAlterarDadosDo = new JLabel("Alterar dados do fornecedor");
		lblAlterarDadosDo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlterarDadosDo.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		lblAlterarDadosDo.setBounds(10, 96, 753, 45);
		getContentPane().add(lblAlterarDadosDo);

		JLabel lblIdDoFornecedor = new JLabel("ID do Fornecedor:");
		lblIdDoFornecedor.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblIdDoFornecedor.setBounds(51, 152, 183, 25);
		getContentPane().add(lblIdDoFornecedor);

		JLabel lblCNPJNovo = new JLabel("Novo CNPJ do Fornecedor:");
		lblCNPJNovo.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblCNPJNovo.setBounds(51, 212, 183, 25);
		getContentPane().add(lblCNPJNovo);

		JLabel lblNovoNomeDo = new JLabel("Novo nome do Fornecedor:");
		lblNovoNomeDo.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNovoNomeDo.setBounds(51, 272, 183, 25);
		getContentPane().add(lblNovoNomeDo);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblSenha.setBounds(525, 212, 183, 25);
		getContentPane().add(lblSenha);

		JLabel lblConfirmarSenha = new JLabel("Confirmar senha:");
		lblConfirmarSenha.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblConfirmarSenha.setBounds(525, 272, 183, 25);
		getContentPane().add(lblConfirmarSenha);

		tfId = new JTextField();
		tfId.setColumns(10);
		tfId.setBackground(SystemColor.controlHighlight);
		tfId.setBounds(51, 176, 91, 25);
		getContentPane().add(tfId);

		tfCnpjNovo = new JFormattedTextField();
		tfCnpjNovo.setEnabled(false);
		tfCnpjNovo.setColumns(10);
		tfCnpjNovo.setBackground(SystemColor.controlHighlight);
		tfCnpjNovo.setBounds(51, 236, 183, 25);
		getContentPane().add(tfCnpjNovo);

		MaskFormatter ms = null;
		try {
			ms = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultFormatterFactory df = new DefaultFormatterFactory(ms);
		tfCnpjNovo.setFormatterFactory(df);
		getContentPane().add(tfCnpjNovo);

		tfNomeNovo = new JTextField();
		tfNomeNovo.setEnabled(false);
		tfNomeNovo.setColumns(10);
		tfNomeNovo.setBackground(SystemColor.controlHighlight);
		tfNomeNovo.setBounds(51, 296, 183, 25);
		getContentPane().add(tfNomeNovo);

		tfSenha = new JPasswordField();
		tfSenha.setEnabled(false);
		tfSenha.setColumns(10);
		tfSenha.setBackground(SystemColor.controlHighlight);
		tfSenha.setBounds(525, 236, 183, 25);
		getContentPane().add(tfSenha);

		tfConfirmar = new JPasswordField();
		tfConfirmar.setEnabled(false);
		tfConfirmar.setColumns(10);
		tfConfirmar.setBackground(SystemColor.controlHighlight);
		tfConfirmar.setBounds(525, 296, 183, 25);
		getContentPane().add(tfConfirmar);

		JButton btnCarregar = new JButton("Carregar");
		btnCarregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCarregar.setBackground(SystemColor.menu);
		btnCarregar.setBounds(145, 176, 89, 25);
		getContentPane().add(btnCarregar);

		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager = new JPAUtil().getEntityManager();

				if (tfId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Você precisa preencher o campo!", "Campo vazio",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Integer idInserido = null;
				try {
					idInserido = Integer.parseInt(tfId.getText());
				} catch (Exception e0) {
					JOptionPane.showMessageDialog(null, "Valor inserido inválido!", "ID inválido",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Query query = manager.createQuery("select id From Fornecedor f where f.id=:idInserido");
				query.setParameter("idInserido", idInserido);
				Object idBruto = null;
				try {
					idBruto = query.getSingleResult();
				} catch (Exception e0) {
					JOptionPane.showMessageDialog(null, "Fornecedor inexistente!", "Fornecedor inexistente",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Integer id = Integer.parseInt(idBruto.toString());
				Fornecedor fornecedorCarregado = manager.find(Fornecedor.class, id);
				if (fornecedorCarregado == null) {
					JOptionPane.showMessageDialog(null, "Fornecedor inexistente!", "Fornecedor inexistente",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					tfCnpjNovo.setText(fornecedorCarregado.getCnpj());
					tfNomeNovo.setText(fornecedorCarregado.getNome());
				}
			}
		});

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setEnabled(false);
		btnAtualizar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnAtualizar.setBackground(SystemColor.menu);
		btnAtualizar.setBounds(394, 398, 131, 31);
		getContentPane().add(btnAtualizar);

		btnAtualizar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Integer idInserido = null;
				try {
					idInserido = Integer.parseInt(tfId.getText());
				} catch (Exception e0) {
					JOptionPane.showMessageDialog(null, "Valor inserido inválido!", "ID inválido",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String cnpj = tfCnpjNovo.getText();
				String nome = tfNomeNovo.getText();
				String senha = tfSenha.getText();
				String confirmar = tfConfirmar.getText();

				if (senha.equals(confirmar) && confirmar.equals(GerenteAtual.getGerente().getSenha())) {
					ControllerFornecedor cf = new ControllerFornecedor();
					cf.atualizarFornecedor(idInserido, cnpj, nome);
					JOptionPane.showMessageDialog(null, "Fornecedor atualizado com sucesso!", "Fornecedor atualizado",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Senha incorreta!", "Senha incorreta",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setEnabled(false);
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnLimpar.setBackground(SystemColor.menu);
		btnLimpar.setBounds(230, 398, 131, 31);
		getContentPane().add(btnLimpar);
		setBounds(-5, 0, 788, 470);
	}
}
