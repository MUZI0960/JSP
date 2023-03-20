package kr.or.ddit.schema.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.schema.service.SchemaService;
import kr.or.ddit.vo.TableSchemaVO;

@WebServlet("/schema/schemaView.do")
public class SchemaControllerServlet extends HttpServlet{
	
	private SchemaService service;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TableSchemaVO> list = service.retrieveTableSchemaList();
		req.setAttribute("list", list);
		
		String viewName = "/WEB-INF/view/schema/schemaView.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
