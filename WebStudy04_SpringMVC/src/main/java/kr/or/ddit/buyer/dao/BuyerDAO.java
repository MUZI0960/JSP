package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.Pagination;

/**
 * 거래처 관리(CRUD) Persistence Layer
 *
 */
public interface BuyerDAO {
	/**
	 * 거래처 등록
	 * @param buyer
	 * @return > 0 , 성공
	 */
	public int insertBuyer(BuyerVO buyer);
	
	/**
	 * 검색 조건에 맞는 레코드수 조회
	 * @param pagination
	 * @return
	 */
	public int selectTotalRecord(Pagination<BuyerVO> pagination);
	/**
	 * 거래처 목록 조회, 해당 거래처와의 거래 품목수를 함께 조회할 것.
	 * @param pagination TODO
	 * @return
	 */
	public List<BuyerVO> selectBuyerList(Pagination<BuyerVO> pagination);
	/**
	 * 거래처 상세 조회
	 * @param buyerId
	 * @return 존재하지 않으면, null
	 */
	public BuyerVO selectBuyer(String buyerId);
	/**
	 * 거래처 수정
	 * @param buyer
	 * @return > 0, 성공
	 */
	public int updateBuyer(BuyerVO buyer);
	/**
	 * 거래처 삭제, 필요시 재정의 가능.
	 * @param buyerId
	 * @return 
	 */
	public default int deleteBuyer(String buyerId) {
		throw new RuntimeException("거래처 삭제 불가");
	}
}
