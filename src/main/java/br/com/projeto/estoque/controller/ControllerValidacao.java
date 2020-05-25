package br.com.projeto.estoque.controller;
import javax.persistence.NoResultException;
import javax.swing.JFormattedTextField;

import br.com.projeto.estoque.model.Status;
import br.com.projeto.estoque.model.Supervisor;
import br.com.projeto.estoque.util.Aviso;
import br.com.projeto.estoque.util.Criptografar;
import br.com.projeto.estoque.util.Essencial;
import br.com.projeto.estoque.util.GerenteAtual;
import br.com.projeto.estoque.view.Janela_cpf_seguranca;

public class ControllerValidacao {
	
	public boolean evitarValorVazio(JFormattedTextField campo, int id) {
		if(campo.getText().equals("")) {
			Aviso.avisar(9);
			return false;
		}else {
			id = Integer.valueOf(campo.getText()); 
			if(id==0) {
				Aviso.avisar(9);
				return false;
			}else {
				return true;
			}
		}
	}
	
	public boolean verificarStatusSupervisor(int id) {
		Supervisor supervisor = new Supervisor();
		supervisor  = Essencial.getManager().find(Supervisor.class, id);
		
		if(supervisor.getStatus()==Status.INATIVO) {
			Janela_cpf_seguranca cpfs = new Janela_cpf_seguranca();
			cpfs.setVisible(true);
			return false;
		}else {
			return true;
		}
	}
	
	public boolean buscarSupervisorAtivo(String cpf){
		Essencial.setQuery(Essencial.getManager().createNamedQuery("buscarSupervisorAtivo"));
		Essencial.getQuery().setParameter("Scpf", cpf);
		Essencial.getQuery().setParameter("Sstatus", Status.ATIVO);
		Supervisor supervisor = new Supervisor();
		
		try {
			supervisor = (Supervisor) Essencial.getQuery().getSingleResult();
		}catch(NoResultException e) {
			supervisor = null;
		}
		if(supervisor!=null) {
			if(supervisor.getStatus()==Status.INATIVO) {
				Janela_cpf_seguranca cpfs = new Janela_cpf_seguranca();
				cpfs.setVisible(true);
				return false;
			}else{
				return true;
			}

		}else {
			Aviso.avisar(7);
			return false;
		}
		
	}	
	public boolean evitarDuplicacaoSupervisor(String cpf){
		Essencial.setQuery(Essencial.getManager().createNamedQuery("buscarSupervisor"));
		Essencial.getQuery().setParameter("Scpf", cpf);
		Supervisor supervisor = new Supervisor();

		try {
			supervisor = (Supervisor) Essencial.getQuery().getSingleResult();
		}catch(NoResultException e) {
			supervisor = null;
		}
		
		if(supervisor!=null) {
			if(supervisor.getStatus()==Status.INATIVO) {
				Janela_cpf_seguranca cpfs = new Janela_cpf_seguranca();
				cpfs.setVisible(true);
			}else {
				Aviso.avisar(3);
			}
			return false;
		}else {
			return true;
		}
	}
	
	public boolean testarCampos(String cpf, String senha) {
		if (cpf.equals("") || senha.equals("")) {
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
	
	public boolean testarCampoSenha(String senha) {
		if (senha.equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean validarConfirmacaoGerente(String cpf, String senha) {
		if(cpf.equals(GerenteAtual.getGerente().getCpf()) && Criptografar.encriptografar(senha).equals(GerenteAtual.getGerente().getSenha())) {
			return true;
		}else {
			Aviso.avisar(2);
			return false;
		}
	}
	
	public boolean confirmarDelecao(boolean varConfirmacao) {
		return varConfirmacao;
	}

}
