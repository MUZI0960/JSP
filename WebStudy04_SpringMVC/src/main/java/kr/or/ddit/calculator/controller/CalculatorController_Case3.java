package kr.or.ddit.calculator.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.calculator.CalculateVO;

@Controller
@RequestMapping("/calculator/case3")
public class CalculatorController_Case3 {
	// parameter content -> json response
	@RequestMapping
	public String case1(@ModelAttribute("calculator") CalculateVO vo, Model model) {
		model.addAttribute("prop1", "샘플");
		model.addAttribute("prop2", 232);
		return "jsonView";
	}
	//	json content -> json response
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public String case2(@RequestBody CalculateVO vo, Model model) {
		model.addAttribute("calculator", vo);
		return "jsonView";
	}
}





















