package utc.bab.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import utc.bab.util.RegisterType;

@Entity
public class Slave {
	@Id
	@GeneratedValue
	private int id;// database Id
	private String alias;
	private int registerAddress;//modbusdan gelecek!
	private RegisterType registerType;//modbus (COIL,DISCRETE,INPUT,HOLDING)
	private int decimalPoint;//kullanici ekliycek
	private int typeId;// sicaklik, basinc vs...
	
	@ManyToOne(targetEntity=Gateway.class)
	@JsonIgnore
	private Gateway gateway;// Gateway ile baglantili veritabani tablosu 
	private long requestThreshold; // sure, iki request arasindaki olmasi gereken zaman farki!
	private long lastRequestDate;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="slave")
	private List<SlaveValues> slaveValues = new ArrayList<>();
	
	
	
	public List<SlaveValues> getSlaveValues() {
		return slaveValues;
	}
	public void setSlaveValues(List<SlaveValues> slaveValues) {
		this.slaveValues = slaveValues;
	}
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getRegisterAddress() {
		return registerAddress;
	}
	public void setRegisterAddress(int registerAddress) {
		this.registerAddress = registerAddress;
	}
	public RegisterType getRegisterType() {
		return registerType;
	}
	public void setRegisterType(RegisterType registerType) {
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
	public long getLastRequestDate() {
		return lastRequestDate;
	}
	public void setLastRequestDate(long lastRequestDate) {
		this.lastRequestDate = lastRequestDate;
	}
	public Gateway getGateway() {
		return gateway;
	}
	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}
	
}
