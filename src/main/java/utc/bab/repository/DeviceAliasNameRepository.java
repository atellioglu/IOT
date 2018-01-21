package utc.bab.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import utc.bab.model.DeviceAliasName;

@Repository
public interface DeviceAliasNameRepository extends CrudRepository<DeviceAliasName, Integer>{

}
