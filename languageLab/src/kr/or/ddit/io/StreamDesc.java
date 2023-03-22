package kr.or.ddit.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Stream : 일련의 연속성을 가진 순차적 데이터의 흐름. 데이터 전송을 위한 단방향 통로.
 * 
 * 스트림의 종류
 * 1. 전송 데이터 크기에 따른 분류
 * 		1) byte Stream (1byte) : inputStream/OutputStream
 * 		ex) FileIn(Out)putStream, ByteArrayIn(Out)putStream, SocKetIn(Out)putStream
 * 		2) char Stream (2byte) : Reader/Writer
 * 		ex) FileReader(Writer), OracleClobReader(Writer), PrintWriter
 * 
 * 2. 스트림 생성 방법과 필터링 여부에 따른 분류
 * 		1) 1차 스트림 : 매체를 대상으로 직접 생성 및 개방할 수 있는 스트림.
 * 			ex) FileIn(Out)putStream, FileReader(Writer)
 * 		2) 2차(연결형) 스트림 : 1차 스트림을 대상으로 연결형으로 생성되는 스트림.
 * 			buffered : BufferedReader(Writer), BufferedIn(Out)putStream
 * 			filtered : DataIn(Out)putStream
 * 			직렬화/역직렬화 : (Serialization/Deserialization) : ObjectIn(Out)putStream
 *				- Serializable 객체의 상태를 전송이나 저장을 위해 바이트 배열의 형태로 바꾸는 작업
 *				- 매체 저장되어있거나 전송된 바이트 배열로부터 Serializable 객체의 상태를 복원하는 작업
 *	
 *	** 스트림을 통해 데이터를 읽는 단계
 *	1. 매체를 핸들링할 수 있는 형태의 객체로 생성. ex) new File("d:/contents/cat1.jpg");
 *	2. 단방향 1차 스트림 객체 생성, 매체를 대상으로 생성. ex) new FileInputStream(file);
 *		2-1. 필요시, 1차 스트림을 대상으로 2차 스트림 연결형으로 생성.  
 *	3. EOF(end of file), EOS(end of stream) (-1, null) 반복적인 읽기
 *	4. 2차에서 1차 순으로 stream 종료 (close)
 *	
 */				
public class StreamDesc {
	public static void main(String[] args) {
		String qualifiedName = "/kr/or/ddit/io/HypeBoy.txt";
		// 1. 
		URI readURI;
		
		try {
			readURI = StreamDesc.class.getResource(qualifiedName).toURI();
			Path readPath = Paths.get(readURI);
	//		File writefile = new File("D:/00.medias/HypeBoy_bak.txt");
	//		Path writePath = writefile.toPath();
			Path writhPath = Paths.get("D:/00.medias/HypeBoy_bak2.txt");
			Files.copy(readPath, writhPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void case3_classPathResource() {
		String qualifiedName = "/kr/or/ddit/io/HypeBoy.txt";
		String realPath = StreamDesc.class.getResource(qualifiedName).getPath();
		// 1. 
		File readfile = new File(realPath);
		File writefile = new File("D:/00.medias/HypeBoy_bak.txt");
		
		try (
			// 2.
			FileInputStream fis = new FileInputStream(readfile);
			FileWriter writer = new FileWriter(writefile);
			// 2-1.
			InputStreamReader isr = new InputStreamReader(fis, Charset.forName("ms949")); //젠더 역할 
			BufferedReader br = new BufferedReader(isr); 
			BufferedWriter bw = new BufferedWriter(writer);	
		){
			String tmp = null;
			while((tmp=br.readLine())!=null) {
				System.out.println(tmp);
				bw.write(tmp+"\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void case2_classPathResource() {
		String qualifiedName = "/kr/or/ddit/io/HypeBoy_utf8.txt";
		String realPath = StreamDesc.class.getResource(qualifiedName).getPath();
		// 1. 
		File readfile = new File(realPath);
		File writefile = new File("D:/00.medias/HypeBoy_utf8_bak.txt");
		
		try (
			// 2.
			FileReader reader = new FileReader(readfile);
			FileWriter writer = new FileWriter(writefile);
			// 2-1.
			BufferedReader br = new BufferedReader(reader);
			BufferedWriter bw = new BufferedWriter(writer);	
		){
			String tmp = null;
			while((tmp=br.readLine())!=null) {
				System.out.println(tmp);
				bw.write(tmp+"\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private static void case1_fileSystemResource() {
		String realPath = "D:/00.medias/another day.txt";
		// 1. 
		File readfile = new File(realPath);
		File writefile = new File("D:/00.medias/another_day_bak.txt");
		try (
			// 2.
			FileReader reader = new FileReader(readfile);
			FileWriter writer = new FileWriter(writefile);
			// 2-1.
			BufferedReader br = new BufferedReader(reader);
			BufferedWriter bw = new BufferedWriter(writer);	
		){
			String tmp = null;
			while((tmp=br.readLine())!=null) {
				System.out.println(tmp);
				bw.write(tmp+"\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}





