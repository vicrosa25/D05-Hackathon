 <%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="administrator/update.do" modelAttribute="administrador">
	
	<%-- Hidden properties --%>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount" />
	
	<!-- Nombre -->
    <acme:textbox code="administrator.name" path="nombre" />
    <br />
    
    <!-- Apellidos -->
    <acme:textbox code="administrator.surname" path="apellidos" />
    <br />
    
    <!-- Email -->
    <acme:textbox code="administrator.email" path="email" />
    <br />   
    
	
	<acme:submit name="update" code="manager.update" />
	<acme:cancel url="/" code="manager.cancel"/>
		
</form:form>
