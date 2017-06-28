package corn.jamboy.contorller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import corn.jamboy.constants.ResultConstants;
import corn.jamboy.contorller.WhiteListVerifyController.WhiteList;
import corn.jamboy.entity.WhitelistIp;
import corn.jamboy.entity.WhitelistIpGroup;
import corn.jamboy.form.UpdateGroupForm;
import corn.jamboy.form.WhitelistGroupForm;
import corn.jamboy.protocol.ResultBean;
import corn.jamboy.protocol.WhiteListResultBean;
import corn.jamboy.service.WhitelistIpGroupService;
import corn.jamboy.service.impl.WhitelistIpGroupServiceImp;
import corn.jamboy.service.impl.WhitelistIpServiceImp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/*
 * 白名单分组
 * 
 */
@RestController
@RequestMapping(value="/admin/v1/whitelist/group")
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
	private WhitelistIpServiceImp whitelistIpService;
	
	/*
	 * 创建白名单分组
	 */
	@CrossOrigin
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public WhiteListResultBean createList(@RequestParam(name = "name") String name,
			@RequestParam(name = "status") Integer status){

		logger.info("创建白名单分组: "+name);
		try{
			
			WhitelistIpGroup whitelistIpGroup = new WhitelistIpGroup();
			whitelistIpGroup.setName(name);
			whitelistIpGroup.setStatus(status);
			whitelistIpGroup.setCreateAt(new Date());
			whitelistIpGroup.setUpdateAt(new Date());

			whitelistIpGroupService.saveEntity(whitelistIpGroup);		
			
			return new WhiteListResultBean(1, new WhiteListResultBean.Obj(true, "已完成创建"));
			//return new ResultBean("已完成创建",ResultConstants.SYS_NORMAL_CODE);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	} 
	
	/*
	 * 查询白名单分组列表
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
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
					
					sb.append("\",\"createAt\":\"");  
					sb.append(whitelistIpGroup.getCreateAt());
					
					sb.append("\",\"updateAt\":\"");  
					sb.append(whitelistIpGroup.getUpdateAt());
					
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
					
					
					sb.append("\",\"createAt\":\"");  
					sb.append(whitelistIpGroup.getCreateAt());
					
					sb.append("\",\"updateAt\":\"");  
					sb.append(whitelistIpGroup.getUpdateAt());
					
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
	 * 删除白名单分组 
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public WhiteListResultBean deleteList(@PathVariable(name = "id") Integer id){
		logger.info("删除白名单分组 ID:"+id);
		
		try {
			WhitelistIpGroup  whitelistIpGroup = whitelistIpGroupService.getEntity(id);
			
			if(whitelistIpGroup == null){ 
				//throw new RuntimeException("ID: "+id + " 不存在");
				return new WhiteListResultBean(0, new WhiteListResultBean.Obj(false, "删除失败：ID不存在"));
			}else{
				whitelistIpGroupService.removeEntity(whitelistIpGroup);
			}
			return new WhiteListResultBean(1, new WhiteListResultBean.Obj(true, "已完成删除"));
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		
	}
	
	/*
	 * 更新白名单分组 verify
	 */
	@RequestMapping(value="/",method=RequestMethod.PUT)
	public WhiteListResultBean updateList(@RequestParam(name = "id") Integer id,
									@RequestParam(name = "name") String name,
									@RequestParam(name = "status") Integer status){
		logger.info("更新白名单分组 ID:");
		try {
			
			WhitelistIpGroup whitelistIpGroup = whitelistIpGroupService.getEntity(id);
			whitelistIpGroup.setName(name);
			whitelistIpGroup.setStatus(status);
			whitelistIpGroup.setUpdateAt(new Date());
			
			List<WhitelistIp> ipList = whitelistIpService.getAll();
			for(WhitelistIp list : ipList){
				if(list.getGroupId() == id){
					list.setStatus(status);
					whitelistIpService.modifyEntity(list);
				}
			}
			

			whitelistIpGroupService.saveEntity(whitelistIpGroup);				
			
			return new WhiteListResultBean(1, new WhiteListResultBean.Obj(true, "已完成更新"));
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
	}
	
	/*
	 * 查询单个分组状态
	 * 
	 */
	@RequestMapping(value="/{id}/status",method=RequestMethod.GET)
	public String searchGroup(@PathVariable(name = "id") Integer id){
		logger.info("查询 ID:"+id + "分组信息");
		try {
			WhitelistIpGroup whitelistIpGroup = whitelistIpGroupService.getEntity(id);
			StringBuffer sb = new StringBuffer();
			
			sb.append("{\"id\":\"");  
			sb.append(whitelistIpGroup.getId());
			
			sb.append("\",\"name\":\"");  
			sb.append(whitelistIpGroup.getName());

			sb.append("\",\"status\":\"");  
			sb.append(whitelistIpGroup.getStatus());
			
			sb.append("\",\"createAt\":\"");  
			sb.append(whitelistIpGroup.getCreateAt());
					
			sb.append("\",\"updateAt\":\"");  
			sb.append(whitelistIpGroup.getUpdateAt());
			
            sb.append("\"");  
            sb.append("}"); 
            
			String str = sb.toString();
			return str;
		
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	/*
	 * 查询分组下白名单 verify
	 */
	@RequestMapping(value="/{id}/ip_list",method=RequestMethod.GET)
	public String searchList(@PathVariable(name = "id") Integer id){
		logger.info("查询 ID:"+id + "分组下白名单");
		try {
			List<WhitelistIp> ipList = whitelistIpService.getAll();
			
			WhitelistIp whitelistIp = null;
			StringBuffer sb = new StringBuffer("[");
			for(int i = 0; i < ipList.size(); i++)  
	        { 
				whitelistIp = ipList.get(i);
				
				if(whitelistIp.getGroupId() == id){
					if(i<ipList.size()-1){

						
						sb.append("{\"id\":\"");  
						sb.append(whitelistIp.getId());
						
						sb.append("\",\"Group\":\"");  
						sb.append(whitelistIp.getGroupId());

						sb.append("\",\"startIp\":\"");  
						sb.append(whitelistIp.getStartIp());
						
						sb.append("\",\"createAt\":\"");  
						sb.append(whitelistIp.getCreateAt());
						
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
					
	                
	                if(i==ipList.size()-1){
						sb.append("{\"id\":\"");  
						sb.append(whitelistIp.getId());
						
						sb.append("\",\"Group\":\"");  
						sb.append(whitelistIp.getGroupId());

						sb.append("\",\"startIp\":\"");  
						sb.append(whitelistIp.getStartIp());
						
						sb.append("\",\"createAt\":\"");  
						sb.append(whitelistIp.getCreateAt());
						
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
				
	        } 
			sb.deleteCharAt(sb.length()-1);
			sb.append("]");
			String str = sb.toString();


			return str;
	        } catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		//return new ResultBean<WhitelistIpGroup>(null, ResultConstants.SYS_NORMAL_CODE, ResultConstants.STATE_NORMAL, whitelistIpGroup);	
	}
	

}
