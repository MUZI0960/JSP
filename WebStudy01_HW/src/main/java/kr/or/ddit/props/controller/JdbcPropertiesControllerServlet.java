package kr.or.ddit.props.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.props.service.PropertyService;
import kr.or.ddit.props.service.PropertyServiceImpl3;
import kr.or.ddit.props.vo.PropertyVO;

@WebServlet("/13/jdbcDesc.do")
public class JdbcPropertiesControllerServlet extends HttpServlet{
	private PropertyService service = new PropertyServiceImpl3();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String accept = req.getHeader("accept");
		
		String propertyName = req.getParameter("propertyName");
		
		String viewName = null;
		List<PropertyVO> list = service.retrieveProperties(propertyName);
		req.setAttribute("list", list);
		
		if(accept.contains("json")) {
			viewName = "/jsonView.view";
		}else{
			viewName = "/WEB-INF/view/13/jdbcDesc_case3.jsp";
		}
		
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
	
}
