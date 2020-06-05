package br.com.projeto.estoque.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.projeto.estoque.model.Endereco;
import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings("rawtypes")
public class ControllerAtualizarFornecedor {
	private static EntityManager manager;

	// Esse método busca o Fornecedor pelo ID inserido no JTextField, e preenche os
	// inputs da view baseado no Fornecedor encontrado
	public void buscarFornecedor(JButton btnBuscarFornecedor, JButton btnResetarFornecedor, JTextField tfId,
			JTextField tfNome, JFormattedTextField tfCnpj, JTextField tfRazaoSocial, JTextField tfTelefone,
			JTextField tfEmail, JFormattedTextField tfCep, JComboBox cbEstado, JTextField tfCidade, JTextField tfBairro,
			JTextField tfNumero, JTextField tfLogradouro, JTextField tfComplemento, JButton btnLimpar,
			JButton btnAtualizarFornecedor) {
		manager = new JPAUtil().getEntityManager();
		Integer idBuscado = null;
		try {
			idBuscado = Integer.parseInt(tfId.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "O campo de ID precisa estar preenchido e ser coerente!", "ID inválido",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		Fornecedor fornecedorBuscado = manager.find(Fornecedor.class, idBuscado);
		// Se o EntityManager não encontrar nenhum Produto com esse ID, o programa para
		// e exibe esse erro
		if (fornecedorBuscado == null) {
			JOptionPane.showMessageDialog(null, "Esse registro não existe!", "Registro inexistente",
					JOptionPane.ERROR_MESSAGE);
			return;
			// Se o Fornecedor existir, os campos serão populados com seus dados
		} else {
			tfNome.setText(fornecedorBuscado.getNome());
			tfCnpj.setText(fornecedorBuscado.getCnpj());
			tfRazaoSocial.setText(fornecedorBuscado.getRazaoSocial());
			tfTelefone.setText(fornecedorBuscado.getTelefone());
			tfEmail.setText(fornecedorBuscado.getEmail());
			tfCep.setText(fornecedorBuscado.getEndereco().getCep());
			cbEstado.setSelectedItem(fornecedorBuscado.getEndereco().getEstado());
			tfCidade.setText(fornecedorBuscado.getEndereco().getCidade());
			tfBairro.setText(fornecedorBuscado.getEndereco().getBairro());
			tfNumero.setText(fornecedorBuscado.getEndereco().getNumero());
			tfLogradouro.setText(fornecedorBuscado.getEndereco().getLogradouro());
			tfComplemento.setText(fornecedorBuscado.getEndereco().getComplemento());

			habilitarAtualizacaoFornecedor(btnBuscarFornecedor, btnResetarFornecedor, tfId, tfNome, tfCnpj,
					tfRazaoSocial, tfTelefone, tfEmail, tfCep, cbEstado, tfCidade, tfBairro, tfNumero, tfLogradouro,
					tfComplemento, btnLimpar, btnAtualizarFornecedor);
		}
		manager.close();
	}

	public void atualizarFornecedor(JTextField tfId, JTextField tfNome, JFormattedTextField tfCnpj,
			JTextField tfRazaoSocial, JTextField tfTelefone, JTextField tfEmail, JFormattedTextField tfCep,
			JComboBox cbEstado, JTextField tfCidade, JTextField tfBairro, JTextField tfNumero, JTextField tfLogradouro,
			JTextField tfComplemento) {
		if (ControllerAuxiliar.conferirDadosFornecedor(tfNome, tfCnpj, tfRazaoSocial, tfTelefone, tfEmail, tfCep,
				cbEstado, tfCidade, tfBairro, tfNumero, tfLogradouro)) {
			Integer idAtualizado = null;
			String nome = tfNome.getText();
			String cnpj = tfCnpj.getText();
			String razaoSocial = tfRazaoSocial.getText();
			String telefone = tfTelefone.getText();
			String email = tfEmail.getText();
			String cep = tfCep.getText();
			String estado = cbEstado.getSelectedItem().toString();
			String cidade = tfCidade.getText();
			String bairro = tfBairro.getText();
			String numero = tfNumero.getText();
			String logradouro = tfLogradouro.getText();
			String complemento = tfComplemento.getText();

			Endereco endereco = new Endereco();
			endereco.setEstado(estado);
			endereco.setCidade(cidade);
			endereco.setBairro(bairro);
			endereco.setCep(cep);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setComplemento(complemento);

			// Esse try/catch verifica se o ID está preenchido e é numérico
			try {
				idAtualizado = Integer.parseInt(tfId.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "O campo de ID precisa estar preenchido e ser coerente!",
						"ID inválido", JOptionPane.ERROR_MESSAGE);
			}

			try {
				ControllerFornecedor cf = new ControllerFornecedor();
				cf.atualizarFornecedor(idAtualizado, cnpj, nome, email, razaoSocial, telefone, endereco);

				JOptionPane.showMessageDialog(null, "Fornecedor atualizado com sucesso!", "Fornecedor atualizado",
						JOptionPane.INFORMATION_MESSAGE);
				ControllerAuxiliar.limparCamposFornecedor(tfNome, tfCnpj, tfRazaoSocial, tfTelefone, tfEmail, tfCep,
						cbEstado, tfCidade, tfBairro, tfNumero, tfLogradouro, tfComplemento);
			} catch (PersistenceException e) {
				JOptionPane.showMessageDialog(null,
						"Não foi possível realizar a atualização, verifique se o CNPJ, o e-mail ou o telefone informados já não estão registrados.\nSe o erro persistir, entre em contato.",
						"Erro inesperado", JOptionPane.ERROR_MESSAGE);
				System.out.println(e.getMessage());
			}
		}
	}

	public void habilitarAtualizacaoFornecedor(JButton btnBuscarFornecedor, JButton btnResetarFornecedor,
			JTextField tfId, JTextField tfNome, JFormattedTextField tfCnpj, JTextField tfRazaoSocial,
			JTextField tfTelefone, JTextField tfEmail, JFormattedTextField tfCep, JComboBox cbEstado,
			JTextField tfCidade, JTextField tfBairro, JTextField tfNumero, JTextField tfLogradouro,
			JTextField tfComplemento, JButton btnLimpar, JButton btnAtualizarFornecedor) {
		btnBuscarFornecedor.setEnabled(false);
		btnResetarFornecedor.setEnabled(true);
		tfId.setEnabled(false);
		tfNome.setEnabled(true);
		tfCnpj.setEnabled(true);
		tfRazaoSocial.setEnabled(true);
		tfTelefone.setEnabled(true);
		tfEmail.setEnabled(true);
		tfCep.setEnabled(true);
		cbEstado.setEnabled(true);
		tfCidade.setEnabled(true);
		tfBairro.setEnabled(true);
		tfNumero.setEnabled(true);
		tfLogradouro.setEnabled(true);
		tfComplemento.setEnabled(true);
		btnLimpar.setEnabled(true);
		btnAtualizarFornecedor.setEnabled(true);
	}

	public void desabilitarAtualizacaoFornecedor(JButton btnBuscarFornecedor, JButton btnResetarFornecedor,
			JTextField tfId, JTextField tfNome, JFormattedTextField tfCnpj, JTextField tfRazaoSocial,
			JTextField tfTelefone, JTextField tfEmail, JFormattedTextField tfCep, JComboBox cbEstado,
			JTextField tfCidade, JTextField tfBairro, JTextField tfNumero, JTextField tfLogradouro,
			JTextField tfComplemento, JButton btnLimpar, JButton btnAtualizarFornecedor) {
		btnBuscarFornecedor.setEnabled(true);
		btnResetarFornecedor.setEnabled(false);
		tfId.setEnabled(true);
		tfNome.setEnabled(false);
		tfCnpj.setEnabled(false);
		tfRazaoSocial.setEnabled(false);
		tfTelefone.setEnabled(false);
		tfEmail.setEnabled(false);
		tfCep.setEnabled(false);
		cbEstado.setEnabled(false);
		tfCidade.setEnabled(false);
		tfBairro.setEnabled(false);
		tfNumero.setEnabled(false);
		tfLogradouro.setEnabled(false);
		tfComplemento.setEnabled(false);
		btnLimpar.setEnabled(false);
		btnAtualizarFornecedor.setEnabled(false);

		ControllerAuxiliar.limparCamposFornecedor(tfNome, tfCnpj, tfRazaoSocial, tfTelefone, tfEmail, tfCep, cbEstado,
				tfCidade, tfBairro, tfNumero, tfLogradouro, tfComplemento);
	}
}
