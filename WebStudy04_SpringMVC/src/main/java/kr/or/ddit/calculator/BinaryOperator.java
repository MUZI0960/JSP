package kr.or.ddit.calculator;

@FunctionalInterface
public interface BinaryOperator {
	public int realOperate(int leftOp, int rightOp);
}
