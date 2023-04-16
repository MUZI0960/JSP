<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<table class="table table-bordered">
	<thead class="table-success">
		<tr>
			<th>일련번호</th>
			<th>거래처명</th>
			<th>분류</th>
			<th>소재지</th>
			<th>담당자</th>
			<th>이메일</th>
			<th>연락처</th>
			<th>거래품목수</th>
		</tr>
	</thead>
	<tbody id="listBody">
	</tbody>
	<tfoot>
		<tr>
			<td colspan="8">
				<div class="pagingArea d-flex justify-content-center">
				</div>
				
				<div id="searchUI" class="row d-flex justify-content-center">
					<div class="col-auto">	
						<select name="buyerLgu" class="form-select">
							<option value>전체</option>
							<c:forEach items="${lprodList }" var="lprod">
								<option value="${lprod.lprodGu }">${lprod.lprodNm }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-auto">	
						<input type="text" name="buyerAdd1"  class="form-control col-auto" placeholder="소재지"/>
					</div>
					<div class="col-auto">	
						<input type="text" name="buyerName"  class="form-control col-auto" placeholder="거래처명"/>
					</div>
					<div class="col-auto">	
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary"/>
						<a href="<c:url value='/buyer/buyerInsert.do'/>" class="btn btn-secondary">신규등록</a>
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
		<input type="text" name="buyerLgu" placeholder="buyerLgu"/>
		<input type="text" name="buyerAdd1" placeholder="buyerAdd1"/>
		<input type="text" name="buyerName" placeholder="buyerName"/>
	</form>
</div>
<script>
	$("[name=buyerLgu]").val("${pagination.detailCondition.buyerLgu}");
	$("[name=buyerAdd1]").val("${pagination.detailCondition.buyerAdd1}");
	$("[name=buyerName]").val("${pagination.detailCondition.buyerName}");
	
	let listBody = $("#listBody");
	let pagingArea = $(".pagingArea");
	let fn_makeTr = function(buyer){
		let viewURL = `${pageContext.request.contextPath}/buyer/buyerView.do?what=\${buyer.buyerId}`;
		let aTag = $("<a>").attr("href", viewURL).html(buyer.buyerName);
		return $("<tr>").append(
					$("<td>").html(buyer.rnum)		
					, $("<td>").html(aTag)		
					, $("<td>").html(buyer.lprodNm)		
					, $("<td>").html(buyer.buyerAdd1)		
					, $("<td>").html(buyer.buyerCharger)		
					, $("<td>").html(buyer.buyerMail)		
					, $("<td>").html(buyer.buyerComtel)		
					, $("<td>").html(buyer.prodCount)		
				);
	}
	let searchForm = $("[name=searchForm]").on("submit", function(event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType : "json"
		}).done(function(resp, textStatus, jqXHR) {
			listBody.empty();
			pagingArea.empty();
			
			let trTags = [];
			if(resp.dataList.length > 0){
				$.each(resp.dataList, function(idx, buyer){
					trTags.push( fn_makeTr(buyer) );
				});
				pagingArea.html(resp.pagingHTML);
			}else{
				trTags.push($("<tr>").html($("<td colspan='8'>").html("거래 품목 없음.")));
			}
			listBody.append(trTags);
		}).fail(function(jqXHR, status, error) {
			console.log(`상태코드 : \${status}, 에러메시지 : \${error}`);
		});
		return false;
	}).submit();
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
		searchForm.find("[name=page]").val("");
		return false;
	}
</script>
