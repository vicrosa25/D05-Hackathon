<%--
 * edit.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="comentario/edit.do" modelAttribute="comentario">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="informacion" />
	<form:hidden path="usuario" />
	<form:hidden path="fecha" />
	
	
	<acme:textarea code="comentario.titulo" path="titulo"/>
	<br>
	
	<acme:textarea code="comentario.descripcion" path="descripcion"/>
	<br>

	
	

	
	
	<acme:submit name="save" code="comentario.save"/>



	<a href=""><input type="button" name="goBack" value="<spring:message code="comentario.goBack"/>" /></a>

</form:form>