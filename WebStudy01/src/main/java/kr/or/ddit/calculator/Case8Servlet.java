package kr.or.ddit.calculator;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.or.ddit.utils.RequestBodyProcessor;

@WebServlet("/calculator/case8")
public class Case8Servlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String accept = req.getHeader("accept");
		String requestContentType = Optional.ofNullable(req.getContentType())
										.orElse(""); // 값이 없으면 "" 반환	
		
		int status = 200;
		CalculateVO vo = null;
		try {
			if(requestContentType.contains("json")) {
				vo = RequestBodyProcessor.getContentFromRequestBody(req, CalculateVO.class);
			}else {
				vo = getCalculateVOFromParameter(req);
			}
		}catch (IllegalArgumentException e) {
			status = 400;
		}
		String viewName = null;
		if(status==200) {
			req.setAttribute("calculateModel", vo);
			
			if(accept.contains("json")) {
				viewName = "/jsonView.view";
			}else if(accept.contains("xml")) {
				viewName = "/xmlView.view";
			}else {
				viewName = "/WEB-INF/view/calculator/case7View.jsp";
			}
		}else {
			resp.sendError(status);
		}
		if(viewName!=null) {
			req.getRequestDispatcher(viewName).forward(req, resp);
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











