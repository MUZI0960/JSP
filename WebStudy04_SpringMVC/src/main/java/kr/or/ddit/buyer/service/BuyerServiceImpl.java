package kr.or.ddit.buyer.service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.or.ddit.attatch.dao.AttatchFileDAO;
import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.vo.AttatchFileVO;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.Pagination;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {
	@Inject
	private BuyerDAO buyerDAO;		
	@Autowired
	private AttatchFileDAO atchDAO;

	@Value("#{appInfo.contracts}")
	private File saveFolder;
	
	@PostConstruct
	public void init() {
		log.info("주입된 dao 객체1 : {}", buyerDAO);
		log.info("주입된 dao 객체2 : {}", atchDAO);
		
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}

	private void processContractFile(BuyerVO buyer){
		Optional.ofNullable(buyer.getAtchContract())
				.ifPresent((c)->{
					try {
//						if(1==1) throw new IOException("트랜잭션 관리 여부 확인을 위한 강제 발생 예외");
						c.saveTo(saveFolder);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				});
	}
	
	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		AttatchFileVO contract = buyer.getAtchContract();
		if(contract!=null) {
			// 파일 메타데이터 db에 저장
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("attatchList", Collections.singletonList(contract));
			atchDAO.insertAttatchList(paramMap);
			Integer atchId = (Integer) paramMap.get("atchId");
			buyer.setBuyerContract(atchId);
		}
		int rowcnt = buyerDAO.insertBuyer(buyer);
		if(rowcnt>0) {
			processContractFile(buyer);			
		}
		
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public List<BuyerVO> retrieveBuyerList(Pagination<BuyerVO> pagination) {
		pagination.setTotalRecord(buyerDAO.selectTotalRecord(pagination));
		return buyerDAO.selectBuyerList(pagination);
	}

	@Override
	public BuyerVO retrieveBuyer(String buyerId) throws PKNotFoundException {
		BuyerVO buyer = buyerDAO.selectBuyer(buyerId);
		if(buyer==null)
			throw new PKNotFoundException(buyerId);
		return buyer;
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyer) {
		retrieveBuyer(buyer.getBuyerId());
		AttatchFileVO contract = buyer.getAtchContract();
		if(contract!=null) {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("attatchList", Collections.singletonList(contract));
			atchDAO.insertAttatchList(paramMap);
			Integer atchId = (Integer) paramMap.get("atchId");
			buyer.setBuyerContract(atchId);
		}
		int rowcnt = buyerDAO.updateBuyer(buyer);
		
		if(rowcnt>0) {
			processContractFile(buyer);
		}
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

}











