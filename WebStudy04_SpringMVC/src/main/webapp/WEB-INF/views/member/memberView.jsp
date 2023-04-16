<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
	<table class="table table-bordered">
		<tr>
			<th>회원아이디</th>
			<td>${member['memId']}</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>${member['memPass']}</td>
		</tr>
		<tr>
			<th>회원명</th>
			<td>${member['memName']}</td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td>${member['memRegno1']}</td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td>${member['memRegno2']}</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>${member['memBir']}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${member['memZip']}</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${member['memAdd1']}</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${member['memAdd2']}</td>
		</tr>
		<tr>
			<th>집전번</th>
			<td>${member['memHometel']}</td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td>${member['memComtel']}</td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td>${member['memHp']}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${member['memMail']}</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>${member['memJob']}</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>${member['memLike']}</td>
		</tr>
		<tr>
			<th>기념일</th>
			<td>${member['memMemorial']}</td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td>${member['memMemorialday']}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${member['memMileage']}</td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td>${member['memDelete']}</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="${pageContext.request.contextPath }/member/memberList.do" class="btn btn-secondary">회원목록</a>
			</td>
		</tr>
		<tr>
			<th>구매상품 목록</th>
			<td>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>상품명</th>
							<th>분류명</th>
							<th>거래처명</th>
							<th>판매가</th>
							<th>세일가</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="prodList" value="${member.prodList }"/>
						<c:choose>
							<c:when test="${not empty prodList }">
								<c:forEach items="${prodList }" var="prod">
									<c:url value="/prod/prodView.do" var="prodURL">
										<c:param name="what" value="${prod.prodId }" />
									</c:url>
									<tr>
										<td><a href="${prodURL }">${prod.prodName }</a></td>
										<td>${prod.lprodNm }</td>
										<td>${prod.buyer.buyerName }</td>
										<td>${prod.prodPrice }</td>
										<td>${prod.prodSale }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="5">구매 기록 없음.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</td>
		</tr>
	</table>








