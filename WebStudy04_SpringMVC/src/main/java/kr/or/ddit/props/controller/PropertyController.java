package kr.or.ddit.props.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.props.service.PropertyService;
import kr.or.ddit.props.vo.PropertyVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/props")
public class PropertyController{
	
	@Inject
	private WebApplicationContext container;
	
	@Autowired
	@Qualifier("propService1")
	private PropertyService service;
	
	private ServletContext application;
	

	@PostConstruct
	public void init(){
		application = container.getServletContext();
		log.info("주입된 컨테이너 레퍼런스 : {}", container);
		log.info("주입된 ServletContext 레퍼런스 : {}", application);
	}
	
	@DeleteMapping
	public String doDelete(@RequestBody PropertyVO delProp, Model model){
		boolean result = service.removeProperty(delProp);
		if(result) {
			return doGet(model);
		}else {
			model.addAttribute("error", 404);
			model.addAttribute("message", "삭제 실패");
			return "jsonView";
		}
	}

	@GetMapping
	public String doGet(Model model){
		List<PropertyVO> list = service.retrieveProperties(null);
		model.addAttribute("list", list);
		return "jsonView";
	}

	// PostRedirectGet pattern
	@PostMapping
	public String doPost(@RequestBody PropertyVO newProp) {
		service.createProperty(newProp);
		return "redirect:/props";
	}
	
	@PutMapping
	public String doPut(@RequestBody PropertyVO modifyProp, Model model){
		boolean result = service.modifyProperty(modifyProp);
		if(result) {
			model.addAttribute("status", 302);
			model.addAttribute("location", application.getContextPath() + "/props");
		}else {
			model.addAttribute("error", 500);
			model.addAttribute("message", "수정 실패");
		}
		return "jsonView";
	}

}















