package utc.bab.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import utc.bab.model.GatewayModel;

public interface GatewayModelRepository extends CrudRepository<GatewayModel, Integer>{
	@Override
	List<GatewayModel> findAll();
	
	GatewayModel findByName(String name);

}
