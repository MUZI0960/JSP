package kr.or.ddit.calculator;

public enum Operator {
	PLUS('+', (l,r)->l+r), 
	MINUS('-', (l,r)->l-r), 
	MULTIPLY('*',(l,r)->l*r), 
	DIVIDE('/', new BinaryOperator() {
		@Override
		public int realOperate(int leftOp, int rightOp) {
			return leftOp / rightOp;
		}
		
	}),
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
	
	public int operate(int leftOp, int rightOp) {
		return realOperator.realOperate(leftOp, rightOp);
	}
	
	private static final String expPtrn = "%d %c %d = %d";
	public String getExpression(int leftOp, int rightOp) {
		return String.format(expPtrn, leftOp, getSign(), rightOp, operate(leftOp, rightOp));
	}
}














