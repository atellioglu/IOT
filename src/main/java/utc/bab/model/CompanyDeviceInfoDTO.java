package utc.bab.model;

public class CompanyDeviceInfoDTO {
	private Company company = null;
	//her bir gateway icin
	private int totalGatewayCount = 0;
	private int workingGatewayCount = 0;
	private int unknownGatewayCount = 0;
	private int brokenGatewayCount = 0;
	// her bir slave icin
	private int totalSlaveCount = 0;
	private int workingSlaveCount = 0;
	private int unknownSlaveCount = 0;
	private int brokenSlaveCount = 0;
	//her bir alarm icin 
	private int totalAlarmCount = 0;
	private int totalOccuredAlarmCount = 0;
	private int lastOccuredAlarmCount = 0;
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public int getTotalGatewayCount() {
		return totalGatewayCount;
	}
	public void setTotalGatewayCount(int totalGatewayCount) {
		this.totalGatewayCount = totalGatewayCount;
	}
	public int getWorkingGatewayCount() {
		return workingGatewayCount;
	}
	public void setWorkingGatewayCount(int workingGatewayCount) {
		this.workingGatewayCount = workingGatewayCount;
	}
	public int getUnknownGatewayCount() {
		return unknownGatewayCount;
	}
	public void setUnknownGatewayCount(int unknownGatewayCount) {
		this.unknownGatewayCount = unknownGatewayCount;
	}
	public int getBrokenGatewayCount() {
		return brokenGatewayCount;
	}
	public void setBrokenGatewayCount(int brokenGatewayCount) {
		this.brokenGatewayCount = brokenGatewayCount;
	}
	public int getTotalSlaveCount() {
		return totalSlaveCount;
	}
	public void setTotalSlaveCount(int totalSlaveCount) {
		this.totalSlaveCount = totalSlaveCount;
	}
	public int getWorkingSlaveCount() {
		return workingSlaveCount;
	}
	public void setWorkingSlaveCount(int workingSlaveCount) {
		this.workingSlaveCount = workingSlaveCount;
	}
	public int getUnknownSlaveCount() {
		return unknownSlaveCount;
	}
	public void setUnknownSlaveCount(int unknownSlaveCount) {
		this.unknownSlaveCount = unknownSlaveCount;
	}
	public int getBrokenSlaveCount() {
		return brokenSlaveCount;
	}
	public void setBrokenSlaveCount(int brokenSlaveCount) {
		this.brokenSlaveCount = brokenSlaveCount;
	}
	public int getTotalAlarmCount() {
		return totalAlarmCount;
	}
	public void setTotalAlarmCount(int totalAlarmCount) {
		this.totalAlarmCount = totalAlarmCount;
	}
	public int getTotalOccuredAlarmCount() {
		return totalOccuredAlarmCount;
	}
	public void setTotalOccuredAlarmCount(int totalOccuredAlarmCount) {
		this.totalOccuredAlarmCount = totalOccuredAlarmCount;
	}
	public int getLastOccuredAlarmCount() {
		return lastOccuredAlarmCount;
	}
	public void setLastOccuredAlarmCount(int lastOccuredAlarmCount) {
		this.lastOccuredAlarmCount = lastOccuredAlarmCount;
	}
	
	
}
