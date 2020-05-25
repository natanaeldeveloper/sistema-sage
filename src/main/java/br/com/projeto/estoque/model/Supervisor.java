package br.com.projeto.estoque.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedQuery;

@NamedQuery(name="buscarSupervisor", query="select s from Supervisor s where s.cpf=:Scpf")
@Entity
public class Supervisor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private Nivel nivel = Nivel.RESTRITO;
	@Column(unique=true, nullable=false)
	private String cpf;
	private String senha;

	@OneToMany(mappedBy="supervisor", cascade = CascadeType.ALL)
	private List<RegistroSupervisor> registros;

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

	public List<RegistroSupervisor> getRegistros() {
		return registros;
	}

	public void setRegistros(List<RegistroSupervisor> registros) {
		this.registros = registros;
	}
	
	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	
}
