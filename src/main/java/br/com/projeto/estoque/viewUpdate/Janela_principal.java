package br.com.projeto.estoque.viewUpdate;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import br.com.projeto.estoque.controller.ControllerTableModels;

public class Janela_principal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTable table_gerentes;
	private JTable table_fornecedores;
	private JTable table_movimentacoes;
	private JTable table_produtos;
	private JTable table_registros;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatDarkLaf());
					Janela_principal frame = new Janela_principal();
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
	public Janela_principal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("New menu");
		mnNewMenu_1.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu_2.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_superior = new JPanel();
		panel_superior.setBounds(0, 0, 1194, 402);
		contentPane.add(panel_superior);
		panel_superior.setLayout(null);
		
		JTabbedPane tabbedPane_area = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_area.setBounds(0, 0, 623, 383);
		panel_superior.add(tabbedPane_area);
		
		JPanel panel_area_gerente = new JPanel();
		tabbedPane_area.addTab("Área do Gerente",null, panel_area_gerente, null);
		panel_area_gerente.setLayout(null);
		
		JTabbedPane tabbedPane_acoes_gerente = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_acoes_gerente.setBounds(0, 0, 618, 355);
		panel_area_gerente.add(tabbedPane_acoes_gerente);
		
		
		
		//ADICIONAR CAMPOS DE CADASTRAR gerente NESSE PANEL
		JPanel panel_add_gerente = new JPanel();
		tabbedPane_acoes_gerente.addTab("Cadastrar Gerente", new ImageIcon(getClass().getResource("/sage_icons/profile_plus_round [#1343].png")), panel_add_gerente, null);
		panel_add_gerente.setLayout(null);
		
		//ADICIONAR CAMPOS DE ATUALIZAR gerente NESSE PANEL
		JPanel panel_update_gerente = new JPanel();
		tabbedPane_acoes_gerente.addTab("Atualizar Gerente",new ImageIcon(getClass().getResource("/sage_icons/profile_round [#1342].png")), panel_update_gerente, null);
		panel_update_gerente.setLayout(null);
		
		//ADICIONAR CAMPOS DE DELETAR gerente NESSE PANEL
		JPanel panel_delete_gerente = new JPanel();
		tabbedPane_acoes_gerente.addTab("Deletar Gerente", new ImageIcon(getClass().getResource("/sage_icons/profile_round [#1346].png")), panel_delete_gerente, null);
		panel_delete_gerente.setLayout(null);
		
		
		
		
		JPanel panel_area_fornecedor = new JPanel();
		tabbedPane_area.addTab("Área do Fornecedor", null, panel_area_fornecedor, null);
		panel_area_fornecedor.setLayout(null);
		
		JTabbedPane tabbedPane_acoes_fornecedor = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_acoes_fornecedor.setBounds(0, 0, 618, 355);
		panel_area_fornecedor.add(tabbedPane_acoes_fornecedor);
		
		
		
		
		//ADICIONAR CAMPOS DE CADASTRAR FORNECEDOR NESSE PANEL
		JPanel panel_add_fornecedor = new JPanel();
		tabbedPane_acoes_fornecedor.addTab("Cadastrar Fornecedor",  new ImageIcon(getClass().getResource("/sage_icons/profile_image_plus_round [#1327].png")), panel_add_fornecedor, null);
		panel_add_fornecedor.setLayout(null);
		
		//ADICIONAR CAMPOS DE ALTERAR FORNECEDOR NESSE PANEL
		JPanel panel_update_fornecedor = new JPanel();
		tabbedPane_acoes_fornecedor.addTab("Atualizar Fornecedor",  new ImageIcon(getClass().getResource("/sage_icons/profile_image_round [#1326].png")), panel_update_fornecedor, null);
		panel_update_fornecedor.setLayout(null);
		
		//ADICIONAR CAMPOS DE DELETAR FORNECEDOR NESSE PANEL
		JPanel panel_delete_fornecedor = new JPanel();
		tabbedPane_acoes_fornecedor.addTab("Deletar Fornecedor",  new ImageIcon(getClass().getResource("/sage_icons/profile_image_minus_round [#1329].png")), panel_delete_fornecedor, null);
		panel_delete_fornecedor.setLayout(null);
		
		
		
		
		JPanel panel_area_produto = new JPanel();
		tabbedPane_area.addTab("Área do Produto", null, panel_area_produto, null);
		panel_area_produto.setLayout(null);
		
		JTabbedPane tabbedPane_acoes_produto = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_acoes_produto.setBounds(0, 0, 618, 355);
		panel_area_produto.add(tabbedPane_acoes_produto);
		
		
		
		
		//ADICIONAR CAMPOS DE CADASTRAR PRODUTO NESSE PANEL
		JPanel panel_add_produto = new JPanel();
		tabbedPane_acoes_produto.addTab("Cadastrar Produto",  new ImageIcon(getClass().getResource("/sage_icons/cart_plus_round [#1158].png")), panel_add_produto, null);
		panel_add_produto.setLayout(null);
		
		//ADICIONAR CAMPOS DE ALTERAR PRODUTO NESSE PANEL
		JPanel panel_update_produto = new JPanel();
		tabbedPane_acoes_produto.addTab("Atualizar Produto",  new ImageIcon(getClass().getResource("/sage_icons/cart_round [#1166].png")), panel_update_produto, null);
		panel_update_produto.setLayout(null);
		
		//ADICIONAR CAMPOS DE DELETAR PRODUTO NESSE PANEL
		JPanel panel_delete_produto = new JPanel();
		tabbedPane_acoes_produto.addTab("Deletar Produto",  new ImageIcon(getClass().getResource("/sage_icons/cart_minus_round [#1162].png")), panel_delete_produto, null);
		panel_delete_produto.setLayout(null);
		
		
		
		
		JTabbedPane tabbedPane_listagem = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_listagem.setBounds(642, 0, 552, 333);
		panel_superior.add(tabbedPane_listagem);
		
		JPanel panel_listar_gerentes = new JPanel();
		tabbedPane_listagem.addTab("Gerentes", null, panel_listar_gerentes, null);
		panel_listar_gerentes.setLayout(null);
		
		JScrollPane scrollPane_s = new JScrollPane();
		scrollPane_s.setBounds(10, 11, 517, 291);
		panel_listar_gerentes.add(scrollPane_s);
		
		table_gerentes = new JTable();
		scrollPane_s.setViewportView(table_gerentes);
		
		JPanel panel_listar_fornecedores = new JPanel();
		tabbedPane_listagem.addTab("Fornecedores", null, panel_listar_fornecedores, null);
		panel_listar_fornecedores.setLayout(null);
		
		JScrollPane scrollPane_f = new JScrollPane();
		scrollPane_f.setBounds(10, 11, 517, 290);
		panel_listar_fornecedores.add(scrollPane_f);
		
		table_fornecedores = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
//		ControllerTableModels.popularTabelaFornecedores(table_fornecedores);
		scrollPane_f.setViewportView(table_fornecedores);
		
		JPanel panel_listar_movimentacoes = new JPanel();
		tabbedPane_listagem.addTab("Movimentações", null, panel_listar_movimentacoes, null);
		panel_listar_movimentacoes.setLayout(null);
		
		JScrollPane scrollPane_m = new JScrollPane();
		scrollPane_m.setBounds(10, 11, 517, 290);
		panel_listar_movimentacoes.add(scrollPane_m);
		
		table_movimentacoes = new JTable();
//		ControllerTableModels.popularTabelaMovimentacoes(table_movimentacoes);
		scrollPane_m.setViewportView(table_movimentacoes);
		
		JPanel panel_listar_registros = new JPanel();
		panel_listar_registros.setLayout(null);
		tabbedPane_listagem.addTab("Registros", null, panel_listar_registros, null);
		
		JScrollPane scrollPane_r = new JScrollPane();
		scrollPane_r.setBounds(10, 11, 517, 283);
		panel_listar_registros.add(scrollPane_r);
		
		table_registros = new JTable();
//		ControllerTableModels.popularTableRegistrosGerente(table_registros);
		scrollPane_r.setViewportView(table_registros);
		
		JSeparator separator_vertical = new JSeparator();
		separator_vertical.setOrientation(SwingConstants.VERTICAL);
		separator_vertical.setBounds(633, 0, 11, 394);
		panel_superior.add(separator_vertical);
		
		JButton btn_atualizar_tabelas_superior = new JButton("Atualizar listas");
		btn_atualizar_tabelas_superior.setBounds(652, 344, 125, 27);
		panel_superior.add(btn_atualizar_tabelas_superior);
		
		JProgressBar progressBar_tabelas_superior = new JProgressBar();
		progressBar_tabelas_superior.setBounds(787, 354, 365, 10);
		panel_superior.add(progressBar_tabelas_superior);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 394, 1194, 8);
		panel_superior.add(separator);
		
		JPanel panel_inferior = new JPanel();
		panel_inferior.setBounds(0, 401, 1194, 239);
		contentPane.add(panel_inferior);
		panel_inferior.setLayout(null);
		
		JButton btn_atualizar_tabela_produtos = new JButton("Atualizar Lista de Produtos");
		btn_atualizar_tabela_produtos.setBounds(10, 11, 190, 27);
		panel_inferior.add(btn_atualizar_tabela_produtos);
		
		JProgressBar progressBar_tabela_produtos = new JProgressBar();
		progressBar_tabela_produtos.setBounds(210, 21, 943, 10);
		panel_inferior.add(progressBar_tabela_produtos);
		
		JLayeredPane layeredPane_produtos = new JLayeredPane();
		layeredPane_produtos.setBorder(new TitledBorder(null, "Produtos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		layeredPane_produtos.setBounds(10, 44, 1174, 184);
		panel_inferior.add(layeredPane_produtos);
		
		JScrollPane scrollPane_p = new JScrollPane();
		scrollPane_p.setBounds(10, 21, 1144, 152);
		layeredPane_produtos.add(scrollPane_p);
		
		table_produtos = new JTable();
		scrollPane_p.setViewportView(table_produtos);
//		ControllerTableModels.popularTabelaProdutos(table_produtos);
		new ControllerTableModels(table_registros, table_gerentes, table_fornecedores, table_produtos, table_movimentacoes);
		setLocationRelativeTo(null);
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
