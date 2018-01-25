package utc.bab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import utc.bab.model.Company;
import utc.bab.repository.CompanyRepository;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	CompanyRepository companyRepository;

	@RequestMapping(value = "/insert", method=RequestMethod.POST)
	public ResponseEntity<?> insertCompany(@RequestBody Company cmpny) {
			Company existCompany = companyRepository.findByName(cmpny.getName()); 
			if(existCompany != null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			Company insertedCompany = companyRepository.save(cmpny);
			return new ResponseEntity<Company>(insertedCompany, HttpStatus.OK);
	}
	@RequestMapping("/getUser")
	public ResponseEntity<?> getCompanyByUserId(String userToken){
		//TODO user Id ile userin companysi getirilcek
		return null;
	}
	
	@RequestMapping(value="/getDevices", method=RequestMethod.GET)
	public ResponseEntity<?> getDevicesByCompanyId(String userToken, int companyId){
		//TODO Company id ye gore devicelari dondurcem.
		//TODO devicelarin yaninda 
		
		return null;
	}
	
}
