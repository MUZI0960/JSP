package kr.or.ddit.modelling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class MarshallingSample {
	public static void main(String[] args) {
//		serializeCase1();
//		deSerializeCase1();
//		marshallingCase2();
//		unMarshallingCase2();
//		marshallingCase3();
//		unMarshallingCase3();
//		marshallingCase4toXML();
		unMashallingCase4FromXML();
		
	}
	
	private static void unMashallingCase4FromXML() {
		File file = new File("D:/00.medias/test3.xml");
	      try(
	            FileInputStream fis = new FileInputStream(file);
	      ){
	         ObjectMapper objectMapper = new XmlMapper();
	          TestVO testVO = objectMapper.readValue(fis, TestVO.class);
	          System.out.println(testVO);
	      }catch (Exception e) {
	         e.printStackTrace();
	   }
	}
	
	private static void marshallingCase4toXML() {
		TestVO testVO = new TestVO();
		testVO.setProp1("value1");
		testVO.setProp2(45);
		testVO.setPassword("비밀번호");
		
		File file = new File("D:/00.medias/test3.xml");
		try(
			FileOutputStream fos = new FileOutputStream(file);
		){
			ObjectMapper objectMapper = new XmlMapper();
			objectMapper.writeValue(fos, testVO);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void unMarshallingCase3() {
		File file = new File("D:/00.medias/test2.json");
	      try(
	            FileInputStream fis = new FileInputStream(file);
	      ){
	         ObjectMapper objectMapper = new ObjectMapper();
	          TestVO testVO = objectMapper.readValue(fis, TestVO.class);
	          System.out.println(testVO);
	      }catch (Exception e) {
	         e.printStackTrace();
	   }
	}
	
	private static void marshallingCase3() {
		// native data
		TestVO testVO = new TestVO();
		testVO.setProp1("value1");
		testVO.setProp2(45);
		testVO.setPassword("비밀번호");
		
		File file = new File("D:/00.medias/test2.json");
		try(
			FileOutputStream fos = new FileOutputStream(file);
		){
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(fos, testVO);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void unMarshallingCase2() {
		File file = new File("D:/00.medias/test.json");
		try(
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
		){
			String json = br.readLine();
			
			//unmarshalling
			ObjectMapper objectMapper = new ObjectMapper();
			TestVO testVO = objectMapper.readValue(json, TestVO.class);
			System.out.println(testVO);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void marshallingCase2() {
		TestVO testVO = new TestVO();
		testVO.setProp1("value1");
		testVO.setProp2(45);
		
		// 직렬화 대상에서 제외
		testVO.setPassword("비밀번호");
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String json = objectMapper.writeValueAsString(testVO);
			System.out.println(json);
			
			File file = new File("D:/00.medias/test.json");
			try(
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
			){
				bw.write(json);
			}
//			TestVO testVO2 = objectMapper.readValue(json, TestVO.class);
//			System.out.println(testVO2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void deSerializeCase1() {
		File file = new File("D:/00.medias/test.dat");	
		try(
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);	
		){
			TestVO testVO =  (TestVO) ois.readObject();
			System.out.println(testVO);
			System.out.println(testVO.getPassword());
		}catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void serializeCase1() {
		TestVO testVO = new TestVO();
		testVO.setProp1("value1");
		testVO.setProp2(45);
		
		// 직렬화 대상에서 제외
		testVO.setPassword("비밀번호");
		
		File file = new File("D:/00.medias/test.dat");
		try(
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
		){
			oos.writeObject(testVO); 	// 직렬화
		}catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
}
