package kr.or.ddit.calculator;

// @FunctionalInterface => 람다식 사용시에만 
@FunctionalInterface
public interface BinaryOperator {
	public int realOperate(int leftOp, int rightOp);
}
