package br.com.projeto.estoque.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.com.projeto.estoque.controller.ControllerGlobal;
import br.com.projeto.estoque.controller.ControllerPermissao;

public class Janela_home extends JFrame {
	private static final long serialVersionUID = 1L;
	// setBounds(-5,0, 788, 498); medidas do internal_jframe
	JDesktopPane desktopPane = new JDesktopPane();
	ControllerPermissao permissao = new ControllerPermissao();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_home frame = new Janela_home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Janela_home() {
		setTitle("Fon");
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/pacote02-30.png"));
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 498);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		setResizable(false);

		JMenu mnNewMenu = new JMenu("Gerente");
		mnNewMenu.setIcon(new ImageIcon(getClass().getResource("/icons/user01-30.png")));
		mnNewMenu.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		JMenuItem mntmItem = new JMenuItem("Cadastrar Supervisor");
		mntmItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (permissao.chamarVerificacao() == true) {
					Janela_persist_supervisor internalFrame = new Janela_persist_supervisor();
					desktopPane.add(internalFrame);
					getContentPane().add(desktopPane);
					internalFrame.setVisible(true);
				}
			}
		});
		mntmItem.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		mntmItem.setBackground(Color.WHITE);
		mntmItem.setIcon(new ImageIcon(getClass().getResource("/icons/add-user-30.png")));
		mnNewMenu.add(mntmItem);

		JMenuItem mntmLogar = new JMenuItem("Atualizar Supervisor");
		mntmLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (permissao.chamarVerificacao() == true) {
					Janela_update_supervisor internalFrame = new Janela_update_supervisor();
					desktopPane.add(internalFrame);
					getContentPane().add(desktopPane);
					internalFrame.setVisible(true);
					permissao.chamarVerificacao();
				}
			}
		});
		mntmLogar.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		mntmLogar.setBackground(Color.WHITE);
		mntmLogar.setIcon(new ImageIcon(getClass().getResource("/icons/editar-user-30.png")));
		mnNewMenu.add(mntmLogar);

		JMenuItem mntmDeletar = new JMenuItem("Deletar Supervisor");
		mntmDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (permissao.chamarVerificacao() == true) {
					Janela_delete_supervisor internalFrame = new Janela_delete_supervisor();
					desktopPane.add(internalFrame);
					getContentPane().add(desktopPane);
					internalFrame.setVisible(true);
				}
			}
		});
		mntmDeletar.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		mntmDeletar.setIcon(new ImageIcon(getClass().getResource("/icons/delete-user-30.png")));
		mntmDeletar.setBackground(Color.WHITE);
		mnNewMenu.add(mntmDeletar);

		JMenu mnEstoque = new JMenu("Estoque");
		mnEstoque.setBackground(Color.WHITE);
		mnEstoque.setIcon(new ImageIcon(getClass().getResource("/icons/pacote01-30.png")));
		mnEstoque.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		menuBar.add(mnEstoque);

		desktopPane.setBounds(0, -37, 772, 460);
		getContentPane().add(desktopPane);

		JMenuItem mntmNewMenuItem = new JMenuItem("Adicionar Produto");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (permissao.chamarVerificacao() == true) {
					Janela_persist_produto internalFrame = new Janela_persist_produto();
					desktopPane.add(internalFrame);
					getContentPane().add(desktopPane);
					internalFrame.setVisible(true);
				}
			}
		});
		mntmNewMenuItem.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		mntmNewMenuItem.setBackground(Color.WHITE);
		mntmNewMenuItem.setIcon(new ImageIcon(getClass().getResource("/icons/add-pacote-30.png")));
		mnEstoque.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Alterar Produto");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (permissao.chamarVerificacao() == true) {
					Janela_update_produto internalFrame = new Janela_update_produto();
					desktopPane.add(internalFrame);
					getContentPane().add(desktopPane);
					internalFrame.setVisible(true);
				}
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		mntmNewMenuItem_1.setIcon(new ImageIcon(getClass().getResource("/icons/load-pacote-30.png")));
		mntmNewMenuItem_1.setBackground(Color.WHITE);
		mnEstoque.add(mntmNewMenuItem_1);

		JMenuItem mntmVerProdutos = new JMenuItem("Ver Produtos");
		mntmVerProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
					Janela_exibir_produto internalFrame = new Janela_exibir_produto();
					desktopPane.add(internalFrame);
					getContentPane().add(desktopPane);
					internalFrame.setVisible(true);
				
			}
		});
		mntmVerProdutos.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		mntmVerProdutos.setBackground(Color.WHITE);

		mntmVerProdutos.setIcon(new ImageIcon(getClass().getResource("/icons/pacotes-30.png")));
		mnEstoque.add(mntmVerProdutos);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Deletar Produto");
		mntmNewMenuItem_2.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		mntmNewMenuItem_2.setIcon(new ImageIcon(getClass().getResource("/icons/delete-pacote-30.png")));
		mntmNewMenuItem_2.setBackground(Color.WHITE);
		mnEstoque.add(mntmNewMenuItem_2);

		JMenu mnFornecedor = new JMenu("Fornecedor");
		mnFornecedor.setIcon(new ImageIcon(getClass().getResource("/icons/user02-30.png")));
		mnFornecedor.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		menuBar.add(mnFornecedor);

		JMenuItem mntmAdicionar = new JMenuItem("Cadastrar fornecedor");
		mntmAdicionar.setBackground(Color.WHITE);
		mntmAdicionar.setIcon(new ImageIcon(getClass().getResource("/icons/add-user-30.png")));
		mntmAdicionar.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		mnFornecedor.add(mntmAdicionar);
		mntmAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (permissao.chamarVerificacao() == true) {
					Janela_persist_fornecedor internalFrame = new Janela_persist_fornecedor();
					desktopPane.add(internalFrame);
					getContentPane().add(desktopPane);
					internalFrame.setVisible(true);
				}
			}
		});

		JMenuItem mntmAlterar = new JMenuItem("Alterar fornecedor");
		mntmAlterar.setBackground(Color.WHITE);
		mntmAlterar.setIcon(new ImageIcon(getClass().getResource("/icons/editar-user-30.png")));
		mntmAlterar.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		mnFornecedor.add(mntmAlterar);
		mntmAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (permissao.chamarVerificacao() == true) {
					Janela_update_fornecedor internalFrame = new Janela_update_fornecedor();
					desktopPane.add(internalFrame);
					getContentPane().add(desktopPane);
					internalFrame.setVisible(true);
				}
			}
		});

		JMenuItem mntmDelerar = new JMenuItem("Deletar fornecedor");
		mntmDelerar.setBackground(Color.WHITE);
		mntmDelerar.setIcon(new ImageIcon(getClass().getResource("/icons/delete-user-30.png")));
		mntmDelerar.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		mnFornecedor.add(mntmDelerar);

		JMenu mnConfiguraes = new JMenu("Sistema");
		mnConfiguraes.setIcon(new ImageIcon(getClass().getResource("/icons/config-30.png")));
		mnConfiguraes.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		menuBar.add(mnConfiguraes);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem(new ImageIcon(getClass().getResource("/icons/pesquisa-30.png")));
		mntmNewMenuItem_3.setText("Registros");
		mntmNewMenuItem_3.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		mntmNewMenuItem_3.setBackground(Color.WHITE);
		mnConfiguraes.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Movimenta\u00E7\u00F5es");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mntmNewMenuItem_4.setBackground(Color.WHITE);
		mntmNewMenuItem_4.setIcon(new ImageIcon(getClass().getResource("/icons/movimentacao-30.png")));
		mntmNewMenuItem_4.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		mnConfiguraes.add(mntmNewMenuItem_4);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControllerGlobal controllerGlob = new ControllerGlobal();
				controllerGlob.registrarSaidaUsuario();
				Janela_entrada je = new Janela_entrada();
				je.setVisible(true);
				dispose();
			}
		});

		mntmSair.setIcon(new ImageIcon(getClass().getResource("/icons/sair-30.png")));
		mntmSair.setBackground(Color.WHITE);
		mntmSair.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		mnConfiguraes.add(mntmSair);
		getContentPane().setLayout(null);

	}
}
