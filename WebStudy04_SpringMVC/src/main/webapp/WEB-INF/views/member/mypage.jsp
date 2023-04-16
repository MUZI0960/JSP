<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<h4>My page</h4>
	
	<form:form modelAttribute="member" method="post" action="${pageContext.request.contextPath }/member/memberUpdate.do" enctype="multipart/form-data">
		<table>
			<tr>
				<th>비밀번호</th>
				<td>
					<form:password path="memPass"  maxlength="15" class="form-control" />
					<form:errors path="memPass" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>회원명</th>
				<td>
					<form:input path="memName"  maxlength="20" class="form-control" />
					<form:errors path="memName" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>기존이미지</th>
				<td>
					<c:if test="${not empty member.memImg }">
						<img src="data:image/*;base64,${member.base64MemImg }" />
					</c:if>
				</td>
			</tr>
			<tr>
				<th>프로필</th>
				<td>
					<input type="file" name="memImage" class="form-control"/>
					<form:errors path="memImage" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td>
					<form:input path="memRegno1" maxlength="6" class="form-control" />
					<form:errors path="memRegno1" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td>
					<form:input path="memRegno2" maxlength="7" class="form-control" />
					<form:errors path="memRegno2" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>생일</th>
				<td>
					<form:input path="memBir" type="datetime-local" maxlength="11" class="form-control" />
					<form:errors path="memBir" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td>
					<form:input path="memZip"  maxlength="7" class="form-control" />
					<form:errors path="memZip" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>주소1</th>
				<td>
					<form:input path="memAdd1"  maxlength="100" class="form-control" />
					<form:errors path="memAdd1" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>주소2</th>
				<td>
					<form:input path="memAdd2"  maxlength="80" class="form-control" />
					<form:errors path="memAdd2" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>집전번</th>
				<td>
					<form:input path="memHometel" maxlength="14" class="form-control" />
					<form:errors path="memHometel" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>회사전번</th>
				<td>
					<form:input path="memComtel" maxlength="14" class="form-control" />
					<form:errors path="memComtel" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td>
					<form:input path="memHp" maxlength="15" class="form-control" />
					<form:errors path="memHp" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<form:input path="memMail" type="email"  maxlength="40" class="form-control" />
					<form:errors path="memMail" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>직업</th>
				<td>
					<form:input path="memJob" maxlength="40" class="form-control" />
					<form:errors path="memJob" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>취미</th>
				<td>
					<form:input path="memLike" maxlength="40" class="form-control" />
					<form:errors path="memLike" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>기념일</th>
				<td>
					<form:input path="memMemorial" maxlength="40" class="form-control" />
					<form:errors path="memMemorial" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td>
					<form:input path="memMemorialday" type="date" maxlength="7" class="form-control" />
					<form:errors path="memMemorialday" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td>${member.memMileage }</td>
			</tr>
			<tr>
				<th>탈퇴여부</th>
				<td>${member.memDelete }</td>
			</tr>
			<tr>
				<td colspan="2">	
					<input type="submit" value="전송" /> 	
					<input type="reset" value="취소" />
					<input type="button" value="탈퇴" id="delBtn"/>
				</td>
			</tr>
		</table>
	</form:form>
	<form id="delForm" action="<c:url value='/member/memberDelete.do'/>" method="post">
		<input type="hidden" name="password" />
	</form>
	<script type="text/javascript">
		$("#delBtn").on("click", function(event){
			Swal.fire({
				  title: '탈퇴 할텨?',
				  showDenyButton: false,
				  showCancelButton: true,
				  confirmButtonText: '탈퇴',
				}).then((result) => {
				  if (result.isConfirmed) {
					let password = prompt("비밀번호");
					if(password){
						delForm.password.value = password;
						delForm.requestSubmit();
					}
				  }
				})
		});
	</script>
