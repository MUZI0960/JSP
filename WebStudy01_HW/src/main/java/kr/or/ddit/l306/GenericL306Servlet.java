package kr.or.ddit.l306;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.l306")
public class GenericL306Servlet extends HttpServlet{
	
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		// 1. template 읽기
		String template = readTemplate(req);
		// 2. data 치환
		String contents = dataReplacement(req, template);
		// 3. contents 전송
		try(
				PrintWriter out = resp.getWriter();
		){
				out.print(contents);
		}
		
	}
	
	private static final Pattern ptrn = Pattern.compile("<%=(\\w+)\\s*%>");
	
	private String dataReplacement(HttpServletRequest req, String template) {
		Matcher matcher = ptrn.matcher(template);
		StringBuffer contents = new StringBuffer();
		
		while(matcher.find()) {
			System.out.println(matcher.group(1));
			String attributeName = matcher.group(1);
			Object attributeValue = req.getAttribute(attributeName);
			String replaceValue = Optional.ofNullable(attributeValue)
					.map((av)->{return av.toString();})
					.orElse("");
			matcher.appendReplacement(contents, replaceValue);
		}
		matcher.appendTail(contents);
		return contents.toString();
	}

	// 1. 템플릿 읽기
	private String readTemplate(HttpServletRequest req) throws IOException {
		String servletPath = req.getServletPath(); // /02/imageForm.l306
		
		System.out.println(servletPath);
		
		String realPath = application.getRealPath(servletPath);
		
		return Files.readAllLines(Paths.get(realPath))
							.stream()
							.collect(Collectors.joining("\n"));
	}

	
	
}
