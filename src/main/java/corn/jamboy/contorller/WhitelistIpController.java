package corn.jamboy.contorller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import corn.jamboy.constants.ResultConstants;
import corn.jamboy.entity.WhitelistIp;
import corn.jamboy.entity.WhitelistIpGroup;
import corn.jamboy.form.UpdateIpForm;
import corn.jamboy.form.WhitelistIpForm;
import corn.jamboy.protocol.ResultBean;
import corn.jamboy.protocol.WhiteListResultBean;
import corn.jamboy.service.impl.WhitelistIpGroupServiceImp;
import corn.jamboy.service.impl.WhitelistIpServiceImp;





/*
 * 白名单管理
 * 
 */
@CrossOrigin
@RestController
@RequestMapping(value="/admin/v1/whitelist/ip")
public class WhitelistIpController {
	
	
	@RequestMapping("/test")
	public String test(){
		logger.info("test");
		return "test";
	}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WhitelistIpServiceImp whitelistIpService; 
	
	@Autowired
	private WhitelistIpGroupServiceImp whitelistIpGroupService;
	
	
	/*
	 * 创建白名单 
	 * 
	 */
	@RequestMapping(value="/",method=RequestMethod.POST)
	public WhiteListResultBean createList(@RequestParam(name="groupId",required = false) Integer groupId,
									@RequestParam(name="startIp") String startIp,
									@RequestParam(name="endIp",required = false) String endIp,
									@RequestParam(name="status",required = false) Integer status,
									@RequestParam(name="remark",required = false) String remark			
			){	
		logger.info("create whiteList ID:");
		try {
			
			WhitelistIp whitelistIp = new WhitelistIp();			
			
			if(groupId == null){
				whitelistIp.setGroupId(0);
			}else {
				whitelistIp.setGroupId(groupId);
			}		
			
			whitelistIp.setStartIp(startIp);
			//endIp(可选) - 为空时，和startId一致
			if(endIp.equals("")){
				whitelistIp.setEndIp(startIp);
			}else {
				whitelistIp.setEndIp(endIp);
			}
			if(status==null){
				whitelistIp.setStatus(1);
			}else{
				whitelistIp.setStatus(status);
			}
			if(status==null){
				whitelistIp.setRemark("");
			}else{
				whitelistIp.setRemark(remark);
			}
			
			whitelistIp.setCreateAt(new Date());
			whitelistIp.setUpdateAt(new Date());
			
		
			
			whitelistIpService.saveEntity(whitelistIp);
			return new WhiteListResultBean(1, new WhiteListResultBean.Obj(true, "已完成创建"));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		} 
	} 
	
	/*
	 * 删除白名单 
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public WhiteListResultBean deleteList(@PathVariable(name = "id") Integer id){
		logger.info("delete whiteList ID:"+id);
		
		try {
			WhitelistIp whitelistIp = whitelistIpService.getEntity(id);
			
			if(whitelistIp == null){ 
				//throw new RuntimeException("ID: "+id + " 不存在");
				return new WhiteListResultBean(0, new WhiteListResultBean.Obj(false, "删除失败：ID不存在"));
			}else{
				whitelistIpService.removeEntity(whitelistIp);
			}
			return new WhiteListResultBean(1, new WhiteListResultBean.Obj(true, "已完成删除"));
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		
	}
	
	/*
	 * 更新白名单
	 */
	@RequestMapping(value="/",method=RequestMethod.PUT)
	public WhiteListResultBean updateList(	@RequestParam(name = "id") Integer id,
										@RequestParam(name = "startIp",required = false) String startIp,
										@RequestParam(name = "status",required = false) Integer status,
										@RequestParam(name = "endIp",required = false) String endIp,
										@RequestParam(name = "groupId",required = false) Integer groupId,
										@RequestParam(name = "remark",required = false) String remark
			
			){
		logger.info("update whiteList ID:"+id);
		try {

			WhitelistIp whitelistIp = whitelistIpService.getEntity(id);
			if(!startIp.equals("")){
				whitelistIp.setStartIp(startIp);
			}
			
			if(status != null){
				whitelistIp.setStatus(status);
			}
			
			if(!endIp.equals("")){
				whitelistIp.setEndIp(endIp);
			}
			if(endIp.equals("")){
				whitelistIp.setEndIp(startIp);
			}
			
			
			if(groupId != null){
				whitelistIp.setGroupId(groupId);
			}
			
			if(!remark.equals("")){
				whitelistIp.setRemark(remark);
			}
			
			whitelistIp.setUpdateAt(new Date());

			whitelistIpService.modifyEntity(whitelistIp);
			
			return new WhiteListResultBean(1, new WhiteListResultBean.Obj(true, "已完成更新"));
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
	}
	


	/*
	 * 查询所有白名单
	 */
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public String searchList(){
		logger.info("search all whitelist ID");
		try {

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();   
			List<WhitelistIp> ipList = whitelistIpService.getAll();
			String listStr = gson.toJson(ipList);

			return listStr;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	


}
