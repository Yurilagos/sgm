package projeto.centroOperacoes.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;

import org.primefaces.PrimeFaces;

import projeto.centroOperacoes.enums.StatusEnum;
import projeto.centroOperacoes.modelo.Equipamento;

public class EquipamentoDAO implements CrudDAO<Equipamento> {

	@Override
	public void insert(Equipamento equipamento) {
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement command = con.prepareStatement(
					"INSERT INTO EQUIPAMENTO (equipamento_descricao, equipamento_status) values(?, ?)");

			command.setString(1, equipamento.getDescricao());
			command.setInt(2, StatusEnum.obterPorCodigo(equipamento.getStatus()).getCodigo());

			command.executeUpdate();
			command.close();
			con.close();

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvar Equipamento",
					"Equipamento Cadastrado com Sucesso!");
			PrimeFaces.current().dialog().showMessageDynamic(message);

		} catch (SQLException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro",
					"Não foi possível Cadastrar Equipamento (ERRO = " + e);
			PrimeFaces.current().dialog().showMessageDynamic(message);

			e.printStackTrace();
		}
	}

	@Override
	public void update(Equipamento equipamento) {
		Connection con = ConnectionFactory.getConnection();

		try {
			PreparedStatement command = con.prepareStatement(
					"UPDATE EQUIPAMENTO SET equipamento_descricao = ?, equipamento_status = ? WHERE equipamento_id = ?");

			command.setString(1, equipamento.getDescricao());
			command.setInt(2, StatusEnum.obterPorCodigo(equipamento.getStatus()).getCodigo());
			command.setInt(3, equipamento.getId());

			command.executeUpdate();
			command.close();
			con.close();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar Equipamento",
					"Equipamento Alterado com Sucesso!");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro",
					"Não foi possível alterar Equipamento (ERRO = " + e);
			PrimeFaces.current().dialog().showMessageDynamic(message);

			e.printStackTrace();
		}
	}

	@Override
	public void delete(Equipamento equipamento) {
		Connection con = ConnectionFactory.getConnection();

		try {
			PreparedStatement command = con.prepareStatement("DELETE FROM EQUIPAMENTO WHERE equipamento_id = ?");

			command.setInt(1, equipamento.getId());
			command.executeUpdate();
			command.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Equipamento> select() {
		Connection con = ConnectionFactory.getConnection();

		ArrayList<Equipamento> list = new ArrayList<Equipamento>();

		try {
			PreparedStatement command = con.prepareStatement("SELECT * FROM EQUIPAMENTO");

			ResultSet rs = command.executeQuery();

			while (rs.next()) {
				Equipamento equipamento = new Equipamento();

				equipamento.setId(rs.getInt("equipamento_id"));
				equipamento.setDescricao(rs.getString("equipamento_descricao"));
				equipamento.setStatus(rs.getInt("equipamento_status"));

				list.add(equipamento);
			}

			rs.close();
			command.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return list;
	}

	@Override
	public Equipamento selectById(Integer equipamentoId) {
		Connection conexao = ConnectionFactory.getConnection();
		Equipamento equipamento = new Equipamento();

		try {
			PreparedStatement command = conexao.prepareStatement("SELECT * FROM EQUIPAMENTO WHERE equipamento_id = ?");

			command.setInt(1, equipamentoId);

			ResultSet rs = command.executeQuery();
			while (rs.next()) {
				equipamento.setId(rs.getInt("equipamento_id"));
				equipamento.setDescricao(rs.getString("equipamento_descricao"));
				equipamento.setStatus(rs.getInt("equipamento_status"));
			}
			rs.close();
			command.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return equipamento;
	}
	
	
}
