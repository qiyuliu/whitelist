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
import corn.jamboy.entity.WhitelistIpGroup;
import corn.jamboy.form.UpdateIpForm;
import corn.jamboy.form.WhitelistIpForm;
import corn.jamboy.protocol.ResultBean;
import corn.jamboy.service.impl.WhitelistIpGroupServiceImp;
import corn.jamboy.service.impl.WhitelistIpServiceImp;





/*
 * 白名单管理
 * 
 */
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
	public ResultBean<Void> createList(@RequestBody WhitelistIpForm form){	
		logger.info("创建白名单 ID:"+form.getGroupId());
		try {

			
			WhitelistIp whitelistIp = new WhitelistIp();			
			WhitelistIpGroup whitelistIpGroup = whitelistIpGroupService.getEntity(Integer.parseInt(form.getGroupId()));
			if(whitelistIpGroup == null){
				throw new RuntimeException("分组不存在");
			}
			
			whitelistIp.setStartIp(form.getStauts());
			whitelistIp.setEndIp(form.getEndIp());
			whitelistIp.setWhitelistIpGroup(whitelistIpGroup);
			whitelistIp.setUpdateAt(new Date());
			whitelistIp.setRemark(form.getRemark());
			whitelistIp.setStatus(Integer.valueOf(form.getStauts()));
			whitelistIpService.saveEntity(whitelistIp);
			return new ResultBean<>(ResultConstants.STATE_NORMAL, ResultConstants.SYS_NORMAL_CODE,"已完成创建"); 
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
			return new ResultBean<>(ResultConstants.STATE_NORMAL, ResultConstants.SYS_NORMAL_CODE,"已完成删除"); 
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		
	}
	
	/*
	 * 更新白名单 verify
	 */
	@RequestMapping(value="/",method=RequestMethod.PUT)
	public ResultBean<Void> updateList(@RequestBody UpdateIpForm updateIpForm){
		logger.info("更新白名单 ID:"+updateIpForm.getId());
		try {
			if(whitelistIpService.getEntity(Integer.parseInt(updateIpForm.getId())) == null){
				throw new RuntimeException("ID: "+updateIpForm.getId() + " 不存在");
			}
			WhitelistIpGroup whitelistIpGroup = whitelistIpGroupService.getEntity(Integer.parseInt(updateIpForm.getGroupId()));
			if(whitelistIpGroup == null){
				throw new RuntimeException("分组：" + whitelistIpGroup + "不存在");
			}
			
			WhitelistIp whitelistIp = new WhitelistIp();
			whitelistIp.setId(Integer.parseInt(updateIpForm.getId()));
			whitelistIp.setStatus(Integer.parseInt(updateIpForm.getStauts()));
			whitelistIp.setStartIp(updateIpForm.getStartIp());
			whitelistIp.setEndIp(updateIpForm.getEndIp());
			whitelistIp.setWhitelistIpGroup(whitelistIpGroup);
			whitelistIp.setUpdateAt(new Date());
			whitelistIp.setRemark(updateIpForm.getRemark());
			whitelistIpService.modifyEntity(whitelistIp);
			
			return new ResultBean<>(ResultConstants.STATE_NORMAL, ResultConstants.SYS_NORMAL_CODE,"已完成更新"); 
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
	}
	
/*	
	 * 查询白名单
	 
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
	
	
	 * 编辑白名单状态
	 
	@RequestMapping(value="/edit_status?id={id}&status={status}",method=RequestMethod.PUT)
	public ModelAndView updateStatus(@PathVariable int id,@PathVariable int status){
		
		logger.info("编辑白名单 ID:"+id + ",状态更改为：" + status);
		
		
		return null;
	}*/

}
