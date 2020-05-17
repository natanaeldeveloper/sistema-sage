package br.com.projeto.estoque.util;

public class RecursoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException() {
		System.out.println("Recurso não encontrado");
	}
}
