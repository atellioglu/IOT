package utc.bab.util;

public class CustomException {
	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CustomException() {
		
	}
	public CustomException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public CustomException(String message) {
		this(-1,message);
	}
	
}
