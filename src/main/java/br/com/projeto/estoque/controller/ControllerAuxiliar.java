package br.com.projeto.estoque.controller;

import java.awt.TextField;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ControllerAuxiliar {
	public void limparCampos(JFormattedTextField campoJformattedTextField, JPasswordField campoJpasswordField , JPasswordField campoJpasswordField2,
			JFormattedTextField campoJformattedTextField2) {
		campoJformattedTextField.setText("");
		campoJpasswordField.setText("");
		campoJpasswordField2.setText("");
		campoJformattedTextField2.setText("");
	}

	public static boolean verificarValorNull(JFormattedTextField valor) {
		if (valor.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public void limparCampos(JFormattedTextField campoFormattedTextField, JPasswordField campoJPasswordField,
			JFormattedTextField campoFormattedTextField2, JFormattedTextField campoFormattedTextField3,
			JFormattedTextField campoFormattedTextField4, JPasswordField campoJPasswordField2) {

			campoFormattedTextField.setText("");
			campoJPasswordField.setText("");
			campoJPasswordField2.setText("");
			campoFormattedTextField2.setText("");
			campoFormattedTextField3.setText("");
			campoFormattedTextField4.setText("");
	}
	
	
	//MÉTODO DESTINADO EXCLUSIVAMENTE PARA O PAINEL DE ATUALIZAÇÃO DOS SUPERVISORES
	public void limparCampos(JFormattedTextField cpf_gerente_AtualizacaoSupervisor, JFormattedTextField id_pesquisa_supervisor_AtualizacaoSupervisor,
			JPasswordField nova_senha_supervisor_AtualizacaoSupervisor, JPasswordField senha_gerente_AtualizacaoSupervisor,
			JFormattedTextField novo_cpf_supervisor_AtualizacaoSupervisor, JFormattedTextField cpf_atual_supervisor_AtualizacaoSupervisor,
			JFormattedTextField login_atual_supervisor_AtualizacaoSupervisor, JFormattedTextField novo_login_supervisor_AtualizacaoSupervisor) {
		
		cpf_gerente_AtualizacaoSupervisor.setText("");
		id_pesquisa_supervisor_AtualizacaoSupervisor.setText("");
		nova_senha_supervisor_AtualizacaoSupervisor.setText("");
		senha_gerente_AtualizacaoSupervisor.setText("");
		novo_cpf_supervisor_AtualizacaoSupervisor.setText("");
		cpf_atual_supervisor_AtualizacaoSupervisor.setText("");
		login_atual_supervisor_AtualizacaoSupervisor.setText("");
		novo_login_supervisor_AtualizacaoSupervisor.setText("");
		
	}
}
