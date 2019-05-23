<%--
 * action-1.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="administrador/changeBanner.do" modelAttribute="bannerEdit">
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="imagenes">
		<spring:message code="banner.imagenes" />
	</form:label>
	<br>
	<form:textarea rows="5" cols="30" path="imagenes" />	
	<form:errors class="error" path="imagenes" />
	<br>
	<spring:message code="banner.urls.help" />
	<br><br>
		
<acme:submit name="save" code="administrador.save"/>

<a href=""><input type="button" name="goBack"
		value="<spring:message code="administrador.cancel"/>" /></a>

</form:form>