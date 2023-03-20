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
	<h4>요청 컨텐츠 MIME : request : Content-Type header</h4>
	<input type="radio" name="contentType" checked value="application/json"/>JSON
	<input type="radio" name="contentType" value="application/x-www-form-urlencoded"/>Parameter
</div>
<div>
	<h4>응답 데이터 MIME : request : Accept header / response Content-Type header</h4>
	<input type="radio" name="dataType" checked value="json"/>JSON
	<input type="radio" name="dataType" value="xml"/>XML
	<input type="radio" name="dataType" value="html"/>HTML
</div>
<form id="calForm" action="<%=request.getContextPath() %>/calculator/case6" method="post">
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
let contentTypeTag = $('[name="contentType"]');
let generateSendData = function(contentType, form){
	 let data = {};
	 $(form).find(":input[name]").each(function(index, input) { // html을 가지고올때는 this/ jquery는 $(this)
//         data.param1="default"; , data["param1"] = "default";
        let propertyName = this.name;
        let propertyValue = $(this).val();
        data[propertyName] = propertyValue;
     });
	if(contentType.indexOf("json")>=0)
	 	data = JSON.stringify(data); // marshalling
    console.log(data);
	return data;
}

$("#calForm").on("submit", function(event) {
	event.preventDefault();

	let url = this.action;
	let method = this.method;
     
	let dataType =  dataTypeTag.filter(":checked").val();
	let contentType = contentTypeTag.filter(":checked").val();
	
	let data = generateSendData(contentType, this);
	
	$.ajax({
		url :url,
		method : method,
		data : data,
		contentType : contentType,
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