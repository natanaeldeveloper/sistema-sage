package br.com.projeto.estoque.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.projeto.estoque.model.Gerente;
import br.com.projeto.estoque.model.RegistroGerente;
import br.com.projeto.estoque.model.TipoComportamento;
import br.com.projeto.estoque.util.Essencial;

public class ControllerRegistroGerente {
	public static Timestamp pegarDataHora() {
		Timestamp dataHora = new Timestamp(System.currentTimeMillis());
		return dataHora;
	}

	public RegistroGerente criarRegistroGerente(TipoComportamento tipoComportamento, Gerente gerente) {
		RegistroGerente registro = new RegistroGerente();
		registro.setGerente(gerente);
		registro.setDataEHora(pegarDataHora());
		registro.setTipoComportamento(tipoComportamento);
		Essencial.getManager().persist(registro);
		return registro;
	}
	

}