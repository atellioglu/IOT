package utc.bab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import utc.bab.model.Company;
import utc.bab.model.DeviceAliasName;
import utc.bab.model.Sensors;
import utc.bab.repository.CompanyRepository;
import utc.bab.repository.DeviceAliasNameRepository;
import utc.bab.repository.SensorsRepository;


@RestController
@RequestMapping("/deviceAlias")
public class DeviceAliasNameController {
	@Autowired
	DeviceAliasNameRepository deviceAliasNameRepository;
	@Autowired
	CompanyRepository  companyRepository;
	@Autowired
	SensorsRepository sensorRepository;
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody DeviceAliasName deviceAlias) {
		//companyId,deviceId,deviceName,model
		DeviceAliasName existDeviceId,insertedDeviceAlias;
		Sensors insertedSensor = new Sensors();
		Company existCompany = companyRepository.findById(deviceAlias.getCompanyId());
		if(existCompany == null) {
			return new ResponseEntity<String>("Boyle bir sirket bulunmamaktadir.",HttpStatus.BAD_REQUEST);
		}else {
			existDeviceId = deviceAliasNameRepository.findOne(deviceAlias.getDeviceId());
			if(existDeviceId != null) {
				return new ResponseEntity<String>("Device ID mevcut", HttpStatus.BAD_REQUEST);
			}
			insertedDeviceAlias = deviceAliasNameRepository.save(deviceAlias);
			insertedSensor.setCompanyId(insertedDeviceAlias.getCompanyId());
			insertedSensor.setDeviceId(insertedDeviceAlias.getDeviceId());
			insertedSensor.setModel(insertedDeviceAlias.getModel());
			sensorRepository.save(insertedSensor);
			return new ResponseEntity<DeviceAliasName>(insertedDeviceAlias, HttpStatus.OK);
		}
		
	}
	
}
