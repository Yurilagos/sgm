package projeto.centroOperacoes.controle;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import projeto.centroOperacoes.modelo.Evento;
import projeto.centroOperacoes.modelo.Navio;
import projeto.centroOperacoes.modelo.Usuario;

@ManagedBean
@RequestScoped
public class EventoBean {
	
	
	ControleEvento controle = new ControleEvento();
	ControleNavio controlen = new ControleNavio();
	ControleUsuario controleu = new ControleUsuario();
	Evento evento = new Evento();
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public void cadastraEventoBean() {
		controle.cadastraEvento(evento);
	}
	
	public Navio getNavioById(Integer id) {
		return controlen.getNavio(id);
	}
	
	public Usuario getUsuarioById(Integer id) {
		return controleu.getUsuario(id);
	}
}
