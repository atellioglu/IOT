package utc.bab.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class UserToken {
	@Id
	@GeneratedValue
	private int id;
	private int userId;
	private String token;
	private Date aliveTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getAliveTime() {
		return aliveTime;
	}

	public void setAliveTime(Date aliveTime) {
		this.aliveTime = aliveTime;
	}

}
