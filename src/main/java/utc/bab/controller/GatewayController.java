package utc.bab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import utc.bab.model.Gateway;
import utc.bab.model.Slave;
import utc.bab.repository.GatewayRepository;
import utc.bab.repository.SlaveRepository;
import utc.bab.repository.UserRepository;
import utc.bab.repository.UserTokenRepository;

@RestController
@RequestMapping("/gateway")
public class GatewayController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserTokenRepository userTokenRepository;
	@Autowired
	GatewayRepository gatewayRepository;
	@Autowired
	SlaveRepository slaveRepository;
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Iterable<Gateway> list(String token){
		Iterable<Gateway> list =  gatewayRepository.findAll();
		return list;
	}
	@RequestMapping(value="/insert",method= RequestMethod.POST)
	public Gateway insert(@RequestBody Gateway gateway) {
		Gateway insertedGateway = gatewayRepository.save(gateway);
		return insertedGateway;
	}
	@RequestMapping(value="/slaves",method=RequestMethod.POST)
	public List<Slave> getSlaves(@RequestBody Gateway gateway) {
		List<Slave> list =  slaveRepository.findByGatewayId(gateway.getId());
		return list;
	}
	
}

