package kr.or.ddit.calculator;

public class CalculateVO {
	private int leftOp;
	private int rightOp;
	private Operator operator;
	
	public int getLeftOp() {
		return leftOp;
	}
	public void setLeftOp(int leftOp) {
		this.leftOp = leftOp;
	}
	public int getRightOp() {
		return rightOp;
	}
	public void setRightOp(int rightOp) {
		this.rightOp = rightOp;
	}
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		if(operator==null)throw new NullPointerException("연산자는 null 일 수 없음.");
		this.operator = operator;
	}
	
	public int getResult() {
		return operator.oprate(leftOp, rightOp);
	}
	
	public String getExpression() {
		return operator.getExpression(leftOp, rightOp);
	}
	
	
}








