package projeto.centroOperacoes.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;

import org.primefaces.PrimeFaces;

import projeto.centroOperacoes.enums.StatusEnum;
import projeto.centroOperacoes.modelo.Equipamento;
import projeto.centroOperacoes.modelo.Navio;

public class NavioDAO implements CrudDAO<Navio> {

	/**
	 * Esse método adiciona um novo Navio ao banco de dados
	 * 
	 * @param navio
	 */
	@Override
	public void insert(Navio navio) {
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement command = con
					.prepareStatement("INSERT INTO NAVIO (navio_nome, navio_status) values(?, ?)");

			command.setString(1, navio.getNome());
			command.setInt(2, StatusEnum.obterPorCodigo(navio.getStatus()).getCodigo());

			command.executeUpdate();
			command.close();
			con.close();
			if (navio.getEquipamentos() != null) {
				alocaEquipamento(navio);
			}
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvar Navio",
					"Navio Cadastrado com Sucesso!");
			PrimeFaces.current().dialog().showMessageDynamic(message);

		} catch (SQLException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro",
					"Não foi possível cadastrar Navio (ERRO = " + e);
			PrimeFaces.current().dialog().showMessageDynamic(message);

			e.printStackTrace();
		}
	}

	public void alocaEquipamento(Navio navio) {
		List<Equipamento> equipamentos = navio.getEquipamentos();

		for (Equipamento equipamento : equipamentos) {
			Connection con = ConnectionFactory.getConnection();

			try {
				PreparedStatement command = con.prepareStatement("INSERT INTO ALOCAEQUIPAMENTO "
						+ "(alocacaoequipamento_id, alocaequipamento_zona, navio_navio_id, equipamento_equipamento_id, alocaequipamento_status) values(?, ?, ?, ?, ?)");

				command.setInt(1, 1);
				command.setString(1, null);
				command.setInt(2, navio.getId());
				command.setInt(3, equipamento.getId());
				command.setInt(2, StatusEnum.obterPorCodigo(navio.getStatus()).getCodigo());

				command.executeUpdate();

				command.close();
				con.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	/**
	 * Esse método atualiza informações do Navio como:( Nome e status)
	 * 
	 * @param navio
	 */
	@Override
	public void update(Navio navio) {
		Connection con = ConnectionFactory.getConnection();

		try {
			PreparedStatement command = con
					.prepareStatement("UPDATE NAVIO SET navio_nome = ?, navio_status = ? WHERE navio_id = ?");

			command.setString(1, navio.getNome());
			command.setInt(2, StatusEnum.obterPorCodigo(navio.getStatus()).getCodigo());
			command.setInt(3, navio.getId());

			command.executeUpdate();
			command.close();
			con.close();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar Navio",
					"Navio Alterado com Sucesso!");
			PrimeFaces.current().dialog().showMessageDynamic(message);
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro",
					"Não foi possível alterar Navio (ERRO = " + e);
			PrimeFaces.current().dialog().showMessageDynamic(message);
			e.printStackTrace();
		}
	}

	/**
	 * Esse método Deleta um Navio utilizando uma classe
	 * 
	 * @param navio
	 */
	@Override
	public void delete(Navio navio) {
		Connection con = ConnectionFactory.getConnection();

		try {
			PreparedStatement command = con.prepareStatement("DELETE FROM NAVIO WHERE navio_id = ?");

			command.setInt(1, navio.getId());
			command.executeUpdate();
			command.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Esse método seleciona todos os navios existente no banco de dados
	 * 
	 * @return ArrayList<Navio>
	 */
	@Override
	public ArrayList<Navio> select() {
		Connection con = ConnectionFactory.getConnection();

		ArrayList<Navio> list = new ArrayList<Navio>();

		try {
			PreparedStatement command = con.prepareStatement("SELECT * FROM NAVIO");

			ResultSet rs = command.executeQuery();

			while (rs.next()) {
				Navio n = new Navio();

				n.setId(rs.getInt("navio_id"));
				n.setNome(rs.getString("navio_nome"));
				n.setStatus(rs.getInt("navio_status"));

				list.add(n);
			}

			rs.close();
			command.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return list;
	}

	public ArrayList<Navio> select(ArrayList<Equipamento> equipamentos) {
		Connection con = ConnectionFactory.getConnection();

		ArrayList<Navio> list = new ArrayList<Navio>();

		try {
			PreparedStatement command = con.prepareStatement("SELECT * FROM NAVIO");

			ResultSet rs = command.executeQuery();

			while (rs.next()) {
				Navio n = new Navio();

				n.setId(rs.getInt("navio_id"));
				n.setNome(rs.getString("navio_nome"));
				n.setStatus(rs.getInt("navio_status"));
				for (Equipamento equipamento : equipamentos) {
					// if(equipamento.getId() == rs.get) TODO

				}

				list.add(n);
			}

			rs.close();
			command.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return list;
	}

	/**
	 * Esse método seleciona um Navio de acordo com seu id
	 * 
	 * @param navioId
	 * @return
	 */
	@Override
	public Navio selectById(Integer navioId) {
		Connection conexao = ConnectionFactory.getConnection();
		Navio navio = new Navio();

		try {
			PreparedStatement command = conexao.prepareStatement("SELECT * FROM Navio WHERE navio_id = ?");

			command.setInt(1, navioId);

			ResultSet rs = command.executeQuery();
			while (rs.next()) {
				navio.setId(rs.getInt("navio_id"));
				navio.setNome(rs.getString("navio_nome"));
				navio.setStatus(rs.getInt("navio_status"));
			}
			rs.close();
			command.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return navio;
	}

	// Metodos HIBERNATE

	protected void addUpdateNavio(Navio navio) {
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		manager.getTransaction().begin();
		manager.merge(navio);
		manager.getTransaction().commit();
		manager.close();
	}

	protected Navio carregaNavio(int idNavio) {
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		Navio navio = manager.find(Navio.class, idNavio);
		return navio;
	}

	public void deleteNavio(Navio navio) {
		EntityManager manager = FabricaConexao.getFactory().createEntityManager();
		manager.getTransaction().begin();
		manager.remove(navio);
		manager.getTransaction().commit();
		manager.close();
	}

}
