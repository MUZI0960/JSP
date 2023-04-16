package kr.or.ddit.prod.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.Pagination;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod")
public class ProdRetrieveController{
	@Inject
	private ProdService service;
	
	@RequestMapping("prodList.do")
	public String prodList(
		@RequestParam(value="page", required = false, defaultValue = "1") int currentPage
		, @ModelAttribute("detailCondition") ProdVO detailCondition
		, Model model
	){
		Pagination<ProdVO> pagination = new Pagination<>(5,2);
		pagination.setCurrentPage(currentPage);
		pagination.setDetailCondition(detailCondition);
		
		service.retrieveProdList(pagination);
		
		model.addAttribute("pagination", pagination);
		
		return  "prod/prodList";				
	}
	
	@RequestMapping("prodView.do")
	public String prodView(@RequestParam("what") String prodId, Model model){
		ProdVO prod = service.retrieveProd(prodId);
		model.addAttribute("prod", prod);
		
		return "prod/prodView";
	}
}












