package kr.or.ddit.bts;

public class BtsMemberNotFoundException extends RuntimeException{

	public BtsMemberNotFoundException(String code) {
		super(String.format("%s 코드에 해당하는 BTS멤버는 없음.", code));
	}
	
}
