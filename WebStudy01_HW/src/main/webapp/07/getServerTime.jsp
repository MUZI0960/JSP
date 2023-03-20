<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%=LocalDateTime.now() %>

<% response.setIntHeader("Refresh", 1); %>

