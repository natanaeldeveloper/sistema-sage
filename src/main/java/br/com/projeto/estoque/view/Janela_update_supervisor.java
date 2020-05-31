package br.com.projeto.estoque.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import br.com.projeto.estoque.controller.ControllerAuxiliar;
import br.com.projeto.estoque.controller.ControllerSupervisor;
import br.com.projeto.estoque.model.Nivel;
import br.com.projeto.estoque.util.Aviso;

public class Janela_update_supervisor extends JInternalFrame {
	JFormattedTextField c_cpfNovo;
	final JFormattedTextField formattedTextField_1 = new JFormattedTextField();
	ControllerAuxiliar ctrlAux = new ControllerAuxiliar();
	ControllerSupervisor ctrlSupervisor = new ControllerSupervisor();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_update_supervisor frame = new Janela_update_supervisor();
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
	public Janela_update_supervisor() {
		setResizable(true);
		setIconifiable(true);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Atualizar dados do Supervisor");
		lblNewLabel.setBounds(20, 83, 753, 45);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 22));
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Senha Atual:");
		lblNewLabel_2.setBounds(119, 215, 253, 25);
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel_2);

		final JPasswordField c_senhaAtual = new JPasswordField();
		c_senhaAtual.setEditable(false);
		c_senhaAtual.setBounds(119, 239, 253, 25);
		c_senhaAtual.setBackground(SystemColor.controlHighlight);
		getContentPane().add(c_senhaAtual);
		c_senhaAtual.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Nova Senha:");
		lblNewLabel_3.setBounds(119, 275, 253, 27);
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel(new ImageIcon(getClass().getResource("/icons/user01-60.png")));
		lblNewLabel_4.setBounds(10, 0, 752, 95);
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("CPF Atual:");
		lblNewLabel_5.setBounds(405, 215, 253, 25);
		lblNewLabel_5.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel_5);

		final JFormattedTextField c_cpfAtual = new JFormattedTextField();
		c_cpfAtual.setEditable(false);

		c_cpfAtual.setColumns(10);
		c_cpfAtual.setBackground(SystemColor.controlHighlight);
		getContentPane().add(c_cpfAtual);

		c_cpfAtual.setBounds(405, 239, 253, 25);
		MaskFormatter ms = null;
		try {
			ms = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultFormatterFactory df = new DefaultFormatterFactory(ms);
//		c_cpfAtual.setFormatterFactory(df);
		getContentPane().add(c_cpfAtual);

		final JPasswordField c_senhaNova = new JPasswordField();
		c_senhaNova.setBounds(119, 303, 253, 25);
		c_senhaNova.setBackground(SystemColor.controlHighlight);
		getContentPane().add(c_senhaNova);
		c_senhaNova.setColumns(10);

		JButton b_limpar = new JButton("Limpar");
		b_limpar.setFont(new Font("Arial", Font.PLAIN, 14));
		b_limpar.setBounds(119, 353, 96, 31);
		b_limpar.setBackground(SystemColor.control);
		getContentPane().add(b_limpar);

		JButton Atualizar = new JButton("Atualizar");
		Atualizar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(ctrlSupervisor.atualizarSupervisor(c_cpfNovo.getText(), c_senhaNova.getText(), formattedTextField_1)==true) {
					ctrlAux.limparCampos(formattedTextField_1, c_cpfAtual, c_senhaAtual, c_senhaNova, c_cpfNovo);
				}
			}
		});
		Atualizar.setFont(new Font("Arial", Font.PLAIN, 14));
		Atualizar.setBackground(SystemColor.control);
		Atualizar.setBounds(225, 353, 108, 31);
		getContentPane().add(Atualizar);

		JLabel lblNewLabel_3_1 = new JLabel("Novo CPF:");
		lblNewLabel_3_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(405, 275, 253, 27);
		getContentPane().add(lblNewLabel_3_1);

		c_cpfNovo = new JFormattedTextField();
		c_cpfNovo.setColumns(10);
		c_cpfNovo.setBackground(SystemColor.controlHighlight);
		c_cpfNovo.setBounds(405, 303, 253, 25);
		getContentPane().add(c_cpfNovo);
		setBounds(-5, 0, 788, 466);
		try {
			ms = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultFormatterFactory d = new DefaultFormatterFactory(ms);
		c_cpfNovo.setFormatterFactory(d);
		getContentPane().add(c_cpfNovo);
		
		formattedTextField_1.setText("0");
		formattedTextField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				formattedTextField_1.setText(formattedTextField_1.getText().replaceAll("[^0-9]", "0"));
			}
		});
		formattedTextField_1.setColumns(10);
		formattedTextField_1.setBackground(SystemColor.controlHighlight);
		formattedTextField_1.setBounds(119, 184, 253, 25);
		getContentPane().add(formattedTextField_1);

		JLabel lblPesquisarId = new JLabel("Pesquisar ID:");
		lblPesquisarId.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblPesquisarId.setBounds(119, 148, 253, 25);
		getContentPane().add(lblPesquisarId);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControllerSupervisor ctls = new ControllerSupervisor();
				try {
					int id = Integer.valueOf(formattedTextField_1.getText());
					ctls.mostrarDadosDoSupervisorPeloId(id, c_cpfAtual, c_senhaAtual);
				} catch (NumberFormatException e) {
					Aviso.avisar(8);
				}
			}
		});
		btnBuscar.setBounds(206, 150, 166, 23);
		getContentPane().add(btnBuscar);
		
		JButton btnAtualizarApenasCpf = new JButton("Atualizar apenas CPF");
		btnAtualizarApenasCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(ctrlSupervisor.atualizarSupervisorCpf(formattedTextField_1, c_cpfNovo.getText())==true) {
					ctrlAux.limparCampos(formattedTextField_1, c_cpfAtual, c_senhaAtual, c_senhaNova, c_cpfNovo);
				}
				
			}
		});
		btnAtualizarApenasCpf.setFont(new Font("Arial", Font.PLAIN, 10));
		btnAtualizarApenasCpf.setBackground(SystemColor.menu);
		btnAtualizarApenasCpf.setBounds(503, 354, 155, 31);
		getContentPane().add(btnAtualizarApenasCpf);
		
		JButton btnAtualizarApenasSenha = new JButton("Atualizar apenas SENHA\r\n");
		btnAtualizarApenasSenha.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				if(ctrlSupervisor.atualizarSupervisorSenha(formattedTextField_1, c_senhaNova.getText())==true) {
					ctrlAux.limparCampos(formattedTextField_1, c_cpfAtual, c_senhaAtual, c_senhaNova, c_cpfNovo);
				}
				
			}
		});
		btnAtualizarApenasSenha.setFont(new Font("Arial", Font.PLAIN, 10));
		btnAtualizarApenasSenha.setBackground(SystemColor.menu);
		btnAtualizarApenasSenha.setBounds(343, 353, 155, 31);
		getContentPane().add(btnAtualizarApenasSenha);

		b_limpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrlAux.limparCampos(formattedTextField_1, c_cpfAtual, c_senhaAtual, c_senhaNova, c_cpfNovo);
			}
		});

	}
}
