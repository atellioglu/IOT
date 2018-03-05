package utc.bab.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utc.bab.model.Company;
import utc.bab.model.Gateway;
import utc.bab.model.Slave;
import utc.bab.repository.GatewayRepository;
import utc.bab.service.SlaveService;
import utc.bab.service.UserService;

@RestController
@RequestMapping("/slave")
public class SlaveController {
	@Autowired
	SlaveService slaveService;
	@Autowired
	GatewayRepository gatewayRepository;
	@Autowired
	UserService userService;
	@PostMapping("insert")
	public ResponseEntity<?> insertSlave(@RequestBody String requestStr) {
		JSONObject requestObject = new JSONObject(requestStr);
		String token = requestObject.getString("token");
		JSONObject slaveObject = requestObject.getJSONObject("slave");
		int gatewayId = slaveObject.getInt("gatewayId");
		Company company = userService.getCompanyFromToken(token);
		if(company == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		Gateway gateway = gatewayRepository.findOne(gatewayId);
		if(gateway == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		Slave slave = new Slave();
		slave.setGatewayId(gateway.getId());
		slave.setAlias(slaveObject.optString("alias"));
		slave.setDecimalPoint(slaveObject.getInt("decimalPoint"));
		slave.setSlaveId(slaveObject.getInt("slaveId"));
		slave.setRegisterType(slaveObject.getInt("registerType"));
		
		slave = slaveService.save(slave);
		
		return new ResponseEntity<>(slave, HttpStatus.OK);
	}
}
