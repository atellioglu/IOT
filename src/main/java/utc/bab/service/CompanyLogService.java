package utc.bab.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utc.bab.model.Company;
import utc.bab.model.CompanyLog;
import utc.bab.repository.CompanyLogRepository;

@Service
public class CompanyLogService {
	@Autowired
	CompanyLogRepository companyLogRepository;
	
	public CompanyLog save(CompanyLog log) {
		log.setDate(new Date());
		return companyLogRepository.save(log);
	}
	public CompanyLog save(Company company, CompanyLog companyLog) {
		companyLog.setCompanyId(company.getId());
		return save(companyLog);
	}
	public CompanyLog save(Company company, String displayMessage) {
		CompanyLog log = new CompanyLog();
		
		log.setDisplayMessage(displayMessage);
		return save(company,log);
	}
	public CompanyLog save(Company company, String tag,String message) {
		return save(company,tag + ": "+message );
	}
}
