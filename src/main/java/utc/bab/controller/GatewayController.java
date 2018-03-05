package utc.bab.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import utc.bab.model.Company;
import utc.bab.model.Gateway;
import utc.bab.model.GatewayModel;
import utc.bab.model.Slave;
import utc.bab.model.dto.GatewayDTO;
import utc.bab.repository.GatewayRepository;
import utc.bab.repository.SlaveRepository;
import utc.bab.repository.UserRepository;
import utc.bab.repository.UserTokenRepository;
import utc.bab.service.GatewayModelService;
import utc.bab.service.GatewayService;
import utc.bab.service.UserService;

@RestController
@RequestMapping("/gateway")
public class GatewayController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;
	@Autowired
	UserTokenRepository userTokenRepository;
	@Autowired
	GatewayRepository gatewayRepository;
	@Autowired
	SlaveRepository slaveRepository;
	@Autowired
	GatewayService gatewayService;
	@Autowired
	GatewayModelService gatewayModelService;
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Iterable<GatewayDTO> list(String token){
		 return gatewayService.getGatewaysFromUserToken(token);
	}
	@RequestMapping(value="/insert",method= RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody String request) {
		JSONObject jsonObject = new JSONObject(request);
		String token = jsonObject.getString("token");
		Company companyFromToken = userService.getCompanyFromToken(token);
		if(companyFromToken == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		JSONObject gatewayJsonObject = jsonObject.getJSONObject("gateway");
		String aliasName = gatewayJsonObject.getString("aliasName");
		Double lat = gatewayJsonObject.optDouble("lat");
		Double lng = gatewayJsonObject.optDouble("lng");
		Long requestTime = new Long(15000);
		Integer model = gatewayJsonObject.optInt("modelId");
		Gateway gateway = new Gateway();
		gateway.setAliasName(aliasName);
		gateway.setLat(lat);
		gateway.setLng(lng);
		gateway.setModel(gatewayModelService.getGatewayModel(model));
		gateway.setCompanyId(companyFromToken.getId());
		gateway.setRequestDate(requestTime);
		gateway = gatewayService.save(gateway);
		return new ResponseEntity<Gateway>(gateway,HttpStatus.OK);
	}
	@RequestMapping(value="/slaves",method=RequestMethod.POST)
	public List<Slave> getSlaves(@RequestBody Gateway gateway,@RequestBody String token) {
		List<Slave> list =  slaveRepository.findByGatewayIdOrderByIdDesc(gateway.getId());
		
		return list;
	}
	
}

