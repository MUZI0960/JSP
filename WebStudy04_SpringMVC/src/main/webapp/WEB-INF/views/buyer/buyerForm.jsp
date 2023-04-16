<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<form:form modelAttribute="buyer" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>거래처코드</th>
				<td>
					<form:input path="buyerId" maxlength="6" readonly="readonly" class="form-control" />
					<form:errors path="buyerId" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>거래처명</th>
				<td>
					<form:input path="buyerName" maxlength="40" class="form-control" />
					<form:errors path="buyerName" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>분류</th>
				<td>
					<form:select path="buyerLgu" class="form-select">
						<form:option value="" label="전체" />
						<c:forEach items="${lprodList }" var="lprod">
							<form:option value="${lprod.lprodGu }" label="${lprod.lprodNm }" />
						</c:forEach>
					</form:select>
					<form:errors path="buyerLgu" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>거래은행</th>
				<td>
					<form:input path="buyerBank" maxlength="40" class="form-control" />
					<form:errors path="buyerBank" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>계좌</th>
				<td>
					<form:input path="buyerBankno" maxlength="40" class="form-control" />
					<form:errors path="buyerBankno" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>계좌주</th>
				<td>
					<form:input path="buyerBankname" maxlength="15" class="form-control" />
					<form:errors path="buyerBankname" element="span"
						class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td>
					<form:input path="buyerZip" maxlength="7" class="form-control" />
					<form:errors path="buyerZip" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>주소1</th>
				<td>
					<form:input path="buyerAdd1" maxlength="100" class="form-control" />
					<form:errors path="buyerAdd1" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>주소2</th>
				<td>
					<form:input path="buyerAdd2" maxlength="80" class="form-control" />
					<form:errors path="buyerAdd2" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>
					<form:input path="buyerComtel" required="required" maxlength="14" class="form-control" />
					<form:errors path="buyerComtel" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>팩스</th>
				<td>
					<form:input path="buyerFax" required="required" maxlength="20" class="form-control" />
					<form:errors path="buyerFax" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<form:input path="buyerMail" required="required" maxlength="40" class="form-control" />
					<form:errors path="buyerMail" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>담당자</th>
				<td>
					<form:input path="buyerCharger" maxlength="10" class="form-control" />
					<form:errors path="buyerCharger" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>내선번호</th>
				<td>
					<form:input path="buyerTelext" maxlength="2" class="form-control" />
					<form:errors path="buyerTelext" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>사업자등록증</th>
				<td>
					<input type="file" name="buyerImage" class="form-control" accept="image/*"/>
					<form:errors path="buyerImage"  element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>계약서</th>
				<td>
					<input type="file" name="contractFile" class="form-control" />
					<form:errors path="contractFile"  element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="등록" class="btn btn-success"/>
					<input type="reset" value="취소" class="btn btn-danger"/>
					<a href="javascript:history.back();" class="btn btn-secondary">뒤로가기</a>
					<a href="<c:url value='/buyer/buyerList.do'/>" class="btn btn-secondary">목록으로</a>
				</td>
			</tr>
		</table>
	</form:form>













