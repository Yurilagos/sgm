package projeto.centroOperacoes.visao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import projeto.centroOperacoes.controle.ControleSensor;
import projeto.centroOperacoes.modelo.Equipamento;
import projeto.centroOperacoes.modelo.Sensor;
import projeto.centroOperacoes.modelo.dao.EquipamentoDAO;
import projeto.centroOperacoes.modelo.dao.SensorDAO;

@ManagedBean
@ViewScoped
public class VisaoSensor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7112290386147890136L;
	
	private ArrayList<Sensor> sensores = new ArrayList<Sensor>();
	private ControleSensor service = new ControleSensor();
	private EquipamentoDAO eDAO = new EquipamentoDAO();
	private Sensor sensor = new Sensor();
	private SensorDAO sDAO = new SensorDAO();
	private Equipamento equipamento = new Equipamento();
	private ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
	
	@PostConstruct
	public void init() {
		equipamentos = eDAO.select();
		sensores = sDAO.select(equipamentos);
	}
	
		
	public void removeSensor(Sensor s) {
		sDAO.delete(s);
		sensores.remove(s);
	}
	public void cadastraSensor() {
		sDAO.insert(sensor);
	}
	
	public void alteraSensor(Sensor s) {
		service.alteraSensor(s);
	}


	
	//GETTERS e SETTERS
	public ArrayList<Sensor> getSensores() {
		return sensores;
	}


	public void setSensores(ArrayList<Sensor> sensores) {
		this.sensores = sensores;
	}


	public ControleSensor getService() {
		return service;
	}


	public void setService(ControleSensor service) {
		this.service = service;
	}


	public Sensor getSensor() {
		return sensor;
	}


	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}


	public SensorDAO getsDAO() {
		return sDAO;
	}


	public void setsDAO(SensorDAO sDAO) {
		this.sDAO = sDAO;
	}


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


	public ArrayList<Equipamento> getEquipamentos() {
		return equipamentos;
	}


	public void setEquipamentos(ArrayList<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}
	
	

}
