package utc.bab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import utc.bab.model.User;
import utc.bab.model.UserToken;

@Repository
public interface UserTokenRepository extends CrudRepository<UserToken, Integer>{
	public UserToken findByUserId(int id);
	public UserToken findByToken(String token);


}
