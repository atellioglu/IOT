package utc.bab.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Gateway {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int companyId;
	private String deviceId;//mac address
	@ManyToOne(targetEntity=GatewayModel.class)
	private GatewayModel model;//EDT2411A
	private int hardwareId; //gateway mi self-control mu 
	private String aliasName;
	private long requestDate;
	private Date lastRequestDate;
	private Double lng;
	private Double lat;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="gateway")
	private List<Slave> slaveList = new ArrayList<>();
	
	
	public List<Slave> getSlaveList() {
		return slaveList;
	}
	public void setSlaveList(List<Slave> slaveList) {
		this.slaveList = slaveList;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		if(lng.isNaN()) {
			this.lng = null;
		}else {
			this.lng = lng;	
		}
		
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		if(lat.isNaN()) {
			this.lat = null;
		}else {
			this.lat = lat;
		}
			
	}
	public long getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(long requestDate) {
		this.requestDate = requestDate;
	}
	public Date getLastRequestDate() {
		return lastRequestDate;
	}
	public void setLastRequestDate(Date lastRequestDate) {
		this.lastRequestDate = lastRequestDate;
	}
	public int getId() {
		return id;
	}
	public int getHardwareId() {
		return hardwareId;
	}
	public void setHardwareId(int hardwareId) {
		this.hardwareId = hardwareId;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public GatewayModel getModel() {
		return model;
	}
	public void setModel(GatewayModel model) {
		this.model = model;
	}
	
}
