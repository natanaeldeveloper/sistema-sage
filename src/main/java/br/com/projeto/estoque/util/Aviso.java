package br.com.projeto.estoque.util;

import javax.swing.JOptionPane;

public class Aviso {

	public static void avisar(int erro) {
		switch (erro) {
		case 1:
			JOptionPane.showMessageDialog(null, "Preencha todos os campos", "", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "Login ou senha incorretos.","", JOptionPane.ERROR_MESSAGE);
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "CPF já cadastrado no sistema!", "SAGE", JOptionPane.ERROR_MESSAGE);
			break;
		case 4:
			JOptionPane.showMessageDialog(null, "Você não tem permissão para fazer isto!");
			break;
		case 5:
			JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "SAGE", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 6:
			JOptionPane.showMessageDialog(null, "As senhas não são iguais!", "SAGE", JOptionPane.ERROR_MESSAGE);
			break;
		case 7:
			JOptionPane.showMessageDialog(null, "O que você está procurando não existe no banco de dados","SAGE", JOptionPane.ERROR_MESSAGE);
			break;
		case 8:
			JOptionPane.showMessageDialog(null, "O ID tem um valor numérico!", "SAGE", JOptionPane.ERROR_MESSAGE);
			break;
		case 9:
			JOptionPane.showMessageDialog(null, "Digite o ID do supervisor a ser modificado!", "SAGE", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 10:
			JOptionPane.showMessageDialog(null, "Atributo modificado com sucesso", "SAGE", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 11:
			JOptionPane.showMessageDialog(null, "O Supervisor a ser modificado não existe no banco de dados", "SAGE", JOptionPane.ERROR_MESSAGE);
			break;
		case 12:
			JOptionPane.showMessageDialog(null, "Digite seu novo login!", "SAGE", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 13:
			JOptionPane.showMessageDialog(null, "Digite sua nova SENHA", "SAGE", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 14:
			JOptionPane.showMessageDialog(null, "Login ja cadastrado no sistema.", "SAGE", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 15:
			JOptionPane.showMessageDialog(null, "CPF ou senha incorretos.", "SAGE", JOptionPane.ERROR_MESSAGE);
			break;
		case 16:
			JOptionPane.showMessageDialog(null, "Atualização feita com sucesso, reentre no sistema para que as alterações sejam aplicadas!", "SAGE", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 17:
			JOptionPane.showMessageDialog(null, "Muitas tentativas sem sucesso :( \n talvez o programa seja encerrado por motivos de segurança.", "SAGE", JOptionPane.WARNING_MESSAGE);
			break;
		default:
			break;
		}
	}

}
