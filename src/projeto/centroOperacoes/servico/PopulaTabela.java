package projeto.centroOperacoes.servico;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PopulaTabela {
	static LocalDateTime data = LocalDateTime.now();
	
	public static void preencheTabela() {
		for(int i=1;i<40;i++) {
			System.out.println("INSERT INTO EVENTO(evento_id, evento_data, evento_descricao, evento_status, navio_navio_id) "
					+ "values("+i+", '2018-12-14T10:23:40.338','Evento teste_"+i+"', 1, 1);" );
		}
	}
	
	public static void main(String[] args) {
		preencheTabela();
	}

}
