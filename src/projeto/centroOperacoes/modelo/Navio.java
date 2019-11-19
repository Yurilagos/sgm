package projeto.centroOperacoes.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import projeto.centroOperacoes.enums.StatusEnum;

@Entity(name = "navio")

public class Navio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "navio_id")
	private Integer id;

	@Column(name = "navio_nome")
	private String nome;

	@Column(name = "navio_status")
	private String status;

	private ArrayList<Equipamento> equipamentos;

	public ArrayList<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(ArrayList<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = StatusEnum.obterPorCodigo(status).getDescricao();
	}

}
