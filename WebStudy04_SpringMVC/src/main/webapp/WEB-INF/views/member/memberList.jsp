<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<table class="table table-bordered">
	<thead>
		<tr>
			<th>일련번호</th>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>생일</th>
			<th>이메일</th>
			<th>휴대폰</th>
			<th>주소1</th>
			<th>기념일자</th>
			<th>마일리지</th>
			<th>구매상품수</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="memberList" value="${pagination.dataList }"/>
		<c:if test="${not empty memberList }">
			<c:forEach items="${memberList }" var="member">
				<c:url value="/member/memberView.do" var="viewURL">
					<c:param name="who" value="${member.memId }" />
				</c:url>
				<tr>
					<td>${member.rnum }</td>
					<td>${member.memId }</td>
					<td><a href="${viewURL }">${member.memName }</a></td>
					<td>${member.memBir }</td>
					<td>${member.memMail }</td>
					<td>${member.memHp }</td>
					<td>${member.memAdd1 }</td>
					<td>${member.memMemorialday }</td>
					<td>${member.memMileage }</td>
					<td>${member.prodCount }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty memberList }">
			<tr>
				<td colspan="9">회원이 없음.</td>
			</tr>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="9">
				<div class="pagingArea">
					${pagination.pagingHTML }
				</div>
				
				<div id="searchUI">	
					<select name="searchType">
						<option value>전체</option>
						<option value="name">이름</option>
						<option value="address">주소</option>
					</select>
					<input type="text" name="searchWord"  />
					<input type="button" value="검색" id="searchBtn"/>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form name="searchForm">
	<input type="text" name="page" placeholder="page"/>
	<input type="text" name="searchType" placeholder="searchType"/>
	<input type="text" name="searchWord" placeholder="searchWord"/>
</form>
<script>
	$("[name=searchType]").val("${pagination.simpleCondition.searchType}");
	$("[name=searchWord]").val("${pagination.simpleCondition.searchWord}");
	let searchForm = $("[name=searchForm]");
	let searchUI = $("#searchUI").on("click", "#searchBtn" , function(){
		$(this).siblings(":input[name]").each(function(idx, input){
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















