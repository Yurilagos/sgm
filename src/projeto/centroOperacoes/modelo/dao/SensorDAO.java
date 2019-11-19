package projeto.centroOperacoes.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.primefaces.PrimeFaces;

import projeto.centroOperacoes.enums.StatusEnum;
import projeto.centroOperacoes.modelo.Equipamento;
import projeto.centroOperacoes.modelo.Navio;
import projeto.centroOperacoes.modelo.Sensor;

public class SensorDAO implements CrudDAO<Sensor> {

	@Override
	public void insert(Sensor sensor) {
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement command = con.prepareStatement(
					"INSERT INTO SENSOR (sensor_id, sensor_descricao, sensor_status, equipamento_equipamento_id) values(?, ?, ?, ?)");

			command.setString(1, sensor.getId());
			command.setString(2, sensor.getDescricao());
			command.setInt(3, StatusEnum.obterPorCodigo(sensor.getStatus()).getCodigo());
			command.setInt(4, sensor.getEquipamento().getId());

			command.executeUpdate();
			command.close();
			con.close();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvar Sensor",
					"Sensor Cadastrado com Sucesso!");
			PrimeFaces.current().dialog().showMessageDynamic(message);


		} catch (SQLException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro",
					"Não foi possível Cadastrar Sensor (ERRO = " + e);
			PrimeFaces.current().dialog().showMessageDynamic(message);
			e.printStackTrace();
		}

	}

	@Override
	public void update(Sensor sensor) {
		Connection con = ConnectionFactory.getConnection();

		try {
			PreparedStatement command = con.prepareStatement(
					"UPDATE SENSOR SET sensor_descricao = ?, sensor_status = ?, equipamento_equipamento_id = ? WHERE sensor_id = ?");

			command.setString(1, sensor.getDescricao());
			
			command.setInt(2, StatusEnum.obterPorCodigo(sensor.getStatus()).getCodigo());
			command.setInt(3, sensor.getEquipamento().getId());
			command.setString(4, sensor.getId());

			command.executeUpdate();
			command.close();
			con.close();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar Sensor",
					"Sensor Alterado com Sucesso!");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro",
					"Não foi possível alterar Sensor (ERRO = " + e);
			PrimeFaces.current().dialog().showMessageDynamic(message);

			e.printStackTrace();
		}

	}

	@Override
	public void delete(Sensor sensor) {
		Connection con = ConnectionFactory.getConnection();

		try {
			PreparedStatement command = con.prepareStatement("DELETE FROM SENSOR WHERE sensor_id = ?");

			command.setString(1, sensor.getId());
			command.executeUpdate();
			command.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Sensor> select() {
		Connection con = ConnectionFactory.getConnection();

		ArrayList<Sensor> list = new ArrayList<Sensor>();		
		EquipamentoDAO eDAO = new EquipamentoDAO();
		ArrayList<Equipamento> equipamentos = eDAO.select();
		

		try {
			PreparedStatement command = con.prepareStatement("SELECT * FROM SENSOR");

			ResultSet rs = command.executeQuery();

			while (rs.next()) {
				Sensor sensor = new Sensor();

				sensor.setId(rs.getString("sensor_id"));
				sensor.setDescricao(rs.getString("sensor_descricao"));
				sensor.setStatus(rs.getInt("sensor_status"));
				
				for (Equipamento equipamento : equipamentos) {
					if(equipamento.getId() == rs.getInt("equipamento_equipamento_id"))
					sensor.setEquipamento(equipamento);
				}

				list.add(sensor);
			}

			rs.close();
			command.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return list;
	}
	
	public ArrayList<Sensor> select(ArrayList<Equipamento> equipamentos) {
		Connection con = ConnectionFactory.getConnection();

		ArrayList<Sensor> list = new ArrayList<Sensor>();

		try {
			PreparedStatement command = con.prepareStatement("SELECT * FROM SENSOR");

			ResultSet rs = command.executeQuery();

			while (rs.next()) {
				Sensor sensor = new Sensor();

				sensor.setId(rs.getString("sensor_id"));
				sensor.setDescricao(rs.getString("sensor_descricao"));
				sensor.setStatus(rs.getInt("sensor_status"));
				
				for (Equipamento equipamento : equipamentos) {
					if(equipamento.getId() == rs.getInt("equipamento_equipamento_id"))
					sensor.setEquipamento(equipamento);
				}

				list.add(sensor);
			}

			rs.close();
			command.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return list;
	}
	
	

	public Sensor selectById(String sensorId) {
		Connection conexao = ConnectionFactory.getConnection();
		Sensor sensor = new Sensor();

		try {
			PreparedStatement command = conexao.prepareStatement("SELECT * FROM SENSOR WHERE sensor_id = ?");
			EquipamentoDAO eDAO = new EquipamentoDAO();

			command.setString(1, sensorId);

			ResultSet rs = command.executeQuery();
			while (rs.next()) {
				sensor.setId(rs.getString("sensor_id"));
				sensor.setDescricao(rs.getString("sensor_descricao"));
				sensor.setStatus(rs.getInt("sensor_status"));
				sensor.setEquipamento(eDAO.selectById(rs.getInt("equipamento_equipamento_id")));

			}
			rs.close();
			command.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sensor;
	}

	@Override
	public Object selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
