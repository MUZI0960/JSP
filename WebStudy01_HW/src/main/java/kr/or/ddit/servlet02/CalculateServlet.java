package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
	}
	
	private void maeshallingToXmlSendResp(Map<String, Object> targetMap, HttpServletResponse resp) throws IOException {
//		marshalling : native data를 공통 표현 방식(json, xml)형태로 바꾸는 작업.
//		unmarshalling : 공통 표현 방식(json, xml)의 데이터를 native 언어 형태로 바꾸는 작업.
			
		StringBuffer xml = new StringBuffer();
		String rootElement = targetMap.getClass().getSimpleName();
		xml.append(String.format("<%s>", rootElement));
		String propPtrn = "<%1$s>%2$s</%1$s>";
		for(Entry<String, Object> entry : targetMap.entrySet()) {
			String propName = entry.getKey();
			Object propValue = entry.getValue();
			xml.append(String.format(propPtrn, propName, propValue));
		}
		xml.append(String.format("</%s>", rootElement));
		
		resp.setContentType("application/xml;charset=UTF-8");
		try(
		PrintWriter out = resp.getWriter();
		){
			out.print(xml);
		}
		
	}

	private void maeshallingToJasonSendResp(Map<String, Object> targetMap, HttpServletResponse resp) throws IOException {
//		marshalling : native data를 공통 표현 방식(json, xml)형태로 바꾸는 작업.
//		unmarshalling : 공통 표현 방식(json, xml)의 데이터를 native 언어 형태로 바꾸는 작업.
			
		StringBuffer json = new StringBuffer();
		json.append("{");
		String propPtrn = "\"%s\" : \"%s\" ,";
		for(Entry<String, Object> entry : targetMap.entrySet()) {
			String propName = entry.getKey();
			Object propValue = entry.getValue();
			json.append(String.format(propPtrn, propName, propValue));
		}
		int lastIndexOf = json.lastIndexOf(",");
		if(lastIndexOf!=-1) {
			json.deleteCharAt(lastIndexOf);
		}
		json.append("}");
		
		resp.setContentType("application/json;charset=UTF-8");
		try(
		PrintWriter out = resp.getWriter();
		){
			out.print(json);
		}
	}
	
	
}
