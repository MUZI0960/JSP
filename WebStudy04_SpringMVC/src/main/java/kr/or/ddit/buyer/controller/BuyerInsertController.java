package kr.or.ddit.buyer.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.BuyerVO;

/**
 * Hibernate-Validator 와 같은 java bean validation api 활용 방법
 * 1. hibernate-validator, validation-api
 * 2. LocalValidatorFactoryBean 의 빈 등록, HandlerAdapter 에게 주입(annotation-driven validator="" ).
 * 
 * 3. 객체 검증 위치에서 Validator 구현체를 주입 받아 검증을 하거나,
 *    @Valid, @Validated 어노테이션으로 command object 를 검증
 * 4. 검증의 결과는 Errors, BindingResult 타입의 객체로 받음.
 *    *** 검증 대상인 command object 바로 다음의 파라미터로 선언해야 함.  
 * 
 * 5. spring form 커스텀 태그를 이용해 검증의 결과와 바인딩 결과를 View layer 에서 출력함.
 */
@Controller
@RequestMapping("/buyer/buyerInsert.do")
public class BuyerInsertController{
	@Resource(name="buyerServiceImpl")
	private BuyerService service;

	@ModelAttribute("buyer")
	public BuyerVO buyer() {
		return new BuyerVO();
	}

	@GetMapping
	public String insertForm() {
		return "buyer/buyerForm"; // logical view name
	}
	
	@PostMapping
	public String insert(
		@Validated(InsertGroup.class) @ModelAttribute("buyer") BuyerVO buyer
		, Errors errors
		, Model model
	) throws IOException{

		String viewName = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.createBuyer(buyer);
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












