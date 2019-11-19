package projeto.centroOperacoes.modelo.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FabricaConexao {

	private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("namastenso");

	public static EntityManagerFactory getFactory() {
		if (!FACTORY.isOpen()) {
			FACTORY = Persistence.createEntityManagerFactory("namastenso");
		}
		return FACTORY;
	}
}