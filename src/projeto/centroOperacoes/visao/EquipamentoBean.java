package projeto.centroOperacoes.visao;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import projeto.centroOperacoes.modelo.Equipamento;
import projeto.centroOperacoes.modelo.dao.EquipamentoDAO;

@ManagedBean
@SessionScoped
public class EquipamentoBean {
	
	public Equipamento equipamento = new Equipamento();
	EquipamentoDAO ed = new EquipamentoDAO();
	
	public List<Equipamento> equipamentos;
	
	
	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setUsuario(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public void listaEquipamentos() {
		equipamentos = ed.select();
	}
	
	public void setEquipamentos(List<Equipamento> equipamentos) {
		listaEquipamentos();
	}

	public void removeEquipamento(Equipamento e) {
		ed.delete(e);
		equipamentos.remove(e);
	}
	
	public List<Equipamento> getEquipamentos(){
		return equipamentos;
	}
	
	public void cadastraEquipamento() {
		if(equipamento!=null && equipamentos.contains(equipamento)) {
			ed.update(equipamento);
		} else {
			ed.insert(equipamento);
		}
	}
	
	public void novoEquipamento() {
		equipamento= new Equipamento();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("paginaAdicionarEquipamento.xhtml");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void atribuiEquipamento(Equipamento e) {
		equipamento=e;
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("paginaAdicionarEquipamento.xhtml");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
