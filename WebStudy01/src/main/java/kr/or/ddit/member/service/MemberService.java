package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;

/**
 * 회원 관리(CRUD)를 위한 Business Logic Layer
 *
 */
public interface MemberService {
	/**
	 * 신규 회원 가입
	 * @param member
	 * @return OK, FAIL, PKDUPLICATED(아이디 중복)
	 */
	public ServiceResult createMember(MemberVO member);
	
	/**
	 * 회원 목록 조회(관리자용)
	 * @return
	 */
	public List<MemberVO> retrieveMemerList();
	
	
	/**
	 * 회원 상세 조회 (ex)마이페이지)
	 * @param memId
	 * @return
	 * @throws UserNotFoundException 존재하지 않는 회원인 경우.
	 */
	public MemberVO retrieveMemer(String memId) throws UserNotFoundException;
	
	/**
	 * 인증 이후 회원 정보 수정
	 * @param member
	 * @return INVALIDPASSWORD, OK, FAIL
	 * @throws UserNotFoundException
	 */
	public ServiceResult modifyMember(MemberVO member) throws UserNotFoundException;
	
	
	/**
	 * 인증 이후 회원 탈퇴
	 * @param member
	 * @return INVALIDPASSWORD, OK, FAIL
	 * @throws UserNotFoundException
	 */
	public ServiceResult removeMember(MemberVO member) throws UserNotFoundException;
}







