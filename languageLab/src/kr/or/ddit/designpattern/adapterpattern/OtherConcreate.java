package kr.or.ddit.designpattern.adapterpattern;

public class OtherConcreate implements Target {

	@Override
	public void request() {
		System.out.printf("%s에서 작업 했음.\n", this.getClass().getSimpleName());
	}

}
