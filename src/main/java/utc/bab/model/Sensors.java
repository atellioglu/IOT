package utc.bab.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sensors {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int companyId;
	private String deviceId;
	private int model;
	private int hardwareId;
	private String aliasName;
	private int decimalPoint;
	private int extensionId;
	private Date requestDate;
	
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public int getDecimalPoint() {
		return decimalPoint;
	}
	public void setDecimalPoint(int decimalPoint) {
		this.decimalPoint = decimalPoint;
	}
	public int getExtensionId() {
		return extensionId;
	}
	public void setExtensionId(int extensionId) {
		this.extensionId = extensionId;
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
