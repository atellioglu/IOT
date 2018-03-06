package utc.bab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utc.bab.model.Slave;
import utc.bab.repository.SlaveRepository;

@Service
public class SlaveService {
	@Autowired
	SlaveRepository slaveRepository;
	public Slave save(Slave slave) {
		return slaveRepository.save(slave);
	}
	
}
