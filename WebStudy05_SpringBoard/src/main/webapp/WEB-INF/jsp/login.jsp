<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<form method="post">
	<security:csrfInput/>
	<input type="text" name="memId" required />
	<input type="password" name="memPass" required />
	<input type="submit" value="로그인" />
</form>

${sessionScope}