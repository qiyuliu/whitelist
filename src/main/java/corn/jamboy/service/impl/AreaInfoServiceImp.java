package corn.jamboy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corn.jamboy.dao.AreaInfoDao;
import corn.jamboy.dao.CommonRepositor;
import corn.jamboy.entity.AreaInfo;
import corn.jamboy.entity.WhitelistIpGroup;
import corn.jamboy.service.AreaInfoService;
import corn.jamboy.service.WhitelistIpGroupService;

@Service
public class AreaInfoServiceImp extends CommonServciceImp<AreaInfo> implements AreaInfoService {

	@Autowired
	private AreaInfoDao areaInfoDao;
	
	@Override
	public CommonRepositor getRepositor() {
		// TODO Auto-generated method stub
		return (CommonRepositor)this.areaInfoDao;
	}

}
