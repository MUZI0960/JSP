package kr.or.ddit.commons.listener;

import javax.servlet.ServletContext;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.commons.event.AuthMemberEvent;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomEventListener {
	ServletContext application;
	
	@EventListener(AuthMemberEvent.class)
	public void authMemberEventListener(AuthMemberEvent event) {
		MemberVO currentUser = event.getSource();
		log.info("{} 님이 로그인했음.", currentUser.getMemId());
	}
	
	@EventListener(ContextRefreshedEvent.class)
	public void contextEventListener(ContextRefreshedEvent event) {
		if (application == null) {
			WebApplicationContext container = (WebApplicationContext) event.getApplicationContext();
			// 웹용 컨테이너는 내부에 서블릿 컨테이너를 갖고 있다.
			application = container.getServletContext();
			application.setAttribute("cPath", application.getContextPath());
		}
	}
}
