package utc.bab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utc.bab.model.Company;
import utc.bab.repository.CompanyRepository;

@Service
public class CompanyService {
	@Autowired
	CompanyRepository companyRepository;
	
	public Company save(Company company){
		Company existCom = companyRepository.findByName(company.getName());
		if(existCom != null) {
			return null;
		}
		return companyRepository.save(company);
	}
}
