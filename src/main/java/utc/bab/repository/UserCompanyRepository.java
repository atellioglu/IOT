package utc.bab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import utc.bab.model.UserCompany;

@Repository
public interface UserCompanyRepository extends CrudRepository<UserCompany, Integer> {
	
	public UserCompany findByUserId(int usrId);
	public UserCompany findByCompanyId(int compId);

}
