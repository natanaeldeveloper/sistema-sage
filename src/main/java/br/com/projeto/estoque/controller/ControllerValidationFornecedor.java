package br.com.projeto.estoque.controller;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.util.GerenteAtual;
import br.com.projeto.estoque.util.JPAUtil;

public class ControllerValidationFornecedor {
	@SuppressWarnings("unused")
	private static EntityManager manager;

	@SuppressWarnings({ "deprecation", "unused" })
	public static void cadastrarFornecedor(JTextField tfCnpj, JTextField tfNome, JPasswordField tfSenha,
			JPasswordField tfConfirmar) {
		if (conferirDados(tfCnpj, tfNome, tfSenha, tfConfirmar)) {
			manager = new JPAUtil().getEntityManager();
			String cnpj = tfCnpj.getText();
			String nome = tfNome.getText();
			String senha = tfSenha.getText();
			String senhaConfirmar = tfConfirmar.getText();
			if (senha.equals(senhaConfirmar) && senhaConfirmar.equals(GerenteAtual.getGerente().getSenha())) {
				ControllerFornecedor cf = new ControllerFornecedor();
				if (conferirFornecedor(tfCnpj, tfNome)) {
					try {
//						cf.criarFornecedor(cnpj, nome);
						JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!",
								"Fornecedor Cadastrado", JOptionPane.INFORMATION_MESSAGE);
						limparDados(tfCnpj, tfNome, tfSenha, tfConfirmar);
					} catch (Exception e0) {
						JOptionPane.showMessageDialog(null, "Falha ao realizar ação!", "Falha de cadastro",
								JOptionPane.ERROR_MESSAGE);
						System.out.println(e0.getMessage());
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Senha incorreta!", "Senha incorreta", JOptionPane.ERROR_MESSAGE);
			}
			manager.close();
		}
	}

	@SuppressWarnings("deprecation")
	public static boolean conferirDados(JTextField tfCnpj, JTextField tfNome, JPasswordField tfSenha,
			JPasswordField tfConfirmar) {
		if (!(tfCnpj.getText().isEmpty() || tfNome.getText().isEmpty() || tfSenha.getText().isEmpty()
				|| tfConfirmar.getText().isEmpty())) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Algum dos campos está vazio!\nCheque e tente novamente.",
					"Campo vazio", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public static boolean conferirFornecedor(JTextField tfCnpj, JTextField tfNome) {
		for (Fornecedor fornecedor : ControllerFornecedor.listarFornecedores()) {
			if (tfNome.getText().equals(fornecedor.getNome()) && tfCnpj.getText().equals(fornecedor.getCnpj())) {
				JOptionPane.showMessageDialog(null, "Esse Fornecedor já existe!", "Dados inválidos",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if (tfCnpj.getText().equals(fornecedor.getCnpj())) {
				JOptionPane.showMessageDialog(null, "Já existe um Fornecedor com esse CNPJ!", "CNPJ inválido",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}
	
	public static void limparDados(JTextField tfCnpj, JTextField tfNome, JPasswordField tfSenha,
			JPasswordField tfConfirmar) {
		tfCnpj.setText("");
		tfNome.setText("");
		tfSenha.setText("");
		tfConfirmar.setText("");
	}
}
