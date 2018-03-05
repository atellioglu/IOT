package utc.bab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import utc.bab.model.Company;
import utc.bab.model.UserCompany;
import utc.bab.repository.CompanyRepository;
import utc.bab.repository.UserCompanyRepository;
import utc.bab.repository.UserTokenRepository;
import utc.bab.service.UserService;
import utc.bab.util.CustomException;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	UserCompanyRepository usrCompRepo;
	@Autowired
	UserTokenRepository usrTokenRepo;
	@Autowired
	UserService userService;
	@RequestMapping(value = "/insert", method=RequestMethod.POST)
	public ResponseEntity<?> insertCompany(@RequestBody Company cmpny) {
			Company existCompany = companyRepository.findByName(cmpny.getName()); 
			if(existCompany != null) {
				return new ResponseEntity<CustomException>(new CustomException("This company exists!"),HttpStatus.BAD_REQUEST);
			}
			Company insertedCompany = companyRepository.save(cmpny);
			return new ResponseEntity<Company>(insertedCompany, HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.GET,value="/info")
	public ResponseEntity<?> getInfo(String token){
		UserCompany userCompany = userService.getUserCompanyFromToken(token);
		if(userCompany == null) {
			return new ResponseEntity<String>("User does not have company",HttpStatus.BAD_REQUEST);
		}
		Company company = companyRepository.findById(userCompany.getCompanyId());
		if(company == null) {
			return new ResponseEntity<String>("User does not have company",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Company>(company,HttpStatus.OK);
	}

	
}
