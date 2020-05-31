package br.com.projeto.estoque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.projeto.estoque.controller.ControllerValidationGrupo;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Janela_persist_grupo extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	private JTextField tfNome;
	public static EntityManager manager;
	private JTextField tfPeso;
	private JComboBox<Object> cbCategoria;
	private JComboBox cbUnidade;

	public Janela_persist_grupo() {
		setResizable(true);
		setIconifiable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setBounds(-5, 0, 788, 450);

		JLabel lblTitulo = new JLabel("Cadastrar Produto");
		lblTitulo.setBounds(10, 120, 753, 45);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		getContentPane().add(lblTitulo);

		JLabel lblNome = new JLabel("Nome do Produto:");
		lblNome.setBounds(20, 162, 253, 25);
		lblNome.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblNome);

		JLabel lblNewLabel_4 = new JLabel(new ImageIcon(getClass().getResource("/icons/pacote02-60.png")));
		lblNewLabel_4.setBounds(10, 24, 752, 95);
		getContentPane().add(lblNewLabel_4);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblCategoria.setBounds(312, 162, 148, 27);
		getContentPane().add(lblCategoria);

		JLabel lblPesoDoProduto = new JLabel("Peso/Volume do Produto:");
		lblPesoDoProduto.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblPesoDoProduto.setBounds(574, 162, 175, 25);
		getContentPane().add(lblPesoDoProduto);

		tfNome = new JTextField();
		tfNome.setBounds(20, 186, 253, 25);
		tfNome.setBackground(SystemColor.controlHighlight);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);

		tfPeso = new JTextField();
		tfPeso.setColumns(10);
		tfPeso.setBackground(SystemColor.controlHighlight);
		tfPeso.setBounds(574, 187, 80, 24);
		getContentPane().add(tfPeso);

		cbCategoria = new JComboBox<Object>(ControllerValidationGrupo.preencherCategorias().toArray());
		cbCategoria.setBackground(SystemColor.controlHighlight);
		cbCategoria.setBounds(312, 187, 148, 22);
		getContentPane().add(cbCategoria);

		String[] unidades = new String[] { "mg", "g", "Kg", "mL", "L", "m" };
		cbUnidade = new JComboBox(unidades);
		cbUnidade.setBackground(SystemColor.controlHighlight);
		cbUnidade.setBounds(669, 188, 80, 22);
		getContentPane().add(cbUnidade);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnLimpar.setBounds(321, 350, 131, 31);
		btnLimpar.setBackground(SystemColor.control);
		getContentPane().add(btnLimpar);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCadastrar.setBackground(SystemColor.control);
		btnCadastrar.setBounds(321, 307, 131, 31);
		getContentPane().add(btnCadastrar);

		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerValidationGrupo.limparDados(tfNome, tfPeso);
			}
		});

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControllerValidationGrupo.cadastrarGrupo(tfNome, null, cbCategoria, tfPeso, cbUnidade);
			}
		});
	}
}
