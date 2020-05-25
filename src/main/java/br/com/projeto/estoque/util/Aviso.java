package br.com.projeto.estoque.util;

import javax.swing.JOptionPane;

public class Aviso {

	public static void avisar (int erro) {
		switch (erro) {
		case 1:
			JOptionPane.showMessageDialog(null, "Preencha todos os campos");
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "CPF ou senha incorretos");
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "CPF j� cadastrado no Sistema");
			break;
		case 4:
			JOptionPane.showMessageDialog(null, "Voc� n�o tem permiss�o para fazer isso!");
			break;
		case 5:
			JOptionPane.showMessageDialog(null, "Usu�rio cadastrado com sucesso!");
			break;
		case 6:
			JOptionPane.showMessageDialog(null, "As senhas n�o s�o iguais!");
			break;
		case 7:
			JOptionPane.showMessageDialog(null, "O que voc� est� procurando n�o existe no banco de dados");
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
		default:
			break;
		}
	}
	
}
