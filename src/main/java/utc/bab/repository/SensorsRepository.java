package utc.bab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import utc.bab.model.Sensors;

@Repository
public interface SensorsRepository extends CrudRepository<Sensors, Integer> {

		public Sensors findById(int id);

}
