package utc.bab.model.dto;

import java.util.Date;
import java.util.List;

import utc.bab.model.Company;
import utc.bab.model.Gateway;
import utc.bab.model.GatewayModel;
import utc.bab.model.Slave;
import utc.bab.util.DeviceStatus;

public class GatewayDTO {
	
	private int id;
	private Company company;
	private String deviceId;//mac address
	private GatewayModel model;
	private int hardwareId;
	private String aliasName;
	private long requestDate;
	private Date lastRequestDate;
	private DeviceStatus status;
	private List<Slave> slaves;
	public GatewayDTO(Gateway gateway) {
		this.id = gateway.getId();
		this.deviceId = gateway.getDeviceId();
		this.model = gateway.getModel();
		this.hardwareId = gateway.getHardwareId();
		this.aliasName = gateway.getAliasName();
		this.requestDate = gateway.getRequestDate();
		this.lastRequestDate = gateway.getLastRequestDate();
		
	}
	
	public List<Slave> getSlaves() {
		return slaves;
	}

	public void setSlaves(List<Slave> slaves) {
		this.slaves = slaves;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	public DeviceStatus getStatus() {
		return status;
	}

	public void setStatus(DeviceStatus status) {
		this.status = status;
	}
}
