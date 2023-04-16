package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/prod/prodUpdate.do")
public class ProdUpdateController{
	
	@Inject
	private ProdService service;
	
	@PostConstruct
	public void init() throws IOException {
		log.info("주입된 서비스 객체 : {}", service);
	}
	
	@GetMapping
	public String updateForm(@RequestParam("what") String prodId, Model model){
		ProdVO prod = service.retrieveProd(prodId);
		model.addAttribute("prod", prod);
		
		return "prod/prodEdit";
	}
	
	@PostMapping
	public String update(
			@Validated(UpdateGroup.class) @ModelAttribute("prod") ProdVO prod
			, BindingResult errors
			, Model model
	) throws IOException{

		String viewName = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.modifyProd(prod);
			switch (result) {
			case OK:
				viewName = "redirect:/prod/prodView.do?what=" + prod.getProdId();
				break;
			default:
				model.addAttribute("message", "서버 오류로 인해 등록 실패, 쫌따 다시!");
				viewName = "prod/prodEdit";
				break;
			}
		} else {
			viewName = "prod/prodEdit";
		}
		return viewName;
	}
}


















