<%@page import="org.apache.tomcat.jni.Local"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.time.DateTimeException"%>
<%@page import="java.time.Month"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.YearMonth"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.DayOfWeek"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/05/calendar.css">

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.3.min.js"></script>

</head>


<body>
<!-- <h4> -->
<%-- <a class="controlA" data-year="<%=before.getYear() %>" data-month="<%=before.getMonthValue()%>">◀◀◀</a> --%>
<%-- <%=String.format(locale, "%1$tY, %1$tB", yearMonth) %> --%>
<%-- <a class="controlA" data-year="<%=next.getYear() %>" data-month="<%=next.getMonthValue()%>">▶▶▶</a> --%>
<!-- </h4> -->
<%-- <h4>current time: <%=LocalDateTime.now(timeZone) %></h4> --%>
<%
	ZoneId timeZone = ZoneId.systemDefault();	
	Locale locale = request.getLocale();
	YearMonth yearMonth = YearMonth.now(timeZone);	
%>
<form name="calForm" action="calendar.jsp">
   <input type="number" name="year" placeholder="년도" value="<%=yearMonth.getYear()%>"/>
   <select name="month">
      <%
         String optPtrn = "<option %s value='%s' label='%s' />";
         for(Month single : Month.values()){
            String selected = single.equals(yearMonth.getMonth()) ?  "selected" : "";
        	 out.println(
               String.format(optPtrn, selected,single.getValue(), single.getDisplayName(TextStyle.SHORT, locale))      
            );
         }
      %>
   </select>
   
   <select name="locale">
      <%
         for(Locale single : Locale.getAvailableLocales()){
            String dN = single.getDisplayName(single);
            String selected = single.equals(locale) ?  "selected" : "";
            if(dN.isEmpty())continue;
            
            out.println(
               String.format(optPtrn, selected, single.toLanguageTag(), dN)      
            );
         }
      %>
   </select>
   <select name="timeZone">
   	<option value='' label="text"/>	
   	<%
   		for(String single : ZoneId.getAvailableZoneIds()){
   			ZoneId thisTurnId = ZoneId.of(single);
   			String selected = thisTurnId.equals(timeZone) ?  "selected" : "";
   			out.println(
   				String.format(optPtrn, selected, single, thisTurnId.getDisplayName(TextStyle.FULL, locale))		
   			);
   		}
   	%>
   </select>
   
</form>



<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/packages/05/calendar.js"></script>
</body>
</html>


