package kr.or.ddit.props.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import kr.or.ddit.props.vo.PropertyVO;

public class PropertyDAOImpl_FS implements PropertyDAO {
	private Properties properties;
	
	public PropertyDAOImpl_FS() {
		properties = new Properties();
		loadData();
	}

	private void loadData() {
	   try(
		   InputStream is = this.getClass().getResourceAsStream("../DataStore.properties");
		){
		   properties.load(is);
	   }catch(IOException e) {
		   throw new RuntimeException(e);
	   }
	}

	private void storeData() {
		URL url = this.getClass().getResource("../DataStore.properties");
		try {
			Path path = Paths.get(url.toURI());
			try(
				OutputStream os = Files.newOutputStream(path);
			){
				properties.store(os, String.format("%s에 저장함.", LocalDateTime.now()));
			}
			}catch (Exception e) {
				 throw new RuntimeException(e);
			}	
		
	}
	
	@Override
	public List<PropertyVO> selectProperties(String propertyName) {
		return properties.entrySet().stream()
							.map((e)->{
								PropertyVO propertyVO = new PropertyVO();
								propertyVO.setPropertyName(e.getKey().toString());
								propertyVO.setPropertyValue(e.getValue().toString());
								return propertyVO;
							}).collect(Collectors.toList());
	}
	
	@Override
	public int insertProperty(PropertyVO propertyVO) {
		 properties.setProperty(propertyVO.getPropertyName(), propertyVO.getPropertyValue());
		 storeData();
		 return 1;
	}
	
	
	@Override
	public int updateProperty(PropertyVO prop){
		Object removeData = properties.setProperty(prop.getPropertyName(), prop.getPropertyValue());
		storeData();
		return removeData == null ? 0 : 1;
	}
	
	@Override
	public int deleteProperty(PropertyVO prop){
		Object removeData = properties.remove(prop.getPropertyName());
		storeData();
		return removeData == null ? 0 : 1;
	}
}
