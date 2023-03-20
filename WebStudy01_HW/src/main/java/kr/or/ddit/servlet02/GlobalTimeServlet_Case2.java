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
import kr.or.ddit.utils.RequestBodyProcessor;

@WebServlet("/time/globalTime_case2")
public class GlobalTimeServlet_Case2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("accept").toLowerCase();
		String	contentType = Optional.ofNullable(req.getContentType())
								.orElse("");
		
		
		ZoneId systemDefault = ZoneId.systemDefault();
		LocalDateTime defaultCurrent = LocalDateTime.now(systemDefault);
		
		req.setAttribute("systemDefault", systemDefault.toString());
		req.setAttribute("defaultCurrent", defaultCurrent.toString());
		String timeZoneId = null;
		if(contentType.contains("json")) {
			Map<String, Object> nativeMap = RequestBodyProcessor.getContentFromRequestBody(req, HashMap.class);
			timeZoneId = (String) nativeMap.get("timeZone");
		}else {
			timeZoneId = req.getParameter("timeZone");
		}
		
		if(timeZoneId != null && !timeZoneId.isEmpty()) {
			try {
				ZoneId timeZone = ZoneId.of(timeZoneId);
				LocalDateTime current = LocalDateTime.now(timeZone);
				
				req.setAttribute("timeZone", timeZone.toString());
				req.setAttribute("current", current.toString());
			}catch (Exception e) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
		}
		
		String viewName = null;
		if(accept.contains("json")) {
			viewName = "/jsonView.view";
		}else if(accept.contains("xml")){
			viewName = "/xmlView.view";
		}else {
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
		if(viewName!=null) {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
		
		
	}

	

}



