package corn.jamboy.service;

import java.io.Serializable;
import java.util.List;



public interface CommonService<T> {

	public T getEntity(Serializable id);
	
	public T saveEntity(T entity);
	
	public void modifyEntity(T entity);
	
	public void removeEntity(T entity);
	
	public List<T> getAll();
}
