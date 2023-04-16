package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.Pagination;

/**
 * 거래처 관리 Business Logic Layer
 *
 */
public interface BuyerService {
	/**
	 * 거래처 등록
	 * @param buyer
	 * @return OK, FAIL
	 */
	public ServiceResult createBuyer(BuyerVO buyer);
	/**
	 * 거래처 목록 조회.
	 * @param pagination TODO
	 * @return
	 */
	public List<BuyerVO> retrieveBuyerList(Pagination<BuyerVO> pagination);

	/**
	 * 거래처 상세 조회.
	 * @param buyerId
	 * @return
	 * @throws PKNotFoundException
	 */
	public BuyerVO retrieveBuyer(String buyerId) throws PKNotFoundException;
	/**
	 * 거래처 수정
	 * @param buyer
	 * @return OK, FAIL
	 */
	public ServiceResult modifyBuyer(BuyerVO buyer);
}
