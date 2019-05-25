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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="administrator/create.do" modelAttribute="administrador">

	<%-- Hidden properties from actor--%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.Authorities" />

	<%-- UserAccount--%>

	<%-- username--%>
	<acme:textbox code="administrator.username" path="userAccount.username"/>
	<br>

	<%-- password--%>
	<acme:password code="administrator.password" path="userAccount.password"/>
	<br>

	<%-- Name --%>
	<acme:textbox code="administrator.name" path="nombre"/>
	<br>


	<%-- Surname --%>
	<acme:textbox code="administrator.surname" path="apellidos"/>
	<br>
	
	<%-- email --%>
	<acme:textbox code="administrator.email" path="email"/>
	<br>


	<%-- Buttons --%>
	<security:authorize access="hasRole('ADMIN')">
		<input type="submit" name="save"
			value="<spring:message code="administrator.save" />" />

		<input type="button" name="cancel"
			value="<spring:message code="administrator.cancel" />"
			onClick="javascript: window.location.replace('administrator/list.do')" />
	</security:authorize>
	<br>
	<br>
</form:form>
