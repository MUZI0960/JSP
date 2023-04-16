package kr.or.ddit.props.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import kr.or.ddit.props.vo.PropertyVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("propDao_FS")
public class PropertyDAOImpl_FS implements PropertyDAO {
	private Properties properties;
	
	@Value("classpath:kr/or/ddit/props/DataStore.properties") // property editor 에 의해 값이 변환된 후 주입됨.
	private File propFile;
	
	@PostConstruct
	public void loadData() throws IOException {
		log.info("로딩된 resource : {}", propFile);
		properties = new Properties();
		try(
			InputStream is = new FileInputStream(propFile);
		){
			properties.load(is);
		}	
	}
	
	private void storeData(){
		try(
			OutputStream os = new FileOutputStream(propFile);
		){
			properties.store(os, String.format("%s 에 저장함.", LocalDateTime.now()));
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}



	@Override
	public List<PropertyVO> selectProperties(String propertyName){
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
		Object removedData = properties.setProperty(prop.getPropertyName(), prop.getPropertyValue());
		storeData();
		return removedData==null ? 0 : 1;
	}
	@Override
	public int deleteProperty(PropertyVO prop){
		Object removedData = properties.remove(prop.getPropertyName());
		storeData();
		return removedData==null ? 0 : 1;
	}
}

























