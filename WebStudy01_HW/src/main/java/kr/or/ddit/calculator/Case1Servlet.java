package kr.or.ddit.calculator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator/case1")
public class Case1Servlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String leftParam = req.getParameter("leftOp");
		String rightParam = req.getParameter("rightOp");
		String operatorParam = req.getParameter("operator");
		
		int status = 200;
		if(leftParam == null || rightParam == null || operatorParam == null) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}else if(!leftParam.matches("\\d+") || !rightParam.matches("\\d+") 
				|| !operatorParam.matches("PLUS|MINUS|MULTIPLY|DIVIDE|MODULAR")){
			status = 400;
		}
		
		if(status==200) {
			
			int leftOp = Integer.parseInt(leftParam);
			int rightOp = Integer.parseInt(rightParam);
			int result = -1;
			String expression = null;
			String expPtrn = "%d %c %d = %d";
			switch (operatorParam) {
			case "PLUS":
				result = leftOp + rightOp;
				expression = String.format(expPtrn, leftOp, '+', rightOp, result);
				break;
			case "MINUS":
				result = leftOp - rightOp;
				expression = String.format(expPtrn, leftOp, '-', rightOp, result);
				break;
			case "MULTIPLY":
				result = leftOp * rightOp;
				expression = String.format(expPtrn, leftOp, '*', rightOp, result);
				break;
			case "DIVIDE":
				result = leftOp / rightOp;
				expression = String.format(expPtrn, leftOp, '/', rightOp, result);
				break;
			case "MODULAR":
				result = leftOp % rightOp;
				expression = String.format(expPtrn, leftOp, '%', rightOp, result);
				break;

			default:
				break;
			}
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
