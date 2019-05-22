 

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${action}" modelAttribute="${modelAttribute}">

	<form:hidden path="version" />
	
	<!-- Nombre -->
    <acme:textbox code="periodista.nombre" path="nombre" />
    <br />
    
    <!-- Apellidos -->
    <acme:textbox code="periodista.apellidos" path="apellidos" />
    <br />
    
    <!-- Apellidos -->
    <acme:textbox code="periodista.foto" path="foto" />
    <br />
    
    <!-- Email -->
    <acme:textbox code="periodista.email" path="email" />
    <br />
    
    <!-- PaypalEmail -->
    <acme:textbox code="periodista.paypalEmail" path="cartera.paypalEmail" />
    <br />    
    
	
	<acme:submit code="master.page.save" name="save" />
	<a href=""><input type="button" name="goBack" value="<spring:message code="periodista.goBack"/>" /></a>
		
</form:form>