package utc.bab.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utc.bab.model.Company;
import utc.bab.model.Gateway;
import utc.bab.model.dto.GatewayDTO;
import utc.bab.repository.GatewayRepository;
import utc.bab.repository.SlaveRepository;
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
			gatDto.setSlaves(slaveRepository.findByGatewayIdOrderByIdDesc(gateway.getId()));
			
			dtoList.add(gatDto);
		}
		return dtoList;
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
}