package kr.or.ddit.servlet01;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageStreamingServlet extends HttpServlet{
	
	// 싱글톤
	private File imageFolder;
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application =  getServletContext();
		String imageFolderPath = application.getInitParameter("imageFolder");
		imageFolder = new File(imageFolderPath);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		
		if(name==null||name.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		File imageFile = new File(imageFolder, name);
		if(!imageFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		// stream copy 
		
		String mime = application.getMimeType(imageFile.getName());
		resp.setContentType(mime);
		OutputStream os = resp.getOutputStream();
		Files.copy(imageFile.toPath(), os);
		os.close();
	}
}
