package kr.or.ddit.vo;

import java.io.Serializable;

public class TableSchemaVO implements Serializable{
	private String tableName;
	private String tablespaceName;
	private Integer numRows;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTablespaceName() {
		return tablespaceName;
	}
	public void setTablespaceName(String tablespaceName) {
		this.tablespaceName = tablespaceName;
	}
	public Integer getNumRows() {
		return numRows;
	}
	public void setNumRows(Integer numRows) {
		this.numRows = numRows;
	}
	
	@Override
	public String toString() {
		return "TableSchemaVO [tableName=" + tableName + ", tablespaceName=" + tablespaceName + ", numRows=" + numRows
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
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
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		return true;
	}
	
	
	
}
