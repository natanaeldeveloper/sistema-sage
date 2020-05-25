package br.com.projeto.estoque.controller;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

public class ControllerAuxiliar {
	public static void limparCampos(JFormattedTextField cpf, JPasswordField senha, JPasswordField confSenha) {
		cpf.setText("");
		senha.setText("");
		confSenha.setText("");
	}

	public void limparCampos(JFormattedTextField id, JFormattedTextField cpfAtual, JPasswordField senhaAtual,
			JPasswordField senhaNova, JFormattedTextField cpfNovo) {
		id.setText("0");
		cpfAtual.setText("");
		senhaAtual.setText("");
		senhaNova.setText("");
		cpfNovo.setText("");
	}

	public static boolean verificarValorNull(JFormattedTextField valor) {
		if (valor.equals("")) {
			return true;
		} else {
			return false;
		}
	}
}
