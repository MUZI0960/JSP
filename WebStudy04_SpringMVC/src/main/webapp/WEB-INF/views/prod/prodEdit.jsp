<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<form:form modelAttribute="prod" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>상품코드</th>
				<td>
					<form:hidden path="prodId" required="required"  class="form-control-plaintext"/>${prod.prodId }
					<form:errors path="prodId" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td>
					<form:input path="prodName" required="required" maxlength="40" class="form-control" />
					<form:errors path="prodName" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>분류코드</th>
				<td>
					<form:select path="prodLgu" class="form-select">
						<form:option value="" label="전체" />
						<c:forEach items="${lprodList }" var="lprod">
							<form:option value="${lprod.lprodGu }" label="${lprod.lprodNm }" />
						</c:forEach>
					</form:select>
					<form:errors path="prodLgu" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>거래처코드</th>
				<td>
					<form:select path="prodBuyer" class="form-select">
						<form:option value="" label="전체" />
						<c:forEach items="${buyerList }" var="buyer">
							<form:option class="${buyer.buyerLgu }" value="${buyer.buyerId }">${buyer.buyerName }</form:option>
						</c:forEach>
					</form:select>
					<form:errors path="prodBuyer" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>구매가</th>
				<td>
					<form:input path="prodCost" required="required" maxlength="22" class="form-control" />
					<form:errors path="prodCost" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>판매가</th>
				<td>
					<form:input path="prodPrice" required="required" maxlength="22" class="form-control" />
					<form:errors path="prodPrice" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>세일가</th>
				<td>
					<form:input path="prodSale" required="required" maxlength="22" class="form-control" />
					<form:errors path="prodSale" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>상품요약</th>
				<td>
					<form:input path="prodOutline" required="required" maxlength="100" class="form-control" />
					<form:errors path="prodOutline" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>상세정보</th>
				<td>
					<form:input path="prodDetail" maxlength="4000" class="form-control" />
					<form:errors path="prodDetail" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>이미지경로</th>
				<td>
					<form:input path="prodImg" required="required" maxlength="40" class="form-control" />
					<form:errors path="prodImg" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>재고</th>
				<td>
					<form:input path="prodTotalstock" required="required" maxlength="22" class="form-control" />
					<form:errors path="prodTotalstock" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>입고일</th>
				<td>
					<form:input path="prodInsdate" type="date" maxlength="7" class="form-control" />
					<form:errors path="prodInsdate" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>적정재고</th>
				<td>
					<form:input path="prodProperstock" required="required" maxlength="22" class="form-control" />
					<form:errors path="prodProperstock" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>크기</th>
				<td>
					<form:input path="prodSize" maxlength="20" class="form-control" />
					<form:errors path="prodSize" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>색상</th>
				<td>
					<form:input path="prodColor" maxlength="20" class="form-control" />
					<form:errors path="prodColor" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>배송방법</th>
				<td>
					<form:input path="prodDelivery" maxlength="255" class="form-control" />
					<form:errors path="prodDelivery" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>단위</th>
				<td>
					<form:input path="prodUnit" maxlength="6" class="form-control" />
					<form:errors path="prodUnit" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>입고량</th>
				<td>
					<form:input path="prodQtyin" maxlength="22" class="form-control" />
					<form:errors path="prodQtyin" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>출고량</th>
				<td>
					<form:input path="prodQtysale" maxlength="22" class="form-control" />
					<form:errors path="prodQtysale" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td>
					<form:input path="prodMileage" maxlength="22" class="form-control" />
					<form:errors path="prodMileage" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="등록" class="btn btn-success" />
					<input type="reset" value="취소" class="btn btn-danger" /> 
					<a href="javascript:history.back();" class="btn btn-secondary">뒤로가기</a>
					<a href="<c:url value='/prod/prodList.do'/>" class="btn btn-secondary">목록으로</a>
				</td>
			</tr>
		</table>
	</form:form>
