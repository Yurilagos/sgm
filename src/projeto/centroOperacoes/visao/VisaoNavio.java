package projeto.centroOperacoes.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import projeto.centroOperacoes.controle.ControleNavio;
import projeto.centroOperacoes.modelo.Equipamento;
import projeto.centroOperacoes.modelo.Navio;
import projeto.centroOperacoes.modelo.dao.EquipamentoDAO;
import projeto.centroOperacoes.modelo.dao.NavioDAO;

@ManagedBean
@ViewScoped
public class VisaoNavio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Navio> navios;
	
	private ControleNavio service;
	private NavioDAO nDAO = new NavioDAO();
	private Navio navio = new Navio();
	private ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
	private EquipamentoDAO eDAO = new EquipamentoDAO();
	
	
	@PostConstruct
	public void init() {
		equipamentos = eDAO.select();
		navios = nDAO.select();
	}
	
	public void removeNavio(Navio n) {
		nDAO.delete(n);
		navios.remove(n);
	}
	
	public void alteraNavio(Navio n) {
		nDAO.update(n);
	}

	public List<Navio> getNavios() {
		return navios;
	}

	public void setNavios(List<Navio> navios) {
		this.navios = navios;
	}

	public ControleNavio getService() {
		return service;
	}

	public void setService(ControleNavio service) {
		this.service = service;
	}


	public Navio getNavio() {
		return navio;
	}

	public void setNavio(Navio navio) {
		this.navio = navio;
	}

	public ArrayList<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(ArrayList<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}
	
	

}
