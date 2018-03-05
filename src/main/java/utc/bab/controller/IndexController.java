package utc.bab.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import utc.bab.constants.Messages;
import utc.bab.model.Company;
import utc.bab.model.CompanyDeviceInfoDTO;
import utc.bab.model.Gateway;
import utc.bab.model.Slave;
import utc.bab.model.SlaveValues;
import utc.bab.model.SocketModel;
import utc.bab.repository.CompanyRepository;
import utc.bab.repository.DeviceValuesRepository;
import utc.bab.repository.GatewayRepository;
import utc.bab.repository.SensorsRepository;
import utc.bab.repository.SlaveRepository;
import utc.bab.repository.SlaveValuesRepository;
import utc.bab.service.CompanyLogService;
import utc.bab.service.GatewayService;
import utc.bab.service.UserService;
import utc.bab.util.DeviceStatus;
import utc.bab.util.UserNewsLog;

@RestController
@RequestMapping("/")
public class IndexController {
	@Autowired
	SensorsRepository sensorRepository;
	@Autowired
	DeviceValuesRepository deviceValuesRepository;
	@Autowired
	UserService userService;
	@Autowired
	GatewayRepository gatewayRepository;
	@Autowired
	SlaveRepository slaveRepository;
	@Autowired
	SlaveValuesRepository slaveValuesRepository;
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	CompanyLogService companyLogService;
	@Autowired
	GatewayService gatewayService;
	
	@RequestMapping("")
	public String hello() {
		return "Hello World!";
	}
	
	@RequestMapping("getLatestNews")
	public List<UserNewsLog> getLatestNews(String token,int companyId, int sensorId){
		List<UserNewsLog> list = new ArrayList<>();
		
		return list;
	}
	@RequestMapping(value= "socket",method=RequestMethod.POST)
	public ResponseEntity<?> socketRequest(@RequestBody SocketModel socketModel){
		
		Company company = companyRepository.findByDeviceNameAndDevicePassword(socketModel.getDeviceName(), socketModel.getPass());
		if(company == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Gateway gateway = gatewayRepository.findByCompanyIdAndDeviceId(company.getId(), socketModel.getDeviceId());
		if(gateway != null) {
			gateway.setLastRequestDate(new Date());
			gatewayRepository.save(gateway);
			//hallet iste geri kalan veriyi de...
		}else {
			companyLogService.save(company, Messages.GATEWAY_NOT_INSERTED,socketModel.getDeviceId());
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@PostMapping("info")
	public ResponseEntity<?> companyInfo(@RequestBody String tmp){
		JSONObject json = new JSONObject(tmp);
		String token = null;
		try {
			token = json.getString("token");	
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Company company = userService.getCompanyFromToken(token);
		if(company == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		CompanyDeviceInfoDTO companyDeviceInfo = new CompanyDeviceInfoDTO();
		companyDeviceInfo.setCompany(company);
		
		List<Gateway> gateways = gatewayRepository.findByCompanyId(company.getId());
		
		companyDeviceInfo.setTotalGatewayCount(gateways.size());
		Date dateNow = new Date();
		for(int i =0;i<gateways.size();i++) {
			//gateway icin calisip calismadigi test edilecek...
			DeviceStatus gatewayStatus = gatewayService.getGatewayStatus(gateways.get(i));
			switch (gatewayStatus) {
			case BROKEN:
				companyDeviceInfo.setBrokenGatewayCount(companyDeviceInfo.getBrokenGatewayCount() + 1);
				break;
			case UNKNOWN:
				companyDeviceInfo.setUnknownGatewayCount(companyDeviceInfo.getUnknownGatewayCount() + 1);
				break;
			case WORKING:
				companyDeviceInfo.setWorkingGatewayCount(companyDeviceInfo.getWorkingGatewayCount() + 1);
				break;
			default:
				break;
			}
			
			List<Slave> slaves = slaveRepository.findByGatewayIdOrderByIdDesc(gateways.get(i).getId());
			//slave sayisini kumulatif bir sekilde toplayacak!
			companyDeviceInfo.setTotalSlaveCount(companyDeviceInfo.getTotalSlaveCount() + slaves.size());
			for(int j =0 ;j<slaves.size();j++) {
				Slave slave = slaves.get(j);
				SlaveValues slaveValues = slaveValuesRepository.findLatestSlaveId(slave.getId());
				Date requestedDate = slaveValues.getDate();
				long threshedTime = dateNow.getTime() - requestedDate.getTime();
				if(threshedTime > slave.getRequestThreshold()) {
					//sure cok bozulmus olabilir
					if(threshedTime > slave.getRequestThreshold() * 2) {
						//kesin gecikmis!
						companyDeviceInfo.setBrokenSlaveCount(companyDeviceInfo.getBrokenSlaveCount() + 1);
					}else {
						//bekliyor
						companyDeviceInfo.setUnknownSlaveCount(companyDeviceInfo.getUnknownSlaveCount() + 1);
					}
				}else {
					//sure az calisiyo
					companyDeviceInfo.setWorkingSlaveCount(companyDeviceInfo.getWorkingSlaveCount() + 1);
				}
			}
		}
		return new ResponseEntity<CompanyDeviceInfoDTO>(companyDeviceInfo, HttpStatus.OK);
	}
	

}
