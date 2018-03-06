package utc.bab.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import utc.bab.model.Alert;

@Repository
public interface AlertRepository extends CrudRepository<Alert, Long>{

	@Override
	List<Alert> findAll(); 
}
