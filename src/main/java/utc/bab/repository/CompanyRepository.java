package utc.bab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import utc.bab.model.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {
	public Company findByName(String name);
	public Company findById(int id);
	public Company findByDeviceNameAndDevicePassword(String deviceName,String password);

}
