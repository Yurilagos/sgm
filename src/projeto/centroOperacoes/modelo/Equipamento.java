package projeto.centroOperacoes.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import projeto.centroOperacoes.enums.StatusEnum;

@Entity(name = "equipamento")
public class Equipamento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "equipamento_id")
	private Integer id;
	
	@Column(name = "equipamento_descricao")
	private String descricao;
	
	@Column(name = "equipamento_status")
	private String status;
	
	@ManyToMany
	private List<Sensor> Sensores;
	
	private String zona;
	
	private Navio navio;
	
	private String statusAlocacao;
	
	
	

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public Navio getNavio() {
		return navio;
	}

	public void setNavio(Navio navio) {
		this.navio = navio;
	}

	public String getStatusAlocacao() {
		return statusAlocacao;
	}

	public void setStatusAlocacao(String statusAlocacao) {
		this.statusAlocacao = statusAlocacao;
	}

	public List<Sensor> getSensores() {
		return Sensores;
	}

	public void setSensores(List<Sensor> sensores) {
		Sensores = sensores;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	
	
	
}
