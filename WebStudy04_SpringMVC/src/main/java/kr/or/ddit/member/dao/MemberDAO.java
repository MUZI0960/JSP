package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.Pagination;

/**
 * 회원관리(CRUD)와 인증시스템을 위한 Persistence Layer
 */
@Mapper
public interface MemberDAO {
	/**
	 * PK 로 회원 한명의 정보 조회
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
	 * 페이징 처리를 위한 전체 레코드수 조회
	 * @param pagination TODO
	 * @return
	 */
	public int selectTotalRecord(Pagination<MemberVO> pagination);
	/**
	 * 회원 목록 조회, 추후 검색과 페이징 추가
	 * @param pagination TODO
	 * @return
	 */
	public List<MemberVO> selectMemberList(Pagination<MemberVO> pagination);
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













