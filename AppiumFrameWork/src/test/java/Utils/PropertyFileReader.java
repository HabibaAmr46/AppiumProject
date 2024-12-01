package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
	
	public static String getProperty(String key)
	{
		  Properties properties = new Properties();
	        String filePath = System.getProperty("user.dir")+"/src/test/resources/configuration.properties";

	        try (FileInputStream input = new FileInputStream(filePath)) {
	            properties.load(input);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        return properties.getProperty(key);
	}

}
