package br.com.projeto.estoque.controller;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.projeto.estoque.model.Endereco;
import br.com.projeto.estoque.model.Fornecedor;

@SuppressWarnings("rawtypes")
public class ControllerValidationFornecedor {
	@SuppressWarnings("unused")
	// private static EntityManager manager;

	// Esse método pega os dados dos campos e os coloca em variáveis, para
	// possibilitar o cadastro do Fornecedor
	// Faltam as validações
	public void enviarDadosParaCadastro(JTextField tfNome, JFormattedTextField tfCnpj, JTextField tfRazaoSocial,
			JTextField tfTelefone, JTextField tfEmail, JFormattedTextField tfCep, JComboBox cbEstado,
			JTextField tfCidade, JTextField tfBairro, JTextField tfNumero, JTextField tfLogradouro,
			JTextField tfComplemento) {
		if (ControllerAuxiliar.conferirDadosFornecedor(tfNome, tfCnpj, tfRazaoSocial, tfTelefone, tfEmail, tfCep,
				cbEstado, tfCidade, tfBairro, tfNumero, tfLogradouro)) {
			String nome = tfNome.getText();
			String cnpj = tfCnpj.getText();
			String razaoSocial = tfRazaoSocial.getText();
			String telefone = tfTelefone.getText();
			String email = tfEmail.getText();

			// Como o endereço é um objeto à parte, ele precisa ser instanciado e populado
			// para ser passado
			// como parâmetro no método de cadastrar o fornecedor no banco
			Endereco endereco = new Endereco();
			endereco.setEstado(cbEstado.getSelectedItem().toString());
			endereco.setCidade(tfCidade.getText());
			endereco.setCep(tfCep.getText());
			endereco.setBairro(tfBairro.getText());
			endereco.setLogradouro(tfLogradouro.getText());
			endereco.setNumero(tfNumero.getText());
			endereco.setComplemento(tfComplemento.getText());

			// O ControllerFornecedor é chamado para efetivar o cadastro do Fornecedor no
			// banco
			if (conferirFornecedorExistente(nome, cnpj, email)) {
				try {
					ControllerFornecedor cf = new ControllerFornecedor();
					cf.criarFornecedor(nome, cnpj, razaoSocial, telefone, email, endereco);

					JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!", "Fornecedor cadastrado",
							JOptionPane.INFORMATION_MESSAGE);

					ControllerAuxiliar.limparCamposFornecedor(tfNome, tfCnpj, tfRazaoSocial, tfTelefone, tfEmail, tfCep,
							cbEstado, tfCidade, tfBairro, tfNumero, tfLogradouro, tfComplemento);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Ocorreu um erro ao cadastrar o Fornecedor, cheque os dados e tente novamente.\nSe o erro persistir, entre em contato.",
							"Erro inesperado", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	// Esse método confere se já não existe um Fornecedor com os mesmos dados únicos
	// no banco
	public static boolean conferirFornecedorExistente(String nomeFornecedor, String cnpjFornecedor,
			String emailFornecedor) {
		for (Fornecedor fornecedor : ControllerFornecedor.listarFornecedores()) {
			if (nomeFornecedor.equals(fornecedor.getNome()) && cnpjFornecedor.equals(fornecedor.getCnpj())) {
				JOptionPane.showMessageDialog(null,
						"Esse Fornecedor já existe!\nEle está no registro " + fornecedor.getId() + "!", "CNPJ inválido",
						JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (cnpjFornecedor.equals(fornecedor.getCnpj())) {
				JOptionPane.showMessageDialog(null, "Já existe um Fornecedor com esse CNPJ!", "CNPJ inválido",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if (emailFornecedor.equals(fornecedor.getEmail())) {
				JOptionPane.showMessageDialog(null, "Já existe um Fornecedor com esse e-mail!", "E-mail inválido",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}
}