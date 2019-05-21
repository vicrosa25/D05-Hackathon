<%--
 * edit.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${action}" modelAttribute="${modelAttribute}">

	<form:hidden path="version" />
	
	<!-- Nombre -->
    <acme:textbox code="usuario.nombre" path="nombre" />
    <br />
    
    <!-- Apellidos -->
    <acme:textbox code="usuario.apellidos" path="apellidos" />
    <br />
    
    <!-- Email -->
    <acme:textbox code="usuario.email" path="email" />
    <br />   
    
	
	<acme:submit code="master.page.save" name="save" />
	<a href=""><input type="button" name="goBack" value="<spring:message code="usuario.goBack"/>" /></a>
		
</form:form>
