package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.Pagination;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdServiceImpl implements ProdService {
	private final ProdDAO prodDAO;
	
	@Value("#{appInfo.prodImages}")
	private File saveFolder;
	
	@PostConstruct
	public void init() {
		log.info("로딩된 resource : {}", saveFolder);
	}
	
	private void processProdImage(ProdVO prod) {
		try {
			prod.saveTo(saveFolder);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ServiceResult createProd(ProdVO prod) {
		int rowcnt = prodDAO.insertProd(prod);
		if(rowcnt > 0)
			processProdImage(prod);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public List<ProdVO> retrieveProdList(Pagination<ProdVO> pagination) {
		pagination.setTotalRecord(prodDAO.selectTotalRecord(pagination));
		List<ProdVO> prodList = prodDAO.selectProdList(pagination);
		pagination.setDataList(prodList);
		return prodList;
	}

	@Override
	public ProdVO retrieveProd(String prodId) throws PKNotFoundException {
		ProdVO prod = prodDAO.selectProd(prodId);
		if(prod==null)
			throw new PKNotFoundException(prodId);			
		return prod;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) throws PKNotFoundException {
		int rowcnt = prodDAO.updateProd(prod); // commit 이후임.
		if(rowcnt==0) {
			throw new PKNotFoundException(prod.getProdId());
		}else {
			processProdImage(prod);
		}
		return ServiceResult.OK;
	}

}














