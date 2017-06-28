package corn.jamboy.protocol;

import java.io.Serializable;

import corn.jamboy.contorller.WhiteListVerifyController.WhiteList.Response.Obj;

public class WhiteListResultBean implements Serializable {
	
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

	
	
	public WhiteListResultBean() {
		super();
	}

	public WhiteListResultBean(Integer code, Obj obj) {
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
