<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<button id="loadBtn">Data Load!</button>
<table>
   <thead>
      <tr>
         <th>프로퍼티명</th>
         <th>프로퍼티값</th>
      </tr>
   </thead>
   <tbody id="listBody">
   
   </tbody>
</table>
<form id="insertForm" method="post" action="<%=request.getContextPath()%>/props">
   <input type="text" name="propertyName" placeholder="property name"/>
   <input type="text" name="propertyValue" placeholder="property value"/>
   <input type="submit" value="저장" />
</form>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
        <form id="updateForm" method="post" action="<%=request.getContextPath()%>/props">
      	<div class="modal-body">
		   <input type="text" name="propertyName" placeholder="property name"/>
		   <input type="text" name="propertyValue" placeholder="property value"/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button id="modBtn" type="submit" class="btn btn-primary">Save changes</button>
      </div>
	</form>
    </div>
  </div>
</div>
<script src="<%=request.getContextPath()%>/resources/js/packages/09/propertyView.js"></script>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>