package kr.or.ddit.buyer.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.Pagination;

@Controller
@RequestMapping("/buyer/buyerList.do")
public class BuyerListController{
	@Inject
	private BuyerService service;

	@GetMapping
	public String getUI() {
		return "buyer/buyerList";
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Pagination<BuyerVO> doGet(
			@RequestParam(value="page", required = false, defaultValue = "1") int currentPage
			, @ModelAttribute("detailCondition")BuyerVO detailCondition
	){
		
		Pagination<BuyerVO> pagination = new Pagination<>(2, 2);
		pagination.setCurrentPage(currentPage);
		pagination.setDetailCondition(detailCondition);
		
		List<BuyerVO> buyerList = service.retrieveBuyerList(pagination);
		pagination.setDataList(buyerList);
		
//		model.addAttribute("pagination", pagination);
		
		return pagination;
	}
}











