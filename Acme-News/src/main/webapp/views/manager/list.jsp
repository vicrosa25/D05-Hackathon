<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="managers" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="manager.name" var="nameHeader" />
	<display:column property="nombre" title="${nameHeader}" />

	<spring:message code="manager.surname" var="surnameHeader" />
	<display:column property="apellidos" title="${surnameHeader}" />
	
	<spring:message code="manager.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" />

</display:table>

<security:authorize access="hasRole('ADMIN')">
	<a href=manager/admin/create.do><spring:message code="manager.create" /></a>
</security:authorize>