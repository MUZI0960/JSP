<%@page import="java.util.Locale"%>
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.ZoneId"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   ZoneId timeZone = ZoneId.systemDefault();
   Locale locale = request.getLocale();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.3.min.js"></script>
</head>
<body>
<h4>현재 서버의 기본 time zone 시각 : <span id="current"></span></h4>
<h4>선택한 time zone 시각 : <span id="specific"></span></h4>
<div>
	<h4>응답 데이터 MIME</h4>
	<input type="radio" name="dataType" checked value="json"/>JSON
	<input type="radio" name="dataType" value="xml"/>XML
</div>
<div>
	<h4>요청 컨텐츠 MIME</h4>
	<input type="radio" name="contentType" checked value="application/json"/>JSON
	<input type="radio" name="contentType" value="application/x-www-form-urlencoded"/>Parameter
</div>
<%-- <form name="timeForm" action="<%=request.getContextPath() %>/time/globalTime2" data-data-type="json"> --%>
<%-- <form name="timeForm" action="<%=request.getContextPath() %>/time/globalTime"> --%>
<form name="timeForm" action="<%=request.getContextPath() %>/time/globalTime_case2">
   <input type="text" name="param1" value="default" />
   <select name="timeZone">
      <%   
         String optPtrn = "<option %s value='%s' label='%s' />";
         for( String single : ZoneId.getAvailableZoneIds()){
            ZoneId thisTrunId = ZoneId.of(single);
            String selected = thisTrunId.equals(timeZone) ? "selected" : "";
            out.println(
            		String.format(optPtrn, selected, single, thisTrunId.getDisplayName(TextStyle.FULL, locale))
            );
         }
      %>
   </select>
</form>
<script type="text/javascript">
   let current = $('#current');
   let specific = $('#specific');
   let timeForm = $("[name=timeForm]");
   
   $('[name="dataType"]').on("change", function(event){
		console.log( $(this).val() );
		let dataType = $(this).val();
		timeForm.data("dataType", dataType);
	}).filter(":checked").trigger("change");
   
   $('[name="contentType"]').on("change", function(){
	   let contentType = $(this).val();
	   timeForm.data("contentType", contentType);
	   if(contentType.indexOf("json")){
		   timeForm.attr("method", "post");
	   }
   }).filter(":checked").trigger("change");
   
   
   timeForm.on("submit", function(event){
      // 1.submit 기본 액션을 중단시킨다.
      event.preventDefault();
      // 2. 어디로 어떤메서드로 뭘가지고 갈지 -> this가 가지고 있네? ...메서드 기본 get? 요청의 조건을 꺼낸다. 
      let url = this.action;
      let method = this.method;
      // adapter design pattern 
//       let data ={
//             param1: "default"
//             , timeZone: "Asia/Seoul"
//       }-> name1 = value1$name2 = value2
      let data = {};
      $(this).find(":input[name]").each(function(index, input) { // html을 가지고올때는 this/ jquery는 $(this)
//          data.param1="default"; , data["param1"] = "default";
         let propertyName = this.name;
         let propertyValue = $(this).val();
         data[propertyName] = propertyValue;
      });
      // 3. 비동기로 요청을 발생시킨다.
      console.log(data);
      let settings = {};
      settings.url = url;
      settings['method']=method;
      settings['data']=data;
      // 요청 - url, method, data
      
//       let dataTypeChk = $("[name=dataType]").val();
//       console.log(dataTypeChk);
      
      let dataType = $(this).data("dataType");
      settings.dataType = dataType?dataType:"json";
//       settings.dataType="xml"; // Accept request header / Content-Type response header 결정 속성.
      // json : application/json, xml : application/xml, html : text/html, text : text/plain, script : application[text]/javascript
      
      let contentType = $(this).data("contentType");
      settings.contentType = contentType;
		
      if(contentType.indexOf("json")>=0){
    	  settings.data = JSON.stringify(data);
      }
      
      $.ajax(settings)
      	// resp -> 자바스크립트 객체
      	.done(function(resp){
      		console.log(resp);
      		let dataType = timeForm.data("dataType");
      		if(dataType=="json"){
	      		current.html(resp.defaultCurrent);
	      		specific.html(resp.current);
      		}else{
	      		current.html($(resp).find("defaultCurrent").text());
	      		specific.html($(resp).find("current").text());
      		}
      	}).fail(function(jqXHR, status){
      		console.log(jqXHR);
      		console.log(status);
      		console.log(arguments);
      	});
      // 응답 - success, fail, dataType
      return false;
   }).on("change", ":input[name]", function(event){
      $(this.form).submit();
   })
   
   
   
//    $('[name="contentType"]').on("change", function(event){
// 		console.log( $(this).val() );
// 		let contentType = $(this).val();
// 		timeForm.data("contentType", contentType);
// 	});
   
   
</script>
</body>
</html>







