<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<style>
	.error{
		color : red;
	}
</style>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
</head>
<body>
	<h4>My page</h4>
	<form method="post" action="<c:url value='/member/memberUpdate.do' />">
		<table>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="text" name="memPass" required maxlength="15"
					class="form-control" /><span
					class="error">${errors.memPass}</span></td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><input type="text" name="memName" required maxlength="20"
					class="form-control" value="${member.memName}" /><span
					class="error">${errors.memName}</span></td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="text" name="memRegno1" maxlength="6"
					class="form-control" value="${member.memRegno1}" /><span
					class="error">${errors.memRegno1}</span></td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="text" name="memRegno2" maxlength="7"
					class="form-control" value="${member.memRegno2}" /><span
					class="error">${errors.memRegno2}</span></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="date" name="memBir" maxlength="7"
					class="form-control" value="${member.memBir}" /><span class="error">${errors.memBir}</span></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="memZip" required maxlength="7"
					class="form-control" value="${member.memZip}" /><span class="error">${errors.memZip}</span></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text" name="memAdd1" required maxlength="100"
					class="form-control" value="${member.memAdd1}" /><span
					class="error">${errors.memAdd1}</span></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" name="memAdd2" required maxlength="80"
					class="form-control" value="${member.memAdd2}" /><span
					class="error">${errors.memAdd2}</span></td>
			</tr>
			<tr>
				<th>집전번</th>
				<td><input type="text" name="memHometel" maxlength="14"
					class="form-control" value="${member.memHometel}" /><span
					class="error">${errors.memHometel}</span></td>
			</tr>
			<tr>
				<th>회사전번</th>
				<td><input type="text" name="memComtel" maxlength="14"
					class="form-control" value="${member.memComtel}" /><span
					class="error">${errors.memComtel}</span></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><input type="text" name="memHp" maxlength="15"
					class="form-control" value="${member.memHp}" /><span class="error">${errors.memHp}</span></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="memMail" required maxlength="40"
					class="form-control" value="${member.memMail}" /><span
					class="error">${errors.memMail}</span></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" name="memJob" maxlength="40"
					class="form-control" value="${member.memJob}" /><span class="error">${errors.memJob}</span></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" name="memLike" maxlength="40"
					class="form-control" value="${member.memLike}" /><span
					class="error">${errors.memLike}</span></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="text" name="memMemorial" maxlength="40"
					class="form-control" value="${member.memMemorial}" /><span
					class="error">${errors.memMemorial}</span></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><input type="date" name="memMemorialday" maxlength="7"
					class="form-control" value="${member.memMemorialday}" /><span
					class="error">${errors.memMemorialday}</span></td>
			</tr>
			
			
			<tr>
            	<td colspan="2">
	               <input type="submit" value="전송" />
	               <input type="reset" value="취소" />
	               <input id="delMem" type="button" value="탈퇴" />
	            </td>
	         </tr>
			
		</table>
	</form>
	<script type="text/javascript">
	$("#delMem").on("click", function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/member/memberDelete.do",
			data : ${member.memId },
			dataType : "json"
		}).done(function(resp, textStatus, jqXHR) {
			alert("삭제완")
		}).fail(function(jqXHR, status, error) {
			console.log(`상태코드 : \${status}, 에러메세지 : \${error}`);
		});
	})
	</script>
	<jsp:include page="/includee/postScript.jsp" />
</body>
</html>