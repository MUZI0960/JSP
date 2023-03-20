<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<style type="text/css">
table{
width: 1000px;
height: 400px;

text-align: center;
border-collapse: collapse;

margin: auto;
}
tr#top_tr{
	font-weight: bold;
}
tr{
	height: 70px;
}
a{
	text-decoration: none;
	color: blue; 
}
#changeMonth{
	text-align: center;
}
#changeDate{
	text-align: center;
	margin-bottom: 10px;
}
</style>


</head>
<%
Calendar cal = Calendar.getInstance();

int nowYear = cal.get(Calendar.YEAR);
int nowMonth = cal.get(Calendar.MONTH)+1;

String fyear = request.getParameter("year");
String fmonth = request.getParameter("month");

int year = nowYear;
int month = nowMonth;

if(fyear != null){
	year = Integer.parseInt(fyear);
}
if(fmonth != null){
	month = Integer.parseInt(fmonth);
}

cal.set(year, month-1,1);

year = cal.get(Calendar.YEAR);
month = cal.get(Calendar.MONTH)+1;

int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
int week = cal.get(Calendar.DAY_OF_WEEK);

int today = cal.get(Calendar.DATE);

%>
<body>

<div id="changeMonth">
<a href="calendar.jsp?year=<%=year %>&month=<%=month-1 %>">◀◀◀</a>
<span><%=year %>년 <%=month %>월</span>
<a href="calendar.jsp?year=<%=year %>&month=<%=month+1 %>">▶▶▶</a>
</div>
<br>

<div id="changeDate">
<input id="input_year" type="text" onkeyup="yearChange()" placeholder="year">

<select id="month_Sel" onchange="changeMonth()">
	<option name="month_Op">Month</option>
	<option name="month_Op" value="1">January</option>
	<option name="month_Op" value="2">February</option>
	<option name="month_Op" value="3">March</option>
	<option name="month_Op" value="4">April</option>
	<option name="month_Op" value="5">May</option>
	<option name="month_Op" value="6">June</option>
	<option name="month_Op" value="7">July</option>
	<option name="month_Op" value="8">August</option>
	<option name="month_Op" value="9">September</option>
	<option name="month_Op" value="10">October</option>
	<option name="month_Op" value="11">November</option>
	<option name="month_Op" value="12">December</option>
</select>
</div>
<table border="1">
	<tr>
		<td>일</td>
		<td>월</td>
		<td>화</td>
		<td>수</td>
		<td>목</td>
		<td>금</td>
		<td>토</td>
	</tr>
	<tr>
	<%
		for(int i = 1; i< week; i++){
			%>
			<td height="20">&nbsp;</td>
			<%
		}
	%>
	<%
	for(int j = 1; j <= endDay; j++){
		week++;
		if(week % 7 == 2){
			%>
		</tr>
			<tr>
			<%} %>
				<%if(week % 7 == 2){%>
					<td style ="color: red;"><%=j %></td>
					<%}else if(week%7 == 1){%>
					<td style ="color: blue;"><%=j %></td>
					<%}else{%>
					<td style ="color: black;"><%=j %></td>
					<%}
					
				}%>
		</tr>
			
</table>

<script type="text/javascript">
function changeMonth() {
	var month = document.getElementById("month_Sel"); 
	var selMonth = (month.options[month.selectedIndex].value);
	
	
	document.location.href="calendar.jsp?year=<%=year %>&month="+selMonth;
}
function yearChange(e) {
	var year = document.getElementById("input_year").value;
	
	if(window.event.keyCode == 13){
// 		alert(year)
	document.location.href="calendar.jsp?year=" + year +"&month=<%=month %>";
	}
}

</script>

</body>
</html>








