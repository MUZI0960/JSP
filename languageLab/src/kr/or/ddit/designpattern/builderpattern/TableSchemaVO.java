package kr.or.ddit.designpattern.builderpattern;

import java.io.Serializable;

// 점층적 생성자 패턴
public class TableSchemaVO implements Serializable{
	
	// private로 아무나 만들지 못하게
	private TableSchemaVO(String tableName, String tablespaceName, Integer numRows) {
		super();
		this.tableName = tableName;
		this.tablespaceName = tablespaceName;
		this.numRows = numRows;
	}

	private String tableName;
	private String tablespaceName;
	private Integer numRows;

	public String getTableName() {
		return tableName;
	}


	public String getTablespaceName() {
		return tablespaceName;
	}


	public Integer getNumRows() {
		return numRows;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numRows == null) ? 0 : numRows.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableSchemaVO other = (TableSchemaVO) obj;
		if (numRows == null) {
			if (other.numRows != null)
				return false;
		} else if (!numRows.equals(other.numRows))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TableSchemaVO [tableName=" + tableName + ", tablespaceName=" + tablespaceName + ", numRows=" + numRows
				+ "]";
	}
	
	public static TableSchemaVOBuilder builder() {
		return new TableSchemaVOBuilder();
	}
	
	// 동일한 프로퍼티 가져야함
	public static class TableSchemaVOBuilder{
		private String tableName;
		private String tablespaceName;
		private Integer numRows;
		
		// setter 역할
		public TableSchemaVOBuilder tableName(String tableName) {
			this.tableName = tableName;
			return this;
		}
		
		public TableSchemaVOBuilder tablespaceName(String tablespaceName){
			this.tablespaceName = tablespaceName;
			return this;
		}
		
		public TableSchemaVOBuilder numRows(Integer numRows){
			this.numRows = numRows;
			return this;
		}
		
		public TableSchemaVO build() {
			return new TableSchemaVO(tableName, tablespaceName, numRows);
		}
	}	
	
}











