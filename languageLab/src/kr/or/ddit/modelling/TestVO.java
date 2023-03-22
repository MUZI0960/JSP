package kr.or.ddit.modelling;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

// implements Serializable 해야 Serialization 가능
public class TestVO implements Serializable{
	private String prop1;
	private Integer prop2;
	
	// transient : 직렬화 대상에서 제외시킴
	// @JsonIgnore : 마샬링에서 제외시킴
	@JsonIgnore
	private transient String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getProp1() {
		return prop1;
	}
	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}
	public Integer getProp2() {
		return prop2;
	}
	public void setProp2(Integer prop2) {
		this.prop2 = prop2;
	}
	
	@Override
	public String toString() {
		return "TestVO [prop1=" + prop1 + ", prop2=" + prop2 + ", password=" + password + "]";
	}
	
	
	
}
