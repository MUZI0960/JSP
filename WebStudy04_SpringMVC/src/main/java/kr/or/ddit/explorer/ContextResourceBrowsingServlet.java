package kr.or.ddit.explorer;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.explorer.fancytree.FancytreeNode;
import kr.or.ddit.explorer.fancytree.FileWrapper;

@WebServlet("/explorer/serverBrowsing")
public class ContextResourceBrowsingServlet extends HttpServlet{
	
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	private void businessLogicLayer(HttpServletRequest req) {
		String base = req.getParameter("base");
		String baseURL = Optional.ofNullable(base)
								.orElse("/");
//		String basePath = application.getRealPath(baseURL);
//		File root = new File(basePath);
		
		List<FancytreeNode<File>> children = application.getResourcePaths(baseURL)
				.stream()
				.map(lp->{
					System.out.println(lp);
					String pp = application.getRealPath(lp);
					return new FileWrapper(new File(pp));
				}).collect(Collectors.toList());
		
		Collections.sort(children);
		
		req.setAttribute("list", children);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("Accept");
		String viewName = null;
		if(accept.contains("json")) {
			businessLogicLayer(req);
			viewName = "/jsonView.view";
		}else {
			viewName = "/WEB-INF/view/explorer/serverBrowser.jsp";
			
		}
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}











