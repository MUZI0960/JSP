<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<table class="table table-bordered">
		<tr>
			<th>상품코드</th>
			<td>${prod.prodId}</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td>${prod.prodName}</td>
		</tr>
		<tr>
			<th>상품분류</th>
			<td>${prod.lprodNm}</td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>거래처코드</th>
							<th>거래처명</th>
							<th>거래처소재지역</th>
							<th>담당자명</th>
							<th>연락처</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${prod.buyer.buyerId }</td>
							<td>
								<c:url value="/buyer/buyerView.do" var="buyerURL">
									<c:param name="what" value="${prod.buyer.buyerId }" />
								</c:url>
								<a href="${buyerURL }">${prod.buyer.buyerName }</a>
							</td>
							<td>${prod.buyer.buyerAdd1 }</td>
							<td>${prod.buyer.buyerCharger }</td>
							<td>${prod.buyer.buyerComtel }</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
		</tr>
		<tr>
			<th>판매가</th>
			<td>${prod.prodPrice}</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>${prod.prodSale}</td>
		</tr>
		<tr>
			<th>상품요약</th>
			<td>${prod.prodOutline}</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>${prod.prodDetail}</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td><img src="<c:url value='/resources/prodImages/${prod.prodImg}' />" /></td>
		</tr>
		<tr>
			<th>재고</th>
			<td>${prod.prodTotalstock}</td>
		</tr>
		<tr>
			<th>입고일</th>
			<td>${prod.prodInsdate}</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>${prod.prodProperstock}</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>${prod.prodSize}</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>${prod.prodColor}</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>${prod.prodDelivery}</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>${prod.prodUnit}</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>${prod.prodQtyin}</td>
		</tr>
		<tr>
			<th>출고량</th>
			<td>${prod.prodQtysale}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${prod.prodMileage}</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:url value="/prod/prodUpdate.do" var="updateURL">
					<c:param name="what" value="${prod.prodId }" />
				</c:url>
				<a href="${updateURL}" class="btn btn-primary">상품수정</a>
				<a href="${pageContext.request.contextPath }/prod/prodList.do" class="btn btn-secondary">상품목록</a>
			</td>
		</tr>
		<tr>
			<th>구매자리스트</th>
			<td>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>구매자이름</th>
							<th>주소</th>
							<th>이메일</th>
							<th>마일리지</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="memList" value="${prod.memberList }" />
						<c:if test="${empty memList }">
							<tr>
								<td colspan="4">구매자가 없습니다</td>
							</tr>
						</c:if>
						<c:if test="${not empty memList }">
							<c:forEach items="${memList }" var="mem">
							<tr>
								<c:url value="/member/memberView.do" var="memURL">
									<c:param name="who" value="${mem.memId }" />
								</c:url>
								<td><a href="${memURL }">${mem.memName }</a></td>
								<td>${mem.memAdd1 }</td>
								<td>${mem.memMail }</td>
								<td>${mem.memMileage }</td>
							</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</td>
		</tr>
	</table>













