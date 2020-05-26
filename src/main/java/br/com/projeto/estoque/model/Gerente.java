package br.com.projeto.estoque.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedQuery;
@NamedQuery(name="buscarGerente", query="select g from Gerente g where g.cpf=:Gcpf")
@NamedQuery(name="buscarGerentes", query="select g from Gerente g")
@Entity
public class Gerente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private Nivel nivel = Nivel.GERAL;
	@Column(unique=true, nullable=false)
	private String cpf;
	@Column(nullable=false)
	private String senha;
	@OneToMany(mappedBy="gerente")
	private List<RegistroGerente> registros;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Nivel getNivel() {
		return nivel;
	}
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
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