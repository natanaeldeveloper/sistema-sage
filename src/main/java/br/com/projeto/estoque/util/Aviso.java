package br.com.projeto.estoque.util;

import javax.swing.JOptionPane;

import br.com.projeto.estoque.view.Janela_de_erros;

public class Aviso {
	
	public String getMenagem() {
		return menagem;
	}

	public void setMenagem(String menagem) {
		this.menagem = menagem;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	private String menagem;
	private String icon;
	
	public static void avisar (int erro) {
		switch (erro) {
		case 1:
		      JOptionPane.showMessageDialog(null, "Preencha todos os campos");
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "CPF ou senha incorretos");
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "CPF já cadastrado no Sistema");
			break;
		case 4:
			JOptionPane.showMessageDialog(null, "Você não tem permissão para fazer isso!");
			break;
		case 5:
			JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
			break;
		case 6:
			JOptionPane.showMessageDialog(null, "As senhas não são iguais!");
			break;
		case 7:
			JOptionPane.showMessageDialog(null, "O que você está procurando não existe no banco de dados");
			break;
		case 8:
			JOptionPane.showMessageDialog(null, "O ID tem um valor numérico!");
			break;
		case 9:
			JOptionPane.showMessageDialog(null, "Digite o ID do supervisor a ser modificado!");
			break;
		case 10:
			JOptionPane.showMessageDialog(null, "Atributo modificado com sucesso");
			break;
		case 11:
			JOptionPane.showMessageDialog(null, "O Supervisor a ser modificado não existe no banco de dados");
			break;
		case 12:
			JOptionPane.showMessageDialog(null, "Digite seu novo CPF!");
			break;
		case 13:
			JOptionPane.showMessageDialog(null, "Digite sua nova SENHA");
			break;
		case 14:
			JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
			break;
		default:
			break;
		}
	}
	
}
