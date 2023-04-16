package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertController{
	@Inject
	private ProdService service;
	
	@PostConstruct
	public void init() throws IOException {
		log.info("주입된 서비스 객체 : {}", service);
	}
	
	@ModelAttribute("prod")
	public ProdVO prod() {
		return new ProdVO();
	}
	
	@GetMapping
	public String insertForm(){
		return "prod/prodForm";
	}
	
	@PostMapping
	public String insert(
		@Validated(InsertGroup.class) @ModelAttribute("prod") ProdVO prod
		, Errors errors
		, Model model
	) throws IOException{
		
		String viewName = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case OK:
				viewName = "redirect:/prod/prodView.do?what=" + prod.getProdId();
				break;
			default:
				model.addAttribute("message", "서버 오류로 인해 등록 실패, 쫌따 다시!");
				viewName = "prod/prodForm";
				break;
			}
		} else {
			viewName = "prod/prodForm";
		}
		return viewName;
	}
}


















