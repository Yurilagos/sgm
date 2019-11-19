package projeto.centroOperacoes.controle;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import projeto.centroOperacoes.modelo.Equipamento;
import projeto.centroOperacoes.modelo.Sensor;
import projeto.centroOperacoes.modelo.dao.SensorDAO;

@ManagedBean
@RequestScoped
public class ControleSensor {

	SensorDAO sDAO = new SensorDAO();
	Sensor sensor = new Sensor();
	Equipamento equipamento = new Equipamento();
	

	public void cadastraSensor() {
		sensor.setEquipamento(equipamento);
		sDAO.insert(sensor);
	}

	public void alteraSensor(Sensor sensor) {
		sDAO.update(sensor);
	}

	public void removeSensor(Sensor sensor) {
		sDAO.delete(sensor);
	}

	public ArrayList<Sensor> listaSensor() {
		return sDAO.select();
	}

	public Sensor getSensorPorId(String id) {

		return sDAO.selectById(id);

	}

	public Sensor getSensor(String id) {
		ArrayList<Sensor> list = listaSensor();

		for (Sensor sensor : list) {
			if (sensor.getId() == id) {
				return sensor;

			}

		}
		return null;

	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	// GETTERS e SETTERS
	public SensorDAO getsDAO() {
		return sDAO;

	}

	public void setsDAO(SensorDAO sDAO) {
		this.sDAO = sDAO;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

}
