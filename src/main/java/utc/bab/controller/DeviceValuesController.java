package utc.bab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import utc.bab.model.DeviceValues;
import utc.bab.model.Sensors;
import utc.bab.repository.DeviceValuesRepository;
import utc.bab.repository.SensorsRepository;

@RestController
@RequestMapping("/deviceValues")
public class DeviceValuesController {

	@Autowired
	SensorsRepository sensorRepository;
	@Autowired
	DeviceValuesRepository valuesRepository;

	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public ResponseEntity<?> showValues(@RequestBody Sensors sensor) {
		Sensors existSensor;
		DeviceValues deviceValuesTop100;
		existSensor = sensorRepository.findById(sensor.getDeviceId());
		if(existSensor != null) {
			//TODO burada sensor id den top 100 getirelecek.
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}
}
