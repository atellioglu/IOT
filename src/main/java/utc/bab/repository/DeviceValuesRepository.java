package utc.bab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import utc.bab.model.DeviceValues;

@Repository
public interface DeviceValuesRepository extends CrudRepository<DeviceValues, Integer> {

}
