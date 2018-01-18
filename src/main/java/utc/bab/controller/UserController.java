package utc.bab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import utc.bab.model.User;
import utc.bab.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public ResponseEntity<?> insertUser(@RequestBody User user) {
		User existUser = userRepository.findByUserName(user.getUserName());
		if(existUser!=null) {
			return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
		}
		User usrInserted = userRepository.save(user);
		return new ResponseEntity<User>(usrInserted, HttpStatus.OK);
	}
	@RequestMapping(value="/nameAvailable",method=RequestMethod.GET)
	public ResponseEntity<?> isUserNameAvailable(@RequestBody User user){
		User existUser = userRepository.findByUserName(user.getUserName());
		if(existUser!=null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
