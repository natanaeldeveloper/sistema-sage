package br.com.projeto.estoque.util;

import javax.swing.JOptionPane;

public class Aviso {

	public static void avisar(int erro) {
		switch (erro) {
		case 1:
			JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Campos vazios",
					JOptionPane.ERROR_MESSAGE);
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "Dados incorretos.", "Credenciais incorretas",
					JOptionPane.ERROR_MESSAGE);
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "CPF já cadastrado no sistema!", "CPF inválido",
					JOptionPane.ERROR_MESSAGE);
			break;
		case 4:
			JOptionPane.showMessageDialog(null, "Você não tem permissão para fazer isto!", "Acesso negado",
					JOptionPane.WARNING_MESSAGE);
			break;
		case 5:
			JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Usuário cadastrado",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case 6:
			JOptionPane.showMessageDialog(null, "As senhas não são iguais!", "Senha inválida",
					JOptionPane.ERROR_MESSAGE);
			break;
		case 7:
			JOptionPane.showMessageDialog(null, "O que você está procurando não existe no banco de dados",
					"Registro inexistente", JOptionPane.ERROR_MESSAGE);
			break;
		case 8:
			JOptionPane.showMessageDialog(null, "O ID tem que ser um valor numérico!", "ID inválido",
					JOptionPane.ERROR_MESSAGE);
			break;
		case 9:
			JOptionPane.showMessageDialog(null, "Digite o ID do supervisor a ser modificado!", "ID obrigatório",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case 10:
			JOptionPane.showMessageDialog(null, "Atributo modificado com sucesso", "Atributo modificado",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case 11:
			JOptionPane.showMessageDialog(null, "O Supervisor a ser modificado não existe no banco de dados",
					"Registro inexistente", JOptionPane.ERROR_MESSAGE);
			break;
		case 12:
			JOptionPane.showMessageDialog(null, "Digite seu novo login!", "Novo login",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case 13:
			JOptionPane.showMessageDialog(null, "Digite sua nova SENHA", "Nova senha", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 14:
			JOptionPane.showMessageDialog(null, "Login já cadastrado no sistema.", "Login inserido inválido",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case 15:
			JOptionPane.showMessageDialog(null, "CPF ou senha incorretos.", "Credenciais inválidas",
					JOptionPane.ERROR_MESSAGE);
			break;
		case 16:
			JOptionPane.showMessageDialog(null,
					"Atualização feita com sucesso, reentre no sistema para que as alterações sejam aplicadas!",
					"Atualização concluída", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 17:
			JOptionPane.showMessageDialog(null,
					"Muitas tentativas sem sucesso :( \n Talvez o programa seja encerrado por motivos de segurança.",
					"Método de segurança", JOptionPane.WARNING_MESSAGE);
			break;
			
		case 18:
			JOptionPane.showMessageDialog(null, "CPF ou senha incorretos.", "Credenciais incorretas",
					JOptionPane.ERROR_MESSAGE);
			break;
		default:
			break;
		}
	}

}
