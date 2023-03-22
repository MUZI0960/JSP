package kr.or.ddit.member.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {

	MemberDAO memDAO = new MemberDAOImpl();
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> retrieveMemerList() {
		List<MemberVO> list = memDAO.selectMemberList();
		if(list.isEmpty())
			throw new RuntimeException("회원 정보 없음");
		return list;
	}

	@Override
	public MemberVO retrieveMemer(String memId) throws UserNotFoundException {
		MemberVO vo = memDAO.selectMember(memId);
		if(vo==null) {
			throw new UserNotFoundException(memId);
		}
		return vo;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
