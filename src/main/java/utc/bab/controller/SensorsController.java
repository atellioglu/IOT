package utc.bab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utc.bab.model.Sensors;
import utc.bab.model.UserCompany;
import utc.bab.model.UserToken;
import utc.bab.repository.SensorsRepository;
import utc.bab.repository.UserCompanyRepository;
import utc.bab.repository.UserTokenRepository;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
	@Autowired
	SensorsRepository sensorsRepository;
	@Autowired
	UserTokenRepository userTokenRepository;
	@Autowired
	UserCompanyRepository userCompanyRepository;
	
	@RequestMapping("/list")
	public ResponseEntity<?> getAll(String userToken){
		UserToken usrToken;
		UserCompany usrCompany;
			usrToken = userTokenRepository.findByToken(userToken);
			if(usrToken == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			usrCompany = userCompanyRepository.findByUserId(usrToken.getUserId());
		Iterable<Sensors> list = sensorsRepository.findAll();
		//TODO burada while ile donup company idsi ayni olan sensorleri liste atip dondurucez.
		return new ResponseEntity<Iterable<Sensors>>(list,HttpStatus.OK);
		
		//TODO 
		//TODO kullanicinin firmasinin sensorlerini vericez
	}
	//TODO burada sensorlerin degerleri gosterilecek Bu haftasonu icin yapilacak
	//TODO yani adam x sensorunu sectiginde x sensorunun datalarini gondericem.
	//
}
