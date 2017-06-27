package corn.jamboy.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_area_info")  
public class AreaInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="n_id",nullable=false,length=20)
	private Integer id;
	
	@Column(name="n_enable_white",nullable=false,length=1)
	private Boolean enableWhite;
	
	@Column(name="s_name",nullable=false,length=255)
	private String name;
	
	@Column(name="s_number",nullable=false,length=255)
	private String number;
	
	@Column(name="s_desc",nullable=false,length=1024)
	private String desc;
	
	@Column(name="n_env_id",nullable=false,length=20)
	private BigInteger envId;
	
	@Column(name="n_admin_id",nullable=false,length=20)
	private BigInteger adminId;
	
	@Column(name="n_entry_id",nullable=false,length=20)
	private BigInteger entryId;
	
	@Column(name="n_store_id",nullable=false,length=20)
	private BigInteger storeId;
	
	@Column(name="n_status",nullable=false,length=2)
	private Integer status;

	@Column(name="d_create",nullable=false,length=0)
	private Date create;
	
	@Column(name="d_update",nullable=false,length=0)
	private Date update;
	
	@Column(name="n_channel_id",nullable=false,length=20)
	private BigInteger channelId;
	
	@Column(name="n_game_id",nullable=false,length=20)
	private BigInteger gameId;
	
	@Column(name="n_count",nullable=false,length=11)
	private Integer count;
	
	@Column(name="n_group_id",nullable=false,length=11)
	private Integer groupId;
	
	@Column(name="n_state",nullable=false,length=255)
	private String state;
	
	@Column(name="d_open_time",nullable=false,length=0)
	private Date openTime;
	
	@Column(name="b_new",nullable=false,length=1)
	private Boolean new$;
	
	@Column(name="b_recommend",nullable=false,length=1)
	private Boolean recommend;
	
	@Column(name="b_acc",nullable=false,length=1)
	private Boolean acc;
	
	@Column(name="s_remark",nullable=false,length=255)
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getEnableWhite() {
		return enableWhite;
	}

	public void setEnableWhite(Boolean enableWhite) {
		this.enableWhite = enableWhite;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public BigInteger getEnvId() {
		return envId;
	}

	public void setEnvId(BigInteger envId) {
		this.envId = envId;
	}

	public BigInteger getAdminId() {
		return adminId;
	}

	public void setAdminId(BigInteger adminId) {
		this.adminId = adminId;
	}

	public BigInteger getEntryId() {
		return entryId;
	}

	public void setEntryId(BigInteger entryId) {
		this.entryId = entryId;
	}

	public BigInteger getStoreId() {
		return storeId;
	}

	public void setStoreId(BigInteger storeId) {
		this.storeId = storeId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public BigInteger getChannelId() {
		return channelId;
	}

	public void setChannelId(BigInteger channelId) {
		this.channelId = channelId;
	}

	public BigInteger getGameId() {
		return gameId;
	}

	public void setGameId(BigInteger gameId) {
		this.gameId = gameId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Boolean getNew$() {
		return new$;
	}

	public void setNew$(Boolean new$) {
		this.new$ = new$;
	}

	public Boolean getRecommend() {
		return recommend;
	}

	public void setRecommend(Boolean recommend) {
		this.recommend = recommend;
	}

	public Boolean getAcc() {
		return acc;
	}

	public void setAcc(Boolean acc) {
		this.acc = acc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
