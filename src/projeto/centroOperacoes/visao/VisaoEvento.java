package projeto.centroOperacoes.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import projeto.centroOperacoes.controle.ControleEvento;
import projeto.centroOperacoes.modelo.Evento;
import projeto.centroOperacoes.modelo.Navio;
import projeto.centroOperacoes.modelo.Usuario;
import projeto.centroOperacoes.modelo.dao.EventoDAO;
import projeto.centroOperacoes.modelo.dao.NavioDAO;
import projeto.centroOperacoes.modelo.dao.UsuarioDAO;

@ManagedBean
@ViewScoped
public class VisaoEvento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Evento> eventos;

	private EventoDAO eDAO = new EventoDAO();
	private UsuarioDAO uDAO = new UsuarioDAO();
	private Evento evento = new Evento();
	private Navio navio = new Navio();
	private Usuario usuario = new Usuario();
	private List<Navio> navios = new ArrayList<Navio>();
	private List<Usuario> usuarioList = new ArrayList<Usuario>();
	private NavioDAO nDAO = new NavioDAO();


	@PostConstruct
	public void init() {
		
		navios = nDAO.select();
		eventos = eDAO.select(navios);
		
	}

	public void removeEvento(Evento e) {
		eDAO.delete(e);
		eventos.remove(e);
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Navio getNavio() {
		return navio;
	}

	public void setNavio(Navio navio) {
		this.navio = navio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public List<Navio> getNavios() {
		return navios;
	}

	public void setNavios(List<Navio> navios) {
		this.navios = navios;
	}
	
	

}
