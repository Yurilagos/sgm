package projeto.centroOperacoes.visao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import projeto.centroOperacoes.modelo.Usuario;
import projeto.centroOperacoes.modelo.dao.UsuarioDAO;

@ManagedBean
@SessionScoped
public class UsuarioBean {
	
	public Usuario usuario = new Usuario();
	UsuarioDAO ud = new UsuarioDAO();
	
	public List<Usuario> usuarios;
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void listaUsuarios() {
		usuarios = ud.select();
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		listaUsuarios();
	}

	public void removeUsuario(Usuario u) {
		ud.delete(u);
		usuarios.remove(u);
	}
	
	public List<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public void cadastraUsuario() {
		if(usuario!=null && usuarios.contains(usuario)) {
			ud.update(usuario);
		} else {
			ud.insert(usuario);
		}
		
	}
	
	public void novoUsuario() {
		usuario = new Usuario();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("paginaAdicionarUsuario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void atribuiUsuario(Usuario u) {
		usuario=u;
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("paginaAdicionarUsuario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Metodo respons?vel em fazer o login do usu?rio
	 * 
	 * @param login
	 * @param pass
	 * @return
	 */
	public Usuario conectaUsuarioLogin(String login, String pass) {
		try {
			login = login.toLowerCase().trim();

			List<Usuario> query = ud.autenticaUsuario(login);

			if (query.size() == 1) {
				// if ( query.get(0).getSenha().equals(pass)){
				if (query.get(0).getSenha().equals(convertStringToMd5(pass))) {
					Usuario usuarioEncontrado = (Usuario) query.get(0);
					// Set user object in session
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggeduser",
							usuarioEncontrado);

					return usuarioEncontrado;
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String convertStringToMd5(String value) {
		MessageDigest mDigest;
		try {
			// Instanciamos o nosso HASH MD5, poder?amos usar outro como
			// SHA, por exemplo, mas optamos por MD5.
			mDigest = MessageDigest.getInstance("MD5");

			// Convert a String valor para um array de bytes em MD5
			byte[] valorMD5 = mDigest.digest(value.getBytes("UTF-8"));

			// Convertemos os bytes para hexadecimal, assim podemos salvar
			// no banco para posterior compara??o se senhas
			StringBuffer sb = new StringBuffer();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}

			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

	}
}
