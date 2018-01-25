package utc.bab.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import utc.bab.model.Sensors;

@Repository
public interface SensorsRepository extends CrudRepository<Sensors, Integer> {
	Sensors findById(int id);
	List<Sensors> findByCompanyId(int companyId);	
	
}
