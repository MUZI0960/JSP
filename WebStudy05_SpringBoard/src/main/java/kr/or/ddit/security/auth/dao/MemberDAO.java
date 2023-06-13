package kr.or.ddit.security.auth.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

@Mapper
public interface MemberDAO extends UserDetailsService{
	
	@Override
	default UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO member = selectMember(username);
		return new MemberVOWrapper(member);
	}
	
	public MemberVO selectMember(String memId);
}
