package corn.jamboy.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * 白名单分组管理
 * @author qiyuliu
 *
 */
@Entity
@Table(name="whitelist_ip_group") 
public class WhitelistIpGroup implements Serializable{

	//分组ID
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	//分组名
	@Column(name="name",nullable=false,length=255)
	private String name;
	
	//启用状态默认0为未启用，1为启用
	@Column(name="status",nullable=false,length=4)
	private int status;

	//分组下拥有的IP
	//一对多
	@OneToMany(mappedBy="whitelistIpGroup",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<WhitelistIp> WhitelistIps = new LinkedList<WhitelistIp>();
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<WhitelistIp> getWhitelistIps() {
		return WhitelistIps;
	}

	public void setWhitelistIps(List<WhitelistIp> whitelistIps) {
		WhitelistIps = whitelistIps;
	}


	
	
}
