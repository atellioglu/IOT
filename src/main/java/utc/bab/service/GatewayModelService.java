package utc.bab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utc.bab.model.GatewayModel;
import utc.bab.repository.GatewayModelRepository;

@Service
public class GatewayModelService {
	@Autowired
	GatewayModelRepository gatewayModelRepository;
	
	public GatewayModel getGatewayModel(int id) {
		return gatewayModelRepository.findOne(id);
	}
	public GatewayModel getGatewayModel(String name) {
		return gatewayModelRepository.findByName(name);
	}
	public List<GatewayModel> listAll() {
		return gatewayModelRepository.findAll();
	}
	
}
