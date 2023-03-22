<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%!
	private final String[] clzCases = new String[]{"red", "green"};
%>
<style>
	<%
		StringBuffer cssSource = new StringBuffer();
		String cssPtrn = ".%1$s{background-color:%1$s;}";
		for(String cssCase : clzCases){
			cssSource.append(String.format(cssPtrn, cssCase));	
		}
		out.println(cssSource);
	%>
</style>
</head>
<body>

<%!
String ptrn2 = "<td>%d * %d = %d</td>";
private void printTd(int dan, int mul, Object target) throws IOException{
	if(target instanceof JspWriter){
		((JspWriter)target).println(
				String.format(ptrn2, dan, mul, (dan*mul))
			);
	}else if(target instanceof StringBuffer){
		((StringBuffer)target).append(
				String.format(ptrn2, dan, mul, (dan*mul))
		);
	}
}

private String getClzValue(int dan){
	return clzCases[dan%2];
}



%>

<%
// 맨 위에다가 작성

String cMinDan = request.getParameter("minDan");
String cMaxDan = request.getParameter("maxDan");

int minDan = 2;
int maxDan = 9;

// server side validation
if(cMinDan != null){
	
	if(cMinDan.matches("[2-9]")){
		minDan = Integer.parseInt(cMinDan);
	}else{
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);		
	}
}

if(cMaxDan != null){
	
	if(cMaxDan.matches("[2-9]")){
		maxDan = Integer.parseInt(cMaxDan);
	}else{
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);		
	}
}


%>


<form name="gForm">
	<input type="number" name="minDan" min="2" max="9" placeholder="최소단" value="<%=minDan%>"/>
	<select name="maxDan" onchange="this.form.submit(); return false;">
		<option value>최대단</option>
<!-- 		<option value="2">2단</option> -->
		
		<%
			String optPtrn = "<option %2$s value='%1$d'>%1$d단</option>";
// 			formatting : 특정 타입의 데이터를 일정한 형식의 문자열로 바꾸는 작업.
// 			parsing : 일정한 형식의 문자열을 특정 타입의 데이터로 바꾸는 작업.
			for(int idx=2; idx<=9; idx++){
				String selected = idx==maxDan ? "selected" : "";
				out.println(String.format(optPtrn, idx, selected));
			}
		
		%>
		
	</select>
	<input type="submit" value="서밋버튼" id="submitBtn"/>
	<input type="button" value="일반버튼" id="generalBtn"/>
	<input type="reset" value="리셋버튼" />
</form>
<table>
<%
	String trPtrn = "<tr class='%s'>";
	for(int dan=minDan; dan<=maxDan; dan++){
		String clz = getClzValue(dan);
		
		out.println(String.format(trPtrn, clz));
		for(int mul=1; mul<=9; mul++){
			printTd(dan, mul, out);
		}
		out.println("</tr>");
	}

%>

</table>

<script type="text/javascript">

	document.querySelector("#generalBtn").addEventListener("click", function(event) {
		console.log(event.target);
	});
	
	
	
	document.gForm.addEventListener("submit", function(event){
// 		event.preventDefault();
		console.log(event.target);
// 		return false;
	});

</script>


</body>
</html>