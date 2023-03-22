package kr.or.ddit.designpattern.adapterpattern;

public class Adaptee {
	public void specificRequest() {
		System.out.printf("%s가 작업을 했는데 기가막히게 해서 겁나 탐나게 일했음..\n", this.getClass().getSimpleName());
	}
}
