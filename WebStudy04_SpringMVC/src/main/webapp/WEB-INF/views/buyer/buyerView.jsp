<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<table class="table table-bordered">
		<tr>
			<th>거래처코드</th>
			<td>${buyer.buyerId}</td>
		</tr>
		<tr>
			<th>거래처명</th>
			<td>${buyer.buyerName}</td>
		</tr>
		<tr>
			<th>분류</th>
			<td>${buyer.lprodNm}</td>
		</tr>
		<tr>
			<th>거래은행</th>
			<td>${buyer.buyerBank}</td>
		</tr>
		<tr>
			<th>계좌</th>
			<td>${buyer.buyerBankno}</td>
		</tr>
		<tr>
			<th>계좌주</th>
			<td>${buyer.buyerBankname}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${buyer.buyerZip}</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${buyer.buyerAdd1}</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${buyer.buyerAdd2}</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${buyer.buyerComtel}</td>
		</tr>
		<tr>
			<th>팩스</th>
			<td>${buyer.buyerFax}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${buyer.buyerMail}</td>
		</tr>
		<tr>
			<th>담당자</th>
			<td>${buyer.buyerCharger}</td>
		</tr>
		<tr>
			<th>내선번호</th>
			<td>${buyer.buyerTelext}</td>
		</tr>
		<tr>
			<th>사업자등록증</th>
			<td>
				<c:if test="${not empty buyer.base64BuyerImg }">
					<img src="data:image/*;base64,${buyer.base64BuyerImg }" />
				</c:if>
			</td>
		</tr>
		<tr>
			<th>계약서</th>
			<td>
				<c:if test="${not empty buyer.atchContract }">
					<c:url value="/buyer/contractDownload.do" var="downloadURL">
						<c:param name="what" value="${buyer.buyerId }" />
					</c:url>
					<a href="${downloadURL }">${buyer.atchContract.atchOrginName }</a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:url value="/buyer/buyerUpdate.do" var="updateURL">
					<c:param name="what" value="${buyer.buyerId }" />
				</c:url>
				<a href="${updateURL }" class="btn btn-primary">수정</a>
				<a href="javascript:history.back();" class="btn btn-secondary">뒤로가기</a>
				<a href="<c:url value='/buyer/buyerList.do'/>" class="btn btn-secondary">목록으로</a>
			</td>
		</tr>
	</table>









