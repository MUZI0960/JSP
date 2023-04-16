<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.props.vo.PropertyVO"%>
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
<form id="jdbcForm">
	<input type="text" name="propertyName"  />
	<input type="submit" value="검색" />
</form>
<table>
	<thead>
		<tr>
			<th>PROPERTY_NAME</th>
			<th>PROPERTY_VALUE</th>
			<th>DESCRIPTION</th>
		</tr>
	</thead>
	<tbody id="listBody">
		
	</tbody>
</table>
<script type="text/javascript">
	let listBody = $("#listBody");
	$("#jdbcForm").on("submit", function(event){
		event.preventDefault();
		let data = $(this).serializeObject();
		console.log(data);
		$.getJSON(this.action, data)
			.done(function(resp){
				let trTags = [];
				$(resp.list).each(function(idx, propertyVO){
					let tr = $("<tr>").append(
								$("<td>").html(this.propertyName)		
								, $("<td>").html(this.propertyValue)		
								, $("<td>").html(this.description)		
							).data("source", this);
					trTags.push(tr);
				});
				listBody.empty();
				listBody.append(trTags);
			});
		
		return false;
	}).trigger("submit");
</script>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>










