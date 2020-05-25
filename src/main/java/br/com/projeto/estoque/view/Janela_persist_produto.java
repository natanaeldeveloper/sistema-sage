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

import com.toedter.calendar.JDateChooser;

import br.com.projeto.estoque.controller.ControllerValidationGrupo;
import br.com.projeto.estoque.controller.ControllerValidationProduto;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Janela_persist_produto extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	private JTextField tfNome;
	private JTextField tfQuantidade;
	private JComboBox<Object> cbFornecedor;
	private JEditorPane tpDescricao;
	public static EntityManager manager;
	private JTextField tfPeso;
	private JComboBox<Object> cbCategoria;
	private JComboBox cbUnidade;
	private JDateChooser dcFabricacao;
	private JDateChooser dcVencimento;
	private JTextField tfPreco;

	public Janela_persist_produto() {
		setResizable(true);
		setIconifiable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setBounds(-5, 0, 788, 517);

		JLabel lblTitulo = new JLabel("Cadastrar Produto");
		lblTitulo.setBounds(10, 120, 753, 45);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		getContentPane().add(lblTitulo);

		JLabel lblNome = new JLabel("Nome do Produto:");
		lblNome.setBounds(20, 162, 253, 25);
		lblNome.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblNome);

		JLabel lblQuantidade = new JLabel("Quantidade do Produto:");
		lblQuantidade.setBounds(484, 283, 159, 27);
		lblQuantidade.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblQuantidade);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o da Transa\u00E7\u00E3o:");
		lblDescricao.setBounds(20, 283, 253, 27);
		lblDescricao.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblDescricao);

		JLabel lblNewLabel_4 = new JLabel(new ImageIcon(getClass().getResource("/icons/pacote02-60.png")));
		lblNewLabel_4.setBounds(10, 24, 752, 95);
		getContentPane().add(lblNewLabel_4);

		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setBounds(306, 162, 148, 27);
		lblFornecedor.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblFornecedor);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblCategoria.setBounds(306, 222, 148, 27);
		getContentPane().add(lblCategoria);

		JLabel lblPesoDoProduto = new JLabel("Peso/Volume do Produto:");
		lblPesoDoProduto.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblPesoDoProduto.setBounds(20, 222, 175, 25);
		getContentPane().add(lblPesoDoProduto);

		JLabel lblDataDeFabricao = new JLabel("Data de Fabricação:");
		lblDataDeFabricao.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblDataDeFabricao.setBounds(484, 160, 148, 27);
		getContentPane().add(lblDataDeFabricao);

		JLabel lblDataDeVencimento = new JLabel("Data de Vencimento:");
		lblDataDeVencimento.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblDataDeVencimento.setBounds(484, 222, 148, 27);
		getContentPane().add(lblDataDeVencimento);

		JLabel lblRS = new JLabel("R$");
		lblRS.setHorizontalAlignment(SwingConstants.CENTER);
		lblRS.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblRS.setBounds(306, 321, 27, 27);
		getContentPane().add(lblRS);

		tfNome = new JTextField();
		tfNome.setBounds(20, 186, 253, 25);
		tfNome.setBackground(SystemColor.controlHighlight);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);

		tfQuantidade = new JTextField();
		tfQuantidade.setBounds(484, 322, 159, 26);
		tfQuantidade.setBackground(SystemColor.controlHighlight);
		getContentPane().add(tfQuantidade);
		tfQuantidade.setColumns(10);

		tfPeso = new JTextField();
		tfPeso.setColumns(10);
		tfPeso.setBackground(SystemColor.controlHighlight);
		tfPeso.setBounds(20, 247, 80, 24);
		getContentPane().add(tfPeso);

		tfPreco = new JTextField();
		tfPreco.setColumns(10);
		tfPreco.setBackground(SystemColor.controlHighlight);
		tfPreco.setBounds(334, 323, 120, 25);
		getContentPane().add(tfPreco);

		dcFabricacao = new JDateChooser();
		dcFabricacao.setBounds(484, 186, 131, 24);
		getContentPane().add(dcFabricacao);

		dcVencimento = new JDateChooser();
		dcVencimento.setBounds(484, 247, 131, 24);
		getContentPane().add(dcVencimento);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 321, 253, 53);
		getContentPane().add(scrollPane);

		tpDescricao = new JEditorPane();
		scrollPane.setViewportView(tpDescricao);
		tpDescricao.setBackground(SystemColor.controlHighlight);

		cbCategoria = new JComboBox<Object>(ControllerValidationGrupo.preencherCategorias().toArray());
		cbCategoria.setBackground(SystemColor.controlHighlight);
		cbCategoria.setBounds(306, 247, 148, 22);
		getContentPane().add(cbCategoria);

		cbFornecedor = new JComboBox<Object>(ControllerValidationProduto.preencherFornecedores().toArray());
		cbFornecedor.setBounds(306, 187, 148, 22);
		cbFornecedor.setBackground(SystemColor.controlHighlight);
		getContentPane().add(cbFornecedor);

		String[] unidades = new String[] { "mg", "g", "Kg", "mL", "L", "m" };
		cbUnidade = new JComboBox(unidades);
		cbUnidade.setBackground(SystemColor.controlHighlight);
		cbUnidade.setBounds(115, 248, 80, 22);
		getContentPane().add(cbUnidade);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnLimpar.setBounds(141, 398, 131, 31);
		btnLimpar.setBackground(SystemColor.control);
		getContentPane().add(btnLimpar);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCadastrar.setBackground(SystemColor.control);
		btnCadastrar.setBounds(305, 398, 131, 31);
		getContentPane().add(btnCadastrar);

		JLabel lblPreo = new JLabel("Preço do Produto:");
		lblPreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPreo.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblPreo.setBounds(306, 289, 148, 27);
		getContentPane().add(lblPreo);

		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				ControllerValidationProduto.limparDados(tfNome, tfQuantidade, tfPeso, tfPreco, tpDescricao);
			}
		});

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				ControllerValidationProduto.executarCadastro(tfNome, tfQuantidade, tfPeso, cbUnidade, tfPreco, tpDescricao,
//						dcFabricacao, dcVencimento, cbCategoria, cbFornecedor);
			}
		});
	}
}
