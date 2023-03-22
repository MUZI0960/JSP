package kr.or.ddit.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/jsonView.view")
public class JsonViewServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Enumeration<String> names = req.getAttributeNames();
		Map<String, Object> target = new HashMap<String, Object>();
		while (names.hasMoreElements()) {
			String attrName = (String) names.nextElement();
			Object attrValue = req.getAttribute(attrName);
			target.put(attrName, attrValue);
		}
		
		resp.setContentType("application/json;charset=UTF-8");
		// 마샬링
		try(
			PrintWriter out = resp.getWriter();
		){
			new ObjectMapper().writeValue(out, target);
		}
		//
	}
}
