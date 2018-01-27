package utc.bab.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Slave {
	@Id
	@GeneratedValue
	private int id;// database Id
	private int slaveId;//modbusdan gelicek
	private String alies;
	private int registerAddress;//modbusdan gelecek!
	private int registerType;//modbus
	private int decimalPoint;//kullanici ekliycek
	
	private int typeId;// sicaklik, basinc vs...
	
	private int gatewayId;// Gateway ile baglantili veritabani tablosu 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSlaveId() {
		return slaveId;
	}
	public String getAlies() {
		return alies;
	}
	public void setAlies(String alies) {
		this.alies = alies;
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
}
