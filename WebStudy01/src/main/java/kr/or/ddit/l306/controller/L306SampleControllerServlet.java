package kr.or.ddit.l306.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sample.do")
public class L306SampleControllerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Date time = new Date();
		req.setAttribute("time", time);
		String viewName = "/WEB-INF/view/01/sample.l306";
		req.getRequestDispatcher(viewName).forward(req, resp);
		
	}
}
