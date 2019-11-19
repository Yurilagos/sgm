package projeto.centroOperacoes.servico;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import projeto.centroOperacoes.modelo.Erro;

@Path("/erro")
public class ErroResource {

	@POST
	@Path("/insereerro")
	@Produces(MediaType.TEXT_PLAIN)
	/*
	 * Essa classe e responsável por receber os dados do erro.
	 * Após recebido os dados, ela transforma os mesmos em um array de objetos do tipo erro (ou outro tipo que escolher).
	 * O JSON deve ser enviado pelo endereço http://dminio-do-projeto/nome-do-projeto/service/erro/insereerro?erro=JSON-COM-OS-ERROS
	 * Os atributos do JSON DEVEM ser iguais aos atributos da classe a qual o mesmo sera convertido
	 * 
	 */
	public Response setErro(@QueryParam("erro") String jsonErro) {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(jsonErro);
		List<Object> erros = null;
		int codigo = 400;
		String mensagemStatus = "Recebido";
		try {
			TypeReference<List<Object>> mapType = new TypeReference<List<Object>>() {};
	    	erros = mapper.readValue(jsonErro, mapType);
		} catch (JsonParseException e) {
			// Caso o formato do json seja incorreto.
			mensagemStatus="Formato inválido de requisição";
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// Caso os dados do json nao combine com os dados do objeto.
			mensagemStatus="OS ATRIBUTOS DO JSON DEVEM SER IGUAIS AOS ATRIBUTOS DA CLASSE a qual o mesmo sera convertido";
			e.printStackTrace();
		} catch (IOException e) {
			// Caso nao seja possivel ler o json
			mensagemStatus="Formato inválido de requisição";
		}
		if(erros!=null) {
			codigo=200;
			mensagemStatus="Cadastrado com sucesso";
			/*Inserir aqui o metodo de controle responsavel pela persistencia da lista.*/
			
		}
		return Response.status(codigo, mensagemStatus).build();
		
	}
}
