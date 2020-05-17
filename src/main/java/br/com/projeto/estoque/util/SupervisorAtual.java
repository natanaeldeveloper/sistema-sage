package br.com.projeto.estoque.util;

import br.com.projeto.estoque.model.Supervisor;

public class SupervisorAtual {
	private static Supervisor supervisor;

	public static Supervisor getSupervisor() {
		return supervisor;
	}

	public static void setSupervisor(Supervisor supervisor) {
		SupervisorAtual.supervisor = supervisor;
	}
}
