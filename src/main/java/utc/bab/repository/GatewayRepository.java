package utc.bab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import utc.bab.model.Gateway;

@Repository
public interface GatewayRepository  extends CrudRepository<Gateway, Integer>{
	

}
