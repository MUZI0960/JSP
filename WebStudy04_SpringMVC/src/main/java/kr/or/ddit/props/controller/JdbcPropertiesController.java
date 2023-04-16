package kr.or.ddit.props.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.ddit.props.service.PropertyService;
import kr.or.ddit.props.vo.PropertyVO;

@Controller
public class JdbcPropertiesController{
	@Resource(name="propService3")
	private PropertyService service;
	
	@GetMapping(value="/13/jdbcDesc.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String doGet(String propertyName, Model model) {
		
		List<PropertyVO> list = service.retrieveProperties(propertyName);
		model.addAttribute("list", list);
		
		return "jsonView";
	}
	
	@GetMapping("/13/jdbcDesc.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "13/jdbcDesc_case3";
	}
}


















