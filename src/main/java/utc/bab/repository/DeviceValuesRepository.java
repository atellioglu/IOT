package utc.bab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import utc.bab.model.SlaveValues;

@Repository
public interface DeviceValuesRepository extends CrudRepository<SlaveValues, Integer> {
	@Query(nativeQuery=true,value="\n" + 
			"Select device_values.* from sensors,company,device_values where sensors.id = device_values.sensor_id AND company.id = sensors.company_id AND company.id = ?1 order by date desc limit ?2")
	List<SlaveValues> findAllTop(int companyId,int limit);
	
	/*public SlaveValues findBySensorId(int sensorId); */
}
