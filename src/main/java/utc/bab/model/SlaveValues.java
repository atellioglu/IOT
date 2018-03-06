package utc.bab.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SlaveValues {
	@Id
	@GeneratedValue
	private int id;//veritabani unique id
	
	private String data; //2 byte
	
	private Date date; // gelen tarih!
	
	@ManyToOne(targetEntity=Slave.class)
	@JsonIgnore
	private Slave slave;//slave veritabanindan!
	
	
	public Slave getSlave() {
		return slave;
	}
	public void setSlave(Slave slave) {
		this.slave = slave;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
