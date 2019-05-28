 

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${action}" modelAttribute="${modelAttribute}">

	<form:hidden path="version" />
	
	<!-- Nombre -->
    <acme:textbox code="moderador.nombre" path="nombre" />
    <br />
    
    <!-- Apellidos -->
    <acme:textbox code="moderador.apellidos" path="apellidos" />
    <br />
    
    
    <!-- Email -->
    <acme:textbox code="moderador.email" path="email" />
    <br />
    
    <!-- PaypalEmail -->
    <acme:textbox code="moderador.paypalEmail" path="cartera.paypalEmail" />
    <br />    
    
	
<acme:submit code="master.page.save" name="save" />
<a href=""><input type="button" name="goBack" value="<spring:message code="moderador.goBack"/>" /></a>
		
<acme:cancel code="actor.export" url="/moderador/generatePDF.do" />

<a href="/Acme-News/moderador/delete.do"><input type="button" name="delete" value="<spring:message code="actor.delete"/>" 
	onclick="return confirm('<spring:message code="actor.delete.confirm" />')"/></a>
</form:form>