<%@page import="kr.or.ddit.calculator.Operator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.3.min.js"></script>
</head>
<body>
<div>
	<h4>응답 데이터 MIME</h4>
	<input type="radio" name="dataType" checked value="json"/>JSON
	<input type="radio" name="dataType" value="xml"/>XML
	<input type="radio" name="dataType" value="html"/>HTML
</div>
<form id="calForm" action="<%=request.getContextPath() %>/calculator/case4" method="post">
	<input type="number" name="leftOp">
	<select name="operator">
		<%
			String optPtrn = "<option value='%s'>%c</option>";
			for(Operator single : Operator.values()){
				out.println(
					String.format(optPtrn, single.name(), single.getSign())		
				);
			}
		
		%>
	</select>
	<input type="number" name="rightOp" />
	<input type="submit" value="=" />
</form>

<div id="resultArea"></div>

<script type="text/javascript">
let resultArea = $('#resultArea');
let dataTypeTag = $('[name="dataType"]');
$("#calForm").on("submit", function(event) {
	event.preventDefault();

	let url = this.action;
	let method = this.method;
	
	 let data = {};
     $(this).find(":input[name]").each(function(index, input) { // html을 가지고올때는 this/ jquery는 $(this)
//         data.param1="default"; , data["param1"] = "default";
        let propertyName = this.name;
        let propertyValue = $(this).val();
        data[propertyName] = propertyValue;
     });
	data = JSON.stringify(data); // marshalling
     console.log(data);
     
	let dataType =  dataTypeTag.filter(":checked").val();
	$.ajax({
		url :url,
		method : method,
		data : data,
		contentType : "application/json;charset=UTF-8",
		dataType : dataType
	}).done(function(resp, textStatus, jqXHR) {
		console.log(jqXHR);
		if(jqXHR.responseJSON){
			resultArea.text(resp['expression']);
		}else if(jqXHR.responseXML){
			resultArea.text($(resp).find("expression").text());
		}else{
			resultArea.html(resp);
		}
		
	}).fail(function(jqXHR, status, error) {
		console.log(`상태코드 : \${status}, 에러메세지 : \${error}`);
	});
	return false;
});

</script>

</body>
</html>