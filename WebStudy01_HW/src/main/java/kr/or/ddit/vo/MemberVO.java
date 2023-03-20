package kr.or.ddit.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * JavaBean 규약
 * ValueObject, DataTransferObject, Model, Bean
 * ex) MemberVO, MemberDTO, MemberModel, MemberBean
 *
 *	1. 값을 저장할 속성(property) 정의
 *	2. 캡슐화
 *	3. 캡슐화된 데이터에 접근할 인터페이스 제공 (getter/setter)
 *		get[set] 프로퍼티명에서 첫문자만 대문자, camel case 적용
 *	4. 상태 비교 메소드 제공
 *	5. 상태 확인 메소드 제공
 *	6. 직렬화 가능
 *
 *	회원관리와 인증시스템을 위한 Domain Layer
 *	
 */
public class MemberVO implements Serializable{
	private String memId;
	@JsonIgnore
	private transient String memPass;
	private String memName;
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memId == null) ? 0 : memId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		if (memId == null) {
			if (other.memId != null)
				return false;
		} else if (!memId.equals(other.memId))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memName=" + memName + "]";
	}
	
}
