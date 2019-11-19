package projeto.centroOperacoes.visao;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import projeto.centroOperacoes.controle.ControleUsuario;
import projeto.centroOperacoes.modelo.Usuario;

@ManagedBean
@RequestScoped
public class LoginView {

	private Usuario usuario;
	private String login;
	private String senha;
 

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}





	public void login(ActionEvent event) {
		try {    	
	        RequestContext context = RequestContext.getCurrentInstance();
	        FacesMessage mensagem = null;
	        boolean estaLogado = false;
	        
	        ControleUsuario controleUsuario = new ControleUsuario();
	        usuario = controleUsuario.conectaUsuarioLogin(login, senha);
	        
	        if (usuario == null) {
	            FacesContext.getCurrentInstance().validationFailed();
	            estaLogado = false;
	            mensagem = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro de Login", "Acesso inválido");	          
	        }else{          
	        		estaLogado = true;
					FacesContext.getCurrentInstance().getExternalContext().redirect("main.xhtml");
					mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem vindo ", usuario.getNome());
	        }
	        	        
		} catch (Exception e) {
			e.printStackTrace();
		}        
	}  

}