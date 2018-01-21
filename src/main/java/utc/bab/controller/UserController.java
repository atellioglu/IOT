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
	public ResponseEntity<?> isUserNameAvailable(@RequestBody User user) {
		User existUser = userRepository.findByUserName(user.getUserName());
		if (existUser != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user) {
		String token = UUID.randomUUID().toString().replace("-", "");
		UserToken userToken;
		User loginUser = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
		if (loginUser != null) {
			System.out.println(token);
			userToken = userTokenRepository.findByUserId(loginUser.getId());
			// TODO : ileride tokena sure atamasi yapilacak ve tokeni olan kisinin suresi
			// var mi kontrolu yapilacak.
			if (userToken != null) {
				//System.out.println(token + "if de");
				userToken.setToken(token);
				userToken.setUserId(loginUser.getId());
				userToken = userTokenRepository.save(userToken);
				return new ResponseEntity<>(userToken, HttpStatus.OK);
			} else {
				userToken = new UserToken();
				//System.out.println(token + "else de");
				userToken.setUserId(loginUser.getId());
				userToken.setToken(token);
				userToken = userTokenRepository.save(userToken);
				return new ResponseEntity<>(userToken, HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

}
