package utc.bab.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import utc.bab.model.SlaveValues;

public interface SlaveValuesRepository extends CrudRepository<SlaveValues, Integer> {

	@Query(nativeQuery = true, value="Select * from slave_values where slave_id = ?1 order by date desc limit 1")
	SlaveValues findLatestSlaveId(int slaveId);
}
