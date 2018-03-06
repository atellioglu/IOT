package utc.bab.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utc.bab.model.Company;
import utc.bab.model.Gateway;
import utc.bab.model.Slave;
import utc.bab.model.SlaveValues;
import utc.bab.model.dto.GatewayDTO;
import utc.bab.repository.GatewayRepository;
import utc.bab.repository.SlaveRepository;
import utc.bab.repository.SlaveValuesRepository;
import utc.bab.util.DeviceStatus;

@Service
public class GatewayService {
	private static final Logger logger =  Logger.getLogger(GatewayService.class.getSimpleName());
	@Autowired
	UserService userService;
	@Autowired
	GatewayRepository gatewayRepository;
	@Autowired
	SlaveRepository slaveRepository;
	@Autowired
	SlaveValuesRepository slaveValuesRepository;
	public List<GatewayDTO> getGatewaysFromUserToken(String token){
		if(token == null)
			return null;
		Company company = userService.getCompanyFromToken(token);
		if(company == null) {
			return null;
		}
		List<Gateway> list = gatewayRepository.findByCompanyId(company.getId());
		List<GatewayDTO> dtoList = new ArrayList<>();
		for(int i =0;i<list.size();i++) {
			Gateway gateway = list.get(i);
			GatewayDTO gatDto = new GatewayDTO(gateway);
			gatDto.setStatus(getGatewayStatus(gateway));
			gatDto.setCompany(company);
			gatDto.setSlaves(gateway.getSlaveList());
			
			dtoList.add(gatDto);
		}
		return dtoList;
	}
	public Gateway save(Gateway gateway) {
		return gatewayRepository.save(gateway);
	}
	public Gateway findByCompanyIdAndDeviceId(int companyId,String deviceId) {
		return gatewayRepository.findByCompanyIdAndDeviceId(companyId,deviceId);
	}
	
	public DeviceStatus getGatewayStatus(Gateway gateway) {
		if(gateway.getLastRequestDate() == null)
			return DeviceStatus.UNKNOWN;
		Date dateNow = new Date();
		long threshedTime = dateNow.getTime() - gateway.getLastRequestDate().getTime();
		if(threshedTime > gateway.getRequestDate()) {
			if(threshedTime > gateway.getRequestDate() * 2 ) {
				return DeviceStatus.BROKEN;
			}else {
				return DeviceStatus.UNKNOWN;
			}
		}else {
			return DeviceStatus.WORKING;
		}
	}
	public void dummySlaveValues(List<Gateway> gateways) {
		for(int i =0; i< gateways.size() ; i++) {
			dummySlaveValues(gateways.get(i));
		}
	}
	
	public void dummySlaveValues(Gateway gateway) {
		List<Slave> slaveList = gateway.getSlaveList();
		for(int i = 0 ;i < slaveList.size();i++) {
			Slave slave = slaveList.get(i);
			SlaveValues slaveValues = new SlaveValues();
			slaveValues.setDate(new Date());
			slaveValues.setSlave(slave);
			slaveValues.setData("asdfsaf");
			slaveValues = slaveValuesRepository.save(slaveValues);
			slave.getSlaveValues().add(slaveValues);
			
		}
	}
	public Gateway findById(Integer gatewayId) {
		return gatewayRepository.findOne(gatewayId);
	}
}
