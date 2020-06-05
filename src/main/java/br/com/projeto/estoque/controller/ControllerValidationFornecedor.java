package br.com.projeto.estoque.controller;

import javax.persistence.EntityManager;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.projeto.estoque.model.Endereco;
import br.com.projeto.estoque.model.Fornecedor;

@SuppressWarnings("rawtypes")
public class ControllerValidationFornecedor {
	@SuppressWarnings("unused")
	private static EntityManager manager;

	//Esse método pega os dados dos campos e os coloca em variáveis, para possibilitar o cadastro do Fornecedor
	//Faltam as validações
	public void enviarDadosParaCadastro(JTextField tfNome, JFormattedTextField tfCnpj, JTextField tfRazaoSocial,
			JTextField tfTelefone, JTextField tfEmail, JFormattedTextField tfCep, JComboBox cbEstado,
			JTextField tfCidade, JTextField tfBairro, JTextField tfNumero, JTextField tfLogradouro,
			JTextField tfComplemento) {
		String nome = tfNome.getText();
		String cnpj = tfCnpj.getText();
		String razaoSocial = tfRazaoSocial.getText();
		String telefone = tfTelefone.getText();
		String email = tfEmail.getText();

		//Como o endereço é um objeto à parte, ele precisa ser instanciado e populado para ser passado
		//como parâmetro no método de cadastrar o fornecedor no banco
		Endereco endereco = new Endereco();
		endereco.setEstado(cbEstado.getSelectedItem().toString());
		endereco.setCidade(tfCidade.getText());
		endereco.setCep(tfCep.getText());
		endereco.setBairro(tfBairro.getText());
		endereco.setLogradouro(tfLogradouro.getText());
		endereco.setNumero(tfNumero.getText());
		endereco.setComplemento(tfComplemento.getText());

		//O ControllerFornecedor é chamado para efetivar o cadastro do Fornecedor no banco
		ControllerFornecedor cf = new ControllerFornecedor();
		cf.criarFornecedor(nome, cnpj, razaoSocial, telefone, email, endereco);

		JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!", "Fornecedor cadastrado",
				JOptionPane.INFORMATION_MESSAGE);
	}

	//Esse método ainda não está sendo usado, mas poderá ser útil num futuro próximo
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
}
