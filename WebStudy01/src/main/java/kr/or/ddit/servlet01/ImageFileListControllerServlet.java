package kr.or.ddit.servlet01;

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

@WebServlet("/image/getImageFileNamesJson.do")
public class ImageFileListControllerServlet extends HttpServlet{
	
	private ServletContext application;
	private File imageFolder;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		String imageFolderPath = application.getInitParameter("imageFolder");
		imageFolder = new File(imageFolderPath);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
		
//		"[\"cat1.jpg\", \"cat2.png\" ... ]"
		String jsonPart = Arrays.stream(fileList)
							.map((file)->String.format("\"%s\"", file))
							.collect(Collectors.joining(","));
		
		resp.setContentType("application/json;cahrset=UTF-8");
		resp.getWriter().print(String.format("[%s]", jsonPart));
		
		
	}
	
}
