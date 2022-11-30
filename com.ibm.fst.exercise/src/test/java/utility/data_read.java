package utility;

import java.io.FileInputStream;
import java.util.Properties;


public class data_read {

	Properties prop;

	public data_read() throws Exception {

		FileInputStream data = new FileInputStream("C:\\Users\\0015NA744\\Documents\\Test Specialist\\FST Training\\eclipse-workspace\\ServiceNow\\data\\inputs.properties");

		prop = new Properties();

		prop.load(data);

	}

	public String getServiceNowApp() {

		return prop.getProperty("serviceNow_URL");

	}
	
	public String getSauceDemoApp() {

		return prop.getProperty("sauceDemo_URL");

	}
	
	public String getBrowser() {

		return prop.getProperty("browser");

	}

}