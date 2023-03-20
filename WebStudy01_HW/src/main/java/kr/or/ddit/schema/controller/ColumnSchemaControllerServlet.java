package kr.or.ddit.schema.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 처리해야 하는 요청 주소의 형태.
 * ex) /schema/columnSchema?what=MEMBER
 *
 */
@WebServlet("/schema/columnSchema")
public class ColumnSchemaControllerServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
	}
}
