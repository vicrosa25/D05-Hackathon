<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="manager/admin/create.do" modelAttribute="manager">

	<%-- Hidden properties from actor--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.Authorities" />

	<%-- UserAccount--%>

	<%-- username--%>
	<form:label path="userAccount.username">
		<spring:message code="manager.username" />
	</form:label>
	<form:input path="userAccount.username" />
	<form:errors class="error" path="userAccount.username" />
	<br>
	<br>

	<%-- password--%>
	<form:label path="userAccount.password">
		<spring:message code="manager.password" />
	</form:label>
	<form:password path="userAccount.password" />
	<form:errors class="error" path="userAccount.password" />
	<br>
	<br>

	<%-- Nombre --%>
	<form:label path="nombre">
		<spring:message code="manager.name" />
	</form:label>
	<form:input path="nombre" />
	<form:errors class="error" path="nombre" />
	<br>
	<br>


	<%-- Surname --%>
	<form:label path="apellidos">
		<spring:message code="manager.surname" />
	</form:label>
	<form:input path="apellidos" />
	<form:errors class="error" path="apellidos" />
	<br>
	<br>
	
	
	<%-- email --%>
	<form:label path="email">
		<spring:message code="manager.email" />
	</form:label>
	<form:input path="email" />
	<form:errors class="error" path="email" />
	<br>
	<br>

	<%-- Buttons --%>
	<security:authorize access="hasRole('ADMIN')">
		<input type="submit" name="save"
			value="<spring:message code="manager.save" />" />

		<input type="button" name="cancel"
			value="<spring:message code="manager.cancel" />"
			onClick="javascript: window.location.replace('manager/admin/list.do')" />
	</security:authorize>
	<br>
	<br>
</form:form>
