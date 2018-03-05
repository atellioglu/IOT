package utc.bab.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class GatewayModel {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="model")
	@JsonIgnore
	private List<Gateway> gateway;

	public List<Gateway> getGateway() {
		return gateway;
	}

	public void setGateway(List<Gateway> gateway) {
		this.gateway = gateway;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
