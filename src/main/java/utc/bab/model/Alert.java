package utc.bab.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Alert {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private int sensorId;
	private int upLim;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSensorId() {
		return sensorId;
	}
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	public int getUpLim() {
		return upLim;
	}
	public void setUpLim(int upLim) {
		this.upLim = upLim;
	}
	public int getLowlim() {
		return lowlim;
	}
	public void setLowlim(int lowlim) {
		this.lowlim = lowlim;
	}
	private int lowlim;
	
}
