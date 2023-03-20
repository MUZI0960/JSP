package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


@WebServlet("/time/globalTime2")
public class GlobalTimeServlet2 extends HttpServlet{
	
	Map<String, Object> targetMap = new HashMap<>();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("accept").toLowerCase();
		String requestContentType = Optional.ofNullable(req.getContentType())
				.orElse(""); // 값이 없으면 "" 반환	
		
		
		ZoneId systemDefault = ZoneId.systemDefault();
		LocalDateTime defaultCurrent = LocalDateTime.now(systemDefault);
		
//		Map<String, Object> targetMap = new HashMap<>();
		targetMap.put("systemDefault", systemDefault.toString());
		targetMap.put("defaultCurrent", defaultCurrent.toString());
		
		int status = 200;
		
		try {
			if(requestContentType.contains("json")) {
				targetMap = getCalculateVOFromBodyContent(req);
			}else {
				targetMap = getCalculateVOFromParameter(req, resp);
			}
		}catch (IllegalArgumentException e) {
			status = 400;
		}
		if(status==200) {
			
			if(accept.contains("json")) {
				marshallingToJson(targetMap, resp);
			}else if(accept.contains("xml")) {
				marshallingToXML(targetMap, resp);
			}else {
				sendHtmlContents(targetMap, resp);
			}
			
		}else {
			resp.sendError(status);
		}
		
	
	
	}
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String accept = req.getHeader("accept").toLowerCase();
//		
//		ZoneId systemDefault = ZoneId.systemDefault();
//		LocalDateTime defaultCurrent = LocalDateTime.now(systemDefault);
//		
//		Map<String, Object> targetMap = new HashMap<>();
//		targetMap.put("systemDefault", systemDefault.toString());
//		targetMap.put("defaultCurrent", defaultCurrent.toString());
//		
//		String timeZoneId = req.getParameter("timeZone");
//		if(timeZoneId != null && !timeZoneId.isEmpty()) {
//			try {
//				ZoneId timeZone = ZoneId.of(timeZoneId);
//				LocalDateTime current = LocalDateTime.now(timeZone);
//				
//				targetMap.put("timeZone", timeZone.toString());
//				targetMap.put("current", current.toString());
//				
//			}catch (Exception e) {
//				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//				return;
//			}
//		}
//		
//		if(accept.contains("json")) {
//		}else if(accept.contains("xml")){
//			maeshallingToXmlSendResp(targetMap, resp);
//		}else {
//			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
//		}
//		
//		
//	}

	private void sendHtmlContents(Map<String, Object> targetMap, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=UTF-8");
		try(
				PrintWriter out = resp.getWriter();	
			){
				out.print(targetMap.get(resp));
			}
		
	}

	private void marshallingToXML(Map<String, Object> targetMap, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/xml;charset=UTF-8");
		try(
				PrintWriter out = resp.getWriter();	
			){
				new XmlMapper().writeValue(out, targetMap.get(resp));
			}
		
	}

	private void marshallingToJson(Map<String, Object> targetMap, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json;charset=UTF-8");
		try(
				PrintWriter out = resp.getWriter();	
			){
				new ObjectMapper().writeValue(out, targetMap);
			}
		
	}

	private Map<String, Object> getCalculateVOFromParameter(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String accept = req.getHeader("accept").toLowerCase();
		
		String timeZoneId = req.getParameter("timeZone");
		if(timeZoneId != null && !timeZoneId.isEmpty()) {
			try {
				ZoneId timeZone = ZoneId.of(timeZoneId);
				LocalDateTime current = LocalDateTime.now(timeZone);
				
				targetMap.put("timeZone", timeZone.toString());
				targetMap.put("current", current.toString());
				
			}catch (Exception e) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return targetMap;
			}
		}
		
		if(accept.contains("json")) {
		}else if(accept.contains("xml")){
			maeshallingToXmlSendResp(targetMap, resp);
		}else {
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
		return targetMap;
	}

	private Map<String, Object> getCalculateVOFromBodyContent(HttpServletRequest req) {
		// deSerialization, unMashalling
		try(
			InputStream is = req.getInputStream();
		){
			return new ObjectMapper().readValue(is, targetMap.getClass());
		}catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	private void maeshallingToXmlSendResp(Map<String, Object> targetMap, HttpServletResponse resp) throws IOException {
//		marshalling : native data를 공통 표현 방식(json, xml)형태로 바꾸는 작업.
//		unmarshalling : 공통 표현 방식(json, xml)의 데이터를 native 언어 형태로 바꾸는 작업.
			
//		StringBuffer xml = new StringBuffer();
//		String rootElement = targetMap.getClass().getSimpleName();
//		xml.append(String.format("<%s>", rootElement));
//		String propPtrn = "<%1$s>%2$s</%1$s>";
//		for(Entry<String, Object> entry : targetMap.entrySet()) {
//			String propName = entry.getKey();
//			Object propValue = entry.getValue();
//			xml.append(String.format(propPtrn, propName, propValue));
//		}
//		xml.append(String.format("</%s>", rootElement));
//		
		resp.setContentType("application/xml;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();
		){
//			out.print(xml);
			ObjectMapper objectMapper = new XmlMapper();
			objectMapper.writeValue(out, targetMap);
		}
		
	}

	private void maeshallingToJasonSendResp(Map<String, Object> targetMap, HttpServletResponse resp) throws IOException {
//		marshalling : native data를 공통 표현 방식(json, xml)형태로 바꾸는 작업.
//		unmarshalling : 공통 표현 방식(json, xml)의 데이터를 native 언어 형태로 바꾸는 작업.
			
//		StringBuffer json = new StringBuffer();
//		json.append("{");
//		String propPtrn = "\"%s\" : \"%s\" ,";
//		for(Entry<String, Object> entry : targetMap.entrySet()) {
//			String propName = entry.getKey();
//			Object propValue = entry.getValue();
//			json.append(String.format(propPtrn, propName, propValue));
//		}
//		int lastIndexOf = json.lastIndexOf(",");
//		if(lastIndexOf!=-1) {
//			json.deleteCharAt(lastIndexOf);
//		}
//		json.append("}");
//		
		resp.setContentType("application/json;charset=UTF-8");
		try(
		PrintWriter out = resp.getWriter();
		){
//			out.print(json);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(out, targetMap);
		}
		
		
		
	}
	

}



