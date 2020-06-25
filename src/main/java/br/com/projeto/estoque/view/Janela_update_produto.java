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
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.projeto.estoque.controller.ControllerAtualizarProduto;
import br.com.projeto.estoque.controller.ControllerValidationGrupo;
import br.com.projeto.estoque.controller.ControllerValidationProduto;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Janela_update_produto extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	private JTextField tfCodigo;
	private JTextField tfFornecedorAtual;
	private JTextField tfQuantidade;
	private JTextField tfNome;
	private JComboBox cbFornecedorNovo;
	private JComboBox cbCategoriaNova;
	private JComboBox cbUnidade;
	private JEditorPane tpDescricao;
	private JButton btnResetar;
	private JButton btnAtualizar;

	public static EntityManager manager;
	private JTextField tfPeso;
	private JTextField tfPreco;
	private JTextField tfCategoriaAtual;

	public Janela_update_produto() {
		setResizable(true);
		setIconifiable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setBounds(-5, 0, 788, 498);

		JLabel lblTitulo = new JLabel("Alterar dados do produto");
		lblTitulo.setBounds(10, 120, 753, 45);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		getContentPane().add(lblTitulo);

		JLabel lblCodigo = new JLabel("C\u00F3digo do produto:");
		lblCodigo.setBounds(58, 176, 183, 25);
		lblCodigo.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblCodigo);

		JLabel lblQuantidade = new JLabel("Quantidade do produto:");
		lblQuantidade.setBounds(494, 176, 219, 27);
		lblQuantidade.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblQuantidade);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o da movimenta\u00E7\u00E3o:");
		lblDescricao.setBounds(494, 236, 219, 27);
		lblDescricao.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblDescricao);

		JLabel lblImagem = new JLabel(new ImageIcon(getClass().getResource("/icons/pacote02-60.png")));
		lblImagem.setBounds(10, 24, 753, 95);
		getContentPane().add(lblImagem);

		JLabel lblFornecedorAtual = new JLabel("Fornecedor atual:");
		lblFornecedorAtual.setBounds(58, 236, 183, 27);
		lblFornecedorAtual.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblFornecedorAtual);

		JLabel lblFornecedorNovo = new JLabel("Novo fornecedor:");
		lblFornecedorNovo.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblFornecedorNovo.setBounds(271, 236, 183, 27);
		getContentPane().add(lblFornecedorNovo);

		JLabel lblCategoriaAtual = new JLabel("Categoria atual:");
		lblCategoriaAtual.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblCategoriaAtual.setBounds(58, 299, 183, 27);
		getContentPane().add(lblCategoriaAtual);

		JLabel lblNovaCategoria = new JLabel("Nova categoria:");
		lblNovaCategoria.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNovaCategoria.setBounds(271, 299, 183, 27);
		getContentPane().add(lblNovaCategoria);

		JLabel lblNome = new JLabel("Nome do produto:");
		lblNome.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNome.setBounds(271, 176, 183, 25);
		getContentPane().add(lblNome);

		JLabel lblPeso = new JLabel("Peso/Volume do Produto:");
		lblPeso.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblPeso.setBounds(58, 369, 183, 27);
		getContentPane().add(lblPeso);

		JLabel lblPreco = new JLabel("Preço do Produto:");
		lblPreco.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreco.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblPreco.setBounds(271, 369, 183, 27);
		getContentPane().add(lblPreco);

		JLabel lblRS = new JLabel("R$");
		lblRS.setHorizontalAlignment(SwingConstants.CENTER);
		lblRS.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblRS.setBounds(271, 395, 34, 27);
		getContentPane().add(lblRS);

		tfCodigo = new JTextField();
		tfCodigo.setBounds(58, 200, 91, 25);
		tfCodigo.setBackground(SystemColor.controlHighlight);
		getContentPane().add(tfCodigo);
		tfCodigo.setColumns(10);

		tfNome = new JTextField();
		tfNome.setEditable(false);
		tfNome.setEnabled(false);
		tfNome.setColumns(10);
		tfNome.setBackground(Color.WHITE);
		tfNome.setBounds(271, 200, 183, 25);
		getContentPane().add(tfNome);

		tfQuantidade = new JTextField();
		tfQuantidade.setEnabled(false);
		tfQuantidade.setBounds(494, 202, 219, 27);
		tfQuantidade.setBackground(SystemColor.controlHighlight);
		getContentPane().add(tfQuantidade);
		tfQuantidade.setColumns(10);

		tfFornecedorAtual = new JTextField();
		tfFornecedorAtual.setEditable(false);
		tfFornecedorAtual.setEnabled(false);
		tfFornecedorAtual.setBounds(58, 262, 183, 25);
		tfFornecedorAtual.setBackground(Color.WHITE);
		getContentPane().add(tfFornecedorAtual);

		tfCategoriaAtual = new JTextField();
		tfCategoriaAtual.setEnabled(false);
		tfCategoriaAtual.setEditable(false);
		tfCategoriaAtual.setBackground(Color.WHITE);
		tfCategoriaAtual.setBounds(58, 325, 183, 25);
		getContentPane().add(tfCategoriaAtual);

		tfPeso = new JTextField();
		tfPeso.setEnabled(false);
		tfPeso.setBackground(SystemColor.controlHighlight);
		tfPeso.setBounds(58, 395, 91, 25);
		getContentPane().add(tfPeso);

		tfPreco = new JTextField();
		tfPreco.setEnabled(false);
		tfPreco.setBackground(SystemColor.controlHighlight);
		tfPreco.setBounds(323, 397, 131, 25);
		getContentPane().add(tfPreco);

//		cbFornecedorNovo = new JComboBox(ControllerValidationProduto.preencherFornecedores().toArray());
		cbFornecedorNovo.setEnabled(false);
		cbFornecedorNovo.setBackground(SystemColor.controlHighlight);
		cbFornecedorNovo.setBounds(271, 262, 183, 25);
		getContentPane().add(cbFornecedorNovo);

		String[] unidades = new String[] { "g", "mg", "Kg", "mL", "L", "m" };

//		cbCategoriaNova = new JComboBox(ControllerValidationGrupo.preencherCategorias().toArray());
		cbCategoriaNova.setEnabled(false);
		cbCategoriaNova.setBackground(SystemColor.controlHighlight);
		cbCategoriaNova.setBounds(271, 325, 183, 25);
		getContentPane().add(cbCategoriaNova);
		cbUnidade = new JComboBox(unidades);
		cbUnidade.setEnabled(false);
		cbUnidade.setBackground(SystemColor.controlHighlight);
		cbUnidade.setBounds(155, 395, 91, 25);
		getContentPane().add(cbUnidade);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(494, 262, 219, 57);
		getContentPane().add(scrollPane);

		tpDescricao = new JEditorPane();
		tpDescricao.setEnabled(false);
		tpDescricao.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		scrollPane.setViewportView(tpDescricao);
		tpDescricao.setBackground(SystemColor.controlHighlight);

		btnResetar = new JButton("Resetar");
		btnResetar.setEnabled(false);
		btnResetar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnResetar.setBounds(539, 351, 131, 31);
		btnResetar.setBackground(SystemColor.control);
		getContentPane().add(btnResetar);

		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setEnabled(false);
		btnAtualizar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnAtualizar.setBackground(SystemColor.control);
		btnAtualizar.setBounds(539, 394, 131, 31);
		getContentPane().add(btnAtualizar);

		JButton btnCarregar = new JButton("Carregar");
		btnCarregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCarregar.setBounds(152, 200, 89, 25);
		btnCarregar.setBackground(SystemColor.control);
		getContentPane().add(btnCarregar);

		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				ControllerAtualizarProduto.carregarRegistro(tfCodigo, tfNome, tfQuantidade, tpDescricao, tfPeso,
//						cbUnidade, tfPreco, tfFornecedorAtual, cbFornecedorNovo, tfCategoriaAtual, cbCategoriaNova,
//						btnResetar, btnAtualizar);
			}
		});

		btnResetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				ControllerAtualizarProduto.limparDados(tfCodigo, tfNome, tpDescricao, tfPeso, cbUnidade, tfPreco,
//						tfQuantidade, tfFornecedorAtual, cbFornecedorNovo, tfCategoriaAtual, cbCategoriaNova,
//						btnResetar, btnAtualizar);
			}
		});

		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				ControllerAtualizarProduto.atualizarRegistro(tfCodigo, tfNome, tfQuantidade, tpDescricao, tfPeso,
//						cbUnidade, tfPreco, tfFornecedorAtual, cbFornecedorNovo, tfCategoriaAtual, cbCategoriaNova,
//						btnResetar, btnAtualizar);
//				;
			}
		});
	}
}
