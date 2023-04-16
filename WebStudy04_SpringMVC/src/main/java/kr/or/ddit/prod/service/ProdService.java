package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.vo.Pagination;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리 Business Logic Layer
 *
 */
public interface ProdService {
	/**
	 * 상품 등록
	 * @param prod
	 * @return OK, FAIL
	 */
	public ServiceResult createProd(ProdVO prod);
	/**
	 * 상품 목록 조회
	 * @param pagination TODO
	 * @return
	 */
	public List<ProdVO> retrieveProdList(Pagination<ProdVO> pagination);
	/**
	 * 상품 상세 조회
	 * @param prodId
	 * @return
	 * @throws PKNotFoundException
	 */
	public ProdVO retrieveProd(String prodId)throws PKNotFoundException;
	/**
	 * 상품 수정
	 * @param prod
	 * @return  OK, FAIL
	 * @throws PKNotFoundException 
	 */
	public ServiceResult modifyProd(ProdVO prod) throws PKNotFoundException;
}
















