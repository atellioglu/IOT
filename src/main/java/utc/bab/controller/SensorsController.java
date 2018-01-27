package utc.bab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import utc.bab.model.SlaveValues;
import utc.bab.model.Gateway;
import utc.bab.model.UserCompany;
import utc.bab.model.UserToken;
import utc.bab.repository.DeviceValuesRepository;
import utc.bab.repository.SensorsRepository;
import utc.bab.repository.UserCompanyRepository;
import utc.bab.repository.UserTokenRepository;
import utc.bab.util.CustomException;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
	@Autowired
	SensorsRepository sensorsRepository;
	@Autowired
	UserTokenRepository userTokenRepository;
	@Autowired
	UserCompanyRepository userCompanyRepository;
	@Autowired
	SensorsRepository    sensorRepo;
	@Autowired
	DeviceValuesRepository deviceValueRepo;

	@RequestMapping("/list")
	public ResponseEntity<?> getAll(String userToken) {
		UserToken usrToken;
		UserCompany usrCompany;
		usrToken = userTokenRepository.findByToken(userToken);
		if (usrToken == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		usrCompany = userCompanyRepository.findByUserId(usrToken.getUserId());
		if (usrCompany == null)
			return new ResponseEntity<CustomException>(new CustomException("Company not found!"), HttpStatus.OK);

		Iterable<Gateway> list = sensorsRepository.findByCompanyId(usrCompany.getCompanyId());
		return new ResponseEntity<Iterable<Gateway>>(list, HttpStatus.OK);
	}
	@RequestMapping(value = "/device", method=RequestMethod.GET)
	public ResponseEntity<?> getDeviceById(int deviceId){
		Gateway sensor;
		SlaveValues deviceValues;
		sensor = sensorRepo.findByDeviceId(deviceId);
		if(sensor == null) {
			
			return new ResponseEntity<CustomException>(new CustomException("There is no sensor"),HttpStatus.OK);
		}
		/*deviceValues = deviceValueRepo.findBySensorId(sensor.getId());
		if(deviceValues == null) {
			return new ResponseEntity<CustomException>(new CustomException("There is no value"),HttpStatus.OK);
		}
		return new ResponseEntity<SlaveValues>(deviceValues,HttpStatus.OK); */
		return null;
	}
}
