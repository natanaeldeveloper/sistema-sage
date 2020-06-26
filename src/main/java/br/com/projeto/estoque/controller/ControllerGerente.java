package br.com.projeto.estoque.controller;

import javax.persistence.NoResultException;

import br.com.projeto.estoque.model.Gerente;
import br.com.projeto.estoque.model.TipoComportamento;
import br.com.projeto.estoque.util.Aviso;
import br.com.projeto.estoque.util.Criptografar;
import br.com.projeto.estoque.util.Essencial;
import br.com.projeto.estoque.util.GerenteAtual;
import br.com.projeto.estoque.util.JPAUtil;

public class ControllerGerente extends ControllerGlobal {
	
	private static int erros = 0;
	ControllerRegistroGerente controller = new ControllerRegistroGerente();

	public boolean fazerLogin(String login_ou_cpf, String senha, TipoComportamento tipoComportamento) {
		boolean login_efetuado = false;
		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.getManager().getTransaction().begin();
		if (verificarCredenciais(login_ou_cpf, senha) == true) {
			if(buscarGerentePeloLogin(login_ou_cpf)!=null) {
				GerenteAtual.setGerente(buscarGerentePeloLogin(login_ou_cpf));	
			}else {
				GerenteAtual.setGerente(buscarGerentePeloCpf(login_ou_cpf));
			}
			controller.criarRegistroGerente(tipoComportamento, GerenteAtual.getGerente());
			Essencial.getManager().getTransaction().commit();
			login_efetuado = true;
		} else {
			ControllerGerente.erros +=1;
			login_efetuado = false;
			if(ControllerGerente.erros==3) {
				Aviso.avisar(17);
			}
			if(ControllerGerente.erros==4) {
				System.exit(0);
			}
		}
		Essencial.getManager().close();
		return login_efetuado;
	}

	public boolean verificarCredenciais(String login_ou_cpf, String senha) {

		Gerente gerenteCpf = new Gerente();
		Gerente gerenteLogin = new Gerente();
		gerenteLogin = buscarGerentePeloLogin(login_ou_cpf);
		gerenteCpf = buscarGerentePeloCpf(login_ou_cpf);

		if (gerenteCpf == null) {
			if(gerenteLogin == null) {
				Aviso.avisar(2);
				return false;	
			}else {
				if (gerenteLogin.getSenha().equals(Criptografar.encriptografar(senha))) {
					return true;
				}else {
					Aviso.avisar(2);
					return false;	
				}
			}
		} else {
			if (gerenteCpf.getSenha().equals(Criptografar.encriptografar(senha))) {
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
	
	public Gerente buscarGerentePeloLogin(String login) {
		try {
			Essencial.setQuery(Essencial.getManager().createNamedQuery("buscarGerenteLogin"));
			Essencial.getQuery().setParameter("Glogin", login);
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
