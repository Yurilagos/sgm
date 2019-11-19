package projeto.centroOperacoes.visao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import projeto.centroOperacoes.modelo.Equipamento;
import projeto.centroOperacoes.modelo.Navio;
import projeto.centroOperacoes.modelo.dao.EquipamentoDAO;
import projeto.centroOperacoes.modelo.dao.NavioDAO;

@ManagedBean
@SessionScoped
public class NavioBean {

	private Navio navio = new Navio();
	private NavioDAO nDAO = new NavioDAO();
	private List<Equipamento> equipamentos = new ArrayList<Equipamento>();
	private EquipamentoDAO eDAO;
	private List<Navio> navios = null;	
	
	
	

	public NavioDAO getnDAO() {
		return nDAO;
	}

	public void setnDAO(NavioDAO nDAO) {
		this.nDAO = nDAO;
	}

	public EquipamentoDAO geteDAO() {
		return eDAO;
	}

	public void seteDAO(EquipamentoDAO eDAO) {
		this.eDAO = eDAO;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public Navio getNavio() {
		return navio;
	}

	public void setNavio(Navio navio) {
		this.navio = navio;
	}

	public List<Navio> getNavios() {
		return navios;
	}

	public void setNavios(List<Navio> navios) {
		listaNavios();
		
	}

	public void listaNavios() {
		navios = nDAO.select();
		
	}

	public void removeNavio(Navio n) {
		nDAO.delete(n);
		navios.remove(n);
	}

	public void cadastraNavio() {
		navios = new ArrayList<Navio>();
		if (navio != null && navios.contains(navio)) {
			nDAO.update(navio);
		} else {
			nDAO.insert(navio);
		}

	}

	public void novoNavio() {
		navio = new Navio();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("paginaAdicionarNavio.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void atribuiNavio(Navio n) {
		navio = n;
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("paginaAdicionarNavio.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
