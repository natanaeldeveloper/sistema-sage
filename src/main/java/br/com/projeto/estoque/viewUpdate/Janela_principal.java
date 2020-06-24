package br.com.projeto.estoque.viewUpdate;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.toedter.calendar.JDateChooser;

import br.com.projeto.estoque.controller.ControllerAtualizarFornecedor;
import br.com.projeto.estoque.controller.ControllerAtualizarProduto;
import br.com.projeto.estoque.controller.ControllerAuxiliar;
import br.com.projeto.estoque.controller.ControllerGlobal;
import br.com.projeto.estoque.controller.ControllerInativarProduto;
import br.com.projeto.estoque.controller.ControllerMovimentacao;
import br.com.projeto.estoque.controller.ControllerPermissao;
import br.com.projeto.estoque.controller.ControllerSupervisor;
import br.com.projeto.estoque.controller.ControllerTableModels;
import br.com.projeto.estoque.controller.ControllerValidationFornecedor;
import br.com.projeto.estoque.controller.ControllerValidationProduto;

@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
public class Janela_principal extends JFrame {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private ControllerTableModels ctb;

	// CAMPOS DO PAINEL DE DELEÇÃO DO SUPERVISOR
	private JFormattedTextField cpf_gerente_deleteSupervisor;
	private JPasswordField senha_gerente_deleteSupervisor;
	private JFormattedTextField cpf_supervisor_deleteSupervisor;
	private JFormattedTextField id_pesquisa_deleteSupervisor;

	private JFormattedTextField login_supervisor_deleteSupervisor = new JFormattedTextField();

	// CAMPOS DO PAINEL DE CRIAÇÃO/CADASTRO DO SUPERVISOR
	private JFormattedTextField cpf_supervisor_criarSupervisor;
	private JFormattedTextField login_supervisor_criarSupervisor = new JFormattedTextField();
	private JPasswordField confirmacaoSenha_supervisor_criarSupervisor;
	private JPasswordField senha_supervisor_criarSupervisor;

	// CAMPOS DO PAINEL DE ATUALIZAÇÃO DO SUPERVISOR
	private JFormattedTextField cpf_gerente_AtualizacaoSupervisor;
	private JFormattedTextField id_pesquisa_supervisor_AtualizacaoSupervisor;
	private JPasswordField nova_senha_supervisor_AtualizacaoSupervisor;
	private JPasswordField senha_gerente_AtualizacaoSupervisor;
	private JFormattedTextField cpf_atual_supervisor_AtualizacaoSupervisor = new JFormattedTextField();
	private JFormattedTextField login_atual_supervisor_AtualizacaoSupervisor = new JFormattedTextField();
	private JFormattedTextField novo_login_supervisor_AtualizacaoSupervisor = new JFormattedTextField();

	private JPanel contentPane;
	private JTable table_fornecedores;
	private JTable table_produtos;
	private JTable table_movimentacoes;
	private JTable table_registros_supervisores;
	private JTable table_supervisores;

	private JTextField tfCidadeAtualizarFornecedor;
	private JTextField tfNomeAtualizarFornecedor;
	private JTextField tfRazaoSocialAtualizarFornecedor;
	private JTextField tfNumeroAtualizarFornecedor;
	private JTextField tfLogradouroAtualizarFornecedor;
	private JTextField tfComplementoAtualizarFornecedor;
	private JFormattedTextField tfCnpjAtualizarFornecedor;

	private JFormattedTextField tfIdAtualizarFornecedor;
	private JFormattedTextField tfTelefoneAtualizarFornecedor;
	private JFormattedTextField tfCepAtualizarFornecedor;
	private JFormattedTextField tfBairroAtualizarFornecedor;
	private JFormattedTextField tfEmailAtualizarFornecedor;

	private JComboBox cbEstadoAtualizarFornecedor;
	private JComboBox cbGrupoCadastrar;
	private JComboBox cbGrupoAtualizar;
	private JComboBox cbUnidadeCadastrarProduto;
	private JComboBox cbFornecedorCadastro;
	private JComboBox cbUnidadeAtualizarProduto;
	private JComboBox cbEstadoCadastrarFornecedor;
	private JComboBox cbUnidadeInativarProduto;
	private JComboBox cbGrupoInativar;

	private JSpinner spQuantidadeCadastrarProduto;
	private JSpinner spQuantidadeAtualizarProduto;
	private JSpinner spQuantidadeInativarProduto;

	private JEditorPane epDescricaoCadastrarProduto;
	private JEditorPane epDescricaoMovimentacaoCadastro;
	private JEditorPane epDescricaoAtualizarProduto;
	private JEditorPane epDescricaoInativarProduto;

	private JDateChooser dcFabricacaoCadastrarProduto;
	private JDateChooser dcVencimentoCadastrarProduto;
	private JDateChooser dcFabricacaoAtualizarProduto;
	private JDateChooser dcVencimentoAtualizarProduto;
	private JDateChooser dcFabricacaoInativarProduto;
	private JDateChooser dcVencimentoInativarProduto;

	private JTextField tfCidadeCadastrarFornecedor;
	private JTextField tfNomeCadastrarFornecedor;
	private JTextField tfRazaoSocialCadastrarFornecedor;
	private JTextField tfNumeroCadastrarFornecedor;
	private JTextField tfLogradouroCadastrarFornecedor;
	private JTextField tfComplementoCadastrarFornecedor;
	private JButton btnLimparCadastrarFornecedor;
	private JProgressBar progressBar_tabelas_superior;
	private JTextField textField_1;
	private JTextField tfNomeGrupoAtualizar;
	private JTextField tfMedidaAtualizarProduto;
	private JTextField tfNomeGrupoCadastrar;
	private JTextField tfMedidaGrupoCadastrar;
	private JFormattedTextField tfEmailCadastrarFornecedor = new JFormattedTextField();
	private JFormattedTextField tfTelefoneCadastrarFornecedor = new JFormattedTextField();
	private JFormattedTextField tfCepCadastrarFornecedor = new JFormattedTextField();
	private JFormattedTextField tfCnpjCadastrarFornecedor = new JFormattedTextField();
	private JFormattedTextField tfBairroCadastrarFornecedor = new JFormattedTextField();
	private JFormattedTextField tfIdInativarFornecedor = new JFormattedTextField();
	private JFormattedTextField c_email_af_1 = new JFormattedTextField();
	private JFormattedTextField c_cnpj_af_1 = new JFormattedTextField();
	private JFormattedTextField tfCodigoGrupoCadastrar = new JFormattedTextField();
	private JFormattedTextField tfPrecoCadastrarProduto = new JFormattedTextField();
	private JFormattedTextField tfCodigoGrupoAtualizar = new JFormattedTextField();
	private JFormattedTextField tfPrecoAtualizarProduto = new JFormattedTextField();
	private JFormattedTextField tfIdAtualizarProduto = new JFormattedTextField();
	private JFormattedTextField tfIdInativarProduto;
	private JFormattedTextField tfPrecoInativarProduto;
	private JFormattedTextField tfCodigoGrupoInativar;

	private JButton botao_limpar_dados_deleteSupervisor = new JButton("Limpar");
	private JButton btnBuscarProduto;
	private JButton btnResetarAtualizarProduto;
	private JButton btnLimparAtualizarProduto;
	private JButton btnAtualizarProduto;

	private JButton btnBuscarAtualizarFornecedor;
	private JButton btnResetarAtualizarFornecedor;
	private JButton btnLimparAtualizarFornecedor;
	private JButton btnAtualizarFornecedor;
	private JButton btnBuscarProdutoInativar;
	private JButton btnResetarInativarProduto;
	private JButton btnLimparInativarProduto;
	private JButton btnInativarProduto;

	private String[] unidades = new String[] { "mg", "g", "kg", "m", "mL", "L" };

	JLabel usuario_atual_atualizar_gerente = new JLabel("");
	JLabel usuario_atual_deletarSupervisor = new JLabel("");
	JLabel usuario_atual_cadastrarSupervisor = new JLabel("");

	ControllerSupervisor ctrlSuper = new ControllerSupervisor();
	ControllerAuxiliar ctrlAux = new ControllerAuxiliar();
	ControllerPermissao ctrlPermissao = new ControllerPermissao();

	MaskFormatter ms;
	DefaultFormatterFactory df;
	private JTextField tfNomeGrupoInativar;
	private JTextField tfMedidaInativarProduto;
	JMenuItem menuItemNovoRelatorio;
	private JTextField tfNomeCadastrarGrupo;
	private JTextField tfSubtotal;
	private JTextField tfQtdMax;
	private JTextField tfSubtotalAtualizar;
	private JTextField tfQtdMaxAtualizar;

	public Janela_principal() {
		ctrlAux.setarLoginUsuarioAtual_na_telaPrincipal(usuario_atual_cadastrarSupervisor,
				usuario_atual_atualizar_gerente, usuario_atual_deletarSupervisor);

		setTitle("SAGE - Menu Principal");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1216, 603);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuRelatorio = new JMenu("Relatório");
		menuRelatorio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(menuRelatorio);

		menuItemNovoRelatorio = new JMenuItem("Novo");
		menuItemNovoRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Janela_relatorio jr = new Janela_relatorio();
				jr.setVisible(true);
			}
		});

		menuRelatorio.add(menuItemNovoRelatorio);

		JMenu menuDefinicoes = new JMenu("Denifições");
		menuDefinicoes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(menuDefinicoes);

		JMenu menuItemAlterarTema = new JMenu("Alterar Tema");
		menuDefinicoes.add(menuItemAlterarTema);

		JMenuItem temaDark = new JMenuItem("Dark");
		temaDark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(new FlatDarkLaf());
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				SwingUtilities.updateComponentTreeUI(new Janela_principal());
				Janela_principal t = new Janela_principal();
				repaint();
				validate();
				t.setVisible(true);
				dispose();

			}
		});
		menuItemAlterarTema.add(temaDark);

		JMenuItem temaLight = new JMenuItem("Light");
		temaLight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(new FlatIntelliJLaf());
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				SwingUtilities.updateComponentTreeUI(new Janela_principal());
				Janela_principal t = new Janela_principal();
				repaint();
				validate();
				t.setVisible(true);
				dispose();
			}
		});
		menuItemAlterarTema.add(temaLight);

		JMenuItem temaLightArc = new JMenuItem("Light Arc");
		temaLightArc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IntelliJTheme.install(Janela_principal.class.getResourceAsStream("/themes/Arc.json"));
				SwingUtilities.updateComponentTreeUI(new Janela_principal());
				Janela_principal t = new Janela_principal();
				repaint();
				validate();
				t.setVisible(true);
				dispose();
			}
		});
		menuItemAlterarTema.add(temaLightArc);

		JMenuItem temaHighContrast = new JMenuItem("High Contrast");
		temaHighContrast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IntelliJTheme.install(Janela_principal.class.getResourceAsStream("/themes/HighContrast.json"));
				SwingUtilities.updateComponentTreeUI(new Janela_principal());
				Janela_principal t = new Janela_principal();
				repaint();
				validate();
				t.setVisible(true);
				dispose();
			}
		});
		menuItemAlterarTema.add(temaHighContrast);

		JMenuItem temaPalenightContrast = new JMenuItem("Palenight Contrast");
		temaPalenightContrast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IntelliJTheme.install(Janela_principal.class.getResourceAsStream("/themes/PalenightContrast.json"));
				SwingUtilities.updateComponentTreeUI(new Janela_principal());
				Janela_principal t = new Janela_principal();
				repaint();
				validate();
				t.setVisible(true);
				dispose();
			}
		});
		menuItemAlterarTema.add(temaPalenightContrast);

		JMenuItem temaDarker = new JMenuItem("Darker");
		temaDarker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IntelliJTheme.install(Janela_principal.class.getResourceAsStream("/themes/Darker.json"));
				SwingUtilities.updateComponentTreeUI(new Janela_principal());
				Janela_principal t = new Janela_principal();
				repaint();
				validate();
				t.setVisible(true);
				dispose();
			}
		});
		menuItemAlterarTema.add(temaDarker);

		JMenuItem menuItemBtnSair = new JMenuItem("Sair");

		menuDefinicoes.add(menuItemBtnSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel painelGeral = new JPanel();
		painelGeral.setBounds(0, 0, 1210, 553);
		contentPane.add(painelGeral);
		painelGeral.setLayout(null);

		JTabbedPane tabbedPane_area = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_area.setBounds(0, 0, 623, 553);
		painelGeral.add(tabbedPane_area);

		JPanel painelAreaSupervisor = new JPanel();
		tabbedPane_area.addTab("Área do Supervisor", null, painelAreaSupervisor, null);
		painelAreaSupervisor.setLayout(null);

		JTabbedPane tpSupervisores = new JTabbedPane(JTabbedPane.TOP);
		tpSupervisores.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		// tabbedPane_acoes_gerente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tpSupervisores.setBounds(0, 0, 618, 525);
		painelAreaSupervisor.add(tpSupervisores);

		// ADICIONAR CAMPOS DE CADASTRAR gerente NESSE PANEL
		JPanel painelCadastrarSupervisor = new JPanel();
		tpSupervisores.addTab("Cadastrar Supervisor",
				new ImageIcon(getClass().getResource("/sage_icons/profile_plus_round [#1343].png")),
				painelCadastrarSupervisor, null);
		painelCadastrarSupervisor.setLayout(null);

		JButton limpar_dados_criarSupervisor = new JButton("Limpar");

		limpar_dados_criarSupervisor.setBounds(383, 406, 105, 34);
		painelCadastrarSupervisor.add(limpar_dados_criarSupervisor);

		JButton btn_cadastrar_dados_supervisor = new JButton("Cadastrar");

		btn_cadastrar_dados_supervisor.setBounds(498, 406, 105, 34);
		painelCadastrarSupervisor.add(btn_cadastrar_dados_supervisor);

		JLayeredPane layeredPane_2_1 = new JLayeredPane();
		layeredPane_2_1.setBorder(
				new TitledBorder(null, "Dados do novo Supervisor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		layeredPane_2_1.setBounds(20, 47, 583, 158);
		painelCadastrarSupervisor.add(layeredPane_2_1);

		senha_supervisor_criarSupervisor = new JPasswordField();
		senha_supervisor_criarSupervisor.setColumns(10);
		senha_supervisor_criarSupervisor.setBounds(21, 111, 253, 25);
		layeredPane_2_1.add(senha_supervisor_criarSupervisor);

		JLabel lblNewLabel_2_1_1 = new JLabel("Senha:");
		lblNewLabel_2_1_1.setBounds(21, 87, 253, 25);
		layeredPane_2_1.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_5_1 = new JLabel("CPF:");
		lblNewLabel_5_1.setBounds(307, 26, 253, 25);
		layeredPane_2_1.add(lblNewLabel_5_1);

		cpf_supervisor_criarSupervisor = new JFormattedTextField();
		cpf_supervisor_criarSupervisor.setColumns(10);
		cpf_supervisor_criarSupervisor.setBounds(307, 50, 253, 25);
		layeredPane_2_1.add(cpf_supervisor_criarSupervisor);

		confirmacaoSenha_supervisor_criarSupervisor = new JPasswordField();
		confirmacaoSenha_supervisor_criarSupervisor.setColumns(10);
		confirmacaoSenha_supervisor_criarSupervisor.setBounds(307, 111, 253, 25);
		layeredPane_2_1.add(confirmacaoSenha_supervisor_criarSupervisor);

		JLabel lblConfirmaoDeSenha = new JLabel("\r\nConfirmação de senha:");
		lblConfirmaoDeSenha.setBounds(307, 87, 253, 25);
		layeredPane_2_1.add(lblConfirmaoDeSenha);

		login_supervisor_criarSupervisor.setBounds(21, 51, 253, 25);
		layeredPane_2_1.add(login_supervisor_criarSupervisor);
		login_supervisor_criarSupervisor.setColumns(10);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(21, 31, 253, 25);
		layeredPane_2_1.add(lblLogin);

		JLabel label = new JLabel("USUÁRIO ATUAL:");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(20, 11, 106, 25);
		painelCadastrarSupervisor.add(label);

		usuario_atual_cadastrarSupervisor.setFont(new Font("Tahoma", Font.ITALIC, 11));
		usuario_atual_cadastrarSupervisor.setBounds(143, 11, 169, 25);
		painelCadastrarSupervisor.add(usuario_atual_cadastrarSupervisor);

		// ADICIONAR CAMPOS DE ATUALIZAR gerente NESSE PANEL
		JPanel painelAtualizarSupervisor = new JPanel();
		tpSupervisores.addTab("Atualizar Supervisor",
				new ImageIcon(getClass().getResource("/sage_icons/profile_round [#1342].png")),
				painelAtualizarSupervisor, null);
		painelAtualizarSupervisor.setLayout(null);

		JButton botao_limpar_dados_AtualizacaoSupervisor = new JButton("Limpar");

		botao_limpar_dados_AtualizacaoSupervisor.setBounds(30, 429, 78, 34);
		painelAtualizarSupervisor.add(botao_limpar_dados_AtualizacaoSupervisor);

		JButton btn_atualizar_tudo_AtualizacaoSupervisor = new JButton("Atualizar Tudo");

		btn_atualizar_tudo_AtualizacaoSupervisor.setBounds(385, 429, 106, 34);
		painelAtualizarSupervisor.add(btn_atualizar_tudo_AtualizacaoSupervisor);

		JButton btn_atualizar_senha_AtualizacaoSupervisor = new JButton("Atualizar Senha");

		btn_atualizar_senha_AtualizacaoSupervisor.setBounds(118, 429, 115, 34);
		painelAtualizarSupervisor.add(btn_atualizar_senha_AtualizacaoSupervisor);

		JLayeredPane botao_atualizar_senha_AtualizacaoSupervisor = new JLayeredPane();
		botao_atualizar_senha_AtualizacaoSupervisor.setBorder(
				new TitledBorder(null, "Dados do Supervisor", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		botao_atualizar_senha_AtualizacaoSupervisor.setBounds(20, 152, 583, 262);
		painelAtualizarSupervisor.add(botao_atualizar_senha_AtualizacaoSupervisor);

		JLabel lblPesquisarId = new JLabel("Pesquisar pelo ID:");
		lblPesquisarId.setBounds(20, 42, 182, 25);
		botao_atualizar_senha_AtualizacaoSupervisor.add(lblPesquisarId);
		// lblPesquisarId.setFont(new Font("Bahnschrift", Font.PLAIN, 14));

		id_pesquisa_supervisor_AtualizacaoSupervisor = new JFormattedTextField();
		id_pesquisa_supervisor_AtualizacaoSupervisor.setBounds(20, 69, 86, 25);
		botao_atualizar_senha_AtualizacaoSupervisor.add(id_pesquisa_supervisor_AtualizacaoSupervisor);
		id_pesquisa_supervisor_AtualizacaoSupervisor.setText("0");
		id_pesquisa_supervisor_AtualizacaoSupervisor.setColumns(10);
		// formattedTextField_1_2.setBackground(SystemColor.controlHighlight);

		JButton buscar_supervisor_AtualizacaoSupervisor = new JButton("Buscar");

		buscar_supervisor_AtualizacaoSupervisor.setBounds(116, 69, 86, 25);
		botao_atualizar_senha_AtualizacaoSupervisor.add(buscar_supervisor_AtualizacaoSupervisor);
		// passwordField.setBackground(SystemColor.controlHighlight);

		JLabel lblNewLabel_3 = new JLabel("Nova Senha:");
		lblNewLabel_3.setBounds(20, 150, 253, 25);
		botao_atualizar_senha_AtualizacaoSupervisor.add(lblNewLabel_3);
		// lblNewLabel_3.setFont(new Font("Bahnschrift", Font.PLAIN, 14));

		nova_senha_supervisor_AtualizacaoSupervisor = new JPasswordField();
		nova_senha_supervisor_AtualizacaoSupervisor.setBounds(20, 175, 253, 25);
		botao_atualizar_senha_AtualizacaoSupervisor.add(nova_senha_supervisor_AtualizacaoSupervisor);
		nova_senha_supervisor_AtualizacaoSupervisor.setColumns(10);
		nova_senha_supervisor_AtualizacaoSupervisor.setEditable(true);

		cpf_atual_supervisor_AtualizacaoSupervisor.setEditable(false);
		cpf_atual_supervisor_AtualizacaoSupervisor.setColumns(10);
		cpf_atual_supervisor_AtualizacaoSupervisor.setBounds(306, 124, 253, 25);

		botao_atualizar_senha_AtualizacaoSupervisor.add(cpf_atual_supervisor_AtualizacaoSupervisor);

		JLabel lblSenhaAtual = new JLabel("Login atual:");
		lblSenhaAtual.setBounds(20, 97, 253, 25);
		botao_atualizar_senha_AtualizacaoSupervisor.add(lblSenhaAtual);

		login_atual_supervisor_AtualizacaoSupervisor.setEditable(false);
		login_atual_supervisor_AtualizacaoSupervisor.setColumns(10);
		login_atual_supervisor_AtualizacaoSupervisor.setBounds(20, 126, 253, 25);
		botao_atualizar_senha_AtualizacaoSupervisor.add(login_atual_supervisor_AtualizacaoSupervisor);

		JLabel lblCpfAtual = new JLabel("CPF atual:");
		lblCpfAtual.setBounds(306, 102, 253, 25);
		botao_atualizar_senha_AtualizacaoSupervisor.add(lblCpfAtual);

		novo_login_supervisor_AtualizacaoSupervisor.setColumns(10);
		novo_login_supervisor_AtualizacaoSupervisor.setBounds(20, 226, 253, 25);
		botao_atualizar_senha_AtualizacaoSupervisor.add(novo_login_supervisor_AtualizacaoSupervisor);

		JLabel lblNovoLogin = new JLabel("Novo Login:");
		lblNovoLogin.setBounds(20, 203, 253, 25);
		botao_atualizar_senha_AtualizacaoSupervisor.add(lblNovoLogin);

		JLayeredPane layeredPane_2 = new JLayeredPane();
		layeredPane_2.setBorder(new TitledBorder(null, "Confirme Seus Dados de Gerente", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		layeredPane_2.setBounds(20, 38, 583, 113);
		painelAtualizarSupervisor.add(layeredPane_2);

		senha_gerente_AtualizacaoSupervisor = new JPasswordField();
		senha_gerente_AtualizacaoSupervisor.setColumns(10);
		senha_gerente_AtualizacaoSupervisor.setBounds(21, 50, 253, 25);
		layeredPane_2.add(senha_gerente_AtualizacaoSupervisor);

		JLabel lblNewLabel_2_1 = new JLabel("Sua Senha:");
		lblNewLabel_2_1.setBounds(21, 26, 253, 25);
		layeredPane_2.add(lblNewLabel_2_1);

		JLabel lblNewLabel_5 = new JLabel("Seu CPF:");
		lblNewLabel_5.setBounds(307, 26, 253, 25);
		layeredPane_2.add(lblNewLabel_5);

		cpf_gerente_AtualizacaoSupervisor = new JFormattedTextField();
		cpf_gerente_AtualizacaoSupervisor.setColumns(10);
		cpf_gerente_AtualizacaoSupervisor.setBounds(307, 50, 253, 25);
		layeredPane_2.add(cpf_gerente_AtualizacaoSupervisor);

		JButton btn_atualizar_login_AtualizacaoSupervisor = new JButton("Atualizar Login");

		btn_atualizar_login_AtualizacaoSupervisor.setBounds(243, 429, 128, 34);
		painelAtualizarSupervisor.add(btn_atualizar_login_AtualizacaoSupervisor);

		JLabel lblUsurioAtual = new JLabel("USUÁRIO ATUAL:");
		lblUsurioAtual.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsurioAtual.setForeground(Color.BLACK);
		lblUsurioAtual.setBounds(20, 11, 106, 25);
		painelAtualizarSupervisor.add(lblUsurioAtual);

		usuario_atual_atualizar_gerente.setFont(new Font("Tahoma", Font.ITALIC, 11));
		usuario_atual_atualizar_gerente.setBounds(143, 11, 169, 25);
		painelAtualizarSupervisor.add(usuario_atual_atualizar_gerente);
		// c_cpfAtual.setBackground(SystemColor.controlHighlight);

		// ADICIONAR CAMPOS DE DELETAR gerente NESSE PANEL
		JPanel painelDeletarSupervisor = new JPanel();
		tpSupervisores.addTab("Deletar Supervisor",
				new ImageIcon(getClass().getResource("/sage_icons/profile_round [#1346].png")), painelDeletarSupervisor,
				null);
		painelDeletarSupervisor.setLayout(null);

		JLayeredPane layeredPane_2_2 = new JLayeredPane();
		layeredPane_2_2.setBorder(new TitledBorder(null, "Confirme Seus Dados de Gerente", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		layeredPane_2_2.setBounds(20, 39, 583, 113);
		painelDeletarSupervisor.add(layeredPane_2_2);

		senha_gerente_deleteSupervisor = new JPasswordField();
		senha_gerente_deleteSupervisor.setColumns(10);
		senha_gerente_deleteSupervisor.setBounds(21, 50, 253, 25);
		layeredPane_2_2.add(senha_gerente_deleteSupervisor);

		JLabel lblNewLabel_2_1_2 = new JLabel("Sua Senha:");
		lblNewLabel_2_1_2.setBounds(21, 26, 253, 25);
		layeredPane_2_2.add(lblNewLabel_2_1_2);

		JLabel lblNewLabel_5_2 = new JLabel("Seu CPF:");
		lblNewLabel_5_2.setBounds(307, 26, 253, 25);
		layeredPane_2_2.add(lblNewLabel_5_2);

		cpf_gerente_deleteSupervisor = new JFormattedTextField();
		cpf_gerente_deleteSupervisor.setColumns(10);
		cpf_gerente_deleteSupervisor.setBounds(307, 50, 253, 25);
		layeredPane_2_2.add(cpf_gerente_deleteSupervisor);

		JLayeredPane layeredPane_1_1 = new JLayeredPane();
		layeredPane_1_1.setBorder(
				new TitledBorder(null, "Dados do Supervisor", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		layeredPane_1_1.setBounds(20, 152, 583, 190);
		painelDeletarSupervisor.add(layeredPane_1_1);

		JLabel lblPesquisarId_1 = new JLabel("Pesquisar ID:");
		lblPesquisarId_1.setBounds(20, 33, 182, 25);
		layeredPane_1_1.add(lblPesquisarId_1);

		id_pesquisa_deleteSupervisor = new JFormattedTextField();
		id_pesquisa_deleteSupervisor.setText("0");
		id_pesquisa_deleteSupervisor.setColumns(10);
		id_pesquisa_deleteSupervisor.setBounds(20, 69, 86, 25);
		layeredPane_1_1.add(id_pesquisa_deleteSupervisor);

		id_pesquisa_deleteSupervisor.setText("0");

		JButton botao_pesquisa_deleteSupervisor = new JButton("Buscar");

		botao_pesquisa_deleteSupervisor.setBounds(116, 69, 86, 25);
		layeredPane_1_1.add(botao_pesquisa_deleteSupervisor);

		JLabel lblNewLabel_3_2 = new JLabel("Login");
		lblNewLabel_3_2.setBounds(20, 103, 253, 27);
		layeredPane_1_1.add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_1_1 = new JLabel("CPF:");
		lblNewLabel_3_1_1.setBounds(306, 103, 253, 27);
		layeredPane_1_1.add(lblNewLabel_3_1_1);

		cpf_supervisor_deleteSupervisor = new JFormattedTextField();
		cpf_supervisor_deleteSupervisor.setEditable(false);
		cpf_supervisor_deleteSupervisor.setColumns(10);
		cpf_supervisor_deleteSupervisor.setBounds(306, 131, 253, 25);
		layeredPane_1_1.add(cpf_supervisor_deleteSupervisor);

		login_supervisor_deleteSupervisor.setEditable(false);
		login_supervisor_deleteSupervisor.setColumns(10);
		login_supervisor_deleteSupervisor.setBounds(20, 133, 253, 25);
		layeredPane_1_1.add(login_supervisor_deleteSupervisor);

		botao_limpar_dados_deleteSupervisor.setBounds(383, 406, 105, 34);
		painelDeletarSupervisor.add(botao_limpar_dados_deleteSupervisor);

		JButton botao_deletar_deleteSupervisor = new JButton("Deletar");

		botao_deletar_deleteSupervisor.setBounds(498, 406, 105, 34);
		painelDeletarSupervisor.add(botao_deletar_deleteSupervisor);

		usuario_atual_deletarSupervisor.setFont(new Font("Tahoma", Font.ITALIC, 11));
		usuario_atual_deletarSupervisor.setBounds(146, 11, 169, 25);
		painelDeletarSupervisor.add(usuario_atual_deletarSupervisor);

		JLabel label_2 = new JLabel("USUÁRIO ATUAL:");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(23, 11, 106, 25);
		painelDeletarSupervisor.add(label_2);

		JPanel painelAreaFornecedor = new JPanel();
		tabbedPane_area.addTab("Área do Fornecedor", null, painelAreaFornecedor, null);
		painelAreaFornecedor.setLayout(null);

		JTabbedPane tpFornecedores = new JTabbedPane(JTabbedPane.TOP);
		tpFornecedores.setBounds(0, 0, 618, 525);
		painelAreaFornecedor.add(tpFornecedores);

		// ADICIONAR CAMPOS DE CADASTRAR FORNECEDOR NESSE PANEL
		JPanel painelCadastrarFornecedor = new JPanel();
		tpFornecedores.addTab("Cadastrar Fornecedor",
				new ImageIcon(getClass().getResource("/sage_icons/profile_image_plus_round [#1327].png")),
				painelCadastrarFornecedor, null);
		painelCadastrarFornecedor.setLayout(null);

		JLayeredPane lpCadastrarFornecedor = new JLayeredPane();
		lpCadastrarFornecedor.setBorder(
				new TitledBorder(null, "Dados do novo Fornecedor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lpCadastrarFornecedor.setBounds(20, 71, 583, 324);
		painelCadastrarFornecedor.add(lpCadastrarFornecedor);

		JLabel lblNewLabel_2_1_1_1_2_1 = new JLabel("CNPJ:");
		lblNewLabel_2_1_1_1_2_1.setBounds(21, 26, 176, 25);
		lpCadastrarFornecedor.add(lblNewLabel_2_1_1_1_2_1);

		JLabel lblNewLabel_5_1_1_1_1 = new JLabel("Email:");
		lblNewLabel_5_1_1_1_1.setBounds(207, 26, 351, 25);
		lpCadastrarFornecedor.add(lblNewLabel_5_1_1_1_1);

		tfEmailCadastrarFornecedor.setColumns(10);
		tfEmailCadastrarFornecedor.setBounds(207, 50, 351, 25);
		lpCadastrarFornecedor.add(tfEmailCadastrarFornecedor);

		JLabel lblNewLabel_2_1_1_1_1_4_1 = new JLabel("Nome:");
		lblNewLabel_2_1_1_1_1_4_1.setBounds(21, 86, 176, 25);
		lpCadastrarFornecedor.add(lblNewLabel_2_1_1_1_1_4_1);

		JLabel lblNewLabel_2_1_1_1_1_1_1_1 = new JLabel("CEP:");
		lblNewLabel_2_1_1_1_1_1_1_1.setBounds(207, 86, 151, 25);
		lpCadastrarFornecedor.add(lblNewLabel_2_1_1_1_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1_2_1_1 = new JLabel("Estado:");
		lblNewLabel_2_1_1_1_1_2_1_1.setBounds(368, 86, 56, 25);
		lpCadastrarFornecedor.add(lblNewLabel_2_1_1_1_1_2_1_1);

		String[] estados = new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG",
				"PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };
		cbEstadoCadastrarFornecedor = new JComboBox(estados);
		cbEstadoCadastrarFornecedor.setBounds(368, 110, 56, 25);
		lpCadastrarFornecedor.add(cbEstadoCadastrarFornecedor);

		JLabel lblNewLabel_2_1_1_1_1_3_1_1 = new JLabel("Cidade:");
		lblNewLabel_2_1_1_1_1_3_1_1.setBounds(434, 86, 124, 25);
		lpCadastrarFornecedor.add(lblNewLabel_2_1_1_1_1_3_1_1);

		tfCidadeCadastrarFornecedor = new JTextField();
		tfCidadeCadastrarFornecedor.setColumns(10);
		tfCidadeCadastrarFornecedor.setBounds(434, 110, 124, 25);
		lpCadastrarFornecedor.add(tfCidadeCadastrarFornecedor);

		tfNomeCadastrarFornecedor = new JTextField();
		tfNomeCadastrarFornecedor.setColumns(10);
		tfNomeCadastrarFornecedor.setBounds(21, 110, 176, 25);
		lpCadastrarFornecedor.add(tfNomeCadastrarFornecedor);

		JLabel lblNewLabel_1_1 = new JLabel("Razão Social:");
		lblNewLabel_1_1.setBounds(21, 148, 248, 14);
		lpCadastrarFornecedor.add(lblNewLabel_1_1);

		tfRazaoSocialCadastrarFornecedor = new JTextField();
		tfRazaoSocialCadastrarFornecedor.setColumns(10);
		tfRazaoSocialCadastrarFornecedor.setBounds(21, 165, 248, 25);
		lpCadastrarFornecedor.add(tfRazaoSocialCadastrarFornecedor);

		JLabel lblTelefone_1_1 = new JLabel("Telefone:");
		lblTelefone_1_1.setBounds(279, 148, 145, 14);
		lpCadastrarFornecedor.add(lblTelefone_1_1);

		ms = null;
		try {
			ms = new MaskFormatter("(##)####-####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		df = new DefaultFormatterFactory(ms);
		tfTelefoneCadastrarFornecedor.setFormatterFactory(df);
		tfTelefoneCadastrarFornecedor.setBounds(279, 165, 145, 25);
		lpCadastrarFornecedor.add(tfTelefoneCadastrarFornecedor);

		ms = null;
		try {
			ms = new MaskFormatter("##.###.###/####-##");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		df = new DefaultFormatterFactory(ms);
		tfCnpjCadastrarFornecedor.setFormatterFactory(df);
		tfCnpjCadastrarFornecedor.setBounds(21, 50, 176, 25);
		lpCadastrarFornecedor.add(tfCnpjCadastrarFornecedor);

		ms = null;
		try {
			ms = new MaskFormatter("#####-###");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		df = new DefaultFormatterFactory(ms);
		tfCepCadastrarFornecedor.setFormatterFactory(df);
		tfCepCadastrarFornecedor.setBounds(207, 110, 151, 25);
		lpCadastrarFornecedor.add(tfCepCadastrarFornecedor);

		JLabel lblBairro_1_1 = new JLabel("Bairro:");
		lblBairro_1_1.setBounds(436, 146, 122, 14);
		lpCadastrarFornecedor.add(lblBairro_1_1);

		tfBairroCadastrarFornecedor.setBounds(434, 165, 124, 25);
		lpCadastrarFornecedor.add(tfBairroCadastrarFornecedor);

		JLabel lblNmerodomiclio_1_1 = new JLabel("Número (Domicílio)");
		lblNmerodomiclio_1_1.setBounds(21, 201, 124, 14);
		lpCadastrarFornecedor.add(lblNmerodomiclio_1_1);

		tfNumeroCadastrarFornecedor = new JTextField();
		tfNumeroCadastrarFornecedor.setColumns(10);
		tfNumeroCadastrarFornecedor.setBounds(21, 218, 124, 25);
		lpCadastrarFornecedor.add(tfNumeroCadastrarFornecedor);

		JLabel lblLogradouro_1_2_1 = new JLabel("Logradouro:");
		lblLogradouro_1_2_1.setBounds(155, 201, 403, 14);
		lpCadastrarFornecedor.add(lblLogradouro_1_2_1);

		tfLogradouroCadastrarFornecedor = new JTextField();
		tfLogradouroCadastrarFornecedor.setColumns(10);
		tfLogradouroCadastrarFornecedor.setBounds(155, 218, 403, 25);
		lpCadastrarFornecedor.add(tfLogradouroCadastrarFornecedor);

		JLabel lblLogradouro_1_1_1_1 = new JLabel("Complemento:");
		lblLogradouro_1_1_1_1.setBounds(21, 254, 537, 14);
		lpCadastrarFornecedor.add(lblLogradouro_1_1_1_1);

		tfComplementoCadastrarFornecedor = new JTextField();
		tfComplementoCadastrarFornecedor.setColumns(10);
		tfComplementoCadastrarFornecedor.setBounds(21, 271, 537, 25);
		lpCadastrarFornecedor.add(tfComplementoCadastrarFornecedor);

		btnLimparCadastrarFornecedor = new JButton("Limpar");
		btnLimparCadastrarFornecedor.setBounds(389, 406, 105, 34);
		painelCadastrarFornecedor.add(btnLimparCadastrarFornecedor);

		JButton btnCadastrarFornecedor = new JButton("Cadastrar");
		btnCadastrarFornecedor.setBounds(504, 406, 99, 34);
		painelCadastrarFornecedor.add(btnCadastrarFornecedor);

		// ADICIONAR CAMPOS DE ALTERAR FORNECEDOR NESSE PANEL
		JPanel painelAtualizarFornecedor = new JPanel();
		tpFornecedores.addTab("Atualizar Fornecedor",
				new ImageIcon(getClass().getResource("/sage_icons/profile_image_round [#1326].png")),
				painelAtualizarFornecedor, null);
		painelAtualizarFornecedor.setLayout(null);

		JLayeredPane lpAtualizarFornecedor = new JLayeredPane();
		lpAtualizarFornecedor.setBorder(
				new TitledBorder(null, "Dados do novo Fornecedor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lpAtualizarFornecedor.setBounds(20, 71, 583, 324);
		painelAtualizarFornecedor.add(lpAtualizarFornecedor);

		JLabel lblNewLabel_2_1_1_1_2 = new JLabel("CNPJ:");
		lblNewLabel_2_1_1_1_2.setBounds(21, 26, 176, 25);
		lpAtualizarFornecedor.add(lblNewLabel_2_1_1_1_2);

		JLabel lblNewLabel_5_1_1_1 = new JLabel("Email:");
		lblNewLabel_5_1_1_1.setBounds(207, 26, 351, 25);
		lpAtualizarFornecedor.add(lblNewLabel_5_1_1_1);

		tfEmailAtualizarFornecedor = new JFormattedTextField();
		tfEmailAtualizarFornecedor.setEnabled(false);
		tfEmailAtualizarFornecedor.setColumns(10);
		tfEmailAtualizarFornecedor.setBounds(207, 50, 351, 25);
		lpAtualizarFornecedor.add(tfEmailAtualizarFornecedor);

		JLabel lblNewLabel_2_1_1_1_1_4 = new JLabel("Nome:");
		lblNewLabel_2_1_1_1_1_4.setBounds(21, 86, 176, 25);
		lpAtualizarFornecedor.add(lblNewLabel_2_1_1_1_1_4);

		JLabel lblNewLabel_2_1_1_1_1_1_1 = new JLabel("CEP:");
		lblNewLabel_2_1_1_1_1_1_1.setBounds(207, 86, 151, 25);
		lpAtualizarFornecedor.add(lblNewLabel_2_1_1_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1_2_1 = new JLabel("Estado:");
		lblNewLabel_2_1_1_1_1_2_1.setBounds(368, 86, 56, 25);
		lpAtualizarFornecedor.add(lblNewLabel_2_1_1_1_1_2_1);

		cbEstadoAtualizarFornecedor = new JComboBox();
		cbEstadoAtualizarFornecedor.setEnabled(false);
		cbEstadoAtualizarFornecedor.setModel(new DefaultComboBoxModel(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cbEstadoAtualizarFornecedor.setBounds(368, 110, 56, 25);
		lpAtualizarFornecedor.add(cbEstadoAtualizarFornecedor);

		JLabel lblNewLabel_2_1_1_1_1_3_1 = new JLabel("Cidade:");
		lblNewLabel_2_1_1_1_1_3_1.setBounds(434, 86, 124, 25);
		lpAtualizarFornecedor.add(lblNewLabel_2_1_1_1_1_3_1);

		tfCidadeAtualizarFornecedor = new JTextField();
		tfCidadeAtualizarFornecedor.setEnabled(false);
		tfCidadeAtualizarFornecedor.setColumns(10);
		tfCidadeAtualizarFornecedor.setBounds(434, 110, 124, 25);
		lpAtualizarFornecedor.add(tfCidadeAtualizarFornecedor);

		tfNomeAtualizarFornecedor = new JTextField();
		tfNomeAtualizarFornecedor.setEnabled(false);
		tfNomeAtualizarFornecedor.setColumns(10);
		tfNomeAtualizarFornecedor.setBounds(21, 110, 176, 25);
		lpAtualizarFornecedor.add(tfNomeAtualizarFornecedor);

		JLabel lblNewLabel_1 = new JLabel("Razão Social:");
		lblNewLabel_1.setBounds(21, 148, 248, 14);
		lpAtualizarFornecedor.add(lblNewLabel_1);

		tfRazaoSocialAtualizarFornecedor = new JTextField();
		tfRazaoSocialAtualizarFornecedor.setEnabled(false);
		tfRazaoSocialAtualizarFornecedor.setColumns(10);
		tfRazaoSocialAtualizarFornecedor.setBounds(21, 165, 248, 25);
		lpAtualizarFornecedor.add(tfRazaoSocialAtualizarFornecedor);

		JLabel lblTelefone_1 = new JLabel("Telefone:");
		lblTelefone_1.setBounds(279, 148, 145, 14);
		lpAtualizarFornecedor.add(lblTelefone_1);

		ms = null;
		try {
			ms = new MaskFormatter("(##)####-####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		df = new DefaultFormatterFactory(ms);
		tfTelefoneAtualizarFornecedor = new JFormattedTextField();
		tfTelefoneAtualizarFornecedor.setEnabled(false);
		tfTelefoneAtualizarFornecedor.setBounds(279, 165, 145, 25);
		tfTelefoneAtualizarFornecedor.setFormatterFactory(df);
		lpAtualizarFornecedor.add(tfTelefoneAtualizarFornecedor);

		ms = null;
		try {
			ms = new MaskFormatter("##.###.###/####-##");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		df = new DefaultFormatterFactory(ms);
		tfCnpjAtualizarFornecedor = new JFormattedTextField();
		tfCnpjAtualizarFornecedor.setEnabled(false);
		tfCnpjAtualizarFornecedor.setBounds(21, 50, 176, 25);
		tfCnpjAtualizarFornecedor.setFormatterFactory(df);
		lpAtualizarFornecedor.add(tfCnpjAtualizarFornecedor);

		ms = null;
		try {
			ms = new MaskFormatter("#####-###");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		df = new DefaultFormatterFactory(ms);
		tfCepAtualizarFornecedor = new JFormattedTextField();
		tfCepAtualizarFornecedor.setEnabled(false);
		tfCepAtualizarFornecedor.setBounds(207, 110, 151, 25);
		tfCepAtualizarFornecedor.setFormatterFactory(df);
		lpAtualizarFornecedor.add(tfCepAtualizarFornecedor);

		JLabel lblBairro_1 = new JLabel("Bairro:");
		lblBairro_1.setBounds(436, 146, 122, 14);
		lpAtualizarFornecedor.add(lblBairro_1);

		tfBairroAtualizarFornecedor = new JFormattedTextField();
		tfBairroAtualizarFornecedor.setEnabled(false);
		tfBairroAtualizarFornecedor.setBounds(434, 165, 124, 25);
		lpAtualizarFornecedor.add(tfBairroAtualizarFornecedor);

		JLabel lblNmerodomiclio_1 = new JLabel("Número (Domicílio)");
		lblNmerodomiclio_1.setBounds(21, 201, 124, 14);
		lpAtualizarFornecedor.add(lblNmerodomiclio_1);

		tfNumeroAtualizarFornecedor = new JTextField();
		tfNumeroAtualizarFornecedor.setEnabled(false);
		tfNumeroAtualizarFornecedor.setColumns(10);
		tfNumeroAtualizarFornecedor.setBounds(21, 218, 124, 25);
		lpAtualizarFornecedor.add(tfNumeroAtualizarFornecedor);

		JLabel lblLogradouro_1_2 = new JLabel("Logradouro:");
		lblLogradouro_1_2.setBounds(155, 201, 403, 14);
		lpAtualizarFornecedor.add(lblLogradouro_1_2);

		tfLogradouroAtualizarFornecedor = new JTextField();
		tfLogradouroAtualizarFornecedor.setEnabled(false);
		tfLogradouroAtualizarFornecedor.setColumns(10);
		tfLogradouroAtualizarFornecedor.setBounds(155, 218, 403, 25);
		lpAtualizarFornecedor.add(tfLogradouroAtualizarFornecedor);

		JLabel lblLogradouro_1_1_1 = new JLabel("Complemento:");
		lblLogradouro_1_1_1.setBounds(21, 254, 537, 14);
		lpAtualizarFornecedor.add(lblLogradouro_1_1_1);

		tfComplementoAtualizarFornecedor = new JTextField();
		tfComplementoAtualizarFornecedor.setEnabled(false);
		tfComplementoAtualizarFornecedor.setColumns(10);
		tfComplementoAtualizarFornecedor.setBounds(21, 271, 537, 25);
		lpAtualizarFornecedor.add(tfComplementoAtualizarFornecedor);

		JLabel lblPesquisarId_2 = new JLabel("Pesquisar pelo ID:");
		lblPesquisarId_2.setBounds(393, 11, 182, 25);
		painelAtualizarFornecedor.add(lblPesquisarId_2);

		tfIdAtualizarFornecedor = new JFormattedTextField();
		tfIdAtualizarFornecedor.setText("0");
		tfIdAtualizarFornecedor.setColumns(10);
		tfIdAtualizarFornecedor.setBounds(393, 37, 86, 23);
		painelAtualizarFornecedor.add(tfIdAtualizarFornecedor);

		btnBuscarAtualizarFornecedor = new JButton("Buscar");
		btnBuscarAtualizarFornecedor.setBounds(489, 36, 86, 25);
		painelAtualizarFornecedor.add(btnBuscarAtualizarFornecedor);

		btnLimparAtualizarFornecedor = new JButton("Limpar");
		btnLimparAtualizarFornecedor.setEnabled(false);
		btnLimparAtualizarFornecedor.setBounds(389, 406, 105, 34);
		painelAtualizarFornecedor.add(btnLimparAtualizarFornecedor);

		btnAtualizarFornecedor = new JButton("Atualizar");
		btnAtualizarFornecedor.setEnabled(false);
		btnAtualizarFornecedor.setBounds(504, 406, 99, 34);
		painelAtualizarFornecedor.add(btnAtualizarFornecedor);

		btnResetarAtualizarFornecedor = new JButton("Resetar");
		btnResetarAtualizarFornecedor.setEnabled(false);
		btnResetarAtualizarFornecedor.setBounds(306, 36, 77, 25);
		painelAtualizarFornecedor.add(btnResetarAtualizarFornecedor);

		// ADICIONAR CAMPOS DE DELETAR FORNECEDOR NESSE PANEL
		JPanel painelInativarFornecedor = new JPanel();
		tpFornecedores.addTab("Inativar Fornecedor",
				new ImageIcon(getClass().getResource("/sage_icons/profile_image_minus_round [#1329].png")),
				painelInativarFornecedor, null);
		painelInativarFornecedor.setLayout(null);

		JLabel lblPesquisarId_2_1 = new JLabel("Pesquisar pelo ID:");
		lblPesquisarId_2_1.setBounds(393, 11, 182, 25);
		painelInativarFornecedor.add(lblPesquisarId_2_1);

		tfIdInativarFornecedor.setText("0");
		tfIdInativarFornecedor.setColumns(10);
		tfIdInativarFornecedor.setBounds(393, 37, 86, 23);
		painelInativarFornecedor.add(tfIdInativarFornecedor);

		JButton btnBuscarInativarFornecedor = new JButton("Buscar");
		btnBuscarInativarFornecedor.setBounds(489, 35, 86, 25);
		painelInativarFornecedor.add(btnBuscarInativarFornecedor);

		JLayeredPane lpInativarFornecedor = new JLayeredPane();
		lpInativarFornecedor.setBorder(
				new TitledBorder(null, "Dados do novo Fornecedor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lpInativarFornecedor.setBounds(20, 71, 583, 173);
		painelInativarFornecedor.add(lpInativarFornecedor);

		JLabel lblNewLabel_2_1_1_1_2_2 = new JLabel("CNPJ:");
		lblNewLabel_2_1_1_1_2_2.setBounds(21, 26, 176, 25);
		lpInativarFornecedor.add(lblNewLabel_2_1_1_1_2_2);

		JLabel lblNewLabel_5_1_1_1_2 = new JLabel("Email:");
		lblNewLabel_5_1_1_1_2.setBounds(21, 86, 525, 25);
		lpInativarFornecedor.add(lblNewLabel_5_1_1_1_2);

		c_email_af_1.setEnabled(false);
		c_email_af_1.setColumns(10);
		c_email_af_1.setBounds(21, 110, 536, 25);
		lpInativarFornecedor.add(c_email_af_1);

		JLabel lblNewLabel_2_1_1_1_1_4_2 = new JLabel("Nome:");
		lblNewLabel_2_1_1_1_1_4_2.setBounds(207, 26, 339, 25);
		lpInativarFornecedor.add(lblNewLabel_2_1_1_1_1_4_2);

		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(207, 50, 350, 25);
		lpInativarFornecedor.add(textField_1);

		c_cnpj_af_1.setEnabled(false);
		c_cnpj_af_1.setBounds(21, 52, 176, 25);
		lpInativarFornecedor.add(c_cnpj_af_1);

		JButton btnLimparInativarFornecedor = new JButton("Limpar");
		btnLimparInativarFornecedor.setBounds(389, 406, 105, 34);
		painelInativarFornecedor.add(btnLimparInativarFornecedor);

		JButton btnInativarFornecedor = new JButton("Inativar");
		btnInativarFornecedor.setBounds(504, 406, 99, 34);
		painelInativarFornecedor.add(btnInativarFornecedor);

		JPanel painelAreaProduto = new JPanel();
		tabbedPane_area.addTab("Área do Produto", null, painelAreaProduto, null);
		painelAreaProduto.setLayout(null);

		JTabbedPane tpProdutos = new JTabbedPane(JTabbedPane.TOP);
		tpProdutos.setBounds(0, 0, 618, 525);
		painelAreaProduto.add(tpProdutos);

		// ADICIONAR CAMPOS DE CADASTRAR PRODUTO NESSE PANEL
		JPanel painelCadastrarProduto = new JPanel();
		tpProdutos.addTab("Cadastrar Produto",
				new ImageIcon(getClass().getResource("/sage_icons/cart_plus_round [#1158].png")),
				painelCadastrarProduto, null);
		painelCadastrarProduto.setLayout(null);

		JLayeredPane lpProdutoCadastrarProduto = new JLayeredPane();
		lpProdutoCadastrarProduto
				.setBorder(new TitledBorder(null, "Dados do Produto", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		lpProdutoCadastrarProduto.setBounds(20, 71, 583, 221);
		painelCadastrarProduto.add(lpProdutoCadastrarProduto);

		dcFabricacaoCadastrarProduto = new JDateChooser();
		dcFabricacaoCadastrarProduto.setBounds(397, 106, 140, 24);
		lpProdutoCadastrarProduto.add(dcFabricacaoCadastrarProduto);

		dcVencimentoCadastrarProduto = new JDateChooser();
		dcVencimentoCadastrarProduto.setBounds(397, 165, 140, 24);
		lpProdutoCadastrarProduto.add(dcVencimentoCadastrarProduto);

		JLabel lblNewLabel_4 = new JLabel("Nome:");
		lblNewLabel_4.setBounds(48, 33, 189, 20);
		lpProdutoCadastrarProduto.add(lblNewLabel_4);

		tfNomeGrupoCadastrar = new JTextField();
		tfNomeGrupoCadastrar.setEnabled(false);
		tfNomeGrupoCadastrar.setColumns(10);
		tfNomeGrupoCadastrar.setBounds(48, 52, 189, 24);
		lpProdutoCadastrarProduto.add(tfNomeGrupoCadastrar);

		JLabel lblNewLabel_2_3 = new JLabel("Descrição do Produto:");
		lblNewLabel_2_3.setBounds(48, 85, 189, 20);
		lpProdutoCadastrarProduto.add(lblNewLabel_2_3);

		tfCodigoGrupoCadastrar.setEnabled(false);
		tfCodigoGrupoCadastrar.setBounds(247, 52, 100, 24);
		lpProdutoCadastrarProduto.add(tfCodigoGrupoCadastrar);

		JLabel lblCdigo_1 = new JLabel("Código:");
		lblCdigo_1.setBounds(247, 33, 100, 20);
		lpProdutoCadastrarProduto.add(lblCdigo_1);

		JLabel lblPreo_1 = new JLabel("Preço:");
		lblPreo_1.setBounds(357, 33, 94, 20);
		lpProdutoCadastrarProduto.add(lblPreo_1);

		tfPrecoCadastrarProduto.setBounds(357, 52, 94, 24);
		lpProdutoCadastrarProduto.add(tfPrecoCadastrarProduto);

		List<String> vazio = ControllerAuxiliar.preencherGrupos();
		vazio.add(0, "...");
		cbGrupoCadastrar = new JComboBox(vazio.toArray());
		cbGrupoCadastrar.setBounds(247, 106, 140, 24);
		lpProdutoCadastrarProduto.add(cbGrupoCadastrar);

		tfMedidaGrupoCadastrar = new JTextField();
		tfMedidaGrupoCadastrar.setColumns(10);
		tfMedidaGrupoCadastrar.setBounds(247, 165, 76, 24);
		lpProdutoCadastrarProduto.add(tfMedidaGrupoCadastrar);

		JLabel lblDataDeFabricao_1 = new JLabel("Data de Fabricação:");
		lblDataDeFabricao_1.setBounds(397, 87, 140, 20);
		lpProdutoCadastrarProduto.add(lblDataDeFabricao_1);

		JLabel lblPreo_1_1_1 = new JLabel("Data de Vencimento:");
		lblPreo_1_1_1.setBounds(397, 141, 140, 20);
		lpProdutoCadastrarProduto.add(lblPreo_1_1_1);

		cbUnidadeCadastrarProduto = new JComboBox(unidades);
		cbUnidadeCadastrarProduto.setBounds(333, 165, 54, 24);
		lpProdutoCadastrarProduto.add(cbUnidadeCadastrarProduto);

		epDescricaoCadastrarProduto = new JEditorPane();
		epDescricaoCadastrarProduto.setBounds(48, 108, 187, 81);
		lpProdutoCadastrarProduto.add(epDescricaoCadastrarProduto);

		spQuantidadeCadastrarProduto = new JSpinner();
		spQuantidadeCadastrarProduto.setBounds(473, 52, 64, 24);
		lpProdutoCadastrarProduto.add(spQuantidadeCadastrarProduto);

		JLabel lblNewLabel_2_2_3 = new JLabel("Grupo:");
		lblNewLabel_2_2_3.setBounds(247, 85, 140, 20);
		lpProdutoCadastrarProduto.add(lblNewLabel_2_2_3);

		JLabel lblNewLabel_2_2_1_1 = new JLabel("Quantidade:");
		lblNewLabel_2_2_1_1.setBounds(473, 33, 76, 20);
		lpProdutoCadastrarProduto.add(lblNewLabel_2_2_1_1);

		JLabel lblNewLabel_2_2_2_2 = new JLabel("Medida:");
		lblNewLabel_2_2_2_2.setBounds(247, 142, 88, 20);
		lpProdutoCadastrarProduto.add(lblNewLabel_2_2_2_2);

		JLabel lblNewLabel_2_2_2_1_1 = new JLabel("Unidade:");
		lblNewLabel_2_2_2_1_1.setBounds(333, 142, 54, 20);
		lpProdutoCadastrarProduto.add(lblNewLabel_2_2_2_1_1);

		JButton btnCadastrarProduto = new JButton("Cadastrar");
		btnCadastrarProduto.setBounds(504, 437, 99, 34);
		painelCadastrarProduto.add(btnCadastrarProduto);

		JButton btnLimparCadastrarProduto = new JButton("Limpar");
		btnLimparCadastrarProduto.setBounds(389, 437, 105, 34);
		painelCadastrarProduto.add(btnLimparCadastrarProduto);

		JLayeredPane lpMovimentacaoCadastrarProduto = new JLayeredPane();
		lpMovimentacaoCadastrarProduto.setBorder(
				new TitledBorder(null, "Movimentação de Entrada", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		lpMovimentacaoCadastrarProduto.setBounds(20, 305, 583, 120);
		painelCadastrarProduto.add(lpMovimentacaoCadastrarProduto);

		JLabel label_1 = new JLabel("Descrição:");
		label_1.setBounds(12, 25, 69, 24);
		lpMovimentacaoCadastrarProduto.add(label_1);

		epDescricaoMovimentacaoCadastro = new JEditorPane();
		epDescricaoMovimentacaoCadastro.setBounds(12, 61, 559, 47);
		lpMovimentacaoCadastrarProduto.add(epDescricaoMovimentacaoCadastro);

		JLabel label_3 = new JLabel("Fornecedor:");
		label_3.setBounds(343, 27, 83, 20);
		lpMovimentacaoCadastrarProduto.add(label_3);

		cbFornecedorCadastro = new JComboBox(ControllerAuxiliar.preencherFornecedores().toArray());
		cbFornecedorCadastro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				ControllerAuxiliar.repopularFornecedores(cbFornecedorCadastro);
			}
		});
		cbFornecedorCadastro.setBounds(431, 25, 140, 24);
		lpMovimentacaoCadastrarProduto.add(cbFornecedorCadastro);

		tfSubtotal = new JTextField();
		tfSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		tfSubtotal.setEditable(false);
		tfSubtotal.setColumns(10);
		tfSubtotal.setBounds(371, 34, 76, 24);
		painelCadastrarProduto.add(tfSubtotal);

		JLabel lblSubtotalQuantidade = new JLabel("Subtotal / Quantidade máxima do grupo");
		lblSubtotalQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtotalQuantidade.setBounds(308, 11, 295, 20);
		painelCadastrarProduto.add(lblSubtotalQuantidade);

		tfQtdMax = new JTextField();
		tfQtdMax.setHorizontalAlignment(SwingConstants.CENTER);
		tfQtdMax.setEditable(false);
		tfQtdMax.setColumns(10);
		tfQtdMax.setBounds(469, 34, 76, 24);
		painelCadastrarProduto.add(tfQtdMax);

		// ADICIONAR CAMPOS DE ALTERAR PRODUTO NESSE PANEL
		JPanel painelAtualizarProduto = new JPanel();
		tpProdutos.addTab("Atualizar Produto",
				new ImageIcon(getClass().getResource("/sage_icons/cart_round [#1166].png")), painelAtualizarProduto,
				null);
		painelAtualizarProduto.setLayout(null);

		JLayeredPane lpAtualizarProduto = new JLayeredPane();
		lpAtualizarProduto
				.setBorder(new TitledBorder(null, "Dados do Produto", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		lpAtualizarProduto.setBounds(20, 71, 583, 221);
		painelAtualizarProduto.add(lpAtualizarProduto);

		dcFabricacaoAtualizarProduto = new JDateChooser();
		dcFabricacaoAtualizarProduto.setForeground(SystemColor.control);
		dcFabricacaoAtualizarProduto.setBounds(397, 106, 140, 24);
		lpAtualizarProduto.add(dcFabricacaoAtualizarProduto);

		dcVencimentoAtualizarProduto = new JDateChooser();
		dcVencimentoAtualizarProduto.setForeground(SystemColor.control);
		dcVencimentoAtualizarProduto.setBounds(397, 165, 140, 24);
		lpAtualizarProduto.add(dcVencimentoAtualizarProduto);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(48, 33, 189, 20);
		lpAtualizarProduto.add(lblNewLabel);

		tfNomeGrupoAtualizar = new JTextField();
		tfNomeGrupoAtualizar.setEnabled(false);
		tfNomeGrupoAtualizar.setColumns(10);
		tfNomeGrupoAtualizar.setBounds(48, 52, 189, 24);
		lpAtualizarProduto.add(tfNomeGrupoAtualizar);

		JLabel lblNewLabel_2 = new JLabel("Descrição do Produto:");
		lblNewLabel_2.setBounds(48, 85, 189, 20);
		lpAtualizarProduto.add(lblNewLabel_2);

		tfCodigoGrupoAtualizar.setEnabled(false);
		tfCodigoGrupoAtualizar.setBounds(247, 52, 100, 24);
		lpAtualizarProduto.add(tfCodigoGrupoAtualizar);

		JLabel lblCdigo = new JLabel("Código:");
		lblCdigo.setBounds(247, 33, 100, 20);
		lpAtualizarProduto.add(lblCdigo);

		JLabel lblPreo = new JLabel("Preço:");
		lblPreo.setBounds(357, 33, 94, 20);
		lpAtualizarProduto.add(lblPreo);
		tfPrecoAtualizarProduto.setEnabled(false);

		tfPrecoAtualizarProduto.setBounds(357, 52, 94, 24);
		lpAtualizarProduto.add(tfPrecoAtualizarProduto);

		cbGrupoAtualizar = new JComboBox(vazio.toArray());
		cbGrupoAtualizar.setEnabled(false);
		cbGrupoAtualizar.setBounds(247, 106, 140, 24);
		lpAtualizarProduto.add(cbGrupoAtualizar);

		tfMedidaAtualizarProduto = new JTextField();
		tfMedidaAtualizarProduto.setEnabled(false);
		tfMedidaAtualizarProduto.setColumns(10);
		tfMedidaAtualizarProduto.setBounds(247, 165, 76, 24);
		lpAtualizarProduto.add(tfMedidaAtualizarProduto);

		JLabel lblDataDeFabricao = new JLabel("Data de Fabricação:");
		lblDataDeFabricao.setBounds(397, 87, 140, 20);
		lpAtualizarProduto.add(lblDataDeFabricao);

		JLabel lblPreo_1_1 = new JLabel("Data de Vencimento:");
		lblPreo_1_1.setBounds(397, 141, 140, 20);
		lpAtualizarProduto.add(lblPreo_1_1);

		cbUnidadeAtualizarProduto = new JComboBox(unidades);
		cbUnidadeAtualizarProduto.setEnabled(false);
		cbUnidadeAtualizarProduto.setBounds(333, 165, 54, 24);
		lpAtualizarProduto.add(cbUnidadeAtualizarProduto);

		epDescricaoAtualizarProduto = new JEditorPane();
		epDescricaoAtualizarProduto.setEnabled(false);
		epDescricaoAtualizarProduto.setBounds(48, 108, 187, 81);
		lpAtualizarProduto.add(epDescricaoAtualizarProduto);

		spQuantidadeAtualizarProduto = new JSpinner();
		spQuantidadeAtualizarProduto.setEnabled(false);
		spQuantidadeAtualizarProduto.setBounds(473, 52, 64, 24);
		lpAtualizarProduto.add(spQuantidadeAtualizarProduto);

		JLabel lblNewLabel_2_2 = new JLabel("Grupo:");
		lblNewLabel_2_2.setBounds(247, 85, 140, 20);
		lpAtualizarProduto.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_2_1 = new JLabel("Quantidade:");
		lblNewLabel_2_2_1.setBounds(473, 33, 76, 20);
		lpAtualizarProduto.add(lblNewLabel_2_2_1);

		JLabel lblNewLabel_2_2_2 = new JLabel("Medida:");
		lblNewLabel_2_2_2.setBounds(247, 142, 88, 20);
		lpAtualizarProduto.add(lblNewLabel_2_2_2);

		JLabel lblNewLabel_2_2_2_1 = new JLabel("Unidade:");
		lblNewLabel_2_2_2_1.setBounds(333, 142, 54, 20);
		lpAtualizarProduto.add(lblNewLabel_2_2_2_1);

		JLabel lblPesquisarId_2_2 = new JLabel("Pesquisar pelo ID:");
		lblPesquisarId_2_2.setBounds(393, 11, 182, 25);
		painelAtualizarProduto.add(lblPesquisarId_2_2);

		tfIdAtualizarProduto.setText("0");
		tfIdAtualizarProduto.setColumns(10);
		tfIdAtualizarProduto.setBounds(393, 36, 86, 23);
		painelAtualizarProduto.add(tfIdAtualizarProduto);

		btnBuscarProduto = new JButton("Buscar");
		btnBuscarProduto.setBounds(489, 35, 86, 25);
		painelAtualizarProduto.add(btnBuscarProduto);

		btnAtualizarProduto = new JButton("Atualizar");
		btnAtualizarProduto.setEnabled(false);
		btnAtualizarProduto.setBounds(504, 406, 99, 34);
		painelAtualizarProduto.add(btnAtualizarProduto);

		btnLimparAtualizarProduto = new JButton("Limpar");
		btnLimparAtualizarProduto.setEnabled(false);
		btnLimparAtualizarProduto.setBounds(393, 406, 105, 34);
		painelAtualizarProduto.add(btnLimparAtualizarProduto);

		btnResetarAtualizarProduto = new JButton("Resetar");
		btnResetarAtualizarProduto.setEnabled(false);
		btnResetarAtualizarProduto.setBounds(306, 35, 77, 25);
		painelAtualizarProduto.add(btnResetarAtualizarProduto);

		JLabel label_4 = new JLabel("Subtotal / Quantidade máxima do grupo");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(306, 305, 295, 20);
		painelAtualizarProduto.add(label_4);

		tfSubtotalAtualizar = new JTextField();
		tfSubtotalAtualizar.setHorizontalAlignment(SwingConstants.CENTER);
		tfSubtotalAtualizar.setEnabled(false);
		tfSubtotalAtualizar.setEditable(false);
		tfSubtotalAtualizar.setColumns(10);
		tfSubtotalAtualizar.setBounds(369, 328, 76, 24);
		painelAtualizarProduto.add(tfSubtotalAtualizar);

		tfQtdMaxAtualizar = new JTextField();
		tfQtdMaxAtualizar.setHorizontalAlignment(SwingConstants.CENTER);
		tfQtdMaxAtualizar.setEnabled(false);
		tfQtdMaxAtualizar.setEditable(false);
		tfQtdMaxAtualizar.setColumns(10);
		tfQtdMaxAtualizar.setBounds(467, 328, 76, 24);
		painelAtualizarProduto.add(tfQtdMaxAtualizar);

		// ADICIONAR CAMPOS DE DELETAR PRODUTO NESSE PANEL
		JPanel painelInativarProduto = new JPanel();
		tpProdutos.addTab("Inativar Produto",
				new ImageIcon(getClass().getResource("/sage_icons/cart_minus_round [#1162].png")),
				painelInativarProduto, null);
		painelInativarProduto.setLayout(null);

		JLayeredPane lpDeletarProduto = new JLayeredPane();
		lpDeletarProduto
				.setBorder(new TitledBorder(null, "Dados do Produto", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		lpDeletarProduto.setBounds(20, 71, 583, 221);
		painelInativarProduto.add(lpDeletarProduto);

		dcFabricacaoInativarProduto = new JDateChooser();
		dcFabricacaoInativarProduto.setEnabled(false);
		dcFabricacaoInativarProduto.setForeground(SystemColor.menu);
		dcFabricacaoInativarProduto.setBounds(397, 106, 140, 24);
		lpDeletarProduto.add(dcFabricacaoInativarProduto);

		dcVencimentoInativarProduto = new JDateChooser();
		dcVencimentoInativarProduto.setEnabled(false);
		dcVencimentoInativarProduto.setForeground(SystemColor.menu);
		dcVencimentoInativarProduto.setBounds(397, 165, 140, 24);
		lpDeletarProduto.add(dcVencimentoInativarProduto);

		JLabel lblNewLabel_6 = new JLabel("Nome:");
		lblNewLabel_6.setBounds(48, 33, 189, 20);
		lpDeletarProduto.add(lblNewLabel_6);

		tfNomeGrupoInativar = new JTextField();
		tfNomeGrupoInativar.setEditable(false);
		tfNomeGrupoInativar.setEnabled(false);
		tfNomeGrupoInativar.setColumns(10);
		tfNomeGrupoInativar.setBounds(48, 52, 189, 24);
		lpDeletarProduto.add(tfNomeGrupoInativar);

		JLabel lblNewLabel_2_4 = new JLabel("Descrição do Produto:");
		lblNewLabel_2_4.setBounds(48, 85, 189, 20);
		lpDeletarProduto.add(lblNewLabel_2_4);

		tfCodigoGrupoInativar = new JFormattedTextField();
		tfCodigoGrupoInativar.setEditable(false);
		tfCodigoGrupoInativar.setEnabled(false);
		tfCodigoGrupoInativar.setBounds(247, 52, 100, 24);
		lpDeletarProduto.add(tfCodigoGrupoInativar);

		JLabel lblCdigo_2 = new JLabel("Código:");
		lblCdigo_2.setBounds(247, 33, 100, 20);
		lpDeletarProduto.add(lblCdigo_2);

		JLabel lblPreo_2 = new JLabel("Preço:");
		lblPreo_2.setBounds(357, 33, 94, 20);
		lpDeletarProduto.add(lblPreo_2);

		tfPrecoInativarProduto = new JFormattedTextField();
		tfPrecoInativarProduto.setEditable(false);
		tfPrecoInativarProduto.setEnabled(false);
		tfPrecoInativarProduto.setBounds(357, 52, 94, 24);
		lpDeletarProduto.add(tfPrecoInativarProduto);

		cbGrupoInativar = new JComboBox(vazio.toArray());
		cbGrupoInativar.setEnabled(false);
		cbGrupoInativar.setBounds(247, 106, 140, 24);
		lpDeletarProduto.add(cbGrupoInativar);

		tfMedidaInativarProduto = new JTextField();
		tfMedidaInativarProduto.setEditable(false);
		tfMedidaInativarProduto.setEnabled(false);
		tfMedidaInativarProduto.setColumns(10);
		tfMedidaInativarProduto.setBounds(247, 165, 76, 24);
		lpDeletarProduto.add(tfMedidaInativarProduto);

		JLabel lblDataDeFabricao_2 = new JLabel("Data de Fabricação:");
		lblDataDeFabricao_2.setBounds(397, 87, 140, 20);
		lpDeletarProduto.add(lblDataDeFabricao_2);

		JLabel lblPreo_1_1_2 = new JLabel("Data de Vencimento:");
		lblPreo_1_1_2.setBounds(397, 141, 140, 20);
		lpDeletarProduto.add(lblPreo_1_1_2);

		cbUnidadeInativarProduto = new JComboBox(unidades);
		cbUnidadeInativarProduto.setEnabled(false);
		cbUnidadeInativarProduto.setBounds(333, 165, 54, 24);
		lpDeletarProduto.add(cbUnidadeInativarProduto);

		epDescricaoInativarProduto = new JEditorPane();
		epDescricaoInativarProduto.setEditable(false);
		epDescricaoInativarProduto.setEnabled(false);
		epDescricaoInativarProduto.setBounds(48, 108, 187, 81);
		lpDeletarProduto.add(epDescricaoInativarProduto);

		spQuantidadeInativarProduto = new JSpinner();
		spQuantidadeInativarProduto.setEnabled(false);
		spQuantidadeInativarProduto.setBounds(473, 52, 64, 24);
		lpDeletarProduto.add(spQuantidadeInativarProduto);

		JLabel lblNewLabel_2_2_4 = new JLabel("Grupo:");
		lblNewLabel_2_2_4.setBounds(247, 85, 140, 20);
		lpDeletarProduto.add(lblNewLabel_2_2_4);

		JLabel lblNewLabel_2_2_1_2 = new JLabel("Quantidade:");
		lblNewLabel_2_2_1_2.setBounds(473, 33, 76, 20);
		lpDeletarProduto.add(lblNewLabel_2_2_1_2);

		JLabel lblNewLabel_2_2_2_3 = new JLabel("Medida:");
		lblNewLabel_2_2_2_3.setBounds(247, 142, 88, 20);
		lpDeletarProduto.add(lblNewLabel_2_2_2_3);

		JLabel lblNewLabel_2_2_2_1_2 = new JLabel("Unidade:");
		lblNewLabel_2_2_2_1_2.setBounds(333, 142, 54, 20);
		lpDeletarProduto.add(lblNewLabel_2_2_2_1_2);

		tfIdInativarProduto = new JFormattedTextField();
		tfIdInativarProduto.setText("0");
		tfIdInativarProduto.setColumns(10);
		tfIdInativarProduto.setBounds(393, 36, 86, 23);
		painelInativarProduto.add(tfIdInativarProduto);

		btnBuscarProdutoInativar = new JButton("Buscar");
		btnBuscarProdutoInativar.setBounds(489, 35, 86, 25);
		painelInativarProduto.add(btnBuscarProdutoInativar);

		JLabel lblPesquisarId_2_2_1 = new JLabel("Pesquisar pelo ID:");
		lblPesquisarId_2_2_1.setBounds(393, 11, 182, 25);
		painelInativarProduto.add(lblPesquisarId_2_2_1);

		btnResetarInativarProduto = new JButton("Resetar");
		btnResetarInativarProduto.setEnabled(false);
		btnResetarInativarProduto.setBounds(306, 35, 77, 25);
		painelInativarProduto.add(btnResetarInativarProduto);

		btnLimparInativarProduto = new JButton("Limpar");
		btnLimparInativarProduto.setEnabled(false);
		btnLimparInativarProduto.setBounds(393, 403, 105, 34);
		painelInativarProduto.add(btnLimparInativarProduto);

		btnInativarProduto = new JButton("Inativar");
		btnInativarProduto.setEnabled(false);
		btnInativarProduto.setBounds(504, 403, 99, 34);
		painelInativarProduto.add(btnInativarProduto);

		JPanel painelAreaGrupo = new JPanel();
		tabbedPane_area.addTab("Área do Grupo", null, painelAreaGrupo, null);
		painelAreaGrupo.setLayout(null);

		JTabbedPane tpGrupo = new JTabbedPane(JTabbedPane.TOP);
		tpGrupo.setBounds(0, 0, 618, 525);
		painelAreaGrupo.add(tpGrupo);

		JPanel painelCadastrarGrupo = new JPanel();
		tpGrupo.addTab("Cadastrar Grupo", null, painelCadastrarGrupo, null);
		painelCadastrarGrupo.setLayout(null);

		JLayeredPane lpCadastrarGrupo = new JLayeredPane();
		lpCadastrarGrupo.setBorder(
				new TitledBorder(null, "Dados do grupo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lpCadastrarGrupo.setBounds(26, 73, 557, 225);
		painelCadastrarGrupo.add(lpCadastrarGrupo);

		JLabel lblNewLabel_7 = new JLabel("Nome");
		lblNewLabel_7.setBounds(28, 33, 227, 14);
		lpCadastrarGrupo.add(lblNewLabel_7);

		tfNomeCadastrarGrupo = new JTextField();
		tfNomeCadastrarGrupo.setBounds(28, 58, 227, 26);
		lpCadastrarGrupo.add(tfNomeCadastrarGrupo);
		tfNomeCadastrarGrupo.setColumns(10);

		JLabel lblNewLabel_7_1 = new JLabel("Quantidade Máxima");
		lblNewLabel_7_1.setBounds(265, 33, 127, 14);
		lpCadastrarGrupo.add(lblNewLabel_7_1);

		JSpinner spQtdMaxCadastrarGrupo = new JSpinner();
		spQtdMaxCadastrarGrupo.setBounds(265, 58, 127, 26);
		lpCadastrarGrupo.add(spQtdMaxCadastrarGrupo);

		JLabel lblNewLabel_7_1_1 = new JLabel("Quantidade Mínima");
		lblNewLabel_7_1_1.setBounds(402, 33, 127, 14);
		lpCadastrarGrupo.add(lblNewLabel_7_1_1);

		JSpinner spQtdMinCadastrarGrupo = new JSpinner();
		spQtdMinCadastrarGrupo.setBounds(402, 58, 127, 26);
		lpCadastrarGrupo.add(spQtdMinCadastrarGrupo);

		JLabel lblNewLabel_7_2 = new JLabel("Descrição");
		lblNewLabel_7_2.setBounds(28, 95, 227, 14);
		lpCadastrarGrupo.add(lblNewLabel_7_2);

		JLabel lblNewLabel_7_2_1 = new JLabel("Categoria");
		lblNewLabel_7_2_1.setBounds(265, 95, 127, 14);
		lpCadastrarGrupo.add(lblNewLabel_7_2_1);

		JComboBox cbCategoriaCadastrarGrupo = new JComboBox();
		cbCategoriaCadastrarGrupo.setBounds(265, 115, 127, 26);
		lpCadastrarGrupo.add(cbCategoriaCadastrarGrupo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 115, 227, 87);
		lpCadastrarGrupo.add(scrollPane);

		JTextPane epDescricaoCadastrarGrupo = new JTextPane();
		scrollPane.setViewportView(epDescricaoCadastrarGrupo);

		JButton btnLimparCadastrarGrupo = new JButton("Limpar");
		btnLimparCadastrarGrupo.setBounds(389, 435, 105, 34);
		painelCadastrarGrupo.add(btnLimparCadastrarGrupo);

		JButton btnCadastrarGrupo = new JButton("Cadastrar");
		btnCadastrarGrupo.setBounds(504, 435, 99, 34);
		painelCadastrarGrupo.add(btnCadastrarGrupo);

		JTabbedPane tabbedPane_listagem = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_listagem.setBounds(652, 49, 548, 475);
		painelGeral.add(tabbedPane_listagem);

		JPanel painelListarProdutos = new JPanel();
		tabbedPane_listagem.addTab("Produtos", null, painelListarProdutos, null);
		painelListarProdutos.setLayout(null);

		JPanel painelListarMovimentacoes = new JPanel();
		tabbedPane_listagem.addTab("Movimentações", null, painelListarMovimentacoes, null);
		painelListarMovimentacoes.setLayout(null);

		JScrollPane spProdutos = new JScrollPane();
		spProdutos.setBounds(10, 11, 517, 425);
		painelListarProdutos.add(spProdutos);

		JPanel painelListarSupervisores = new JPanel();
		tabbedPane_listagem.addTab("Supervisores", null, painelListarSupervisores, null);
		painelListarSupervisores.setLayout(null);

		JPanel painelListarRegistroSupervisores = new JPanel();
		painelListarRegistroSupervisores.setLayout(null);
		tabbedPane_listagem.addTab("Supervisores - registros", null, painelListarRegistroSupervisores, null);

		JScrollPane spSupervisores = new JScrollPane();
		spSupervisores.setBounds(10, 11, 517, 425);
		painelListarSupervisores.add(spSupervisores);
		table_supervisores = new JTable();
		spSupervisores.setViewportView(table_supervisores);

		JScrollPane spMovimentacoes = new JScrollPane();
		spMovimentacoes.setBounds(10, 11, 517, 425);
		painelListarMovimentacoes.add(spMovimentacoes);

		table_produtos = new JTable();
		table_movimentacoes = new JTable();
		spMovimentacoes.setViewportView(table_movimentacoes);
		spProdutos.setViewportView(table_produtos);

		JPanel painelListarFornecedores = new JPanel();
		tabbedPane_listagem.addTab("Fornecedores", null, painelListarFornecedores, null);
		painelListarFornecedores.setLayout(null);

		JScrollPane spFornecedores = new JScrollPane();
		spFornecedores.setBounds(10, 11, 517, 425);
		painelListarFornecedores.add(spFornecedores);

		table_fornecedores = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		// ControllerTableModels.popularTabelaFornecedores(table_fornecedores);
		spFornecedores.setViewportView(table_fornecedores);

		JScrollPane spRegistroSupervisores = new JScrollPane();
		spRegistroSupervisores.setBounds(10, 11, 517, 425);
		painelListarRegistroSupervisores.add(spRegistroSupervisores);

		table_registros_supervisores = new JTable();
		// ControllerTableModels.popularTableRegistrosGerente(table_registros_supervisores_supervisores);
		spRegistroSupervisores.setViewportView(table_registros_supervisores);

		JSeparator separator_vertical = new JSeparator();
		separator_vertical.setOrientation(SwingConstants.VERTICAL);
		separator_vertical.setBounds(633, 0, 11, 553);
		painelGeral.add(separator_vertical);

		JButton btnAtualizarListas = new JButton("Atualizar listas");
		btnAtualizarListas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctb = new ControllerTableModels(table_registros_supervisores, table_fornecedores, table_produtos,
						table_movimentacoes, table_supervisores);
				new Atualizar().start();
			}
		});
		btnAtualizarListas.setBounds(652, 11, 125, 27);
		painelGeral.add(btnAtualizarListas);

		progressBar_tabelas_superior = new JProgressBar();
		progressBar_tabelas_superior.setBounds(799, 21, 385, 10);
		painelGeral.add(progressBar_tabelas_superior);

		new ControllerTableModels(table_registros_supervisores, table_fornecedores, table_produtos, table_movimentacoes,
				table_supervisores);

		// ControllerTableModels.popularTabelaProdutos(table_produtos);
		// new ControllerTableModels(table_registros_supervisores_supervisores,
		// table_gerentes,
		// table_fornecedores, table_produtos, table_movimentacoes);
		// setLocationRelativeTo(null);

		// ----------ActionListeners de Cadastrar o Produto

		// MÉTODO DE PREENCHER OS DADOS DO GRUPO DA TELA DE CADASTRAR PRODUTOS BASEADO
		// NO GRUPO ESCOLHIDO
		cbGrupoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbGrupoCadastrar.getSelectedIndex() != 0) {
					ControllerAuxiliar.preencherCamposGrupo(cbGrupoCadastrar, tfNomeGrupoCadastrar,
							tfCodigoGrupoCadastrar, tfSubtotal, tfQtdMax);
				} else {
					ControllerAuxiliar.resetarCamposGrupoProduto(tfNomeGrupoCadastrar, tfCodigoGrupoCadastrar,
							tfSubtotal, tfQtdMax);
				}
			}
		});

		// MÉTODO QUE PEGA OS DADOS DOS INPUTS E CHAMA O CONTROLLER PARA CADASTRAR O
		// PRODUTO
		btnCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (ctrlPermissao.chamarVerificacao() == true) {
					ControllerValidationProduto cvp = new ControllerValidationProduto();
					cvp.enviarDadosParaCadastrar(tfPrecoCadastrarProduto, spQuantidadeCadastrarProduto,
							epDescricaoCadastrarProduto, dcFabricacaoCadastrarProduto, dcVencimentoCadastrarProduto,
							cbGrupoCadastrar, tfMedidaGrupoCadastrar, cbUnidadeCadastrarProduto,
							epDescricaoMovimentacaoCadastro, cbFornecedorCadastro);
				}
			}
		});

		// MÉTODO DE LIMPAR OS DADOS DA TELA DE CADASTRAR PRODUTOS
		btnLimparCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControllerAuxiliar.resetarTodosOsCampos(tfPrecoCadastrarProduto, spQuantidadeCadastrarProduto,
						epDescricaoCadastrarProduto, dcFabricacaoCadastrarProduto, dcVencimentoCadastrarProduto,
						cbGrupoCadastrar, tfMedidaGrupoCadastrar, cbUnidadeCadastrarProduto);
				ControllerMovimentacao.limparNoCadastro(epDescricaoMovimentacaoCadastro, cbFornecedorCadastro);
			}
		});

		// ------------------ActionListeners de Atualizar o Produto
		// MÉTODO DE PREENCHER OS DADOS DO PRODUTO NA TELA DE ATUALIZAR BASEADO NO ID
		btnBuscarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerAtualizarProduto cap = new ControllerAtualizarProduto();
				cap.buscarProduto(btnBuscarProduto, btnResetarAtualizarProduto, tfIdAtualizarProduto,
						tfPrecoAtualizarProduto, spQuantidadeAtualizarProduto, epDescricaoAtualizarProduto,
						cbGrupoAtualizar, tfMedidaAtualizarProduto, cbUnidadeAtualizarProduto,
						dcFabricacaoAtualizarProduto, dcVencimentoAtualizarProduto, tfSubtotalAtualizar,
						tfQtdMaxAtualizar, btnLimparAtualizarProduto, btnAtualizarProduto);
			}
		});

		// MÉTODO DE PREENCHER DADOS DO GRUPO DA TELA DE ATUALIZAR PRODUTOS BASEADO NO
		// GRUPO ESCOLHIDO
		cbGrupoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbGrupoAtualizar.getSelectedIndex() != 0) {
					ControllerAuxiliar.preencherCamposGrupo(cbGrupoAtualizar, tfNomeGrupoAtualizar,
							tfCodigoGrupoAtualizar, tfSubtotalAtualizar, tfQtdMaxAtualizar);
				} else {
					ControllerAuxiliar.resetarCamposGrupoProduto(tfNomeGrupoAtualizar, tfCodigoGrupoAtualizar,
							tfSubtotalAtualizar, tfQtdMaxAtualizar);
				}
			}
		});

		// BOTÃO DE ATUALIZAR O PRODUTO
		btnAtualizarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ctrlPermissao.chamarVerificacao() == true) {
					ControllerAtualizarProduto cap = new ControllerAtualizarProduto();
					cap.atualizarProduto(tfIdAtualizarProduto, tfPrecoAtualizarProduto, spQuantidadeAtualizarProduto,
							epDescricaoAtualizarProduto, cbGrupoAtualizar, tfMedidaAtualizarProduto,
							cbUnidadeAtualizarProduto, dcFabricacaoAtualizarProduto, dcVencimentoAtualizarProduto);
				}

			}
		});

		// BOTÃO DE LIMPAR NA TELA DE ATUALIZAR PRODUTO
		btnLimparAtualizarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControllerAuxiliar.resetarTodosOsCampos(tfPrecoAtualizarProduto, spQuantidadeAtualizarProduto,
						epDescricaoAtualizarProduto, dcFabricacaoAtualizarProduto, dcVencimentoAtualizarProduto,
						cbGrupoAtualizar, tfMedidaAtualizarProduto, cbUnidadeAtualizarProduto);
			}
		});

		// BOTÃO DE RESETAR O ID E DESABILITAR OS CAMPOS DA TELA DE ATUALIZAR PRODUTOS
		btnResetarAtualizarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerAtualizarProduto cap = new ControllerAtualizarProduto();
				cap.desabilitarAtualizacao(btnBuscarProduto, btnResetarAtualizarProduto, tfIdAtualizarProduto,
						tfPrecoAtualizarProduto, spQuantidadeAtualizarProduto, epDescricaoAtualizarProduto,
						cbGrupoAtualizar, tfMedidaAtualizarProduto, cbUnidadeAtualizarProduto,
						dcFabricacaoAtualizarProduto, dcVencimentoAtualizarProduto, tfSubtotal, tfQtdMax,
						btnLimparAtualizarProduto, btnAtualizarProduto);
			}
		});

		// ------------------ActionListeners de Inativar o Produto
		btnBuscarProdutoInativar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerInativarProduto cip = new ControllerInativarProduto();
				cip.buscarProdutoInativado(btnBuscarProdutoInativar, btnResetarInativarProduto, tfIdInativarProduto,
						tfPrecoInativarProduto, spQuantidadeInativarProduto, epDescricaoInativarProduto,
						cbGrupoInativar, tfMedidaInativarProduto, cbUnidadeInativarProduto, dcFabricacaoInativarProduto,
						dcVencimentoInativarProduto, btnLimparInativarProduto, btnInativarProduto);
			}
		});

		btnResetarInativarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerInativarProduto cip = new ControllerInativarProduto();
				cip.desabilitarInativacao(btnBuscarProdutoInativar, btnResetarInativarProduto, tfIdInativarProduto,
						tfPrecoInativarProduto, spQuantidadeInativarProduto, epDescricaoInativarProduto,
						cbGrupoInativar, tfMedidaInativarProduto, cbUnidadeInativarProduto, dcFabricacaoInativarProduto,
						dcVencimentoInativarProduto, btnLimparInativarProduto, btnInativarProduto);
			}
		});

		cbGrupoInativar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbGrupoInativar.getSelectedIndex() != 0) {
					ControllerAuxiliar.preencherCamposGrupo(cbGrupoInativar, tfNomeGrupoInativar, tfCodigoGrupoInativar,
							tfSubtotalAtualizar, tfQtdMaxAtualizar);
				} else {
					ControllerAuxiliar.resetarCamposGrupoProduto(tfNomeGrupoInativar, tfCodigoGrupoInativar,
							tfSubtotalAtualizar, tfQtdMaxAtualizar);
				}
			}
		});

		btnInativarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerInativarProduto cip = new ControllerInativarProduto();
				cip.inativarProduto(tfIdInativarProduto, tfPrecoInativarProduto, spQuantidadeInativarProduto,
						epDescricaoInativarProduto, cbGrupoInativar, tfMedidaInativarProduto, cbUnidadeInativarProduto,
						dcFabricacaoInativarProduto, dcVencimentoInativarProduto);
			}
		});

		// ------------------ActionListeners de Cadastrar Fornecedores

		// ESSE BOTÃO CHAMA O MÉTODO DE CADASTRAR O FORNECEDOR
		btnCadastrarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ctrlPermissao.chamarVerificacao() == true) {
					ControllerValidationFornecedor cvf = new ControllerValidationFornecedor();
					cvf.enviarDadosParaCadastro(tfNomeCadastrarFornecedor, tfCnpjCadastrarFornecedor,
							tfRazaoSocialCadastrarFornecedor, tfTelefoneCadastrarFornecedor, tfEmailCadastrarFornecedor,
							tfCepCadastrarFornecedor, cbEstadoCadastrarFornecedor, tfCidadeCadastrarFornecedor,
							tfBairroCadastrarFornecedor, tfNumeroCadastrarFornecedor, tfLogradouroCadastrarFornecedor,
							tfComplementoCadastrarFornecedor);
				}

			}
		});

		// ESSE BOTÃO CHAMA O MÉTODO DE LIMPAR OS DADOS DO FORNECEDOR NA SUA VIEW DE
		// CADASTRO
		btnLimparCadastrarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerAuxiliar.limparCamposFornecedor(tfNomeCadastrarFornecedor, tfCnpjCadastrarFornecedor,
						tfRazaoSocialCadastrarFornecedor, tfTelefoneCadastrarFornecedor, tfEmailCadastrarFornecedor,
						tfCepCadastrarFornecedor, cbEstadoCadastrarFornecedor, tfCidadeCadastrarFornecedor,
						tfBairroCadastrarFornecedor, tfNumeroCadastrarFornecedor, tfLogradouroCadastrarFornecedor,
						tfComplementoCadastrarFornecedor);
			}
		});

		// ------------------ActionListeners de Atualizar Fornecedores

		// ESSE BOTÃO CHAMA O MÉTODO DE BUSCAR O FORNECEDOR PELO ID
		btnBuscarAtualizarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerAtualizarFornecedor caf = new ControllerAtualizarFornecedor();
				caf.buscarFornecedor(btnBuscarAtualizarFornecedor, btnResetarAtualizarFornecedor,
						tfIdAtualizarFornecedor, tfNomeAtualizarFornecedor, tfCnpjAtualizarFornecedor,
						tfRazaoSocialAtualizarFornecedor, tfTelefoneAtualizarFornecedor, tfEmailAtualizarFornecedor,
						tfCepAtualizarFornecedor, cbEstadoAtualizarFornecedor, tfCidadeAtualizarFornecedor,
						tfBairroAtualizarFornecedor, tfNumeroAtualizarFornecedor, tfLogradouroAtualizarFornecedor,
						tfComplementoAtualizarFornecedor, btnLimparAtualizarFornecedor, btnAtualizarFornecedor);
			}
		});

		// ESSE BOTÃO CHAMA O MÉTODO DE RESETAR OS INPUTS DA VIEW DE ATUALIZAR O
		// FORNECEDOR
		btnResetarAtualizarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerAtualizarFornecedor caf = new ControllerAtualizarFornecedor();
				caf.desabilitarAtualizacaoFornecedor(btnBuscarAtualizarFornecedor, btnResetarAtualizarFornecedor,
						tfIdAtualizarFornecedor, tfNomeAtualizarFornecedor, tfCnpjAtualizarFornecedor,
						tfRazaoSocialAtualizarFornecedor, tfTelefoneAtualizarFornecedor, tfEmailAtualizarFornecedor,
						tfCepAtualizarFornecedor, cbEstadoAtualizarFornecedor, tfCidadeAtualizarFornecedor,
						tfBairroAtualizarFornecedor, tfNumeroAtualizarFornecedor, tfLogradouroAtualizarFornecedor,
						tfComplementoAtualizarFornecedor, btnLimparAtualizarFornecedor, btnAtualizarFornecedor);
			}
		});

		// ESSE BOTÃO CHAMA O MÉTODO DE LIMPAR OS INPUTS NA TELA DE ATUALIZAR O
		// FORNECEDOR
		btnLimparAtualizarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerAuxiliar.limparCamposFornecedor(tfNomeAtualizarFornecedor, tfCnpjAtualizarFornecedor,
						tfRazaoSocialAtualizarFornecedor, tfTelefoneAtualizarFornecedor, tfEmailAtualizarFornecedor,
						tfCepAtualizarFornecedor, cbEstadoAtualizarFornecedor, tfCidadeAtualizarFornecedor,
						tfBairroAtualizarFornecedor, tfNumeroAtualizarFornecedor, tfLogradouroAtualizarFornecedor,
						tfComplementoAtualizarFornecedor);
			}
		});

		// ESSE BOTÃO CHAMA O MÉTODO DE ATUALIZAR O FORNECEDOR
		btnAtualizarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ctrlPermissao.chamarVerificacao() == true) {
					ControllerAtualizarFornecedor caf = new ControllerAtualizarFornecedor();
					caf.atualizarFornecedor(tfIdAtualizarFornecedor, tfNomeAtualizarFornecedor,
							tfCnpjAtualizarFornecedor, tfRazaoSocialAtualizarFornecedor, tfTelefoneAtualizarFornecedor,
							tfEmailAtualizarFornecedor, tfCepAtualizarFornecedor, cbEstadoAtualizarFornecedor,
							tfCidadeAtualizarFornecedor, tfBairroAtualizarFornecedor, tfNumeroAtualizarFornecedor,
							tfLogradouroAtualizarFornecedor, tfComplementoAtualizarFornecedor);
				}

			}
		});

		// ESSES MÉTODOS IRÃO PERMITIR APENAS NÚMEROS PARA SEUS RESPECTIVOS CAMPOS
		id_pesquisa_deleteSupervisor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				id_pesquisa_deleteSupervisor.setText(id_pesquisa_deleteSupervisor.getText().replaceAll("[^0-9]", "0"));
			}
		});

		id_pesquisa_supervisor_AtualizacaoSupervisor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				id_pesquisa_supervisor_AtualizacaoSupervisor
						.setText(id_pesquisa_supervisor_AtualizacaoSupervisor.getText().replaceAll("[^0-9]", "0"));
			}
		});

		// MÉTODO DE MOSTRAR OS DADOS DOS CAMPOS DO PAINEL DE DELEÇÃO DO SUPERVISOR

		botao_pesquisa_deleteSupervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ctrlPermissao.chamarVerificacao() == true) {
					ctrlSuper.mostrarDadosDoSupervisor(id_pesquisa_deleteSupervisor, cpf_supervisor_deleteSupervisor,
							login_supervisor_deleteSupervisor);
				}

			}
		});
		// MÉTODO DE LIMPAR OS DADOS DOS CAMPOS DO PAINEL DE DELEÇÃO DO SUPERVISOR

		botao_limpar_dados_deleteSupervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlAux.limparCampos(cpf_gerente_deleteSupervisor, senha_gerente_deleteSupervisor,
						cpf_supervisor_deleteSupervisor, id_pesquisa_deleteSupervisor,
						login_supervisor_deleteSupervisor, senha_gerente_deleteSupervisor);

			}
		});

		// MÉTODO DE DELEÇÃO DO SUPERVISOR
		botao_deletar_deleteSupervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ctrlPermissao.chamarVerificacao() == true) {
					ctrlSuper.excluirContaSupervisor(id_pesquisa_deleteSupervisor,
							cpf_gerente_deleteSupervisor.getText(), senha_gerente_deleteSupervisor.getText());

					ctrlAux.limparCampos(cpf_gerente_deleteSupervisor, senha_gerente_deleteSupervisor,
							cpf_supervisor_deleteSupervisor, id_pesquisa_deleteSupervisor,
							login_supervisor_deleteSupervisor, senha_gerente_deleteSupervisor);
				}
			}
		});

		// MÉTODO DE LIMPAR OS CAMPOS NO PAINEL DE CADASTRO DO SUPERVISOR

		limpar_dados_criarSupervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrlAux.limparCampos(cpf_supervisor_criarSupervisor, confirmacaoSenha_supervisor_criarSupervisor,
						senha_supervisor_criarSupervisor, login_supervisor_criarSupervisor);
			}
		});

		// MÉTODO PARA O CADASTRO DE SUPERVISOR

		btn_cadastrar_dados_supervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ctrlPermissao.chamarVerificacao() == true) {
					ctrlSuper.cadastrarSupervisor(cpf_supervisor_criarSupervisor.getText(),
							senha_supervisor_criarSupervisor.getText(),
							confirmacaoSenha_supervisor_criarSupervisor.getText(),
							login_supervisor_criarSupervisor.getText(), login_supervisor_criarSupervisor,
							cpf_supervisor_criarSupervisor, senha_supervisor_criarSupervisor,
							confirmacaoSenha_supervisor_criarSupervisor);
				}

			}
		});

		// MÉTODO LIMPAR CAMPOS DO PAINEL DE ATUALIZAÇÃO DO SUPERVISOR

		botao_limpar_dados_AtualizacaoSupervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ctrlAux.limparCampos(cpf_gerente_AtualizacaoSupervisor, id_pesquisa_supervisor_AtualizacaoSupervisor,
						nova_senha_supervisor_AtualizacaoSupervisor, senha_gerente_AtualizacaoSupervisor,
						cpf_atual_supervisor_AtualizacaoSupervisor, login_atual_supervisor_AtualizacaoSupervisor,
						novo_login_supervisor_AtualizacaoSupervisor);

			}
		});

		// MÉTODO QUE MOSTRAr OS DADOS DO SUPERVISOR NO PAINEL DE ATUALIZAÇÃO DE
		// SUPERVISOR

		buscar_supervisor_AtualizacaoSupervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ctrlPermissao.chamarVerificacao() == true) {
					ctrlSuper.mostrarDadosDoSupervisor(id_pesquisa_supervisor_AtualizacaoSupervisor,
							cpf_atual_supervisor_AtualizacaoSupervisor, login_atual_supervisor_AtualizacaoSupervisor);
				}

			}
		});

		// MÉTODO DE ATUALIZAÇÃO DE SENHA DO SUPERVISOR DO PAINEL DE ATUALIZAÇÃO DO
		// SUPERVISOR

		btn_atualizar_senha_AtualizacaoSupervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ctrlPermissao.chamarVerificacao() == true) {
					if (ctrlSuper.atualizarSupervisorSenha(id_pesquisa_supervisor_AtualizacaoSupervisor,
							nova_senha_supervisor_AtualizacaoSupervisor.getText(), cpf_gerente_AtualizacaoSupervisor,
							senha_gerente_AtualizacaoSupervisor) == true) {

						ctrlAux.limparCampos(cpf_gerente_AtualizacaoSupervisor,
								id_pesquisa_supervisor_AtualizacaoSupervisor,
								nova_senha_supervisor_AtualizacaoSupervisor, senha_gerente_AtualizacaoSupervisor,
								cpf_atual_supervisor_AtualizacaoSupervisor,
								login_atual_supervisor_AtualizacaoSupervisor,
								novo_login_supervisor_AtualizacaoSupervisor);
					}
				}
			}
		});

		// MÉTODO DE ATUALIZAÇÃO DO LOGIN DO SUPERVISOR
		btn_atualizar_login_AtualizacaoSupervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (ctrlPermissao.chamarVerificacao() == true) {
					if (ctrlSuper.atualizarSupervisorLogin(id_pesquisa_supervisor_AtualizacaoSupervisor,
							novo_login_supervisor_AtualizacaoSupervisor.getText(), cpf_gerente_AtualizacaoSupervisor,
							senha_gerente_AtualizacaoSupervisor) == true) {

						ctrlAux.limparCampos(cpf_gerente_AtualizacaoSupervisor,
								id_pesquisa_supervisor_AtualizacaoSupervisor,
								nova_senha_supervisor_AtualizacaoSupervisor, senha_gerente_AtualizacaoSupervisor,
								cpf_atual_supervisor_AtualizacaoSupervisor,
								login_atual_supervisor_AtualizacaoSupervisor,
								novo_login_supervisor_AtualizacaoSupervisor);
					}

				}

			}
		});

		// MÉTODO DE ATUALIZAÇÃO DE TODOS OS DADOS DO SUPERVISOR
		btn_atualizar_tudo_AtualizacaoSupervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (ctrlPermissao.chamarVerificacao() == true) {
					if (ctrlSuper.atualizarSupervisor(nova_senha_supervisor_AtualizacaoSupervisor.getText(),
							id_pesquisa_supervisor_AtualizacaoSupervisor,
							novo_login_supervisor_AtualizacaoSupervisor.getText(), cpf_gerente_AtualizacaoSupervisor,
							senha_gerente_AtualizacaoSupervisor) == true) {

						ctrlAux.limparCampos(cpf_gerente_AtualizacaoSupervisor,
								id_pesquisa_supervisor_AtualizacaoSupervisor,
								nova_senha_supervisor_AtualizacaoSupervisor, senha_gerente_AtualizacaoSupervisor,
								cpf_atual_supervisor_AtualizacaoSupervisor,
								login_atual_supervisor_AtualizacaoSupervisor,
								novo_login_supervisor_AtualizacaoSupervisor);

					}

				}
			}
		});

		// MÉTODO DE REGISTRAR A SAÍDA DO USUÁRIO AO CLICAR NO BOTÃO DE SAIR

		menuItemBtnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerGlobal controllerGlob = new ControllerGlobal();
				controllerGlob.registrarSaidaUsuario();
				Janela_login jlogin = new Janela_login();
				jlogin.setVisible(true);
				dispose();
			}
		});

	}

	public class Atualizar extends Thread {
		public void run() {
			try {
				progressBar_tabelas_superior.setStringPainted(true);
				while (progressBar_tabelas_superior.getValue() < 100) {
					sleep(5);
					progressBar_tabelas_superior.setValue(progressBar_tabelas_superior.getValue() + 1);
				}
				sleep(500);
				progressBar_tabelas_superior.setValue(0);
				progressBar_tabelas_superior.setStringPainted(false);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}