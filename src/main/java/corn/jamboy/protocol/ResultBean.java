package corn.jamboy.protocol;

import java.io.Serializable;



public class ResultBean<T> implements Serializable{


	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String message;
	
	
	private String code;
	
	
	private Integer state;
	
	
	private T obj;
	
	


	public ResultBean() {
		
	}
	

	public ResultBean(Integer status , String code) {
		this.code = code;
		this.state = status;
	}
	

	public ResultBean( Integer state, String code,String message) {
		
		this.message = message;
		this.code = code;
		this.state = state;
	}


	public String getMessage() {
		return message;
	}

	

	

 
	public T getObj() {
		return obj;
	}


	public void setObj(T obj) {
		this.obj = obj;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "ResultBean [message=" + message + ", code=" + code + ", state="
				+ state + ", obj=" + obj + "]";
	}


	public ResultBean(String message, String code, Integer state, T obj) {
		
		this.message = message;
		this.code = code;
		this.state = state;
		this.obj = obj;
	}

	public String toJsonStr(){
		return "{\"code\":\""+this.code+"\",\"state\":"+this.state+",\"message\":\""+this.message+"\"}";
	}
	
	

	
}
