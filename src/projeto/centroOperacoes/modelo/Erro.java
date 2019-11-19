package projeto.centroOperacoes.modelo;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import projeto.centroOperacoes.enums.StatusEnum;

@Entity(name="erro")
public class Erro {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "erro_id")
	private Integer id;
	
	@Column(name = "erro_nome")
	private String nome;
	
	@Column(name = "erro_descricao")
	private String descricao;
	
	@Column(name = "erro_tipo")
	private String tipo;
	
	@Column(name = "erro_status")
	private String status;
	
	@Column(name = "sensor_sensor_id")
	private Sensor sensor;
	
	private Equipamento equipamento;
	
	
	

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = StatusEnum.obterPorCodigo(status).getDescricao();
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
	

}
