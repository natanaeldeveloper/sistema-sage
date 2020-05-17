package br.com.projeto.estoque.util;

import br.com.projeto.estoque.model.Gerente;

public class CriaGerente {
	public static void main(String[] args) {
		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.getManager().getTransaction().begin();
		Gerente gerente = new Gerente();
		gerente.setCpf("333.333.333-33");
		gerente.setSenha(Criptografar.encriptografar("login"));
		Essencial.getManager().persist(gerente);
		Essencial.getManager().getTransaction().commit();
		Essencial.getManager().close();
	}
}
