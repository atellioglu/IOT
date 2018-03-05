package utc.bab.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utc.bab.model.Company;
import utc.bab.model.UserCompany;
import utc.bab.model.UserToken;
import utc.bab.repository.CompanyRepository;
import utc.bab.repository.UserCompanyRepository;
import utc.bab.repository.UserTokenRepository;

@Service
public class UserService {
	private static final Logger logger =  Logger.getLogger(UserService.class.getSimpleName()); 
	@Autowired
	UserTokenRepository userTokenRepository;
	
	@Autowired
	UserCompanyRepository userCompanyRepository;
	@Autowired
	CompanyRepository companyRepository;
	public UserCompany getUserCompanyFromToken(String userToken) {
		UserToken usrToken;
		UserCompany usrCompany;
		usrToken = userTokenRepository.findByToken(userToken);
		if (usrToken == null) {
			logger.info("User not found");
			return null;
		}
		usrCompany = userCompanyRepository.findByUserId(usrToken.getUserId());
		logger.info(userToken+" user's company id is "+ usrCompany.getCompanyId());
		
		return usrCompany;
	}
	public Company getCompanyFromToken(String userToken) {
		UserToken usrToken;
		UserCompany usrCompany;
		usrToken = userTokenRepository.findByToken(userToken);
		if (usrToken == null) {
			logger.info("User not found");
			return null;
		}
		
		usrCompany = userCompanyRepository.findByUserId(usrToken.getUserId());
		Company company = companyRepository.findById(usrCompany.getCompanyId());
		logger.info(userToken+" user's company id is "+ usrCompany.getCompanyId());
		return company;
	}
}
