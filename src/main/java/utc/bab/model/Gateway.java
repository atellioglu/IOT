package utc.bab.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gateway {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int companyId;
	private String deviceId;//mac address
	private int model;
	private int hardwareId;
	private String aliasName;
	private long requestDate;
	private Date lastRequestDate;
	private long lng;
	private long lat;
	
	public long getLng() {
		return lng;
	}
	public void setLng(long lng) {
		this.lng = lng;
	}
	public long getLat() {
		return lat;
	}
	public void setLat(long lat) {
		this.lat = lat;
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
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
}
