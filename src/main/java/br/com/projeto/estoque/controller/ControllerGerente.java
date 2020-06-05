package br.com.projeto.estoque.controller;

import javax.persistence.NoResultException;

import br.com.projeto.estoque.model.Gerente;
import br.com.projeto.estoque.model.TipoComportamento;
import br.com.projeto.estoque.util.Aviso;
import br.com.projeto.estoque.util.Essencial;
import br.com.projeto.estoque.util.GerenteAtual;
import br.com.projeto.estoque.util.JPAUtil;

public class ControllerGerente extends ControllerGlobal {
	ControllerRegistroGerente controller = new ControllerRegistroGerente();

	public boolean fazerLogin(String cpf, String senha, TipoComportamento tipoComportamento) {
		boolean login_efetuado = false;
		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.getManager().getTransaction().begin();
		if (verificarCredenciais(cpf, senha) == true) {
			GerenteAtual.setGerente(buscarGerentePeloCpf(cpf));
			controller.criarRegistroGerente(tipoComportamento, GerenteAtual.getGerente());
			Essencial.getManager().getTransaction().commit();
			login_efetuado = true;
		}
		Essencial.getManager().close();
		return login_efetuado;
	}

	public boolean verificarCredenciais(String cpf, String senha) {

		Gerente gerente = new Gerente();
		gerente = buscarGerentePeloCpf(cpf);

		if (gerente == null) {
			Aviso.avisar(2);
			return false;
		} else {
			if (gerente.getSenha().equals(senha)) {
				return true;
			} else {
				Aviso.avisar(2);
				return false;
			}
		}
	}

	public Gerente buscarGerentePeloCpf(String cpf) {
		try {
			Essencial.setQuery(Essencial.getManager().createNamedQuery("buscarGerente"));
			Essencial.getQuery().setParameter("Gcpf", cpf);
			Gerente gerente = (Gerente) Essencial.getQuery().getSingleResult();
			return gerente;
		} catch (NoResultException e) {
			return null;
		} finally {

		}

	}

	public Gerente criarGerente(String cpf, String senha) {
		Gerente gerente = new Gerente();
		gerente.setCpf(cpf);
		gerente.setSenha(senha);

		return gerente;
	}
}
