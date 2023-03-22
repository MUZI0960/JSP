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
import kr.or.ddit.vo.ColumnSchemaVO;

/**
 * 처리해야 하는 요청 주소의 형태.
 * ex) /schema/columnSchema?what=MEMBER
 *
 */
@WebServlet("/schema/columnSchema")
public class ColumnSchemaControllerServlet extends HttpServlet{
	
	private SchemaService service = new SchemaServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String tableName = req.getParameter("tableName");
		
		List<ColumnSchemaVO> list = service.retrieveColumnSchemaListByTableName(tableName);
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/jsonView.view").forward(req, resp);
		
	}
}
