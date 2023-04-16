package kr.or.ddit.commons.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.BuyerVO;

/**
 * 상품의 분류를 선택하는 UI 와 거래처를 선택하는 UI 를 동적으로 생성하기 위한 DAO
 *
 */
@Mapper
public interface OthersDAO {
	/**
	 * 전체 상품 분류 조회 (lprodGu, lprodNm)
	 * @return
	 */
	public List<Map<String, Object>> selectLprodList();
	/**
	 * 전체 거래처 목록(코드,이름,분류) 조회
	 * @return
	 */
	public List<BuyerVO> selectBuyerList();
	
}
