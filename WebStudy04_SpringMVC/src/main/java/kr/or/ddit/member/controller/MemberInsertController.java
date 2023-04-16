package kr.or.ddit.member.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/memberInsert.do")
public class MemberInsertController{
	
	private final MemberService service;

	@ModelAttribute("member")
	public MemberVO member() {
		return new MemberVO();
	}
	
	@GetMapping
	public String insertForm(){
		return "member/memberForm"; // logical view name
	}

	@PostMapping
	public String insert(
			@Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member
			, BindingResult errors
			, Model model
	) throws IOException{

		String viewName = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				model.addAttribute("message", "아이디 중복");
				viewName = "member/memberForm";
				break;
			case OK:
				viewName = "redirect:/";
				break;
			default:
				model.addAttribute("message", "서버 오류로 인해 등록 실패, 쫌따 다시!");
				viewName = "member/memberForm";
				break;
			}
		} else {
			viewName = "member/memberForm";
		}
		
		return viewName;
	}
}











