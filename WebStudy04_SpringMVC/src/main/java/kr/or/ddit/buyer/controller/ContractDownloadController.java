package kr.or.ddit.buyer.controller;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.attatch.view.AttatchDownloadView;
import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.exception.BadRequestException;
import kr.or.ddit.vo.AttatchFileVO;
import kr.or.ddit.vo.BuyerVO;

@Controller
public class ContractDownloadController {
	
	@Inject
	private BuyerService service;
	
	@Value("#{appInfo.contracts}")
	private File saveFolder;
	
	@RequestMapping("/buyer/contractDownload.do")
	public String downloadContract(@RequestParam("what") String buyerId, Model model){
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		AttatchFileVO atchContract = buyer.getAtchContract();
		atchContract.setAtchFile(new File(saveFolder, atchContract.getAtchSaveName()));
		
		if(atchContract!=null) {
			
			model.addAttribute(AttatchDownloadView.DOWNTARGETNAME, atchContract);
			
			return "attatchDownloadView";
			
		}else {
			throw new BadRequestException("없는 계약서를 다운로드 할 수는 없어요.");
		}
	}
}


















