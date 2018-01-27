package utc.bab.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import utc.bab.model.Gateway;

@Repository
public interface SensorsRepository extends CrudRepository<Gateway, Integer> {
	Gateway findById(int id);
	List<Gateway> findByCompanyId(int companyId);	
	Gateway findByDeviceId(int deviceId);
	
}
