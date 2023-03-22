package kr.or.ddit.calculator;

// 1.통틀어서 일정 갯수만 사용 2.상수
public enum Operator {
	PLUS('+', (l,r)->l+r), 
	MINUS('-',(l,r)->l-r), 
	MULTIPLY('*', (l,r)->l*r), 
	DIVIDE('/', (l,r)->l/r),
	MODULAR('%', (l,r)->l%r);
	
	private Operator(char sign, BinaryOperator realOperator){
		this.sign = sign;
		this.realOperator = realOperator;
	}
	private char sign;
	private BinaryOperator realOperator; 
	public char getSign() {
		return sign;
	}
	
	public int oprate(int leftOp, int rightOp) {
		return realOperator.realOperate(leftOp, rightOp);
	}
	private static final String expPtrn = "%d %c %d = %d";
	public String getExpression(int leftOp, int rightOp) {
		return String.format(expPtrn, leftOp, getSign(), rightOp, oprate(leftOp, rightOp));
	}
	
}
