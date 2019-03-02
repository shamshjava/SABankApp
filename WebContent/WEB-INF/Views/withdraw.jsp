<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="lbl.title"/></title>
<style >
	.error{
		color:red
	}
</style>
</head>
<body>
<h2>Money Withdrawal Form</h2>
<form:form action="withdrawSave" modelAttribute="account">
<table>
	<tr>
		<td><spring:message code="lbl.accountNo"/> :</td>
		<td><form:input path="accountNo" size="30" />
		<form:errors path="accountNo" cssClass="error"/>
		<b><font color="red">${message}</font></b>
		</td>
	</tr>
	<tr>
		<td><spring:message code="lbl.withdawalAmount"/> :</td>
		<td><form:input path="withdawalAmount" size="30" />
		<form:errors path="withdawalAmount" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<td><spring:message code="lbl.accountType"/> :</td>
		<td><form:select path="accountType">
		<form:option value="">Select Account Type</form:option>
		<form:option value="SAVING">Saving</form:option>
		<form:option value="CURRENT">Current</form:option>
		</form:select>
		<form:errors path="accountType" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<td><spring:message code="lbl.psCode"/> :</td>
		<td><form:input path="psCode" size="30" />
		<form:errors path="psCode" cssClass="error"/>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input type="submit" name="btnSubmit" value="Withdraw"></td>
	</tr>
</table>
<a href="index">Home</a>&nbsp;&nbsp;&nbsp;<a href="list">View Account Holder List</a>
</form:form>
</body>
</html>