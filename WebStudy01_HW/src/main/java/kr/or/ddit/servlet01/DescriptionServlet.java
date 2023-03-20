package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿이란?
 *  : WAS에 의해 관리되는 객체의 형태로, 웹상에서 발생한 요청을 받고, 그에 대한 응답을 전송할 수 있는 객체들의 요건에 대한 명세(spec) : HttpServlet
 *  
 *  WAS(Web Application Server), Web Container, Servlet Container, JSP Container
 *  	Container ? 내부의 관리객체(Component)에 대한 생명주기 관리자(운영 주체), 
 *  				객체 관리 중 발생하는 이벤트들에 대한 콜백(callback)구조를 가짐.
 *  		  
 *  	Servlet Container ? 내부의 관리객체(Servlet)에 대한 생명주기 관리자(운영 주체)  
 *  	JSP Container ? 내부의 관리객체(JSP)에 대한 생명주기 관리자(운영 주체)  
 *  
 *  서블릿이 정의된 callback 메소드 종류
 *  - lifecycle callback : init, destroy
 *  - request callback : service, doXXX(service에 의해 호출되고, request method에 따라 선택됨)
 *
 * 서블릿 개발 단계
 *	1. HttpServlet을 상속받은 구현체 정의
 *		- 필요한 callback 메소드 재정의
 *	2. compile : /WEB-INF/classes(context's classpath)에 배포
 *	3. 컨테이너에 등록
 *		- 2.x : web.xml -> servlet -> servlet-name, servlet-class, loadonstartup, init-param
 *		- 3.x : @WebServlet
 *  4. 서블릿 매핑
 *  	- 2.x : web.xml -> servlet-mapping -> servlet-name, url-pattern
 *  	- 3.x : @WebServlet(value, urlPatterns)
 *  	url : 
 *  		경로 매핑 :  ex) /image/imageForm.do (절대 경로)
 *  		확장자 매핑 : ex) *.l306, *.jsp (경로를 포함하지 않는다.)
 *  		/image/*.l306(X) 
 */
@WebServlet(value = "/desc") // CoC 에 따라, 클래스의 simple name이 서블릿 명으로 사용됨.
public class DescriptionServlet extends HttpServlet{

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.printf("%s 's init() 메소드 호출\n", this.getClass().getName());
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.printf("%s 's init(config) 메소드 호출\n", this.getClass().getName());
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service 메소드 실행 시작");
		
		super.service(req, resp);
		
		System.out.println("service 메소드 실행 종료");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 메소드 실행");
	}

	
	@Override
	public void destroy() {
		super.destroy();
		System.out.printf("%s 's destroy() 메소드 호출\n", this.getClass().getName());
	}
	
}










