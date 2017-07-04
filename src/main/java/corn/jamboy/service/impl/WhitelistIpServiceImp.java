package corn.jamboy.service.impl;

import corn.jamboy.dao.CommonRepositor;
import corn.jamboy.dao.WhitelistIpDao;
import corn.jamboy.entity.WhitelistIp;
import corn.jamboy.entity.WhitelistIpGroup;
import corn.jamboy.service.WhitelistIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;





@Service
public class WhitelistIpServiceImp extends CommonServciceImp<WhitelistIp> implements WhitelistIpService {

	@Autowired
	private WhitelistIpDao whitelistIpDao;
	
	@Override
	public CommonRepositor getRepositor() {
		// TODO Auto-generated method stub
		return (CommonRepositor)this.whitelistIpDao;
	}

}
