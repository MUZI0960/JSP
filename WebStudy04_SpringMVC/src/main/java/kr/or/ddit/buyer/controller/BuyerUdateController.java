package kr.or.ddit.buyer.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;

@Controller
public class BuyerUdateController{
	@Inject
	private BuyerService service;
	
	@RequestMapping("/buyer/buyerUpdate.do")
	public String updateForm(@RequestParam("what") String buyerId, Model model){
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		model.addAttribute("buyer", buyer);
		return "buyer/buyerForm";
	}
	

	
	@RequestMapping(value="/buyer/buyerUpdate.do", method = RequestMethod.POST)
	public String update(
		@Validated(UpdateGroup.class) @ModelAttribute("buyer") BuyerVO buyer
		, BindingResult errors
		, Model model
	) throws IOException{

		String viewName = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.modifyBuyer(buyer);
			switch (result) {
			case OK:
				viewName = "redirect:/buyer/buyerView.do?what=" + buyer.getBuyerId();
				break;
			default:
				model.addAttribute("message", "서버 오류로 인해 등록 실패, 쫌따 다시!");
				viewName = "buyer/buyerForm";
				break;
			}
		} else {
			viewName = "buyer/buyerForm";
		}
		return viewName;
	}
}












