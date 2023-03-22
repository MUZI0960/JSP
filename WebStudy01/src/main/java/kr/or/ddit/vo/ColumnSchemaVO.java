package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString(exclude = {"dataType"})
@EqualsAndHashCode(of = {"tableName", "columnName"})
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ColumnSchemaVO implements Serializable{
	private String tableName;
	private String columnName;
	private String dataType;
	private Integer dataLength;
	private String nullable;
}
