package utc.bab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import utc.bab.model.DeviceValues;

@Repository
public interface DeviceValuesRepository extends CrudRepository<DeviceValues, Integer> {
	@Query(nativeQuery=true,value="\n" + 
			"Select device_values.* from sensors,company,device_values where sensors.id = device_values.sensor_id AND company.id = sensors.company_id AND company.id = ?1 order by date desc limit ?2")
	List<DeviceValues> findAllTop(int companyId,int limit);
}
