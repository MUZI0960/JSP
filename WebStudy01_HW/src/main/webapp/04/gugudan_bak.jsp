<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

%>

<%! 
private StringBuffer gugudanTable() throws IOException{
		StringBuffer contents = new StringBuffer();
			contents.append("<table>");	
			for(int dan = 2; dan<9; dan++){
			contents.append("<tr>");	
			
				for(int mul = 1; mul<=9; mul++){
// 					contents.append(String.format(ptrn2, dan, mul, (dan*mul)));
					printTd(dan, mul, contents);

				}
			
			contents.append("</tr>");
			}
			contents.append("</table>");	
	
	return contents;
}

%>

	<%
		out.println(gugudanTable());
	%>

<table>
<%
	
	for(int dan=2; dan<=9; dan++){
		out.println("<tr>");
		for(int mul=1; mul<=9; mul++){
			printTd(dan, mul, out);
		}
		out.println("</tr>");
	}

%>

</table>


<table>

<%
	String ptrn = "<td>%d * %d = %d</td>";
	for(int dan=2; dan<=9; dan++){
		out.println("<tr>");
		for(int mul=1; mul<=9; mul++){
			out.println(
					String.format(ptrn, dan, mul, (dan*mul))
				);
		}
		out.println("</tr>");
	}


%>

</table>

</body>
</html>