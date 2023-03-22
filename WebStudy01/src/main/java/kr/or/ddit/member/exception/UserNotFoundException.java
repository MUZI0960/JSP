package kr.or.ddit.member.exception;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String memId) {
		super(String.format("%s 아이디 회원 없음.", memId));
	}
	
	
	
}
