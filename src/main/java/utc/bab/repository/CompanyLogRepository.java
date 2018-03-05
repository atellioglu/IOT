package utc.bab.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import utc.bab.model.CompanyLog;

@Repository
public interface CompanyLogRepository extends PagingAndSortingRepository<CompanyLog, Integer>{
	
}
