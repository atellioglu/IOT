package utc.bab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import utc.bab.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByUserName(String userName);
	public User findByUserNameAndPassword(String userName, String password);
	public User findById(int id);
}
