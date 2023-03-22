package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * MIME(Multi purpose Internet Mail Extension) : 전송 컨텐츠의 종류를 표현하는 방법.
 *  mainType/subType;charset=XXX
 *  ex) text/html;charset=UTF-8, text/plain;charset=UTF-8
 *  	text/javascript, text/css
 *  	image/gif, video/mpeg, audio/mp4
 *
 */
@WebServlet("/mimeTest")
public class MimeTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mime = "text/plain;charset=UTF-8";
		response.setContentType(mime);
		StringBuffer content = new StringBuffer();
		content.append("<html>");
		content.append("<body>");
		content.append("<h4>한글 타이틀</h4>");
		content.append("</body>");
		content.append("</html>");
		PrintWriter out = response.getWriter();
		out.println(content);
		out.close();
		
	}

}
