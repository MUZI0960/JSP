package kr.or.ddit.bts;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.btsview")
public class BtsViewServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		/ bts/rm .btsview
		String contentPage = Optional.of(req.getServletPath())
				.map(p->{
					int idx = p.lastIndexOf(".");
					return p.substring(1, idx);
				}).get();
		req.setAttribute("content", String.format("/WEB-INF/view/%s.jsp", contentPage));
		req.getRequestDispatcher("/WEB-INF/view/bts/btsLayout.jsp").forward(req, resp);
	}
}
