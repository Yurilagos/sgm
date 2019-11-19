package projeto.centroOperacoes.modelo.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.primefaces.PrimeFaces;

import projeto.centroOperacoes.enums.StatusEnum;
import projeto.centroOperacoes.modelo.Equipamento;
import projeto.centroOperacoes.modelo.Erro;
import projeto.centroOperacoes.modelo.Sensor;

public class ErroDAO implements CrudDAO<Erro> {

	/**
	 * Esse método adiciona um novo Usuário ao banco de dados
	 * 
	 * @param usuario
	 */
	@Override
	public void insert(Erro erro) {
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement command = con.prepareStatement(
					"INSERT INTO ERRO (erro_id, erro_nome, erro_descricao, erro_tipo, erro_status, sensor_sensor_id, sensor_equipamento_equipamento_id "
							+ ") values(?, ?, ?, ?, ?, ?, ?)");

			command.setInt(1, erro.getId());
			command.setString(2, erro.getNome());
			command.setString(3, erro.getDescricao());
			command.setString(4, erro.getTipo());
			command.setInt(5, StatusEnum.obterPorCodigo(erro.getStatus()).getCodigo());
			command.setString(6, erro.getSensor().getId());
			command.setInt(7, erro.getEquipamento().getId());

			command.executeUpdate();
			command.close();
			con.close();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvar Erro",
					"Erro Cadastrado com Sucesso!");
			PrimeFaces.current().dialog().showMessageDynamic(message);

		} catch (SQLException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvar Erro",
					"Erro Cadastrado com Sucesso!");
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
	public void update(Erro erro) {
		Connection con = ConnectionFactory.getConnection();

		try {
			PreparedStatement command = con.prepareStatement(
					"UPDATE ERRO SET erro_nome = ?, erro_descricao = ?, erro_tipo = ?, erro_status = ?, sensor_equipamento_equipamento_id = ?"
							+ "WHERE erro_id = ?");

			command.setString(1, erro.getNome());
			command.setString(2, erro.getDescricao());
			command.setString(3, erro.getTipo());
			command.setInt(4, StatusEnum.obterPorCodigo(erro.getStatus()).getCodigo());
			command.setInt(5, erro.getEquipamento().getId());

			command.executeUpdate();
			command.close();
			con.close();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar Erro",
					"Erro Alterado com Sucesso!");
			PrimeFaces.current().dialog().showMessageDynamic(message);
			
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro",
					"Não foi possível alterar Erro (ERRO = " + e);
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
	public void delete(Erro erro) {
		Connection con = ConnectionFactory.getConnection();

		try {
			PreparedStatement command = con.prepareStatement("DELETE FROM ERRO WHERE erro_id = ?");

			command.setInt(1, erro.getId());
			command.executeUpdate();
			command.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Esse método seleciona todos os Usuários existente no banco de daods
	 * 
	 * @return ArrayList<Usuario>
	 */
	@Override
	public ArrayList<Erro> select() {

		Connection con = ConnectionFactory.getConnection();
		EquipamentoDAO eDAO = new EquipamentoDAO();
		ArrayList<Equipamento> equipamentos = eDAO.select();
		SensorDAO sDAO = new SensorDAO();
		ArrayList<Sensor> sensores = sDAO.select(equipamentos);

		
		ArrayList<Erro> list = new ArrayList<Erro>();

		try {
			PreparedStatement command = con.prepareStatement("SELECT * FROM ERRO");

			ResultSet rs = command.executeQuery();

			while (rs.next()) {
				Erro er = new Erro();
				er.setId(rs.getInt("erro_id"));
				er.setNome(rs.getString("erro_nome"));
				er.setDescricao(rs.getString("erro_descricao"));
				er.setTipo(rs.getString("erro_tipo"));
				er.setStatus(rs.getInt("erro_status"));

				sensor:for (Sensor sensor : sensores) {
					if (sensor.getId().equals(rs.getString("sensor_sensor_id"))) {
						er.setSensor(sensor);
						break sensor;
					}
				}

				equip:for (Equipamento equipamento : equipamentos) {
					if (equipamento.getId() == rs.getInt("sensor_equipamento_equipamento_id")) {
						er.setEquipamento(equipamento);
						break equip;
					}
				}
				list.add(er);

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
	public Erro selectById(Integer erroId) {
		Connection conexao = ConnectionFactory.getConnection();
		Erro erro = new Erro();
		SensorDAO sDAO = new SensorDAO();
		EquipamentoDAO eDAO = new EquipamentoDAO();

		try {
			PreparedStatement command = conexao.prepareStatement("SELECT * FROM ERRO WHERE erro_id = ?");

			command.setInt(1, erroId);

			ResultSet rs = command.executeQuery();

			erro.setId(rs.getInt("erro_id"));
			erro.setNome(rs.getString("erro_nome"));
			erro.setDescricao(rs.getString("erro_descricao"));
			erro.setId(rs.getInt("erro_tipo"));
			erro.setStatus(rs.getInt("erro_status"));
			erro.setSensor(sDAO.selectById(rs.getString("sensor_sensor_id")));
			erro.setEquipamento(eDAO.selectById(rs.getInt("sensor_equipamento_equipamento_id")));

			rs.close();
			command.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return erro;
	}

}
