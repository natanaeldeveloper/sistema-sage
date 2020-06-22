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
		cc.criarCategoria("categoria2");
		cc.criarCategoria("categoria3");
		cc.criarCategoria("categoria4");
		cc.criarCategoria("categoria5");
	}
	public void grupoFactory() {
//		cg.cadastrarGrupo("grupo1","descrição grupo 1",1,1.0,"Kg");
//		cg.cadastrarGrupo("grupo2","descrição grupo 2",2,2.5,"M");
//		cg.cadastrarGrupo("grupo3","descrição grupo 3",3,3.4,"mm");
//		cg.cadastrarGrupo("grupo4","descrição grupo 4",4,0.9,"g");
//		cg.cadastrarGrupo("grupo5","descrição grupo 5",5,7.5,"m²");
	}
}
