package kr.or.ddit.schema.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.schema.service.SchemaService;
import kr.or.ddit.schema.service.SchemaServiceImpl;
import kr.or.ddit.vo.TableSchemaVO;

@WebServlet("/schema/tableSchema")
public class TableSchemaControllerServlet extends HttpServlet{
	private SchemaService service = new SchemaServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TableSchemaVO> tableList = service.retrieveTableSchemaList();
		req.setAttribute("tableList", tableList);
		String viewNam = "/jsonView.view";
		req.getRequestDispatcher(viewNam).forward(req, resp);
	}
}
