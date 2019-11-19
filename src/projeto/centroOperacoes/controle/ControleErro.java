package projeto.centroOperacoes.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.PrimeFaces;

import projeto.centroOperacoes.modelo.Equipamento;
import projeto.centroOperacoes.modelo.Erro;
import projeto.centroOperacoes.modelo.Sensor;
import projeto.centroOperacoes.modelo.dao.ErroDAO;


@ManagedBean
@RequestScoped
public class ControleErro {

	private ErroDAO erd = new ErroDAO();
	private Erro Erro = new Erro();
	
	
	
	public Erro getErro() {
		return Erro;
	}

	public void setErro(Erro Erro) {
		this.Erro = Erro;
	}

	public void cadastraErro() {

		erd.insert(Erro);
		
		//Mensagem confirmação de cadastro
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvar Erro", "Erro Cadastrado com Sucesso!");
		PrimeFaces.current().dialog().showMessageDynamic(message);
	      
	}

	public void alteraErro(Erro Erro) {

		erd.update(Erro);

	}

	public void removeEquipamento(Erro Erro) {
		erd.delete(Erro);

	}

	public ArrayList<Erro> listarErros() {

		return erd.select();

	}

	public Erro getErroById(Integer id) {

		return erd.selectById(id);

	}

	public Erro getErro(Integer id) {
		List<Erro> list = listarErros();

		for (Erro Erro : list) {
			if (Erro.getId() == id) {
				return Erro;

			}

		}
		return null;
	}

	
	

}
