package kr.or.ddit.calculator;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@WebServlet("/calculator/case4")
public class Case4Servlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String accept = req.getHeader("accept");
		
		int status = 200;
		CalculateVO vo = null;
		try {
			vo = getCalculateVOFromBodyContent(req);
		}catch (IllegalArgumentException e) {
			status = 400;
		}
		if(status==200) {
			
			if(accept.contains("json")) {
				marshallingToJson(vo, resp);
			}else if(accept.contains("xml")) {
				marshallingToXML(vo, resp);
			}else {
				sendHtmlContents(vo, resp);
			}
			
		}else {
			resp.sendError(status);
		}
	}
	
	private void sendHtmlContents(CalculateVO target, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=UTF-8");
		try(
				PrintWriter out = resp.getWriter();	
			){
				out.print(target.getExpression());
			}
				
	}
	
	private void marshallingToJson(CalculateVO target, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json;charset=UTF-8");
		try(
				PrintWriter out = resp.getWriter();	
			){
				new ObjectMapper().writeValue(out, target);
			}
	}
	private void marshallingToXML(CalculateVO target, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/xml;charset=UTF-8");
		try(
				PrintWriter out = resp.getWriter();	
			){
				new XmlMapper().writeValue(out, target);
			}
	}
	
	private CalculateVO getCalculateVOFromBodyContent(HttpServletRequest req) throws IOException {
		// deSerialization, unMashalling
		try(
			InputStream is = req.getInputStream();
				// req.getInputStream() => req-Body에 연결되어있음
		){
			return new ObjectMapper().readValue(is, CalculateVO.class);
		}catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	private CalculateVO getCalculateVOFromParameter(HttpServletRequest req) {
		String leftParam = req.getParameter("leftOp");
		String rightParam = req.getParameter("rightOp");
		String operatorParam = req.getParameter("operator");
		
		Operator oprator = null;
		
		int status = 200;
		if(leftParam == null || rightParam == null || operatorParam == null) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}else if(!leftParam.matches("\\d+") || !rightParam.matches("\\d+")){
			status = 400;
		}else {
			try {
				oprator = Operator.valueOf(operatorParam);
			}catch (Exception e) {
				status = 400;
			}
		}
		
		if(status == 200) {
			int leftOp = Integer.parseInt(leftParam);
			int rightOp = Integer.parseInt(rightParam);
			CalculateVO vo = new CalculateVO();
			vo.setLeftOp(leftOp);
			vo.setRightOp(rightOp);
			vo.setOperator(oprator);
			return vo;
		}else {
			throw new IllegalArgumentException("요청 파라미터가 잘못됐음.");
		}	
	}
	
	
}











