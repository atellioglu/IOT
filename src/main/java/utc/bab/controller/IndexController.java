package utc.bab.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utc.bab.repository.DeviceValuesRepository;
import utc.bab.repository.SensorsRepository;
import utc.bab.util.UserNewsLog;

@RestController
@RequestMapping("/")
public class IndexController {
	@Autowired
	SensorsRepository sensorRepository;
	@Autowired
	DeviceValuesRepository deviceValuesRepository;
	
	@RequestMapping("")
	public String helloWorld() {
		return "Hello Spring";
	}
	
	@RequestMapping("getLatestNews")
	public List<UserNewsLog> getLatestNews(String token,int companyId, int sensorId){
		List<UserNewsLog> list = new ArrayList<>();
		
		
		
		
		return list;
	}
	

}
