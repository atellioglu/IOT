package utc.bab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utc.bab.model.Alert;
import utc.bab.repository.AlertRepository;

@RestController
@RequestMapping("/alert")
public class AlertController {
	@Autowired
	AlertRepository alertRepository;
	
	@GetMapping("/list")
	public ResponseEntity<?> list(){
		return new ResponseEntity<>(alertRepository.findAll(),HttpStatus.OK);
	}
	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody Alert alert){
		Alert savedAlert = alertRepository.save(alert);
		return new ResponseEntity<>(savedAlert,HttpStatus.OK);
	}
	@PostMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Alert alert){
		alertRepository.delete(alert);
		return new ResponseEntity<>(alert,HttpStatus.OK);
	}
	@PostMapping("/pause")
	public ResponseEntity<?> pauseOrUnpause(@RequestBody Alert alert){
		Alert alertFromEntity = alertRepository.findOne(alert.getId());
		alertFromEntity.setActive(!alertFromEntity.isActive());
		alertFromEntity = alertRepository.save(alertFromEntity);
		return new ResponseEntity<>(alertFromEntity,HttpStatus.OK);
	}
	
}
