package projeto.centroOperacoes.visao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import projeto.centroOperacoes.modelo.Equipamento;
import projeto.centroOperacoes.modelo.Sensor;
import projeto.centroOperacoes.modelo.dao.SensorDAO;

@ManagedBean
@SessionScoped
public class SensorBean {

		
		public Sensor sensor = new Sensor();
		SensorDAO sd = new SensorDAO();
		private Equipamento equipamento = new Equipamento();
		
		public List<Sensor> sensores = new ArrayList<Sensor>();
		

		public Sensor getSensor() {
			return sensor;
		}

		public void setSensor(Sensor sensor) {
			this.sensor = sensor;
		}

		public void listaSensores() {
			
			sensores = sd.select();
		}
		
		public void setSensores(List<Sensor> sensores) {
			listaSensores();
		}

		public void removeSensor(Sensor s) {
			sd.delete(s);
			sensores.remove(s);
		}


		public List<Sensor> getSensores() {
			return sensores;
		}

		
		public void cadastraSensor() {
			if(sensor!=null && sensores.contains(sensor)) {
				sd.update(sensor);
			} else {
				sd.insert(sensor);
			}
		}
		
		public void novoSensor() {
			sensor = new Sensor();
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("paginaAdicionarSensor.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
				}
			}
		
		public void atribuiSensor(Sensor s) {
			sensor=s;
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("paginaAdicionarSensor.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public Equipamento getEquipamento() {
			return equipamento;
		}

		public void setEquipamento(Equipamento equipamento) {
			this.equipamento = equipamento;
		}
		
		
	}	
	

