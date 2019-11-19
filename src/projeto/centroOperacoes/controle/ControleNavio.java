package projeto.centroOperacoes.controle;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import projeto.centroOperacoes.modelo.Equipamento;
import projeto.centroOperacoes.modelo.Navio;
import projeto.centroOperacoes.modelo.dao.NavioDAO;

@ManagedBean
@RequestScoped
public class ControleNavio {
	private NavioDAO nd = new NavioDAO();
	private Navio navio = new Navio();
	private ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
	

	public Navio getNavio() {
		return navio;
	}

	public void setNavio(Navio navio) {
		this.navio = navio;
	}

	public void cadastraNavio() {
		nd.insert(navio);
		if(equipamentos != null) {
		}

	}

	public void alteraNavio(Navio navio) {

		nd.update(navio);

	}

	public void removeNavio(Navio navio) {

		nd.delete(navio);

	}

	public ArrayList<Navio> listarnavios() {

		return nd.select();

	}

	public Navio getNavioById(Integer id) {

		return nd.selectById(id);

	}

	public Navio getNavio(Integer id) {
		ArrayList<Navio> list = listarnavios();

		for (Navio navio : list) {
			if (navio.getId() == id) {
				return navio;

			}

		}
		return null;

	}

	public ArrayList<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(ArrayList<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

}
