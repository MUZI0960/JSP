package kr.or.ddit.designpattern.adapterpattern;

//Wrapper 기본생성자를 가질 수 없다 / 단독으로 사용 불가 / adaptee 필요
public class Adapter implements Target {
	
	private Adaptee adaptee;
	
	public Adapter(Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}

	@Override
	public void request() {
		adaptee.specificRequest();
	}

}
