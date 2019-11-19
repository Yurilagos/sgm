package projeto.centroOperacoes.modelo.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.primefaces.PrimeFaces;

import projeto.centroOperacoes.modelo.Usuario;

public class UsuarioDAO implements CrudDAO<Usuario> {

	/**
	 * Esse método adiciona um novo Usuário ao banco de dados
	 * 
	 * @param usuario
	 */
	@Override
	public void insert(Usuario usuario) {
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement command = con
					.prepareStatement("INSERT INTO USuARIO (usuario_cpf, usuario_nome, usuario_sobrenome, "
							+ "usuario_funcao, usuario_login, usuario_senha, usuario_status) values(?, ?, ?, ?, ?, ?, ?)");

			command.setString(1, usuario.getCpf());
			command.setString(2, usuario.getNome());
			command.setString(3, usuario.getSobrenome());
			command.setString(4, usuario.getFuncao());
			command.setString(5, usuario.getLogin());
			command.setString(6, usuario.getSenha());
			command.setString(7, usuario.getStatus());

			command.executeUpdate();
			command.close();
			con.close();

			// Mensagem confirmação de cadastro
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvar Usuário",
					"Usuário Cadastrado com Sucesso!");
			PrimeFaces.current().dialog().showMessageDynamic(message);
				

		} catch (SQLException e) {

			// Mensagem erro de cadastro
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro",
					"Não foi possível cadastrar Usuário (ERRO = " + e);
			PrimeFaces.current().dialog().showMessageDynamic(message);

			e.printStackTrace();
		}

	}

	/**
	 * Esse método atualiza informações do Usuário como:( Nome e status)
	 * 
	 * @param usuario
	 */
	@Override
	public void update(Usuario usuario) {
		Connection con = ConnectionFactory.getConnection();

		try {
			PreparedStatement command = con.prepareStatement(
					"UPDATE USUARIO SET usuario_cpf = ?, usuario_nome = ?, usuario_sobrenome = ?, usuario_funcao = ?, "
							+ "usuario_login = ?, usuario_senha = ?, usuario_status = ? WHERE usuario_id = ?");

			command.setString(1, usuario.getCpf());
			command.setString(2, usuario.getNome());
			command.setString(3, usuario.getSobrenome());
			command.setString(4, usuario.getFuncao());
			command.setString(5, usuario.getLogin());
			command.setString(6, usuario.getSenha());
			command.setString(7, usuario.getStatus());
			command.setInt(8, usuario.getId());

			command.executeUpdate();
			command.close();
			con.close();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar Usuário",
					"Usuário Alterado com Sucesso!");
			PrimeFaces.current().dialog().showMessageDynamic(message);

			
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro",
					"Não foi possível Alterar Usuário (ERRO = " + e);
			PrimeFaces.current().dialog().showMessageDynamic(message);
			
			e.printStackTrace();
		}
	}

	/**
	 * Esse método Deleta um Usuário utilizando uma classe
	 * 
	 * @param usuario
	 */
	@Override
	public void delete(Usuario usuario) {
		Connection con = ConnectionFactory.getConnection();

		try {
			PreparedStatement command = con.prepareStatement("DELETE FROM USUARIO WHERE usuario_id = ?");

			command.setInt(1, usuario.getId());
			command.executeUpdate();
			command.close();
			con.close();

			// Mensagem confirmação de Exclusão
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluir Usuário",
					"Usuário: " + usuario.getNome() + "Excluído com Sucesso!");
			PrimeFaces.current().dialog().showMessageDynamic(message);

		} catch (SQLException e) {

			// Mensagem Erro de Exclusão
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro",
					"Não foi possível excluir o Usuário " + usuario.getNome() + "(Erro = " + e);
			PrimeFaces.current().dialog().showMessageDynamic(message);

			e.printStackTrace();
		}
	}

	/**
	 * Esse método seleciona todos os Usuários existente no banco de daods
	 * 
	 * @return ArrayList<Usuario>
	 */
	@Override
	public ArrayList<Usuario> select() {

		Connection con = ConnectionFactory.getConnection();

		ArrayList<Usuario> list = new ArrayList<Usuario>();

		try {
			PreparedStatement command = con.prepareStatement("SELECT * FROM USUARIO");

			ResultSet rs = command.executeQuery();

			while (rs.next()) {

				Usuario u = new Usuario();

				u.setId(rs.getInt("usuario_id"));
				u.setCpf(rs.getString("usuario_cpf"));
				u.setNome(rs.getString("usuario_nome"));
				u.setSobrenome(rs.getString("usuario_sobrenome"));
				u.setFuncao(rs.getString("usuario_funcao"));
				u.setLogin(rs.getString("usuario_login"));
				u.setSenha(rs.getString("usuario_senha"));
				u.setStatus(rs.getString("usuario_status"));

				list.add(u);
			}

			rs.close();
			command.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return list;
	}

	/**
	 * Esse método seleciona um Usuário de acordo com seu id
	 * 
	 * @param usuarioId
	 * @return Usuario
	 */
	@Override
	public Usuario selectById(Integer usuarioId) {
		Connection conexao = ConnectionFactory.getConnection();
		Usuario usuario = new Usuario();

		try {
			PreparedStatement command = conexao.prepareStatement("SELECT * FROM USUARIO WHERE usuario_id = ?");

			command.setInt(1, usuarioId);

			ResultSet rs = command.executeQuery();
			while (rs.next()) {
				usuario.setId(rs.getInt("usuario_id"));
				usuario.setCpf(rs.getString("usuario_cpf"));
				usuario.setNome(rs.getString("usuario_nome"));
				usuario.setSobrenome(rs.getString("usuario_sobrenome"));
				usuario.setFuncao(rs.getString("usuario_funcao"));
				usuario.setLogin(rs.getString("usuario_login"));
				usuario.setSenha(rs.getString("usuario_senha"));
				usuario.setStatus(rs.getString("usuario_status"));

			}

			rs.close();
			command.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return usuario;
	}

	public Usuario validaLogin(String user, String pass) {
		pass = encriptaSenha(pass);
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		Query query = manager.createNamedQuery("AUTENTICA_USUARIO", Usuario.class);
		query.setParameter("email", user);
		query.setParameter("senha", pass);
		Usuario usuario;
		try {
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			usuario = null;
		}
		return usuario;
	}

	private String encriptaSenha(String pass) {
		MessageDigest md = null;
		String retorno = "";
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		md.update(pass.getBytes());
		byte[] hashMd5 = md.digest();
		for (int i = 0; i < hashMd5.length; i++) {
			retorno += hashMd5[i];
		}
		retorno = retorno.replaceAll("-", "");
		return retorno;
	}

	public Usuario recuperaUsuarioPorLogin(String usuario_id) throws SQLException {

		Connection conexao = ConnectionFactory.getConnection();
		PreparedStatement stmt = conexao.prepareStatement("select * from Usuario where usuario_login=?");

		try {

			stmt.setString(1, usuario_id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Usuario usuario = criaUsuario(rs);
				rs.close();
				return usuario;
			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conexao != null) {
				conexao.close();
			}
		}
		return null;
	}

	public List<Usuario> autenticaUsuario(String usuario_id) throws SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		PreparedStatement stmt = conexao.prepareStatement("select * from Usuario where usuario_login=?");
		List<Usuario> usuarios = new ArrayList<>();
		try {
			stmt.setString(1, usuario_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Usuario u = criaUsuario(rs);
				usuarios.add(u);
			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conexao != null) {
				conexao.close();
			}
		}
		return usuarios;
	}

	public Usuario criaUsuario(ResultSet rs) throws SQLException {
		Usuario u = new Usuario();
		u.setLogin(rs.getString("usuario_login"));
		u.setSenha(rs.getString("usuario_senha"));
		u.setNome(rs.getString("usuario_nome"));
		u.setFuncao(rs.getString("usuario_funcao"));
		u.setStatus(rs.getString("usuario_status"));
		return u;
	}

}
