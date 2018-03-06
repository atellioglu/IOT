package utc.bab.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Alert {
	//TODO company veya user listesi yok
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int lowLim;
	private int sensorId;
	private int upLim;
	private boolean active;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public int getLowLim() {
		return lowLim;
	}
	public void setLowlim(int lowLim) {
		this.lowLim = lowLim;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
