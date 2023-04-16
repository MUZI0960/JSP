package kr.or.ddit.commons.controller.advice;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.commons.dao.OthersDAO;
import kr.or.ddit.vo.BuyerVO;

@ControllerAdvice(basePackages = {"kr.or.ddit.prod.controller", "kr.or.ddit.buyer.controller"})
public class OthersControllerAdvice {
	@Inject
	private OthersDAO othersDAO;
	
	@ModelAttribute("lprodList")
	public List<Map<String, Object>> lprodList(){
		System.err.println("==============lprodList====================");
		 return othersDAO.selectLprodList();
	}
	
	@ModelAttribute("buyerList")
	public List<BuyerVO> buyerList() {
		System.err.println("==============buyerList====================");
		return othersDAO.selectBuyerList();
	}
}
