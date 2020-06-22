package br.com.projeto.estoque.util;

import br.com.projeto.estoque.controller.ControllerCategoria;
import br.com.projeto.estoque.controller.ControllerGrupo;

public class Factorys {
	ControllerCategoria cc = new ControllerCategoria();
	ControllerGrupo cg = new ControllerGrupo();
	static Factorys f = new Factorys();
	
	public static void main(String[]args) {
		f.categoriaFactory();
		f.grupoFactory();
	}
	
	public void categoriaFactory() {
		cc.criarCategoria("categoria1");
	}
	public void grupoFactory() {
		cg.cadastrarGrupo("grupo1","descrição grupo 1",1,1,1000);
	}
}
