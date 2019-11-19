package projeto.centroOperacoes.visao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import projeto.centroOperacoes.modelo.Equipamento;
import projeto.centroOperacoes.modelo.dao.EquipamentoDAO;

@ManagedBean
@ViewScoped
public class VisaoEquipamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2702686422166977095L;
	
	
	private Equipamento equipamento = new Equipamento();
	private ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
	private EquipamentoDAO eDAO = new EquipamentoDAO();
	
	
	@PostConstruct
	public void init() {
		equipamentos = eDAO.select();
	}

	public void removeEquipamento(Equipamento e) {
		eDAO.delete(e);
		equipamentos.remove(e);
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}


	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}


	public ArrayList<Equipamento> getEquipamentos() {
		return equipamentos;
	}


	public void setEquipamentos(ArrayList<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}


	public EquipamentoDAO geteDAO() {
		return eDAO;
	}


	public void seteDAO(EquipamentoDAO eDAO) {
		this.eDAO = eDAO;
	}
	
	
	
}
