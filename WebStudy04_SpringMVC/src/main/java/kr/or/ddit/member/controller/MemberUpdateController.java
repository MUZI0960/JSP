package kr.or.ddit.member.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member/memberUpdate.do")
public class MemberUpdateController{
	private final MemberService service; 
	
	@PostMapping
	public String update(
			@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member
			, Errors errors
			, @SessionAttribute(value="authMember", required=true) MemberVO authMember
			, MemberVOWrapper principal
			, Model model
	) throws IOException{
		log.info("principal : {}", principal);
		log.info("authMember : {}", authMember);
		
		member.setMemId(authMember.getMemId());

		String viewName = null;
		if(!errors.hasErrors()) {
//		통과
//			수정 로직
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
//			인증 실패
//				mypage 로 이동(forward) + message
				model.addAttribute("message", "비밀번호 오류");
				viewName = "member/mypage";
				break;
			case OK:
//			수정 성공
//				mypage 로 이동(redirect)
				viewName = "redirect:/mypage.do";
				break;

			default:
//			실패
				model.addAttribute("message", "서버 오류");
				viewName = "member/mypage";
				break;
			}
			
		}else {
//		불통
//			mypage 로 이동(forward) + errors
			viewName = "member/mypage";
		}
		
		return viewName;
	}
}


















