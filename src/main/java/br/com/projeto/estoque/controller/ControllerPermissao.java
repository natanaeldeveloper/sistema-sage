package br.com.projeto.estoque.controller;

import br.com.projeto.estoque.model.Nivel;
import br.com.projeto.estoque.util.Aviso;
import br.com.projeto.estoque.util.GerenteAtual;
import br.com.projeto.estoque.util.SupervisorAtual;

public class ControllerPermissao {
	private boolean verificarPermissao(Nivel nivelAcess) {
		if (nivelAcess == Nivel.RESTRITO) {
			Aviso.avisar(4);
			return false;
		}else{
			return true;
		} 
	}

	public boolean chamarVerificacao() {
		boolean verificacao_aceita;
		if(SupervisorAtual.getSupervisor()!=null) {
			verificacao_aceita = verificarPermissao(SupervisorAtual.getSupervisor().getNivel());
		}else {
			verificacao_aceita = verificarPermissao(GerenteAtual.getGerente().getNivel());
		}
		try {
			
		} catch (NullPointerException e) {
			System.out.println("");
		}
		return verificacao_aceita;
	}
}
