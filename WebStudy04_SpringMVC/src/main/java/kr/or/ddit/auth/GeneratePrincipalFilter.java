package kr.or.ddit.auth;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

public class GeneratePrincipalFilter implements Filter {
	public static class PrincipalHttpServletRequestWrapper extends HttpServletRequestWrapper{
		private HttpServletRequest original;
		
		public PrincipalHttpServletRequestWrapper(HttpServletRequest request) {
			super(request);
			this.original = request;
		}
		
		@Override
		public Principal getUserPrincipal() {
			HttpSession session = original.getSession(false);
			return Optional.ofNullable(session)
							.map((s)->{
								MemberVO authMember = (MemberVO) s.getAttribute("authMember");
								return new MemberVOWrapper(authMember);
							}).orElse(null);
		}
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getSession().getAttribute("authMember")!=null) {
			PrincipalHttpServletRequestWrapper wrapper = new PrincipalHttpServletRequestWrapper(req);
			chain.doFilter(wrapper, response);
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}










