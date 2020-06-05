package br.com.projeto.estoque.controller;

import javax.swing.JFormattedTextField;

import br.com.projeto.estoque.util.Aviso;
import br.com.projeto.estoque.util.Criptografar;
import br.com.projeto.estoque.util.GerenteAtual;

public class ControllerValidacao {

	public boolean evitarValorVazio(JFormattedTextField campo, int id) {
		if (campo.getText().equals("")) {
			Aviso.avisar(9);
			return false;
		} else {
			id = Integer.valueOf(campo.getText());
			if (id == 0) {
				Aviso.avisar(9);
				return false;
			} else {
				return true;
			}
		}
	}

	public boolean testarCamposCadastroSupervisor(String cpf_supervisor, String senha_supervisor,
			String login_supervisor, String confSenha_supervisor) {
		if (cpf_supervisor.equals("") || senha_supervisor.equals("") || login_supervisor.equals("")
				|| confSenha_supervisor.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean testarCampoCpf(String cpf) {
		if (cpf.equals("   .   .   -  ")) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean testarCampoSenha(String senha) {
		if (senha.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validarConfirmacaoGerente(String cpf, String senha) {
		if (cpf.equals(GerenteAtual.getGerente().getCpf())
				&& Criptografar.encriptografar(senha).equals(GerenteAtual.getGerente().getSenha())) {
			return true;
		} else {
			Aviso.avisar(2);
			return false;
		}
	}

	public boolean confirmarDelecao(boolean varConfirmacao) {
		return varConfirmacao;
	}

	public static boolean testarCampos(String novo_cpf_supervisor, String nova_senha_supervisor,
			String novo_login_supervisor) {
		if (novo_cpf_supervisor.equals("   .   .   -  ") || nova_senha_supervisor.equals("")
				|| novo_login_supervisor.equals("")) {
			return false;
		} else {
			return true;
		}
	}

}