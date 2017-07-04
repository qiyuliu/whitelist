package corn.jamboy.service.impl;



import corn.jamboy.dao.CommonRepositor;
import corn.jamboy.dao.WhitelistIpGroupDao;
import corn.jamboy.entity.WhitelistIpGroup;
import corn.jamboy.service.WhitelistIpGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;




@Service
public class WhitelistIpGroupServiceImp extends CommonServciceImp<WhitelistIpGroup> implements WhitelistIpGroupService {

	@Autowired
	private WhitelistIpGroupDao whitelistIpGroupDao;

	@Override
	public CommonRepositor getRepositor() {
		// TODO Auto-generated method stub
		return (CommonRepositor)this.whitelistIpGroupDao;
	}
	

}
