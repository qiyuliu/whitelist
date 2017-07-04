package corn.jamboy.service.impl;

import java.io.Serializable;
import java.util.List;

import corn.jamboy.dao.CommonRepositor;
import corn.jamboy.service.CommonService;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;



public abstract class CommonServciceImp<T> implements CommonService<T> {

	@Override
	@Cacheable(value = "ipcache",keyGenerator = "wiselyKeyGenerator")  
	public T getEntity(Serializable id) {
		return (T) this.getRepositor().findOne(id);
	}

	@Transactional
	@Override
	@Cacheable(value = "ipcache",keyGenerator = "wiselyKeyGenerator")  
	public T saveEntity(T entity) {
		return (T) this.getRepositor().save(entity);
	}

	@Transactional
	@Override
	@Cacheable(value = "ipcache",keyGenerator = "wiselyKeyGenerator")  
	public void modifyEntity(T entity) {
		this.getRepositor().save(entity);
	}

	@Transactional
	@Override
	@Cacheable(value = "ipcache",keyGenerator = "wiselyKeyGenerator")  
	public void removeEntity(T entity) {
		this.getRepositor().delete(entity);
		
	}
	
	
	@Override
	@Cacheable(value = "ipcache",keyGenerator = "wiselyKeyGenerator")  
	public List<T> getAll(){
		return (List<T>) this.getRepositor().findAll();
	};
	
	
	
	public abstract CommonRepositor getRepositor();
	
	



	
	
}
