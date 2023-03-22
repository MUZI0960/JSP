<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<table>
	<thead>
		<tr>
			<th>테이블명</th>
			<th>테이블스페이스명</th>
			<th>레코드수</th>
		</tr>
	</thead>
	<tbody id="listBody">
		
	</tbody>
</table>
<script type="text/javascript">
	let listBody = $("#listBody");
	$.getJSON("<%=request.getContextPath()%>/schema/tableSchema.do")
		.done(function(resp){
			console.log(resp)
			let trTags = [];
			
			$(resp.list).each(function(idx, TableSchemaVO){
				let tr = $("<tr>").append(
							$("<td>").html(this.tableName)
							, $("<td>").html(this.tablespaceName)
							, $("<td>").html(this.numRows)
				).data("source", this);		
				trTags.push(tr);
			});
			listBody.append(trTags);
		});
	
	let modalBody = $("#modalBody");
	let data = JSON.stringify( $(this).serializeObject() );
	$.getJSON("<%=request.getContextPath()%>/schema/columnSchema", data)
		.done(function(){
			let trTags2 =[];
			
			$(resp.list).each(function(idx, ColumnSchemaVO){
				let tr = $("<tr>").append(
							$("<td>").html(this.tableName)
							, $("<td>").html(this.columnName)
							, $("<td>").html(this.dataType)
				).data("source", this);		
				trTags2.push(tr);
			});
			modalBody.append(trTags2);
		});
	
</script>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>