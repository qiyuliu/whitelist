package corn.jamboy.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 白名单
 * @author qiyuliu
 *
 */
@Entity
@Table(name="whitelist_ip")  
public class WhitelistIp implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,length=20)
	private int id;
	
	//所属白名单分组
	//多对一
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
	@JoinColumn(name="group_id")
	private WhitelistIpGroup whitelistIpGroup;
	
	//开始IP
	@Column(name="start_ip",nullable=false,length=20)
	private String startIp;
	
	//结束IP
	@Column(name="end_ip",nullable=false,length=20)
	private String endIp;
	
	//更新时间
	@Column(name="update_at",nullable=false,length=0)
	private Date updateAt;
	
	//启用状态默认0为未启用，1为启用
	@Column(name="status",nullable=false,length=4)
	private int status;
	
	//备注
	@Column(name="remark",nullable=false,length=255)
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public WhitelistIpGroup getWhitelistIpGroup() {
		return whitelistIpGroup;
	}

	public void setWhitelistIpGroup(WhitelistIpGroup whitelistIpGroup) {
		this.whitelistIpGroup = whitelistIpGroup;
	}



	public String getStartIp() {
		return startIp;
	}

	public void setStartIp(String startIp) {
		this.startIp = startIp;
	}

	public String getEndIp() {
		return endIp;
	}

	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
