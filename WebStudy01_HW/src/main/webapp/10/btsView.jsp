<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Optional"%>
<%@page import="com.ctc.wstx.shaded.msv_core.verifier.ErrorInfo.BadTagName"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<select id="btsSel">
	<%
		Map<String, String[]> btsMap = (Map)application.getAttribute("btsMap");
		String options = btsMap.entrySet().stream()
					.map(entry->{
						String code = entry.getKey();
						String name = Optional.ofNullable(entry.getValue())
										.filter(array->array.length>0)
// 										.map(array->array[0])
										.flatMap(array->Optional.of(array[0]))
										.orElse("");
						return String.format("<option value='%s'>%s</option>", code, name);
					}).collect(Collectors.joining("\n"));
		out.println(options);
	%>
	</select>
<script type="text/javascript">
	$("#btsSel").on("change", function(){
		let code = $(this).val();
		let url = `<%=request.getContextPath()%>/btsMem/\${code}`;
		location.href=url;
	});
</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>