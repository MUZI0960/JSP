package kr.or.ddit.auth;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 인증(Authentication)과 인가(Authorization)를 기본으로 하는 접근 제어.
 * Authentication : 신원 확인
 * Authorization :  신원 확인 후 허가받은 사용자인지 확인(권한을 소유했는지 확인)
 */
@Slf4j
public class AuthenticationFilter implements Filter {

	private Map<String, String[]> securedMap;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Properties properties = new Properties();
		try(
			InputStream is = getClass().getResourceAsStream("/kr/or/ddit/SecuredResources.properties");
		){
			properties.load(is);
			securedMap = properties.entrySet().stream()
							.peek((e)->{
								log.info("{} : {}", e.getKey(), e.getValue());
							}).collect(Collectors.toMap((e)->{
								return e.getKey().toString().trim();
							}, (e)->{
								String[] roles = e.getValue().toString().trim().split(",");
								Arrays.sort(roles);
								return roles;
							}));
			filterConfig.getServletContext().setAttribute("securedMap", securedMap);
		} catch (IOException e) {
			throw new ServletException(e);
		}
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = StringUtils.substringAfter(req.getRequestURI(), req.getContextPath());
//		1. 보호 자원의 요청 여부 판단
		boolean pass = false;
		boolean secured = securedMap.containsKey(uri);
		if(secured) {
//		2-1. 보호 자원 :
			Object authMember = req.getSession().getAttribute("authMember");
			Principal principal = req.getUserPrincipal();
			if(principal!=null) {
//			3-1. 인증된 사용자 : 통과
				pass = true;
			}else {
//			3-2. 미인증 사용자 : 로그인 폼으로 이동.
				pass = false;
			}
		}else {
//		2-2. 비보호 자원 : 통과
			pass = true;
		}
		if(pass) {
			chain.doFilter(request, response);
		}else {
			resp.sendRedirect(req.getContextPath() + "/login/loginForm.jsp");
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

















