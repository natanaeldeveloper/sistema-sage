package br.com.projeto.estoque.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categoria")
public class Categoria {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false, unique = true, length = 100)
	private String nome;
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getId() {
		return this.id;
	}
}
