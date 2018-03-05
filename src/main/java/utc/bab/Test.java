package utc.bab;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utc.bab.model.Company;
import utc.bab.model.Gateway;
import utc.bab.model.User;
import utc.bab.model.UserCompany;
import utc.bab.repository.CompanyRepository;
import utc.bab.repository.DeviceValuesRepository;
import utc.bab.repository.GatewayRepository;
import utc.bab.repository.UserCompanyRepository;
import utc.bab.repository.UserRepository;

@Component
public class Test {
	private static final Logger logger =  Logger.getLogger(Test.class.getSimpleName());
	@Autowired
	UserRepository userRepository;
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	GatewayRepository gatewayRepository;
	@Autowired
	UserCompanyRepository userCompanyRepository;
	@Autowired
	DeviceValuesRepository deviceValuesRepository;
    @PostConstruct
    public void init(){
		logger.info("Test class init");
		User user = new User();
		user.setUserName("asd");
		user.setPassword("123");
		user = userRepository.save(user);
		
		Company company = new Company();
		company.setName("Tellioglu");
		company.setDeviceName("ARCEL");
		company.setDevicePassword("PASSS");
		
		company = companyRepository.save(company);
		UserCompany userCompany = new UserCompany();
		userCompany.setUserId(user.getId());
		userCompany.setCompanyId(company.getId());
		userCompanyRepository.save(userCompany);
		logger.info("Test finished!");
		Gateway client2 = new Gateway();
		client2.setAliasName("ClientSocketTest2");
		client2.setRequestDate(1000 * 15);
		client2.setDeviceId("XXXYZ2");
		client2.setCompanyId(company.getId());
		client2.setModel(8);
		client2 = gatewayRepository.save(client2);
    }
}
