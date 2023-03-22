package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
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


//@WebServlet(value="/image/imageForm1",initParams = )
public class ImageFormServlet extends HttpServlet{
	
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
		String mime = "text/html;charset=UTF-8";
		resp.setContentType(mime);
		StringBuffer contents = new StringBuffer();
		
		contents.append("<html>                                   ");
		contents.append("<head>                                   ");
		contents.append("<meta charset='UTF-8'>                   ");
		contents.append("<title>Insert title here</title>         ");
		contents.append("</head>                                  ");
		contents.append("<body>                                   ");
		contents.append(String.format("<form action='%s/image.do'>", req.getContextPath()));
		contents.append("<select name='name'>                     ");

		String options = generateOptions();
		contents.append(options);
		
		contents.append("</select>                                ");
		contents.append("<input type='submit' value='전송'/>      ");
		contents.append("</form>                                  ");
//		contents.append("<img src='../../image.do?name=cat1.jpg'/>");
		contents.append("</body>                                  ");
		contents.append("</html>                                  ");
//		PrintWriter out = null;
//		try {
//			out = resp.getWriter();
//			out.print(contents);
//		}finally {
//			if(out!=null)
//				out.close();
//		}
		
		// try with resource 구문
		try(
				// Closable 객체 선언 및 생성
				PrintWriter	out = resp.getWriter();
		) {
			out.print(contents);
		}
		
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
}






