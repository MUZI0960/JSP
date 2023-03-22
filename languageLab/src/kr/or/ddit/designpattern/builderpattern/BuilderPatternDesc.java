package kr.or.ddit.designpattern.builderpattern;

/**
 * Design Pattern
 * 1. 객체 생성 패턴 : singleton pattern, Factory Method pattern, Builder pattern
 * 		** 객체 생성 및 바인드 방법
 * 		1) 점층적 생성자 패턴
 * 			new 생성자(프로퍼티값...)
 * 		2) 자바빈 패턴 : 기본생성자, setter 호출 -> immutable 객체 생성 불가
 * 2. 객체 구조 패턴 : adapter pattern, facade pattern 
 * 3. 객체 행동 패턴 : template method pattern
 *
 */
public class BuilderPatternDesc {
	public static void main(String[] args) {
//		TableSchemaVO vo1 = new TableSchemaVO("table명");
//		TableSchemaVO vo2 = new TableSchemaVO("table명", "스페이스명");
//		TableSchemaVO vo3 = new TableSchemaVO("table명", "스페이스명", 2345);
//		
//		TableSchemaVO bean = new TableSchemaVO();
//		bean.setTableName("테이블명");
//		bean.setNumRows(2123);
		
		TableSchemaVO vo = TableSchemaVO.builder()
										.tableName("테이블명")
										.numRows(342)
										.tablespaceName("스페이스명")
										.build();
		System.out.println(vo);
	}
}
