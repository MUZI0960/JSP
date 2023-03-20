<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 비동기 처리 기반의 사칙연산기 -->
<input type="radio" name="dataType" value="json" />JSON
<input type="radio" name="dataType" value="xml" />XML
<form action="./calculate.do">
	<input type="number" name="leftTop">
	<select name="operator">
		<option value="+" label="+">
		<option value="-" label="-">
		<option value="*" label="*">
		<option value="/" label="/">
	</select>
	<input type="number" name="rightTop" />
	<input type="submit" value="=" />
</form>
<div id="resultArea">
	ex) 2 * 2 = 4
	모듈화 / 패턴사용
</div>

<script>
$('[name="calForm"]'). on("submit", function(event) {
	event.preventDefault();
	
	let leftNum = $('[name=lefTop]').val();
	let rightNum = $('[name=righTop]').val();
	let operator = $('[name=operator]')
	
	
	let url = this.action;
	let method = this.method;
	
	$.ajax({
		url : url
		, type : method
		, success : function(res){
			$('#resultArea').html()
		}
	})
	
})

$()

</script>

</body>
</html>