package br.com.projeto.estoque.controller;

import javax.swing.JFormattedTextField;

import org.apache.commons.lang3.StringUtils;

import br.com.projeto.estoque.util.Aviso;
import br.com.projeto.estoque.util.Criptografar;
import br.com.projeto.estoque.util.GerenteAtual;
import br.com.projeto.estoque.util.SupervisorAtual;

public class ControllerValidacao {

	public boolean evitarValorVazio(JFormattedTextField campo, int id) {
		if (StringUtils.isBlank(campo.getText())) {
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
		if (StringUtils.isBlank(cpf_supervisor) || StringUtils.isBlank(senha_supervisor) || StringUtils.isBlank(login_supervisor)
				|| StringUtils.isBlank(confSenha_supervisor)) {
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
		if (StringUtils.isBlank(senha)) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean testarCampoLogin(String login) {
		if (StringUtils.isBlank(login)) {
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
	
	public boolean validarConfirmacaoSupervisor(String cpf, String senha) {
		if (cpf.equals(SupervisorAtual.getSupervisor().getCpf())
				&& Criptografar.encriptografar(senha).equals(SupervisorAtual.getSupervisor().getSenha())) {
			return true;
		} else {
			Aviso.avisar(2);
			return false;
		}
	}

	public boolean confirmarDelecao(boolean varConfirmacao) {
		return varConfirmacao;
	}

	public static boolean testarCampos(String nova_senha_supervisor,
			String novo_login_supervisor) {
		if (StringUtils.isBlank(nova_senha_supervisor)
				|| StringUtils.isBlank(novo_login_supervisor)) {
			return false;
		} else {
			return true;
		}
	}

}