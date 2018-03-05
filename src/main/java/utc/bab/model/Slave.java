package utc.bab.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Slave {
	@Id
	@GeneratedValue
	private int id;// database Id
	private int slaveId;//modbusdan gelicek (1-254 arasi deger gelecek)
	private String alias;
	private int registerAddress;//modbusdan gelecek!
	private int registerType;//modbus (COIL,DISCRETE,INPUT,HOLDING)
	private int decimalPoint;//kullanici ekliycek
	private int typeId;// sicaklik, basinc vs...
	private int gatewayId;// Gateway ile baglantili veritabani tablosu 
	private long requestThreshold; // sure, iki request arasindaki olmasi gereken zaman farki!
	private long lastRequestDate;
	
	public long getRequestThreshold() {
		return requestThreshold;
	}
	public void setRequestThreshold(long requestThreshold) {
		this.requestThreshold = requestThreshold;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSlaveId() {
		return slaveId;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public void setSlaveId(int slaveId) {
		this.slaveId = slaveId;
	}
	public int getRegisterAddress() {
		return registerAddress;
	}
	public void setRegisterAddress(int registerAddress) {
		this.registerAddress = registerAddress;
	}
	public int getRegisterType() {
		return registerType;
	}
	public void setRegisterType(int registerType) {
		this.registerType = registerType;
	}
	public int getDecimalPoint() {
		return decimalPoint;
	}
	public void setDecimalPoint(int decimalPoint) {
		this.decimalPoint = decimalPoint;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getGatewayId() {
		return gatewayId;
	}
	public void setGatewayId(int gatewayId) {
		this.gatewayId = gatewayId;
	}
	public long getLastRequestDate() {
		return lastRequestDate;
	}
	public void setLastRequestDate(long lastRequestDate) {
		this.lastRequestDate = lastRequestDate;
	}
	
}
