package corn.jamboy.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;




@NoRepositoryBean
public  interface CommonRepositor<T> extends JpaRepository<T, Serializable>,  CrudRepository<T, Serializable> {
	
	
}