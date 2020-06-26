package br.com.projeto.estoque.viewUpdate;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.projeto.estoque.controller.ControllerAuxiliar;
import br.com.projeto.estoque.controller.ControllerProduto;
import br.com.projeto.estoque.controller.ControllerValidationMovimentacao;
import br.com.projeto.estoque.model.TipoMovimentacao;

@SuppressWarnings({ "serial", "rawtypes" })
public class Janela_movimentacao extends JFrame {

	private JComboBox cbTipoMovimentacao;
	private JPanel contentPane;

	private JTextField tfQuantidade;
	private JComboBox cbFornecedorMovimentacao;
	private JComboBox cbProdutoMovimentacao;
	private JEditorPane epDescricaoMovimentacao;
	private JTextField tfQtdMin;
	private JTextField tfSubtotal;
	private JTextField tfQtdMax;
	private JTextField tfQtdAtual;

	@SuppressWarnings("unchecked")
	public Janela_movimentacao() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Janela_login.class.getResource("/sage_icons/logoTransparente.png")));
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setTitle("Movimentações");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);

		JButton btnMovimentar = new JButton("Movimentar");
		btnMovimentar.setBounds(130, 437, 125, 32);
		contentPane.add(btnMovimentar);

		JLabel lblTitulo = new JLabel("Realizar Movimentação");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		lblTitulo.setBounds(10, 11, 364, 42);
		contentPane.add(lblTitulo);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 64, 364, 9);
		contentPane.add(separator);

		TipoMovimentacao[] tipoMovimentacao = new TipoMovimentacao[] { TipoMovimentacao.ENTRADA,
				TipoMovimentacao.SAIDA };
		cbTipoMovimentacao = new JComboBox(tipoMovimentacao);
		cbTipoMovimentacao.setBounds(134, 84, 181, 27);
		contentPane.add(cbTipoMovimentacao);

		JLabel lblTipoMovimentacao = new JLabel("Tipo:");
		lblTipoMovimentacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoMovimentacao.setBounds(10, 84, 125, 27);
		contentPane.add(lblTipoMovimentacao);

		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblFornecedor.setBounds(10, 122, 125, 27);
		contentPane.add(lblFornecedor);

		cbFornecedorMovimentacao = new JComboBox(ControllerAuxiliar.preencherFornecedores().toArray());
		cbFornecedorMovimentacao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (cbFornecedorMovimentacao.isEnabled() == false) {
					JOptionPane.showMessageDialog(null, "Movimentações de SAÍDA não possuem Fornecedor!",
							"Movimentação de Saída", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		cbFornecedorMovimentacao.setBounds(134, 122, 181, 27);
		contentPane.add(cbFornecedorMovimentacao);

		JLabel lblDescrio = new JLabel("Descrição da movimentação:");
		lblDescrio.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescrio.setBounds(40, 331, 180, 27);
		contentPane.add(lblDescrio);

		JScrollPane spDescricaoMovimentacao = new JScrollPane();
		spDescricaoMovimentacao.setBounds(40, 370, 305, 55);
		contentPane.add(spDescricaoMovimentacao);

		epDescricaoMovimentacao = new JEditorPane();
		spDescricaoMovimentacao.setViewportView(epDescricaoMovimentacao);

		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblProduto.setBounds(10, 160, 125, 27);
		contentPane.add(lblProduto);

		List<String> arrayProdutos = ControllerProduto.listarDescricaoProdutosAtivos();
		arrayProdutos.add(0, "...");
		cbProdutoMovimentacao = new JComboBox(arrayProdutos.toArray());
		cbProdutoMovimentacao.setBounds(134, 160, 181, 27);
		contentPane.add(cbProdutoMovimentacao);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantidade.setBounds(10, 199, 125, 27);
		contentPane.add(lblQuantidade);

		tfQuantidade = new JTextField();
		tfQuantidade.setBounds(134, 199, 181, 27);
		contentPane.add(tfQuantidade);
		tfQuantidade.setColumns(10);

		JLabel lblQuantidadeMnima = new JLabel("Quantidade mínima / Subtotal / Quantidade máxima");
		lblQuantidadeMnima.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantidadeMnima.setBounds(10, 238, 364, 27);
		contentPane.add(lblQuantidadeMnima);

		tfQtdMin = new JTextField();
		tfQtdMin.setEnabled(false);
		tfQtdMin.setEditable(false);
		tfQtdMin.setColumns(10);
		tfQtdMin.setBounds(90, 266, 60, 27);
		contentPane.add(tfQtdMin);

		tfSubtotal = new JTextField();
		tfSubtotal.setEnabled(false);
		tfSubtotal.setEditable(false);
		tfSubtotal.setColumns(10);
		tfSubtotal.setBounds(162, 266, 60, 27);
		contentPane.add(tfSubtotal);

		tfQtdMax = new JTextField();
		tfQtdMax.setEnabled(false);
		tfQtdMax.setEditable(false);
		tfQtdMax.setColumns(10);
		tfQtdMax.setBounds(234, 266, 60, 27);
		contentPane.add(tfQtdMax);

		tfQtdAtual = new JTextField();
		tfQtdAtual.setEnabled(false);
		tfQtdAtual.setEditable(false);
		tfQtdAtual.setColumns(10);
		tfQtdAtual.setBounds(327, 199, 45, 27);
		contentPane.add(tfQtdAtual);

		JLabel lblAtual = new JLabel("Atual:");
		lblAtual.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtual.setBounds(327, 160, 45, 27);
		contentPane.add(lblAtual);
		setLocationRelativeTo(null);

		// ------------------------------------ActionListeners

		// Ação do botão de realizar a Movimentação
		btnMovimentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControllerValidationMovimentacao cvm = new ControllerValidationMovimentacao();
				cvm.realizarMovimentacao(cbTipoMovimentacao, cbFornecedorMovimentacao, cbProdutoMovimentacao,
						tfQuantidade, epDescricaoMovimentacao, tfQtdAtual);
			}
		});

		// Quando for selecionado o tipo de movimentação de SAÍDA
		cbTipoMovimentacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cbTipoMovimentacao.getSelectedItem() == TipoMovimentacao.SAIDA) {
					cbFornecedorMovimentacao.setEnabled(false);
					ControllerValidationMovimentacao.movimentacaoTipoSaida = true;
				} else {
					cbFornecedorMovimentacao.setEnabled(true);
					ControllerValidationMovimentacao.movimentacaoTipoSaida = false;
				}
			}
		});

		// Quando o Produto selecionado mudar:
		cbProdutoMovimentacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbProdutoMovimentacao.getSelectedIndex() == 0) {
					ControllerAuxiliar.resetarCamposProdutoEGrupo(tfQtdAtual, tfQtdMin, tfSubtotal, tfQtdMax);
				} else {
					ControllerAuxiliar.preencherCamposProdutoEGrupo(cbProdutoMovimentacao, tfQtdAtual, tfQtdMin,
							tfSubtotal, tfQtdMax);
				}
			}
		});
	}
}
