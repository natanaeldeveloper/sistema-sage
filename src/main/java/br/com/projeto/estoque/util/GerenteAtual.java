package br.com.projeto.estoque.util;

import br.com.projeto.estoque.model.Gerente;

public class GerenteAtual {

	private static Gerente gerente;

	public static Gerente getGerente() {
		return gerente;
	}

	public static void setGerente(Gerente gerente) {
		GerenteAtual.gerente = gerente;
	}

}
