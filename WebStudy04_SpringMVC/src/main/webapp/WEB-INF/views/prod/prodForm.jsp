<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

	<form:form modelAttribute="prod" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th><spring:message code="prod.prodName" /></th>
				<td>
					<form:input path="prodName"  maxlength="40" class="form-control" />
					<form:errors path="prodName" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodLgu" /></th>
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
				<th><spring:message code="prod.prodBuyer" /></th>
				<td>
					<form:select path="prodBuyer" class="form-select">
						<form:option value="" label="전체" />
<%-- 						<form:options items="${buyerList }" itemLabel="buyerName" itemValue="buyerId" /> --%>
						<c:forEach items="${buyerList }" var="buyer">
							<form:option class="${buyer.buyerLgu }" value="${buyer.buyerId }">${buyer.buyerName }</form:option>
						</c:forEach>
					</form:select> 
					<form:errors path="prodBuyer" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodCost" /></th>
				<td>
					<form:input path="prodCost" type="number"  maxlength="22" class="form-control" />
					<form:errors path="prodCost" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodPrice" /></th>
				<td>
					<form:input path="prodPrice" type="number"  maxlength="22" class="form-control" />
					<form:errors path="prodPrice" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodSale" /></th>
				<td>
					<form:input path="prodSale" type="number"  maxlength="22" class="form-control" />
					<form:errors path="prodSale" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodOutline" /></th>
				<td>
					<form:input path="prodOutline"  maxlength="100" class="form-control" />
					<form:errors path="prodOutline" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodDetail" /></th>
				<td>
					<form:input path="prodDetail" maxlength="4000" class="form-control" />
					<form:errors path="prodDetail" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodImage" /></th>
				<td>
					<input type="file" name="prodImage" class="form-control" />
					<form:errors path="prodImage" element="span" class="text-danger" />
					<form:errors path="prodImg" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodTotalstock" /></th>
				<td>
					<form:input path="prodTotalstock"  maxlength="22" class="form-control" />
					<form:errors path="prodTotalstock" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodInsdate" /></th>
				<td>
					<form:input path="prodInsdate" type="date" maxlength="7" class="form-control" />
					<form:errors path="prodInsdate" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodProperstock" /></th>
				<td>
					<form:input path="prodProperstock"  maxlength="22" class="form-control" />
					<form:errors path="prodProperstock" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodSize" /></th>
				<td>
					<form:input path="prodSize" maxlength="20" class="form-control" />
					<form:errors path="prodSize" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodColor" /></th>
				<td>
					<form:input path="prodColor" maxlength="20" class="form-control" />
					<form:errors path="prodColor" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodDelivery" /></th>
				<td>
					<form:input path="prodDelivery" maxlength="255" class="form-control" />
					<form:errors path="prodDelivery" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodUnit" /></th>
				<td>
					<form:input path="prodUnit" maxlength="6" class="form-control" />
					<form:errors path="prodUnit" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodQtyin" /></th>
				<td>
					<form:input path="prodQtyin" maxlength="22" class="form-control" />
					<form:errors path="prodQtyin" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodQtysale" /></th>
				<td>
					<form:input path="prodQtysale" maxlength="22" class="form-control" />
					<form:errors path="prodQtysale" element="span" class="text-danger" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="prod.prodMileage" /></th>
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
	<script type="text/javascript">
		$("[name=prodLgu]").on("change", function(event) {
			let lgu = $(this).val();
			prodBuyerTag.find("option").not(":first").hide();
			prodBuyerTag.find("option").filter(`.\${lgu}`).show();
		});
		let prodBuyerTag = $("[name=prodBuyer]");
	</script>
