package kr.or.ddit.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

public class AuthorizationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		Map<String, String[]> securedMap =  (Map<String, String[]>) req.getServletContext().getAttribute("securedMap");
		String uri = StringUtils.substringAfter(req.getRequestURI(), req.getContextPath());
//		1. 보호 자원의 요청 여부 판단
		boolean secured = securedMap.containsKey(uri);
		boolean pass = false;
		if(secured) {
//		2-1. 보호자원
//			3. 권한 체크 : 사용자의 롤과 자원에 설정된 롤정보들이 일치.
//			MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
			MemberVOWrapper principal = (MemberVOWrapper) req.getUserPrincipal();
			MemberVO authMember = principal.getRealUser();
			String memRole = authMember.getMemRole();
			String[] roles = securedMap.get(uri);
			boolean hasPermission = Arrays.binarySearch(roles, memRole)>=0;
			if(hasPermission) {
//			4-1. 권한 소유(인가된 사용자) : 통과
				pass = true;	
			}else {
//			4-2. 비인가 사용자 : 403  에러.
				pass = false;
			}

		}else {
//		2-2. 비보호자원 : 통과
			pass = true;
		}
		if(pass) {
			chain.doFilter(request, response);
		}else {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN, String.format("%s 에 접근할 권한이 없음.", uri));
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}










