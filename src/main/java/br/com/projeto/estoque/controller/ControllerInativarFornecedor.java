package br.com.projeto.estoque.controller;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.model.Status;
import br.com.projeto.estoque.util.JPAUtil;

public class ControllerInativarFornecedor {
	private static EntityManager manager;
	protected static boolean sucessoInativacao = false;

	public void buscarFornecedorInativado(JButton btnResetar, JButton btnBuscar, JButton btnInativar, JTextField tfId,
			JTextField tfCnpj, JTextField tfNome, JTextField tfEmail, JTextField tfRazaoSocial) {

		manager = new JPAUtil().getEntityManager();

		Integer idInativado;
		try {
			idInativado = Integer.parseInt(tfId.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "O ID precisa ser preenchido corretamente!", "ID inválido",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Fornecedor fornecedorInativo = manager.find(Fornecedor.class, idInativado);

		if (fornecedorInativo == null) {
			JOptionPane.showMessageDialog(null, "Esse registro não existe!", "Registro inexistente",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else if (fornecedorInativo.getStatus() == Status.INATIVO) {
			JOptionPane.showMessageDialog(null, "Esse Fornecedor já está inativo!", "Fornecedor inativo",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			tfCnpj.setText(fornecedorInativo.getCnpj());
			tfNome.setText(fornecedorInativo.getNome());
			tfEmail.setText(fornecedorInativo.getEmail());
			tfRazaoSocial.setText(fornecedorInativo.getRazaoSocial());

			btnResetar.setEnabled(true);
			btnBuscar.setEnabled(false);
			btnInativar.setEnabled(true);
			tfId.setEnabled(false);
		}

		manager.close();
	}

	public void resetarInativar(JButton btnResetar, JButton btnBuscar, JButton btnInativar, JTextField tfId,
			JTextField tfCnpj, JTextField tfNome, JTextField tfEmail, JTextField tfRazaoSocial) {
		btnResetar.setEnabled(false);
		btnBuscar.setEnabled(true);
		btnInativar.setEnabled(false);
		tfId.setEnabled(true);
		limparDados(tfCnpj, tfNome, tfEmail, tfRazaoSocial);
	}

	public void inativarFornecedor(JButton btnResetar, JButton btnBuscar, JButton btnInativar, JTextField tfId,
			JTextField tfCnpj, JTextField tfNome, JTextField tfEmail, JTextField tfRazaoSocial) {
		Integer idInativado = Integer.parseInt(tfId.getText());

		ControllerFornecedor cf = new ControllerFornecedor();
		cf.inativarFornecedor(idInativado);

		if (sucessoInativacao) {
			limparDados(tfCnpj, tfNome, tfEmail, tfRazaoSocial);
			btnResetar.setEnabled(false);
			btnBuscar.setEnabled(true);
			btnInativar.setEnabled(false);
			tfId.setEnabled(true);
			sucessoInativacao = false;
		}
	}

	private void limparDados(JTextField tfCnpj, JTextField tfNome, JTextField tfEmail, JTextField tfRazaoSocial) {
		tfCnpj.setText("");
		tfNome.setText("");
		tfEmail.setText("");
		tfRazaoSocial.setText("");
	}
}
