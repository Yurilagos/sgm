package projeto.centroOperacoes.controle;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import projeto.centroOperacoes.modelo.Evento;
import projeto.centroOperacoes.modelo.dao.EventoDAO;

@ManagedBean
@RequestScoped
public class ControleEvento {

	EventoDAO ed = new EventoDAO();

	public void cadastraEvento(Evento e) {
		ed.insert(e);
	}

	public void alteraEvento(Evento evento) {
		ed.update(evento);

	}

	public void removeEvento(Evento evento) {
		ed.delete(evento);

	}

	public ArrayList<Evento> listarEventos() {

		return ed.select();
	}

	public Evento getEventoById(Integer id) {

		return ed.selectById(id);

	}
	public Evento getEvento(Integer id) {
		ArrayList<Evento> list = listarEventos();
		for (Evento evento : list) {
			if (evento.getId() == id) {
				return evento;
				
			}
			
		}return null;
	}

}
