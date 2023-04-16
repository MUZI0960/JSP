package kr.or.ddit.calculator.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.calculator.CalculateVO;

@RestController
@RequestMapping(value="/calculator/case2", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CalculatorController_Case2 {
	// RESTfurl URI , /member(GET), /member/a001(GET), /member/a001(PUT)
	// parameter content -> json response
	@RequestMapping
	public CalculateVO case1(CalculateVO vo) {
		return vo;
	}
	
	// json content -> json response
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public CalculateVO case2(@RequestBody CalculateVO vo ) {
		return vo;
	}
}
