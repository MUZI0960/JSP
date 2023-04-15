package kr.or.ddit.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class ReadPropertiesFile {
	public static void main(String[] args) throws IOException {
		String baseName = "kr.or.ddit.properties.ValidationMessages";
//		ResourceBundle messageBundle = ResourceBundle.getBundle(baseName); // 기본 언어 사용
		ResourceBundle messageBundle = ResourceBundle.getBundle(baseName, Locale.ENGLISH);
		String message = messageBundle.getString("javax.validation.constraints.NotBlank.message");
		System.out.println(message);
		
//		Properties properties = new Properties();
//		String name = "/kr/or/ddit/properties/ValidationMessages_ko.properties";
//		try(
//			InputStream is = ReadPropertiesFile.class.getResourceAsStream(name);	
//				
//		){
//			properties.load(is);
//			String value = properties.getProperty("javax.validation.constraints.NotBlank.message");
//			System.out.println(value);
//		}
	}
}
