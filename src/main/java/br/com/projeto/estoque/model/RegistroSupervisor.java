package br.com.projeto.estoque.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RegistroSupervisor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private Supervisor supervisor;
	@Column(nullable=false)
	private Timestamp dataEHora;
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoComportamento tipoComportamento;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Supervisor getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}
	public Timestamp getDataEHora() {
		return dataEHora;
	}
	public void setDataEHora(Timestamp dataEHora) {
		this.dataEHora = dataEHora;
	}
	public TipoComportamento getTipoComportamento() {
		return tipoComportamento;
	}
	public void setTipoComportamento(TipoComportamento tipoComportamento) {
		this.tipoComportamento = tipoComportamento;
	}

}
