package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.Pagination;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리(CRUD) Persistence Layer
 *
 */
public interface ProdDAO {
	/**
	 * 상품 등록, 등록시 prodId 생성 필요.
	 * @param prod
	 * @return > 0, 성공
	 */
	public int insertProd(ProdVO prod);
	/**
	 * 검색 조건에 맞는 레코드 수 조회
	 * @param pagination
	 * @return
	 */
	public int selectTotalRecord(Pagination<ProdVO> pagination);
	/**
	 * 상품 목록 조회, 추후 페이징과 검색 적용.
	 * @param pagination TODO
	 * @return
	 */
	public List<ProdVO> selectProdList(Pagination<ProdVO> pagination);
	/**
	 * 상품 상세 조회
	 * @param prodId
	 * @return 존재하지 않으면, null 반환
	 */
	public ProdVO selectProd(String prodId);
	/**
	 * 상품 수정
	 * @param prod
	 * @return > 0, 성공
	 */
	public int updateProd(ProdVO prod);
	public default int deleteProd(String prodId) {
		throw new RuntimeException("상품 삭제 불가능.");
	}
}













