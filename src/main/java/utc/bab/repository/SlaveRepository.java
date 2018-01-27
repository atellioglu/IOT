package utc.bab.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import utc.bab.model.Slave;

public interface SlaveRepository extends CrudRepository<Slave, Integer>{
	List<Slave> findByGatewayId(int id);

}
