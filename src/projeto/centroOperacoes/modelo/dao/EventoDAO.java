package projeto.centroOperacoes.modelo.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.primefaces.PrimeFaces;

import projeto.centroOperacoes.controle.ControleNavio;
import projeto.centroOperacoes.controle.ControleUsuario;
import projeto.centroOperacoes.enums.StatusEnum;
import projeto.centroOperacoes.modelo.Evento;
import projeto.centroOperacoes.modelo.Navio;

public class EventoDAO implements CrudDAO<Evento> {

	/**
	 * Esse método adiciona um novo Evento ao banco de dados
	 * 
	 * @param evento
	 */
	@Override
	public void insert(Evento evento) {
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement command = con
					.prepareStatement("INSERT INTO EVENTO (evento_data, evento_descricao, evento_status, "
							+ "navio_navio_id, usuario_usuario_id) values(?, ?, ?, ?, ?)");

			command.setDate(1, java.sql.Date.valueOf(LocalDate.now().toString()));
			command.setString(2, evento.getDescricao());
			command.setInt(3, StatusEnum.obterPorCodigo(evento.getStatus()).getCodigo());
			command.setInt(5, evento.getNavio().getId());
			command.setInt(6, evento.getUsuario().getId());

			command.executeUpdate();
			command.close();
			con.close();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvar Evento",
					"Evento Cadastrado com Sucesso!");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro",
					"Não foi possível cadastrar Evento (ERRO = " + e);
			PrimeFaces.current().dialog().showMessageDynamic(message);
			e.printStackTrace();
		}
	}

	/**
	 * Esse método atualiza informações do evento como:( name, cpf, email, address,
	 * birth date)
	 * 
	 * @param evento
	 */
	@Override
	public void update(Evento evento) {
		Connection con = ConnectionFactory.getConnection();

		try {
			PreparedStatement command = con.prepareStatement(
					"UPDATE EVENTO SET evento_data = ?, evento_descricao = ?, evento_status = ?, navio_navio_id = ?, "
							+ "usuario_usuario_id = ?  WHERE evento_id = ?");

			command.setDate(1, java.sql.Date.valueOf(evento.getData_time()));
			command.setString(2, evento.getDescricao());
			command.setInt(3, StatusEnum.obterPorCodigo(evento.getStatus()).getCodigo());
			command.setInt(4, evento.getNavio().getId());
			command.setInt(5, evento.getUsuario().getId());
			command.setInt(6, evento.getId());

			command.executeUpdate();
			command.close();
			con.close();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar Evento",
					"Evento Alterado com Sucesso!");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro",
					"Não foi possível alterar Evento (ERRO = " + e);
			PrimeFaces.current().dialog().showMessageDynamic(message);
			e.printStackTrace();
		}
	}

	/**
	 * Esse método Deleta um Evento utilizando uma classe
	 * 
	 * @param evento
	 */
	@Override
	public void delete(Evento evento) {
		Connection con = ConnectionFactory.getConnection();

		try {
			PreparedStatement command = con.prepareStatement("DELETE FROM evento WHERE evento_id = ?");

			command.setInt(1, evento.getId());
			command.executeUpdate();
			command.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Esse método seleciona todos os eventos existente no banco de daods
	 * 
	 * @return
	 */
	@Override
	public ArrayList<Evento> select() {
		Connection con = ConnectionFactory.getConnection();

		ArrayList<Evento> list = new ArrayList<Evento>();

		try {
			PreparedStatement command = con.prepareStatement("SELECT * FROM EVENTO");

			ResultSet rs = command.executeQuery();

			while (rs.next()) {

				Evento e = new Evento();
				ControleNavio gn = new ControleNavio();
				ControleUsuario gu = new ControleUsuario();

				e.setId(rs.getInt("evento_id"));
				e.setData_time(LocalDate.now());
				e.setDescricao(rs.getString("evento_descricao"));
				e.setStatus(rs.getInt("evento_status"));
				e.setNavio(gn.getNavio(rs.getInt("navio_navio_id")));
				e.setUsuario(gu.getUsuario(rs.getInt("usuario_usuario_id")));

				list.add(e);
			}

			rs.close();
			command.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return list;
	}

	
	
	
	public ArrayList<Evento> select(List<Navio> navios) {
		Connection con = ConnectionFactory.getConnection();

		ArrayList<Evento> list = new ArrayList<Evento>();

		try {
			PreparedStatement command = con.prepareStatement("SELECT * FROM EVENTO");

			ResultSet rs = command.executeQuery();

			while (rs.next()) {

				Evento e = new Evento();
				ControleNavio gn = new ControleNavio();
				ControleUsuario gu = new ControleUsuario();

				e.setId(rs.getInt("evento_id"));
				e.setData_time(LocalDate.now());
				e.setDescricao(rs.getString("evento_descricao"));
				e.setStatus(rs.getInt("evento_status"));
				
				for (Navio navio : navios) {
					if(navio.getId() == rs.getInt("navio_navio_id")) {
						e.setNavio(navio);
					}
					
				}

				list.add(e);
			}

			rs.close();
			command.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return list;
	}
	
	
	/**
	 * Esse método seleciona um Evento de acordo com seu id
	 * 
	 * @param eventoId
	 * @return
	 */
	@Override
	public Evento selectById(Integer eventoId) {
		Connection conexao = ConnectionFactory.getConnection();
		Evento evento = new Evento();
		ControleNavio gn = new ControleNavio();
		ControleUsuario gu = new ControleUsuario();

		try {
			PreparedStatement command = conexao.prepareStatement("SELECT * FROM Evento WHERE evento_id = ?");

			command.setInt(1, eventoId);

			ResultSet rs = command.executeQuery();

			evento.setId(rs.getInt("evento_id"));
			evento.setData_time(rs.getDate("evento_data").toLocalDate());
			evento.setDescricao(rs.getString("evento_descricao"));
			evento.setStatus(rs.getInt("evento_status"));
			evento.setNavio(gn.getNavioById(rs.getInt("navio_navio_id")));
			evento.setUsuario(gu.getUsuarioById(rs.getInt("usuario_usuario_id")));

			rs.close();
			command.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return evento;
	}
	
	
	public ArrayList<Evento> select(Navio navio) {
		Connection con = ConnectionFactory.getConnection();

		ArrayList<Evento> list = new ArrayList<Evento>();

		try {
			PreparedStatement command = con.prepareStatement("SELECT * FROM EVENTO Where navio_id = ?");

			
			command.setInt(1, navio.getId());
			ResultSet rs = command.executeQuery();

			while (rs.next()) {

				Evento e = new Evento();
				ControleNavio gn = new ControleNavio();
				ControleUsuario gu = new ControleUsuario();

				e.setId(rs.getInt("evento_id"));
				e.setData_time(LocalDate.now());
				e.setDescricao(rs.getString("evento_descricao"));
				e.setStatus(rs.getInt("evento_status"));
				e.setNavio(gn.getNavio(rs.getInt("navio_navio_id")));
				e.setUsuario(gu.getUsuario(rs.getInt("usuario_usuario_id")));

				list.add(e);
			}

			rs.close();
			command.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return list;
	}

}
