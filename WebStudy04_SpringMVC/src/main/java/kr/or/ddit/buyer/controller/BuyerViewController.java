package kr.or.ddit.buyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.vo.BuyerVO;

/**
 * command URI : /buyer/buyerView.do?what=P10101
 *
 */
@Controller
public class BuyerViewController{
	@Autowired
	private BuyerService service;
	
//	RequestToViewNameTranslator
	@RequestMapping("/buyer/buyerView.do")
	public void buyerView(@RequestParam("what") String buyerId, Model model){
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		model.addAttribute("buyer", buyer);
//		return "buyer/buyerView";
	}
}













