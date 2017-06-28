package corn.jamboy.contorller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private WhitelistIpServiceImp whitelistIpService; 
	
	@Autowired
	private WhitelistIpGroupServiceImp whitelistIpGroupService;
	
	
	/*
	 * 创建白名单 verify
	 * 
	 */
	@RequestMapping(value="/",method=RequestMethod.POST)
	public WhiteListResultBean createList(@RequestParam(name="groupId",required = false) Integer groupId,
									@RequestParam(name="startIp") String startIp,
									@RequestParam(name="endIp",required = false) String endIp,
									@RequestParam(name="status") Integer status,
									@RequestParam(name="remark") String remark			
			){	
		logger.info("创建白名单 ID");
		try {
			
			WhitelistIp whitelistIp = new WhitelistIp();			
			
			if(groupId == null){
				whitelistIp.setGroupId(0);
			}else {
				whitelistIp.setGroupId(groupId);
			}		
			
			whitelistIp.setStartIp(startIp);
			//endIp(可选) - 为空时，和startId一致
			if(endIp == null){
				whitelistIp.setEndIp(startIp);
			}else {
				whitelistIp.setEndIp(endIp);
			}
			
			whitelistIp.setCreateAt(new Date());
			whitelistIp.setUpdateAt(new Date());
			whitelistIp.setStatus(status);
			whitelistIp.setRemark(remark);
			
			whitelistIpService.saveEntity(whitelistIp);
			return new WhiteListResultBean(1, new WhiteListResultBean.Obj(true, "已完成创建"));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		} 
	} 
	
	/*
	 * 删除白名单 verify
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public WhiteListResultBean deleteList(@PathVariable(name = "id") Integer id){
		logger.info("删除白名单 ID:"+id);
		
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
	 * 更新白名单 verify
	 */
	@RequestMapping(value="/",method=RequestMethod.PUT)
	public WhiteListResultBean updateList(	@RequestParam(name = "id") Integer id,
										@RequestParam(name = "startIp") String startIp,
										@RequestParam(name = "status") Integer status,
										@RequestParam(name = "endIp",required = false) String endIp,
										@RequestParam(name = "groupId",required = false) Integer groupId,
										@RequestParam(name = "remark") String remark
			
			){
		logger.info("更新白名单 ID:"+id);
		try {

			WhitelistIp whitelistIp = whitelistIpService.getEntity(id);
			
			if(groupId == null){
				whitelistIp.setGroupId(0);
			}else {
				whitelistIp.setGroupId(groupId);
			}		
			if(endIp == null){
				whitelistIp.setEndIp("");
			}else {
				whitelistIp.setEndIp(endIp);
			}
			
			whitelistIp.setStartIp(startIp);
			whitelistIp.setStatus(status);
			whitelistIp.setRemark(remark);
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
		logger.info("查询所有白名单 ID");
		try {

			List<WhitelistIp> ipList = whitelistIpService.getAll();
			
			WhitelistIp whitelistIp = null;
			StringBuffer sb = new StringBuffer("[");
			for(int i = 0; i < ipList.size(); i++)  
	        { 
				whitelistIp = ipList.get(i);   
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
			sb.append("]");
			String str = sb.toString();


			return str;

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	


}
