package br.com.projeto.estoque.controller;

import java.sql.Timestamp;

import br.com.projeto.estoque.model.RegistroSupervisor;
import br.com.projeto.estoque.model.Supervisor;
import br.com.projeto.estoque.model.TipoComportamento;
import br.com.projeto.estoque.util.Essencial;

public class ControllerRegistroSupervisor {

	public static void main(String[] args) {
		System.out.println("a");
	}

	public static Timestamp pegarDataHora() {
		Timestamp dataHora = new Timestamp(System.currentTimeMillis());
		return dataHora;
	}

	public RegistroSupervisor criarRegistroSupervisor(TipoComportamento tipoComportamento, Supervisor supervisor) {
		RegistroSupervisor registro = new RegistroSupervisor();
		registro.setSupervisor(supervisor);
		registro.setDataEHora(pegarDataHora());
		registro.setTipoComportamento(tipoComportamento);
		Essencial.getManager().persist(registro);
		return registro;
	}

}
