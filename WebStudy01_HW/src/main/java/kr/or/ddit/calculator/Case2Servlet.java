package kr.or.ddit.calculator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator/case2")
public class Case2Servlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String leftParam = req.getParameter("leftOp");
		String rightParam = req.getParameter("rightOp");
		String operatorParam = req.getParameter("operator");
		
		Operator operator = null;
		
		int status = 200;
		if(leftParam == null || rightParam == null || operatorParam == null) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}else if(!leftParam.matches("\\d+") || !rightParam.matches("\\d+")){
			status = 400;
		}else {
			try {
				operator = Operator.valueOf(operatorParam);
			}catch (Exception e) {
				status = 400;
			}
		}
		
		if(status==200) {
			
			int leftOp = Integer.parseInt(leftParam);
			int rightOp = Integer.parseInt(rightParam);
			int result = operator.oprate(leftOp, rightOp);
			String expression = operator.getExpression(leftOp, rightOp);
			
			resp.setContentType("text/html;charset=UTF-8");
			try(
				PrintWriter out = resp.getWriter();
			){
				out.print(expression);
			}
		}else {
			resp.sendError(status);
		}
		
	}
}
