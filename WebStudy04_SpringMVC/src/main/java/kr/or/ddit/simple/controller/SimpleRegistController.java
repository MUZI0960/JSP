package kr.or.ddit.simple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.SimpleRegistVO;

@Controller
@RequestMapping("/simple/regist.do")
public class SimpleRegistController {
	
	@ModelAttribute("regist")
	public SimpleRegistVO regist() {
		return new SimpleRegistVO();
	}
	
	@GetMapping
	public String registForm() {
		return "simple/registForm";
	}
	
	@PostMapping
	public String registProcess(@Validated(InsertGroup.class) @ModelAttribute("regist") SimpleRegistVO regist, Errors errors) {
		
		if(!errors.hasErrors()) {
			return "redirect:/";
		}else {
			return "simple/registForm";
		}
	}
}






















