package projeto.centroOperacoes.visao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import projeto.centroOperacoes.controle.ControleUsuario;
import projeto.centroOperacoes.modelo.Usuario;
import projeto.centroOperacoes.modelo.dao.UsuarioDAO;

@ManagedBean
@ViewScoped
public class VisaoUsuario implements Serializable {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 3807470002194096343L;

	private List<Usuario> usuarios;
	
	private ControleUsuario service;
	private UsuarioDAO uDAO = new UsuarioDAO();
	private Usuario usuario = new Usuario();
	
	@PostConstruct
	public void init() {
		usuarios = uDAO.select();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ControleUsuario getService() {
		return service;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		init();
	}

	public void removeUsuario(Usuario u) {
		uDAO.delete(u);
		usuarios.remove(u);
	}
	
	public List<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public void setService(ControleUsuario service) {
		this.service = service;
	}

}
