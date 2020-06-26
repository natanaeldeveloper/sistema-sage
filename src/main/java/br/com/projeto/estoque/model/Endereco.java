package br.com.projeto.estoque.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//Essa classe não tem uma tabela no banco, apenas adiciona suas colunas na tabela necessária (Fornecedor, no caso)
@Embeddable
public class Endereco {
	@Column(nullable = false, length = 20)
	private String estado;
	@Column(nullable = false, length = 30)
	private String cidade;
	@Column(nullable = false, length = 30)
	private String bairro;
	@Column(nullable = false, length = 100)
	private String logradouro;
	@Column(nullable = false, length = 20)
	private String cep;
	@Column(nullable = false, length = 20)
	private String numero;
	private String complemento;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
