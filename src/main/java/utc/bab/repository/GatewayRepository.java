package utc.bab.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import utc.bab.model.Gateway;

@Repository
public interface GatewayRepository  extends CrudRepository<Gateway, Integer>{
	public List<Gateway> findByCompanyId(int companyId);

	public Gateway findByCompanyIdAndDeviceId(int companyId, String deviceId);

}
