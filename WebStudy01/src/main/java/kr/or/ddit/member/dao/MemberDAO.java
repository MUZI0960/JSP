package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

/**
 * 회원관리(CRUD)와 인증시스템을 위한 Persistence Layer
 *
 */
/**
 * @author PC-04
 *
 */
public interface MemberDAO {
	/**
	 * PK로 회원 한명의 정보 조회
	 * @param memId
	 * @return 존재하지 않는 경우, null 반환.
	 */
	public MemberVO selectMemberForAuth(String memId);
	
	/**
	 * 신규 등록
	 * @param member
	 * @return >= 0 , 성공
	 */
	public int insertMember(MemberVO member); 
	
	/**
	 * 회원목록 조회, 추후 검색과 페이징 추가
	 * @return 
	 */
	public List<MemberVO> selectMemberList();
	
	
	/**
	 * 회원 상세 조회
	 * @param memId
	 * @return 존재하지 않는 경우, null 반환
	 */
	public MemberVO selectMember(String memId);
	
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return >= 0, 성공
	 */
	public int updateMember(MemberVO member);
	
	/**
	 * 회원 정보 삭제
	 * @param memId
	 * @return >= 0, 성공
	 */
	public int deleteMember(String memId);
	
	
}








