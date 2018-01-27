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
import utc.bab.model.UserToken;
import utc.bab.repository.CompanyRepository;
import utc.bab.repository.UserCompanyRepository;
import utc.bab.repository.UserTokenRepository;
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
		UserToken usrToken;
		Company company;
		UserCompany usrCompany;
		usrToken = usrTokenRepo.findByToken(userToken);
		if(usrToken == null) {
			return new ResponseEntity<CustomException>(new CustomException("Unavailable User"),HttpStatus.BAD_REQUEST);
		}
		usrCompany = usrCompRepo.findByUserId(usrToken.getUserId());
		if(usrCompany == null) {
			return new ResponseEntity<CustomException>(new CustomException("This user have no Company"),HttpStatus.BAD_REQUEST);
		}
		
		company = companyRepository.findById(usrCompany.getCompanyId());
		return new ResponseEntity<UserCompany>(usrCompany,HttpStatus.OK);
	}
	

	
}
