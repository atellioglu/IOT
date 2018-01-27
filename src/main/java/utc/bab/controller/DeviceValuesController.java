package utc.bab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import utc.bab.model.SlaveValues;
import utc.bab.model.Gateway;
import utc.bab.repository.DeviceValuesRepository;
import utc.bab.repository.SensorsRepository;

@RestController
@RequestMapping("/deviceValues")
public class DeviceValuesController {

	@Autowired
	SensorsRepository sensorRepository;
	@Autowired
	DeviceValuesRepository valuesRepository;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ResponseEntity<?> showValues(@RequestBody Gateway sensor) {
		Gateway existSensor;
		SlaveValues deviceValuesTop100;
		existSensor = sensorRepository.findById(sensor.getId());
		if(existSensor != null) {
			//TODO burada sensor id den top 100 getirelecek.
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}
	@RequestMapping(value = "/insert",method= RequestMethod.POST)
	public ResponseEntity<?> insertFromSocket(
			String companyId, 
			String deviceId, String pass, int hardwareId, int model, 
			int functionId, int dataSize, String data){
		//TODO  1 bu device sensors tablosunda yoksa ilk olarak ekle.
		//TODO  2 gelen verileri deviceValues tablosuna yaz.
	return null;	
	}
}
