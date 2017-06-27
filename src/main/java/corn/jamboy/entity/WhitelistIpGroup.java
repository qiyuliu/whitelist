package corn.jamboy.entity;

import java.io.Serializable;
import java.util.Date;
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
	private Integer id;
	
	//分组名
	@Column(name="name",nullable=false,length=255)
	private String name;
	
	//启用状态默认0为未启用，1为启用
	@Column(name="status",nullable=false,length=4)
	private Integer status;

/*	//分组下拥有的IP
	//一对多
	@OneToMany(mappedBy="whitelistIpGroup",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<WhitelistIp> WhitelistIps = new LinkedList<WhitelistIp>();*/

	//分组创建时间
	@Column(name="create_at",nullable = false,length = 0)
	private Date createAt;

	//分组更新时间
	@Column(name = "update_at",nullable = false,length = 0)
	private Date updateAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	
	
}
