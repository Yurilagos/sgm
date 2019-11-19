package projeto.centroOperacoes.visao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import projeto.centroOperacoes.controle.ControleErro;
import projeto.centroOperacoes.modelo.Equipamento;
import projeto.centroOperacoes.modelo.Erro;
import projeto.centroOperacoes.modelo.Sensor;
import projeto.centroOperacoes.modelo.dao.EquipamentoDAO;
import projeto.centroOperacoes.modelo.dao.ErroDAO;
import projeto.centroOperacoes.modelo.dao.SensorDAO;

@ManagedBean
@ViewScoped
public class VisaoErro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Erro> erros;
	private ErroDAO erDAO = new ErroDAO();
	private Erro erro = new Erro();

	@PostConstruct
	public void init() {
		erros = erDAO.select();
	}
	
	

	public ArrayList<Erro> getErros() {
		return erros;
	}

	public void setErros(ArrayList<Erro> erros) {
		this.erros = erros;
	}

	public Erro getErro() {
		return erro;
	}

	public void setErro(Erro erro) {
		this.erro = erro;
	}

	public void removeErros(Erro er) {
		erDAO.delete(er);
		erros.remove(er);
	}

}
