package corn.jamboy.contorller;


import java.util.List;

import corn.jamboy.constants.ResultConstants;
import corn.jamboy.entity.WhitelistIp;
import corn.jamboy.entity.WhitelistIpGroup;
import corn.jamboy.protocol.ResultBean;
import corn.jamboy.service.WhitelistIpGroupService;
import corn.jamboy.service.impl.WhitelistIpGroupServiceImp;
import corn.jamboy.service.impl.WhitelistIpServiceImp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




/*
 * 白名单分组
 * 
 */
@RestController
@RequestMapping(value="/admin_v1/white_ip_group")
public class WhitelistIpGroupController {
	
	@RequestMapping("/test")
	public String test(){
		logger.info("test");
		return "test";
	}
	
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private WhitelistIpGroupServiceImp whitelistIpGroupService;
 
	@Autowired
	private WhitelistIpServiceImp WhitelistIpService;
	
	/*
	 * 创建白名单分组 verify
	 */
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResultBean<Void> createList(@RequestBody WhitelistIpGroup whitelistIpGroup){
		logger.info("创建白名单分组: "+whitelistIpGroup.getName());
		try{
			whitelistIpGroupService.saveEntity(whitelistIpGroup);				
			return new ResultBean<>(ResultConstants.STATE_NORMAL, ResultConstants.SYS_NORMAL_CODE);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	} 
	
	/*
	 * 查询白名单分组列表 verify
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String searchTable(){
		logger.info("查询白名单分组列表");
		
		try {
			List<WhitelistIpGroup> table =whitelistIpGroupService.getAll();
			StringBuffer sb = new StringBuffer("[");
			WhitelistIpGroup whitelistIpGroup = null;
			for(int i= 0; i<table.size();i++){
				whitelistIpGroup = table.get(i);
				if(i<table.size()-1){
					sb.append("{\"id\":\"");  
					sb.append(whitelistIpGroup.getId());
					
					sb.append("\",\"name\":\""); 
					sb.append(whitelistIpGroup.getName());
					
					sb.append("\",\"status\":\"");  
					sb.append(whitelistIpGroup.getStatus());
					sb.append("\"");  
		            sb.append("},"); 
				}
				if(i==table.size()-1){
					sb.append("{\"id\":\"");  
					sb.append(whitelistIpGroup.getId());
					
					sb.append("\",\"name\":\""); 
					sb.append(whitelistIpGroup.getName());
					
					sb.append("\",\"status\":\"");  
					sb.append(whitelistIpGroup.getStatus());
					sb.append("\"");  
		            sb.append("}"); 
				}
			}
			sb.append("]");
			String str = sb.toString();
			return str;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}

	}
	
	/*
	 * 删除白名单分组 verify
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResultBean<Void> deleteList(@PathVariable(name = "id") Integer id){
		logger.info("删除白名单分组 ID:"+id);
		
		try {
			WhitelistIpGroup  whitelistIpGroup = whitelistIpGroupService.getEntity(id);
			
			if(whitelistIpGroup == null){ 
				throw new RuntimeException("ID: "+id + " 不存在");
			}else{
				whitelistIpGroupService.removeEntity(whitelistIpGroup);
			}
			return new ResultBean<>(ResultConstants.STATE_NORMAL, ResultConstants.SYS_NORMAL_CODE); 
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		
	}
	
	/*
	 * 更新白名单分组 verify
	 */
	@RequestMapping(value="/",method=RequestMethod.PUT)
	public ResultBean<Void> updateList(@RequestBody WhitelistIpGroup whitelistIpGroup){
		logger.info("更新白名单分组 ID:"+whitelistIpGroup.getId());
		try {
			
			if(whitelistIpGroupService.getEntity(whitelistIpGroup.getId()) == null){
				throw new RuntimeException("ID: "+whitelistIpGroup.getId() + " 不存在");
			}
			whitelistIpGroupService.modifyEntity(whitelistIpGroup);
			
			//更新分组下所有白名单状态
			int status = whitelistIpGroup.getStatus();
			
			List<WhitelistIp> whiteList = WhitelistIpService.getAll();

			
			for(WhitelistIp wl : whiteList){
				if(wl.getWhitelistIpGroup().getId() == whitelistIpGroup.getId()){
					wl.setStatus(status);
					WhitelistIpService.modifyEntity(wl);
				}
			}
			
			return new ResultBean<>(ResultConstants.STATE_NORMAL, ResultConstants.SYS_NORMAL_CODE); 
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
	}
	
	/*
	 * 查询分组下白名单 verify
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String searchList(@PathVariable(name = "id") Integer id){
		logger.info("查询白名单分组 ID:"+id);
		try {
			WhitelistIpGroup whitelistIpGroup = whitelistIpGroupService.getEntity(id);
			
			List<WhitelistIp> list = whitelistIpGroup.getWhitelistIps();
			WhitelistIp whitelistIp = null;
			StringBuffer sb = new StringBuffer("[");
			for(int i = 0; i < list.size(); i++)  
	        {  
				whitelistIp = list.get(i);   
				if(i<list.size()-1){

					
					sb.append("{\"id\":\"");  
					sb.append(whitelistIp.getId());
					
					sb.append("\",\"Group\":\"");  
					sb.append(whitelistIp.getWhitelistIpGroup().getId());

					sb.append("\",\"startIp\":\"");  
					sb.append(whitelistIp.getStartIp());
					
					sb.append("\",\"endIp\":\"");  
					sb.append(whitelistIp.getStatus());
					
					sb.append("\",\"updateAt\":\"");  
					sb.append(whitelistIp.getUpdateAt());
					
					sb.append("\",\"status\":\"");  
					sb.append(whitelistIp.getStatus());
					
					sb.append("\",\"remark\":\"");  
					sb.append(whitelistIp.getRemark());
	                sb.append("\"");  
	                sb.append("},"); 
				}
				
                
                if(i==list.size()-1){
					sb.append("{\"id\":\"");  
					sb.append(whitelistIp.getId());
					
					sb.append("\",\"Group\":\"");  
					sb.append(whitelistIp.getWhitelistIpGroup().getId());

					sb.append("\",\"startIp\":\"");  
					sb.append(whitelistIp.getStartIp());
					
					sb.append("\",\"endIp\":\"");  
					sb.append(whitelistIp.getStatus());
					
					sb.append("\",\"updateAt\":\"");  
					sb.append(whitelistIp.getUpdateAt());
					
					sb.append("\",\"status\":\"");  
					sb.append(whitelistIp.getStatus());
					
					sb.append("\",\"remark\":\"");  
					sb.append(whitelistIp.getRemark());
	                sb.append("\"");  
	                sb.append("}"); 
                }
	        } 
			sb.append("]");
			String str = sb.toString();


			return str;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		//return new ResultBean<WhitelistIpGroup>(null, ResultConstants.SYS_NORMAL_CODE, ResultConstants.STATE_NORMAL, whitelistIpGroup);	
	}
	

}
