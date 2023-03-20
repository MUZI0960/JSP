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
    
<%
   String yearParam = request.getParameter("year");
   String monthParam = request.getParameter("month");
   String localeParam = request.getParameter("locale");
   String zoneIdParam = request.getParameter("timeZone");	

   ZoneId timeZone = ZoneId.systemDefault();
// ZoneId timeZone = ZoneId.of("America/New_York");
	
   if(zoneIdParam != null && !zoneIdParam.isEmpty()){
	   timeZone = ZoneId.of(zoneIdParam);
   }

   Locale locale = request.getLocale();
   
   
    
   if(localeParam != null && !localeParam.isEmpty()){
      locale = Locale.forLanguageTag(localeParam);
   }
   
   YearMonth yearMonth = YearMonth.now(timeZone);
   if(yearParam != null && yearParam.matches("\\d{4}") 
         && monthParam!=null && monthParam.matches("\\d{1,2}")){
      int year = Integer.parseInt(yearParam);
      int monthValue = Integer.parseInt(monthParam);
      try{
         Month month = Month.of(monthValue);
         yearMonth = YearMonth.of(year, month);
      }catch(DateTimeException e){
         response.sendError(400);
         return;
      }
   }
   
   YearMonth before = yearMonth.minusMonths(1);
   YearMonth next = yearMonth.plusMonths(1);
%>    

<table>
   <thead>
      <tr>
         <% 
            String thPtrn = "<th>%s</th>";
            for( DayOfWeek single : DayOfWeek.values()){
               out.println(
                  String.format(thPtrn, single.getDisplayName(TextStyle.FULL, locale))      
               );
            }
         %>
      </tr>
   </thead>

   <tbody>
      <%
         String tdPtrn = "<td id='%s' class='%s'>%te</td>";
         LocalDate firstDay = yearMonth.atDay(1);
         
         int offset = firstDay.getDayOfWeek().getValue() - 1;
         LocalDate today = LocalDate.now(timeZone);
         
         LocalDate date = firstDay.minusDays(offset);

         for(int row=1; row<=6; row++){
            out.println("<tr>");
            for(DayOfWeek single : DayOfWeek.values()){
               String id = date.isEqual(today) ? "today" : "sample_" + date;   
               YearMonth thisTurn = YearMonth.from(date);
               String clz = thisTurn.isBefore(yearMonth) || thisTurn.isAfter(yearMonth) ? "disabled" 
                     : date.getDayOfWeek().name();
               out.println(
                     String.format(tdPtrn,id,clz, date)
               );
               date = date.plusDays(1);
            }
            out.println("</tr>");
         }
      %>
   </tbody>
</table>


