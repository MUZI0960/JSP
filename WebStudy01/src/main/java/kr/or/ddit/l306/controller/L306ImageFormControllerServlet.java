package kr.or.ddit.l306.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/image/imageForm3.do")
public class L306ImageFormControllerServlet extends HttpServlet{

	private ServletContext application;
	private File imageFolder;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		String imageFolderPath = application.getInitParameter("imageFolder");
		imageFolder = new File(imageFolderPath);
	}
	
	
	private String generateOptions() {
		
		String[] fileList = imageFolder.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				String mimeType = application.getMimeType(name);
				// 이 mimeType은 null일 수 있다.
				return Optional.ofNullable(mimeType)
						.orElse("application/octet-stream")
						.startsWith("image/");
			}
		});
		
		return Arrays.stream(fileList)
			.map((file)->{
				return String.format("<option>%s</option>", file);
			}).collect(Collectors.joining("\n")); // 모아서 하나씩 \n
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("cPath", req.getContextPath());
		req.setAttribute("options", generateOptions());
		String viewName = "/WEB-INF/view/02/imageForm.l306";
		req.getRequestDispatcher(viewName).forward(req, resp);
		
	}
	
}
