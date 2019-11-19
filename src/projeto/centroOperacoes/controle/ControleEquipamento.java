package projeto.centroOperacoes.controle;

import java.util.ArrayList;

import projeto.centroOperacoes.modelo.Equipamento;
import projeto.centroOperacoes.modelo.Navio;
import projeto.centroOperacoes.modelo.dao.EquipamentoDAO;

public class ControleEquipamento {
	
	EquipamentoDAO eDAO = new EquipamentoDAO();
	Equipamento equipamento = new Equipamento();
	
	
	
	
	
	//Controle metodos
	
	public void cadastraEquipamento() {

		eDAO.insert(equipamento);

	}

	public void alteraEquipamento(Equipamento equipamento) {

		eDAO.update(equipamento);

	}

	public void removeEquipamento(Equipamento equipamento) {

		eDAO.delete(equipamento);

	}

	public ArrayList<Equipamento> listarEquipamento() {

		return eDAO.select();

	}

	public Equipamento getEquipamentoById(Integer id) {

		return eDAO.selectById(id);

	}
	
	
	//GETTERS e SETTERs
	
	public EquipamentoDAO geteDAO() {
		return eDAO;
	}
	public void seteDAO(EquipamentoDAO eDAO) {
		this.eDAO = eDAO;
	}
	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	
	
	public Equipamento getEquipamento(Integer id) {
		ArrayList<Equipamento> list = listarEquipamento();

		for (Equipamento equipamento : list) {
			if (equipamento.getId() == id) {
				return equipamento;

			}

		}
		return null;

	}
	
}
