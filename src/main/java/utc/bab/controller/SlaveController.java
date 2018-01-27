package utc.bab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import utc.bab.model.Slave;
import utc.bab.repository.GatewayRepository;
import utc.bab.repository.SlaveRepository;
import utc.bab.util.CustomException;

@RequestMapping("/slaves")
public class SlaveController {
	@Autowired
	SlaveRepository slaveRepository;
	@Autowired
	GatewayRepository gatewayRepository;

	public ResponseEntity<?> insertSlave(Slave slave) {
		slaveRepository.save(slave);

		return new ResponseEntity<CustomException>(new CustomException("Successfully inserted"), HttpStatus.OK);
	}
}
