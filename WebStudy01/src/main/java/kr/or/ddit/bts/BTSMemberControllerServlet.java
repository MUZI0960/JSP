package kr.or.ddit.bts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/btsMem/*", loadOnStartup = 1)
public class BTSMemberControllerServlet extends HttpServlet {
	private ServletContext application;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = Optional.of(req.getRequestURI()).map(u -> u.substring(req.getContextPath().length()))
				.map(p -> p.substring("/btsMem/".length())).get();
		Map<String, String[]> btsMap = (Map) application.getAttribute("btsMap");
		String[] member = btsMap.get(code);

		if (member == null) {
//			resp.sendError(404);
			throw new BtsMemberNotFoundException(code);
		} else {
			String logicalViewName = member[1];
			req.getRequestDispatcher("/" + logicalViewName + ".btsview").forward(req, resp);
		}
		
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		application = config.getServletContext();
		Map<String, String[]> btsMap = new HashMap<>();
		application.setAttribute("btsMap", btsMap);
		String pk = btsMap.keySet().stream().max(String::compareTo).map(code -> Integer.parseInt(code.substring(1)))
				.map(num -> String.format("B%03d", num + 1)).orElse("B001");
		btsMap.put(pk, new String[] { "RM", "bts/rm" });

		pk = btsMap.keySet().stream().max(String::compareTo).map(code -> Integer.parseInt(code.substring(1)))
				.map(num -> String.format("B%03d", num + 1)).orElse("B001");
		btsMap.put(pk, new String[] { "진", "/bts/jin" });

		pk = btsMap.keySet().stream().max(String::compareTo).map(code -> Integer.parseInt(code.substring(1)))
				.map(num -> String.format("B%03d", num + 1)).orElse("B001");
		btsMap.put(pk, new String[] { "슈가", "/bts/suga" });

		pk = btsMap.keySet().stream().max(String::compareTo).map(code -> Integer.parseInt(code.substring(1)))
				.map(num -> String.format("B%03d", num + 1)).orElse("B001");
		btsMap.put(pk, new String[] { "제이홉", "/bts/jhop" });

		pk = btsMap.keySet().stream().max(String::compareTo).map(code -> Integer.parseInt(code.substring(1)))
				.map(num -> String.format("B%03d", num + 1)).orElse("B001");
		btsMap.put(pk, new String[] { "지민", "/bts/jimin" });

		pk = btsMap.keySet().stream().max(String::compareTo).map(code -> Integer.parseInt(code.substring(1)))
				.map(num -> String.format("B%03d", num + 1)).orElse("B001");
		btsMap.put(pk, new String[] { "정국", "/bts/jungkuk" });

		pk = btsMap.keySet().stream().max(String::compareTo).map(code -> Integer.parseInt(code.substring(1)))
				.map(num -> String.format("B%03d", num + 1)).orElse("B001");
		btsMap.put(pk, new String[] { "뷔", "/bts/bui" });
	}
}
