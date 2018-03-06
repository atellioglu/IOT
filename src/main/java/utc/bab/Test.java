package utc.bab;

import java.util.Random;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utc.bab.model.Alert;
import utc.bab.model.Company;
import utc.bab.model.Gateway;
import utc.bab.model.GatewayModel;
import utc.bab.model.Slave;
import utc.bab.model.User;
import utc.bab.model.UserCompany;
import utc.bab.repository.AlertRepository;
import utc.bab.repository.CompanyRepository;
import utc.bab.repository.DeviceValuesRepository;
import utc.bab.repository.GatewayModelRepository;
import utc.bab.repository.GatewayRepository;
import utc.bab.repository.SlaveRepository;
import utc.bab.repository.UserCompanyRepository;
import utc.bab.repository.UserRepository;
import utc.bab.util.RegisterType;

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
	@Autowired
	GatewayModelRepository gatewayModelRepository;
	@Autowired
	SlaveRepository slaveRepository;
	@Autowired
	AlertRepository alertRepository;
    @PostConstruct
    public void init(){
		logger.info("Test class init");
		User user = new User();
		user.setUserName("asd");
		user.setPassword("123");
		user = userRepository.save(user);
		
		Company company = new Company();
		company.setName("Enda");
		company.setDeviceName("ARCEL");
		company.setDevicePassword("PASSS");
		company.setIconUrl("enda.png");
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
		
		client2 = gatewayRepository.save(client2);
		insertSlaves(client2);
		
    }
    public void insertSlaves(Gateway gateway) {
    		Random rnd = new Random();
    		int slaveSize = rnd.nextInt(5 + 2);
    		for(int i =0;i < slaveSize; i++) {
    			Slave slave = new Slave();
    			slave.setGateway(gateway);
    			slave.setAlias(String.format("Slave%d",(i + 1)));
    			slave.setDecimalPoint(1);
    			slave.setRegisterAddress(rnd.nextInt(255));
    			slave.setRegisterType(RegisterType.values()[rnd.nextInt(RegisterType.values().length)]);
    			slave.setRequestThreshold(gateway.getRequestDate());
    			slave = slaveRepository.save(slave);
    			gateway.getSlaveList().add(slave);
    		}
    }
    @PostConstruct
    public void insertAlerts() {
    		Random rnd = new Random();
    		int alertSize = rnd.nextInt(5)+ 2;
    		for(int i =0; i< alertSize;i++) {
    			Alert alert = new Alert();
    			alert.setName("Sunum Alarm - " + (i+1));
    			alert.setLowlim(rnd.nextInt(100));
    			alert.setUpLim(rnd.nextInt(100) + alert.getLowLim() + 10);
    			alert.setActive(rnd.nextInt(2) == 1 ? true : false);
    			alertRepository.save(alert);
    		}
    }
    @PostConstruct
    public void insertGatewayModels() {
    		for(int i =0;i<10;i++) {
    			GatewayModel gatModel = new GatewayModel();
    			gatModel.setName("EDT2411"+String.valueOf('A' + i));
    			gatewayModelRepository.save(gatModel);
    		}
    }
}
