package br.com.projeto.estoque.model;

public class Usuario {
	private Object usuario;
	private Integer id;
	private String cpf;
	private String senha;
	
	public Object getUsuario() {
		return usuario;
	}
	public void setUsuario(Object usuario) {
		this.usuario = usuario;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
