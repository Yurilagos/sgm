package projeto.centroOperacoes.modelo.dao;

import java.util.List;



public interface CrudDAO<E> {
	
	public void insert(E entidade);
	public void update(E entidade);
	public void delete(E entidade);
	public List<E> select();
	public Object selectById(Integer id);
	
}