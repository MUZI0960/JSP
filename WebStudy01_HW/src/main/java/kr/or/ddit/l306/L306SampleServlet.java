package kr.or.ddit.l306;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class L306SampleServlet extends HttpServlet{
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String servletPath = req.getServletPath();
		System.out.println(servletPath);
		// 논리주소를 넣어주면 물리주소를 준다.
		String realPath = application.getRealPath(servletPath);
		System.out.println(realPath);
		
		String template = Files.readAllLines(Paths.get(realPath))
							.stream()
							.collect(Collectors.joining("\n"));
		String contents = template.replace("<%=time %>", new Date().toString());
		try(
				PrintWriter out = resp.getWriter();
		) {
				out.print(contents);
		}
		
	}
	
}
