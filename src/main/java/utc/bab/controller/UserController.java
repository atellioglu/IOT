package utc.bab.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import utc.bab.model.User;
import utc.bab.model.UserToken;
import utc.bab.repository.UserRepository;
import utc.bab.repository.UserTokenRepository;
import utc.bab.util.CustomException;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserTokenRepository userTokenRepository;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<?> insertUser(@RequestBody User user) {
		User existUser = userRepository.findByUserName(user.getUserName());
		if (existUser != null) {
			return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
		}
		User usrInserted = userRepository.save(user);
		return new ResponseEntity<User>(usrInserted, HttpStatus.OK);
	}

	@RequestMapping(value = "/name/available", method = RequestMethod.GET)
	public ResponseEntity<?> isUserNameAvailable(String usrName) {
		User existUser = userRepository.findByUserName(usrName);
		if (existUser != null) {
			return new ResponseEntity<CustomException>(new CustomException("This User is not AVAILABLE"),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<CustomException>(new CustomException(0,"This User is AVAILABLE"),HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user) {
		String token = UUID.randomUUID().toString().replace("-", "");
		UserToken userToken;
		User loginUser = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
		if(loginUser == null) 
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		System.out.println(token);
		userToken = userTokenRepository.findByUserId(loginUser.getId());
		// TODO : ileride tokena sure atamasi yapilacak ve tokeni olan kisinin suresi
		// var mi kontrolu yapilacak.
		if(userToken == null)
			userToken = new UserToken();
		userToken.setToken(token);
		userToken.setUserId(loginUser.getId());
		userToken = userTokenRepository.save(userToken);
		return new ResponseEntity<>(userToken, HttpStatus.OK);
	}
	@RequestMapping(value="/isTokenAlive", method=RequestMethod.GET)
	public ResponseEntity<?> isTokenAlive(String token){
		UserToken userToken;
		User usr;
		userToken = userTokenRepository.findByToken(token);
		if(userToken == null) {
			return new ResponseEntity<CustomException>(new CustomException("Unavailable Token"),HttpStatus.BAD_REQUEST);
		}
		usr = userRepository.findById(userToken.getUserId());
		return new ResponseEntity<User>(usr,HttpStatus.OK);
	}

}
