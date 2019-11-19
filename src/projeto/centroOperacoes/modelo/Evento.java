package projeto.centroOperacoes.modelo;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import projeto.centroOperacoes.controle.EventoBean;
import projeto.centroOperacoes.enums.StatusEnum;



@Entity(name ="evento")
public class Evento {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Evento")	
	private Integer id;
	
	@Column
	private LocalDate data_time;
	
	@Column
	private  String descricao;
	
	
	@Column
	private String status;
	
	@ManyToOne
	@JoinColumn(name="Navio")
	private Navio navio;
	@Column
	private Usuario usuario;
	
	private String nomeNavio;
	
	
	
	public String getNomeNavio() {
		return nomeNavio;
	}
	public void setNomeNavio(String nomeNavio) {
		this.nomeNavio = nomeNavio;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getData_time() {
		return data_time;
	}
	public void setData_time(LocalDate data_time) {
		this.data_time = data_time;
	}
	public void setData_time(String strDateTime) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.data_time = LocalDate.parse(strDateTime, dtf);
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = StatusEnum.obterPorCodigo(status).getDescricao();
	}
	public Navio getNavio() {
		return navio;
	}
	public void setNavio(Navio navio) {
		this.navio = navio;
	}
	public void setNavio(Integer id) {
		EventoBean eb = new EventoBean();
		this.navio = eb.getNavioById(id);
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Integer id) {
		EventoBean eb = new EventoBean();
		this.usuario = eb.getUsuarioById(id);
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
}
