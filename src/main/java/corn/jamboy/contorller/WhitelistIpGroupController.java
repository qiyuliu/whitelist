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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


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
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); 
			List<WhitelistIpGroup> table =whitelistIpGroupService.getAll();
			String str = gson.toJson(table);
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
	 * 更新白名单分组
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
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); 
			WhitelistIpGroup whitelistIpGroup = whitelistIpGroupService.getEntity(id);
			String str = gson.toJson(whitelistIpGroup);
			return str;
		
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	/*
	 * 查询指定分组下白名单 
	 */
	@RequestMapping(value="/{id}/ip_list",method=RequestMethod.GET)
	public String searchList(@PathVariable(name = "id") Integer id){
		logger.info("查询 ID:"+id + "分组下白名单");
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();   
			List<WhitelistIp> ipList = whitelistIpService.getAll();
			List<WhitelistIp> List = new ArrayList<>();
			for(int i = 0; i < ipList.size(); i++){
				WhitelistIp ip = ipList.get(i);
				if(ip.getGroupId() == id){
					List.add(ip);
				}
			}  
			String listStr = gson.toJson(List);
			return listStr;
	        } catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		//return new ResultBean<WhitelistIpGroup>(null, ResultConstants.SYS_NORMAL_CODE, ResultConstants.STATE_NORMAL, whitelistIpGroup);	
	}
	

}
