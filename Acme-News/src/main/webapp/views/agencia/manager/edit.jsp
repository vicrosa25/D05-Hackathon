<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="agencia/agenciaForm.do" modelAttribute="agencia">

	<form:hidden path="version" />
	
	<!-- Title -->
    <acme:textbox code="agencia.titulo" path="titulo" />
    <br />
    
    <!-- Address -->
    <acme:textbox code="agencia.direccion" path="direccion" />
    <br />
    
    <!-- Capacity -->
    <spring:message code="agencia.capacidadDisponible" />
    <form:input path="capacidadDisponible" code="agencia.capacidadDisponible" type="number" min="${ actualCapacity }"/>
    <br />
    
    <!-- Tax -->
    <acme:textbox code="agencia.tasa" path="tasa" />
    <br />
    
    <!-- Save -->
	<acme:submit code="agencia.save" name="save" />	
	<!-- Cancel -->
	<acme:cancel code="agencia.cancel" url="agencia/manager/list.do" />

</form:form>