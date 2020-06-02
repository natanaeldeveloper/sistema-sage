package br.com.projeto.estoque.controller;


import br.com.projeto.estoque.model.TipoComportamento;
import br.com.projeto.estoque.util.Aviso;
import br.com.projeto.estoque.util.Essencial;
import br.com.projeto.estoque.util.GerenteAtual;
import br.com.projeto.estoque.util.JPAUtil;
import br.com.projeto.estoque.util.SupervisorAtual;

public class ControllerGlobal extends ControllerValidacao {

	public void registrarSaidaUsuario() {

		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.getManager().getTransaction().begin();
		
		if(GerenteAtual.getGerente()==null) {
			ControllerRegistroSupervisor crs = new ControllerRegistroSupervisor();
			crs.criarRegistroSupervisor(TipoComportamento.SAIDA, SupervisorAtual.getSupervisor());
			SupervisorAtual.setSupervisor(null);
		}else {
			ControllerRegistroGerente cr = new ControllerRegistroGerente();
			cr.criarRegistroGerente(TipoComportamento.SAIDA, GerenteAtual.getGerente());
			GerenteAtual.setGerente(null);
		}
		Essencial.getManager().getTransaction().commit();
		Essencial.getManager().close();
	}
	
	public Object criarUsuario(Object objeto) {
		Essencial.getManager().persist(objeto);
		Aviso.avisar(5);
		return objeto;
	}

//	public boolean cadastrarUsuario(String cpf, String senha) {
//		if (verificarUsuarioJaCadastrado(cpf) == true) {
//			criarUsuario(cpf, Criptografar.encriptografar(senha));
//			JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso");
//			return true;
//		} else {
//			JOptionPane.showMessageDialog(null, "CPF j� cadastrado, tente outro!");
//			return false;
//		}
//
//	}

//	public boolean chamarMetodosDeCriacao(String cpf, String senha) {
//		boolean criou_usuario = false;
//
//		Essencial.setManager(new JPAUtil().getEntityManager());
//		Essencial.getManager().getTransaction().begin();
//
//		if (testarCampos(cpf, senha) == false) {
//			JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
//		} else {
//			criou_usuario = cadastrarUsuario(cpf, senha);
//		}
//
//		Essencial.getManager().getTransaction().commit();
//		Essencial.getManager().close();
//
//		return criou_usuario;
//	}
	public void teste(){
		int a = 0;
	}

}