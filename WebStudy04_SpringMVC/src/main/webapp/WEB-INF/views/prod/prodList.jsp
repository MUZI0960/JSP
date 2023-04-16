<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<h4>상품 목록 조회</h4>
<table class="table table-bordered">
	<thead class="table-success">
		<tr>
			<th>일련번호</th>
			<th>상품명</th>
			<th>상품분류</th>
			<th>거래처</th>
			<th>입고일</th>
			<th>판매가</th>
			<th>마일리지</th>
			<th>구매자수</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="prodList" value="${pagination.dataList }" />
		<c:if test="${not empty prodList }">
			<c:forEach items="${prodList }" var="prod">
				<tr>
					<td>${prod.rnum }</td>
					<td>
						<c:url value="/prod/prodView.do" var="viewURL">
							<c:param name="what" value="${prod.prodId }" />
						</c:url>
						<a href="${viewURL }">${prod.prodName }</a>
					</td>
					<td>${prod.lprodNm }</td>
					<td>${prod.buyer.buyerName }</td>
					<td>${prod.prodInsdate }</td>
					<td>${prod.prodPrice }</td>
					<td>${prod.prodMileage }</td>
					<td>${prod.memCount }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty prodList }">
			<tr>
				<td colspan="7">상품 없음.</td>
			</tr>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				<div class="pagingArea d-flex justify-content-center">
					${pagination.pagingHTML }
				</div>
				
				<div id="searchUI" class="row d-flex justify-content-center">
					<div class="col-auto">
						<select name="prodLgu" class="form-select">
							<option value>전체</option>
							<c:forEach items="${lprodList }" var="lprod">
								<option value="${lprod.lprodGu }">${lprod.lprodNm }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-auto">
						<select name="prodBuyer" class="form-select">
							<option value>전체</option>
							<c:forEach items="${buyerList }" var="buyer">
								<option class="${buyer.buyerLgu }" value="${buyer.buyerId }">${buyer.buyerName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-auto">
						<input type="text" name="prodName" class="form-control"/>
					</div>
					<div class="col-auto">
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary"/>
						<a href="<c:url value='/prod/prodInsert.do'/>" class="btn btn-secondary">신규등록</a>
					</div>
				</div>
			</td>
		</tr>
	</tfoot>	
</table>
<div style="border: 1px solid green;">
	<h4>검색 조건 전송을 위한 Hidden Form</h4>
	<form name="searchForm">
		<input type="text" name="page" placeholder="page"/>
		<input type="text" name="prodLgu" placeholder="prodLgu"/>
		<input type="text" name="prodBuyer" placeholder="prodBuyer"/>
		<input type="text" name="prodName" placeholder="prodName"/>
	</form>
</div>
<script>
	$("[name=prodLgu]").on("change", function(event){
		let lgu = $(this).val();
		prodBuyerTag.find("option").not(":first").hide();
		prodBuyerTag.find("option").filter(`.\${lgu}`).show();
	}).val("${detailCondition.prodLgu}");
	let prodBuyerTag = $("[name=prodBuyer]").val("${detailCondition.prodBuyer}");
	$("[name=prodName]").val("${detailCondition.prodName}");
	let searchForm = $("[name=searchForm]");
	let searchUI = $("#searchUI").on("click", "#searchBtn" , function(){
		$(this).parents("#searchUI").find(":input[name]").each(function(idx, input){
			let iptName = input.name;
			let iptValue = $(input).val();
			searchForm.find(`[name=\${iptName}]`).val(iptValue);
		});
		searchForm.submit();
	});
	let fn_paging = function(page, event){
		searchForm.find("[name=page]").val(page);
		searchForm.submit();
		return false;
	}
</script>









