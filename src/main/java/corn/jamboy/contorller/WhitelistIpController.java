package corn.jamboy.contorller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import corn.jamboy.constants.ResultConstants;
import corn.jamboy.entity.WhitelistIp;
import corn.jamboy.protocol.ResultBean;
import corn.jamboy.service.impl.WhitelistIpGroupServiceImp;
import corn.jamboy.service.impl.WhitelistIpServiceImp;





/*
 * 白名单管理
 * 
 */
@RestController
@RequestMapping(value="/admin_v1/white_ip")
public class WhitelistIpController {
	
	
	@RequestMapping("/test")
	public String test(){
		logger.info("test");
		return "test";
	}
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private WhitelistIpServiceImp whitelistIpService; 
	
	/*
	 * 创建白名单 verify
	 * 
	 */
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResultBean<Void> createList(@RequestBody WhitelistIp whitelistIp){	
		logger.info("创建白名单 ID:"+whitelistIp.getId());
		try {
			whitelistIp.setUpdateAt(new Date());
			whitelistIpService.saveEntity(whitelistIp);
			return new ResultBean<>(ResultConstants.STATE_NORMAL, ResultConstants.SYS_NORMAL_CODE); 
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		} 
	} 
	
	/*
	 * 删除白名单 verify
	 */
	@RequestMapping(value="/{whitelist_id}",method=RequestMethod.DELETE)
	public ResultBean<Void> deleteList(@PathVariable(name = "whitelist_id") Integer id){
		logger.info("删除白名单 ID:"+id);
		
		try {
			WhitelistIp whitelistIp = whitelistIpService.getEntity(id);
			
			if(whitelistIp == null){ 
				throw new RuntimeException("ID: "+id + " 不存在");
			}else{
				whitelistIpService.removeEntity(whitelistIp);
			}
			return new ResultBean<>(ResultConstants.STATE_NORMAL, ResultConstants.SYS_NORMAL_CODE); 
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		
	}
	
	/*
	 * 更新白名单 verify
	 */
	@RequestMapping(value="/",method=RequestMethod.PUT)
	public ResultBean<Void> updateList(@RequestBody WhitelistIp whitelistIp){
		logger.info("更新白名单 ID:"+whitelistIp.getId());
		try {
			if(whitelistIpService.getEntity(whitelistIp.getId()) == null){
				throw new RuntimeException("ID: "+whitelistIp.getId() + " 不存在");
			}
			whitelistIp.setUpdateAt(new Date());
			whitelistIpService.modifyEntity(whitelistIp);
			
			return new ResultBean<>(ResultConstants.STATE_NORMAL, ResultConstants.SYS_NORMAL_CODE); 
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
	}
	
	/*
	 * 查询白名单
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResultBean<WhitelistIp> searchList(@PathVariable int id){
		logger.info("查询白名单 ID:"+id);
		try {
			if(whitelistIpService.getEntity(id) == null){
				throw new RuntimeException("ID: "+id + " 不存在");
			}
			WhitelistIp whitelistIp = whitelistIpService.getEntity(id);
			
			return new ResultBean<WhitelistIp>(ResultConstants.STATE_NORMAL, ResultConstants.SYS_NORMAL_CODE); 
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
/*	
	 * 编辑白名单状态
	 
	@RequestMapping(value="/edit_status?id={id}&status={status}",method=RequestMethod.PUT)
	public ModelAndView updateStatus(@PathVariable int id,@PathVariable int status){
		
		logger.info("编辑白名单 ID:"+id + ",状态更改为：" + status);
		
		
		return null;
	}*/

}
