package utc.bab.util;

import java.util.Date;

public class UserNewsLog {

	private String text;
	private String hexColor;
	private Date date;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getHexColor() {
		return hexColor;
	}
	public void setHexColor(String hexColor) {
		this.hexColor = hexColor;
	}
}
