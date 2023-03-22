package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.calculator.CalculateVO;

@WebServlet("/time/globalTime")
public class GlobalTimeServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("accept").toLowerCase();
		String	contentType = Optional.ofNullable(req.getContentType())
								.orElse("");
		
		
		ZoneId systemDefault = ZoneId.systemDefault();
		LocalDateTime defaultCurrent = LocalDateTime.now(systemDefault);
		
		Map<String, Object> targetMap = new HashMap<>();
		targetMap.put("systemDefault", systemDefault.toString());
		targetMap.put("defaultCurrent", defaultCurrent.toString());
		String timeZoneId = null;
		if(contentType.contains("json")) {
			Map<String, Object> nativeMap = getNativeObjectFromBodyContent(req);
			timeZoneId = (String) nativeMap.get("timeZone");
		}else {
			timeZoneId = req.getParameter("timeZone");
		}
		
		if(timeZoneId != null && !timeZoneId.isEmpty()) {
			try {
				ZoneId timeZone = ZoneId.of(timeZoneId);
				LocalDateTime current = LocalDateTime.now(timeZone);
				
				targetMap.put("timeZone", timeZone.toString());
				targetMap.put("current", current.toString());
				
			}catch (Exception e) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
		}
		
		if(accept.contains("json")) {
		}else if(accept.contains("xml")){
			maeshallingToXmlSendResp(targetMap, resp);
		}else {
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
		
		
	}

	private Map<String, Object> getNativeObjectFromBodyContent(HttpServletRequest req) throws IOException {
		// deSerialization, unMashalling
		try(
			InputStream is = req.getInputStream();
		){
			return new ObjectMapper().readValue(is, HashMap.class);
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



