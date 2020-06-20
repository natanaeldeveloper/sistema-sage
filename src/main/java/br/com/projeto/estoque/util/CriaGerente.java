package br.com.projeto.estoque.util;

import br.com.projeto.estoque.model.Gerente;

public class CriaGerente {
	public static void main(String[] args) {
		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.getManager().getTransaction().begin();
		Gerente gerente = new Gerente();
		gerente.setLogin("gerente1");
		gerente.setCpf("222.222.222-22");
		gerente.setSenha(Criptografar.encriptografar("login"));
		Essencial.getManager().persist(gerente);
		Essencial.getManager().getTransaction().commit();
		Essencial.getManager().close();
	}
}