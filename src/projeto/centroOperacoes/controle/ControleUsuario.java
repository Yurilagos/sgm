package projeto.centroOperacoes.controle;

import java.io.UnsupportedEncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import projeto.centroOperacoes.modelo.Usuario;
import projeto.centroOperacoes.modelo.dao.UsuarioDAO;

@ManagedBean
@RequestScoped
public class ControleUsuario {

	UsuarioDAO ud = new UsuarioDAO();
	Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void cadastraUsuario() {

		ud.insert(usuario);

	}

	public void alteraUsuario(Usuario usuario) {

		ud.update(usuario);

	}

	public void removeUsuario(Usuario usuario) {
		ud.delete(usuario);

	}

	public ArrayList<Usuario> listarUsuarios() {

		return ud.select();

	}

	public Usuario getUsuarioById(Integer id) {

		return ud.selectById(id);

	}

	public Usuario getUsuario(Integer id) {
		List<Usuario> list = listarUsuarios();

		for (Usuario usuario : list) {
			if (usuario.getId() == id) {
				return usuario;

			}

		}
		return null;
	}

	/**
	 * Metodo responsável em fazer o login do usuário
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
			// Instanciamos o nosso HASH MD5, poderíamos usar outro como
			// SHA, por exemplo, mas optamos por MD5.
			mDigest = MessageDigest.getInstance("MD5");

			// Convert a String valor para um array de bytes em MD5
			byte[] valorMD5 = mDigest.digest(value.getBytes("UTF-8"));

			// Convertemos os bytes para hexadecimal, assim podemos salvar
			// no banco para posterior comparação se senhas
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
