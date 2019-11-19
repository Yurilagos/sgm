package projeto.centroOperacoes.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import projeto.centroOperacoes.enums.StatusEnum;

@Entity(name = "sensor")
public class Sensor {

	@Id
	@Column(name = "sensor_id")
	private String id;

	@Column(name = "sensor_descricao")
	private String descricao;

	@Column(name = "sensor_status")
	private String status;

	@JoinColumn(name = "erro")
	private List<Erro> erros;

	@JoinColumn(name = "equipamento_equipamento_id")
	private Equipamento equipamento;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = StatusEnum.obterPorCodigo(status).getDescricao();
	}

	public List<Erro> getErros() {
		return erros;
	}

	public void setErros(List<Erro> erros) {
		this.erros = erros;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

}
