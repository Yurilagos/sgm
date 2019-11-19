package projeto.centroOperacoes.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name = "usuario")
/*@NamedQueries({
	@NamedQuery(name="AUTENTICA_USUARIO", query="SELECT * from Usuario where email=:email and senha=:senha"),
	@NamedQuery(name="BUSCA_USUARIO_POR_EMAIL", query="SELECT * from Usuario where email=:email"),
	@NamedQuery(name="BUSCA_USUARIO	_ATIVO", query="SELECT * from Usuario where email=:email and ativo='1'")
})*/
public class Usuario {

	@Id
	private Integer id;

	@Column(name = "usuario_cpf")
	private String cpf;

	@Column(name = "usuario_nome")
	private String nome;

	@Column(name = "usuario_sobrenome")
	private String sobrenome;

	@Column(name = "usuario_funcao")
	private String funcao;

	@Column(name = "usuario_login")
	private String login;

	@Column(name = "usuario_senha")
	private String senha;

	@Column(name = "usuario_status")
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
