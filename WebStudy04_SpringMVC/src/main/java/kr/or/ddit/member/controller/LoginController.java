package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.commons.event.AuthMemberEvent;
import kr.or.ddit.exception.BadRequestException;
import kr.or.ddit.member.exception.AuthenticateException;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.validate.LoginGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
	private final AuthenticateService service;
	@Inject
	private ApplicationEventPublisher publisher;
	
	@GetMapping("loginForm")
	public String form() {
		return "login/loginForm";
	}
	
	@PostMapping("loginProcess")
	public String loginProcess(
			HttpSession session
			, @Validated(LoginGroup.class) MemberVO input
			, Errors errors
			, RedirectAttributes redirectAttributes
	) {
		if(session.isNew()) {
			throw new BadRequestException("세션이 처음 만들어진거면 넌 누구냐!!!");
		}
		boolean valid = !errors.hasErrors();
		String viewName = null;
		if(valid) {
			try {
				MemberVO saved = service.authenticate(input);
				session.setAttribute("authMember", saved);
				publisher.publishEvent(new AuthMemberEvent(saved));
				
				viewName = "/";
			}catch (AuthenticateException e) {
				String message = "로그인 실패";
				redirectAttributes.addFlashAttribute("message", message);
				viewName = "/login/loginForm";
			}
		}else {
			redirectAttributes.addFlashAttribute("message", "필수 파라미터 누락");
			viewName = "/login/loginForm";
		}
		
		return "redirect:"+viewName;
	}
	
	@PostMapping("logout")
	public String logout(HttpSession session){
		if(session.isNew()) {
			throw new BadRequestException("유효 세션이 아님.");
		}
//		session.removeAttribute("authMember");
		session.invalidate();
		
		return "redirect:/";
	}
}


























