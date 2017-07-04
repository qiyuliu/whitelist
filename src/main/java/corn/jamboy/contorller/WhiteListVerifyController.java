package corn.jamboy.contorller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import corn.jamboy.entity.AreaInfo;
import corn.jamboy.entity.WhitelistIp;
import corn.jamboy.entity.WhitelistIpGroup;
import corn.jamboy.service.impl.AreaInfoServiceImp;
import corn.jamboy.service.impl.WhitelistIpGroupServiceImp;
import corn.jamboy.service.impl.WhitelistIpServiceImp;

/**
 * 白名单验证接口
 * @author qiyuliu
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value="/admin/v1/whitelist/verify",method=RequestMethod.GET)
public class WhiteListVerifyController {

	@RequestMapping("/test")
	public String test(){
		logger.info("test");
		return "test";
	}
	
	@Autowired
	private WhitelistIpGroupServiceImp whitelistIpGroupService;
 
	@Autowired
	private WhitelistIpServiceImp whitelistIpService;
	
	@Autowired
	private AreaInfoServiceImp areaInfoServiceImp;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * 白名单验证
	 * 	areaId 是否 open
	 *	group 是否 open
	 *	ip 是否 open
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public WhiteList.Response verify(@RequestParam(name="areaId") Integer areaId,
			@RequestParam(name="accountId",required = false) String accountId,
			@RequestParam(name="clientIp") String clientIp
			){
		
		logger.info("whitelist verify ...Session");	
		
		AreaInfo areaInfo = areaInfoServiceImp.getEntity(areaId);
		Boolean flag = areaInfo.getEnableWhite();
		WhiteList.Response res = null;
		
		String start,end,ip;
		//区服是否开启白名单
		if(flag){
			//获取分组
			List<WhitelistIpGroup> groupList = whitelistIpGroupService.getAll();
			//获取全部IP
			List<WhitelistIp> ipList = whitelistIpService.getAll();		
			
			outer:
			for(WhitelistIp list : ipList){
				//IP状态为开启
				if(list.getStatus()==1){
					//是否在开始IP和结束IP之间
					if(Double.valueOf(start = list.getStartIp().replaceAll("\\.", "")) <= Double.valueOf(ip = clientIp.replaceAll("\\.", "")) && Double.valueOf(ip = clientIp.replaceAll("\\.", "")) <= Double.valueOf(end = list.getEndIp().replaceAll("\\.", ""))){
						
						for(WhitelistIpGroup grouplist : groupList){
							//找出所在分组
							if(grouplist.getId() == list.getGroupId() || list.getGroupId() == 0 ){
								//所在IP分组是否开启白名单
								if(grouplist.getStatus() == 1){
									res = new WhiteList.Response(1,new WhiteList.Response.Obj(true, "白名单IP验证成功"));
									break outer;
								}
							}else{
								res = new WhiteList.Response(0,new WhiteList.Response.Obj(false, "白名单IP验证失败：IP所在分组未开启白名单"));
								break outer;
							}
						}
					}else{
						res = new WhiteList.Response(0,new WhiteList.Response.Obj(false, "白名单IP验证失败：IP白名单未开启"));
					}
				}else{
					res = new WhiteList.Response(0,new WhiteList.Response.Obj(false, "白名单IP验证失败：IP白名单未开启"));		
				}
			}		
		}else {
			res = new WhiteList.Response(0,new WhiteList.Response.Obj(false, "白名单IP验证失败：区服白名单未开启"));
		}
		return res;
	}
	
	
	public static class WhiteList {
		
/*		public static class Request{
		
			private Integer areaId;
			private String accountId;
			private String clientIp;
			public Integer getAreaId() {
				return areaId;
			}
			public void setAreaId(Integer areaId) {
				this.areaId = areaId;
			}
			public String getAccountId() {
				return accountId;
			}
			public void setAccountId(String accountId) {
				this.accountId = accountId;
			}
			public String getClientIp() {
				return clientIp;
			}
			public void setClientIp(String clientIp) {
				this.clientIp = clientIp;
			}
			
			
		}*/
		
		public static class Response{
			
			private Integer code;
			private Obj obj;
			
			
			public Integer getCode() {
				return code;
			}

			public void setCode(Integer code) {
				this.code = code;
			}

			public Obj getObj() {
				return obj;
			}

			public void setObj(Obj obj) {
				this.obj = obj;
			}

			public Response(){}
			
			public Response(Integer code,Obj obj){
				super();
				this.code = code;
				this.obj = obj;
			}
			
			
			public static class Obj{
				
				private Boolean result;
				
				private String reason;

				
				public Obj() {
					super();
				}

				public Obj(Boolean result, String reason) {
					super();
					this.result = result;
					this.reason = reason;
				}

				public Boolean getResult() {
					return result;
				}

				public void setResult(Boolean result) {
					this.result = result;
				}

				public String getReason() {
					return reason;
				}

				public void setReason(String reason) {
					this.reason = reason;
				}
				
				
			}
		}
	}
	
}
