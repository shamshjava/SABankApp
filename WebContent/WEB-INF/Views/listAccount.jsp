<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SA Bank Holders Account Details</title>
<%@ page isELIgnored="false" %>
</head>
<body>
<div align="center">
		<h1><font color="green">${message}</font></h1>
</div>
	<table border="1" width="100%">
		<thead>
			<tr>
				<th><spring:message code="lbl.accountNo" /></th>
				<th><spring:message code="lbl.accountHolderName" /></th>
				<th><spring:message code="lbl.balance" /></th>
				<th><spring:message code="lbl.accountType" /></th>
				<th><spring:message code="lbl.dob" /></th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="account" items="${accounts}">
				<c:url var="updateLink" value="/edit">
					<c:param name="accountNo" value="${account.accountNo}"></c:param>
				</c:url>
				<c:url var="deleteLink" value="/delete">
					<c:param name="accountNo" value="${account.accountNo}"></c:param>
				</c:url>
				<tr>
					<td>${account.accountNo}</td>
					<td>${account.accountHolderName}</td>
					<td>${account.balance}</td>
					<td>${account.accountType}</td>
					<td>${account.dateOfBirth}</td>
					<td><a href="${updateLink}">Edit</a></td>
					<td><a href="${deleteLink}"
						onClick="if(!(confirm('Are you sure to delate'))) return false">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
		<a href="index">Home</a>&nbsp;&nbsp;&nbsp;<a href="new">Create New Account</a>
	</table>
</body>
</html>