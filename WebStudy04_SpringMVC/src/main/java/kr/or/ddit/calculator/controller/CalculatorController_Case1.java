package kr.or.ddit.calculator.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.calculator.CalculateVO;

@Controller
@RequestMapping("/calculator/case1")
public class CalculatorController_Case1 {
	// json content -> json response
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CalculateVO case1(@RequestBody CalculateVO vo) {
		return vo;
	}
	// json content -> xml response
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public CalculateVO case2(@RequestBody CalculateVO vo) {
		return vo;
	}
	// json content -> html response
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
	public String case3(@RequestBody CalculateVO vo, Model model) {
		model.addAttribute("calculator", vo);
		return "calculator/expressionView";
	}
	
	// parameter content -> json response
	@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CalculateVO case4(@ModelAttribute("calculator") CalculateVO vo) {
		return vo;
	}
	
	// parameter content -> xml response
	@RequestMapping(produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public CalculateVO case5(CalculateVO vo) {
		return vo;
	}
	
	// parameter content -> html response
	@RequestMapping
	public String case6(@ModelAttribute("calculator") CalculateVO vo) {
		return "calculator/expressionView";
	}
	
	
}
























